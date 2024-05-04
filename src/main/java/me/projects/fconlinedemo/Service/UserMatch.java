package me.projects.fconlinedemo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import me.projects.fconlinedemo.dto.Usermatch;
import me.projects.fconlinedemo.dto.UsermatchInfo;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserMatch {
    @Autowired
    ObjectMapper objectMapper;

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
//        try {
//            List<String> matchIds = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<String>>(){});
//            usermatches = new ArrayList<>();
//            for (String matchId : matchIds) {
//                Usermatch usermatch = new Usermatch();
//                usermatch.setMatchId(matchId);
//                usermatches.add(usermatch);
//            }
//            return usermatches;
//        } catch (IOException e) {
//            // Handle parsing exception
//            throw new RuntimeException(e);
//        }
        try {
            usermatches = objectMapper.readValue(responseEntity.getBody(), Usermatch[].class);
            return usermatches;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void MatchDetails(String ouid) {
        final String url = "https://open.api.nexon.com/fconline/v1/match-detail?matchid=" + ouid;
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
        );  // 주소, 메소드방식,

        System.out.println("responseEntity.getBody() = " + responseEntity.getBody());
//        JSONObject jsonResponse = null;
        UsermatchInfo jsonResponse;
        try {
//            jsonResponse = new JSONObject(responseEntity.getBody());
            jsonResponse = objectMapper.readValue(responseEntity.getBody(), UsermatchInfo.class);
            System.out.println(jsonResponse);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
