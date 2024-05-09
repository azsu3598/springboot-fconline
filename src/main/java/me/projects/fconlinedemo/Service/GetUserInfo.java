package me.projects.fconlinedemo.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.projects.fconlinedemo.dto.UserIdResponse;
import me.projects.fconlinedemo.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class GetUserInfo {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    HttpRequest httpRequest;
    /**
     *
     * 유저 닉네임 받아와서 해당 유저의 id값 추출하기
     */
    public UserIdResponse getUserId(String  nickname){
        final String url = "https://open.api.nexon.com/fconline/v1/id?nickname="+nickname;
        // 요청해야 하는 주소 + 변수로 받아온 닉네임 추가
        ResponseEntity<String> responseEntity = httpRequest.httpRequest(url);
        try {
            UserIdResponse userIdResponse = objectMapper.readValue(responseEntity.getBody(), UserIdResponse.class);
            // JSON형식의 응답을 objectMapper의 readValue를 이용하여 역직렬화
            // UserIdResponse 클래스로 매핑
            return userIdResponse;
            // 값을 반환
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
            // 오류 발생시 처리
        }
    }

    /**
     *id값을 사용하여 유저 정보 가져오기
     */
    public UserInfo getUserInfo(String ouid){
        final String url = "https://open.api.nexon.com/fconline/v1/user/basic?ouid="+ouid;
        ResponseEntity<String> responseEntity = httpRequest.httpRequest(url);
        UserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(responseEntity.getBody(), UserInfo.class);
            return userInfo;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
