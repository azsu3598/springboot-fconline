package me.projects.fconlinedemo.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HttpRequest {
    public ResponseEntity<String> httpRequest(String url){
        // 요청해야 하는 url 매개변수로 받기
        RestTemplate rt = new RestTemplate();
        // 외부 api요청 위해 RestTemplate사용
        HttpHeaders headers = new HttpHeaders();
        // header 설정을 위해 HttpHeader 클래스 생성
        headers.set("accept", "application/json");
        headers.set("x-nxopen-api-key", "test_afe1517c544acc2bdbac122b22911e54695ca7a60aee10b605f19b274cc10f515d6d4b26f831f2fb4ba3f9511cc72095");
        // header 내용 추가
        HttpEntity<String> http = new HttpEntity<>(headers);
        // HttpEntity 객체에 HttpHeader 추가
        ResponseEntity<String> responseEntity = rt.exchange(
                url,
                HttpMethod.GET,
                http,
                String.class
        );
        // exchane 메서드로 api 호출
        // 1. 주소
        // 2. 요청 방식
        // 3. 요청에 대한 엔티티, 생략시에는 null로 작성
        // 4. 응답을 받을 데이터의 형식 지정
        // 5. 응답을 ResponseEntity<String>형식으로 받음
        return responseEntity;
    }
}
