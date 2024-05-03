package me.projects.fconlinedemo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.projects.fconlinedemo.dto.UserIdResponse;
import me.projects.fconlinedemo.dto.UserInfo;
import me.projects.fconlinedemo.dto.Usermatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserInfo {
    @Autowired
    ObjectMapper objectMapper;
    public UserIdResponse getUserId(String  nickname){
        final String url = "https://open.api.nexon.com/fconline/v1/id?nickname="+nickname;

        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        headers.set("x-nxopen-api-key", "test_afe1517c544acc2bdbac122b22911e54695ca7a60aee10b605f19b274cc10f515d6d4b26f831f2fb4ba3f9511cc72095");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = rt.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        try {
            UserIdResponse userIdResponse = objectMapper.readValue(responseEntity.getBody(), UserIdResponse.class);
            return userIdResponse;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public UserInfo getUserInfo(String ouid){
        final String url = "https://open.api.nexon.com/fconline/v1/user/basic?ouid="+ouid;
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        headers.set("x-nxopen-api-key", "test_afe1517c544acc2bdbac122b22911e54695ca7a60aee10b605f19b274cc10f515d6d4b26f831f2fb4ba3f9511cc72095");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = rt.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        UserInfo userInfo;
        try {
            userInfo = objectMapper.readValue(responseEntity.getBody(), UserInfo.class);
            return userInfo;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Usermatch> getMatches(String ouid){
        final int matchtype = 50;
        final int offset = 0;
        final int limit = 10;
        final String url = "https://open.api.nexon.com/fconline/v1/user/match?ouid="+ouid
                +"&matchtype="+matchtype+"&offset="+offset+"&limit="+limit;
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept","application/json");
        headers.set("x-nxopen-api-key", "test_afe1517c544acc2bdbac122b22911e54695ca7a60aee10b605f19b274cc10f515d6d4b26f831f2fb4ba3f9511cc72095");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = rt.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        List<Usermatch> usermatches;
        try {
            // Parse the JSON array into a List<String> using Jackson
            List<String> matchIds = objectMapper.readValue(responseEntity.getBody(), new TypeReference<List<String>>(){});

            // Now you have the match IDs, you can convert each to Usermatch object
            usermatches = new ArrayList<>();
            for (String matchId : matchIds) {
                // Create a new Usermatch object using the match ID
                Usermatch usermatch = new Usermatch();
                usermatch.setMatchId(matchId); // Assuming there's a setId() method in Usermatch class

                // Add the Usermatch object to the list
                usermatches.add(usermatch);
            }
            return usermatches;
        } catch (IOException e) {
            // Handle parsing exception
            throw new RuntimeException(e);
        }
    }
}