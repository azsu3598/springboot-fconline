package me.projects.fconlinedemo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import me.projects.fconlinedemo.dto.Usermatch;
import me.projects.fconlinedemo.dto.UsermatchInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMatch {
    @Autowired
    ObjectMapper objectMapper;

    /**
     * 유저 id값으로 유저 매치 정보 가져오기(최근 5경기)
     */
    public Usermatch[] getMatches(String ouid) {
        final int matchtype = 50;
        final int offset = 0;
        final int limit = 10;
        final String url = "https://open.api.nexon.com/fconline/v1/user/match?ouid=" + ouid
                + "&matchtype=" + matchtype + "&offset=" + offset + "&limit=" + limit;
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("x-nxopen-api-key", "test_afe1517c544acc2bdbac122b22911e54695ca7a60aee10b605f19b274cc10f515d6d4b26f831f2fb4ba3f9511cc72095");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = rt.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        Usermatch[] usermatches;
        try {
            usermatches = objectMapper.readValue(responseEntity.getBody(), Usermatch[].class);
            return usermatches;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 각 경기별 정보 가져오기
     */
    public List<UsermatchInfo> MatchDetails(Usermatch[] usermatches) {
        List<UsermatchInfo> matchDetailsList = new ArrayList<>();

        for (Usermatch usermatch : usermatches) {
            final String matchId = usermatch.getMatchId();
            final String url = "https://open.api.nexon.com/fconline/v1/match-detail?matchid=" + matchId;
            RestTemplate rt = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("accept", "application/json");
            headers.set("x-nxopen-api-key", "test_afe1517c544acc2bdbac122b22911e54695ca7a60aee10b605f19b274cc10f515d6d4b26f831f2fb4ba3f9511cc72095");
            HttpEntity<String> http = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = rt.exchange(
                    url,
                    HttpMethod.GET,
                    http,
                    String.class
            );

            try {
                UsermatchInfo matchInfo = objectMapper.readValue(responseEntity.getBody(), UsermatchInfo.class);
                matchDetailsList.add(matchInfo);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return matchDetailsList;
    }


}
