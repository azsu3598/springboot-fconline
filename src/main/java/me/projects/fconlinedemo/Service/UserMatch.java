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
    @Autowired
    HttpRequest httpRequest;
    /**
     * 유저 id값으로 유저 매치 정보 가져오기(최근 5경기)
     */
    public Usermatch[] getMatches(String ouid) {
        // url에 추가할 유저 id값
        final int matchtype = 50;
        // 경기 종류 50 : 공식 경기
        final int offset = 0;
        final int limit = 10;
        final String url = "https://open.api.nexon.com/fconline/v1/user/match?ouid=" + ouid
                + "&matchtype=" + matchtype + "&offset=" + offset + "&limit=" + limit;
        ResponseEntity<String> responseEntity = httpRequest.httpRequest(url);

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
        // 여러 매치들을 뽑아오기에 List로 지정
        for (Usermatch usermatch : usermatches) {
            // 매치 하나씩 뽑기
            final String matchId = usermatch.getMatchId();
            // 매치의 id값 추출
            final String url = "https://open.api.nexon.com/fconline/v1/match-detail?matchid=" + matchId;
            // url 지정
            ResponseEntity<String> responseEntity = httpRequest.httpRequest(url);


            try {
                UsermatchInfo matchInfo = objectMapper.readValue(responseEntity.getBody(), UsermatchInfo.class);
                // UsermatchInfo 클래스로 매핑하여 역직렬화
                matchDetailsList.add(matchInfo);
                // list에 추가
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        return matchDetailsList;
    }


}
