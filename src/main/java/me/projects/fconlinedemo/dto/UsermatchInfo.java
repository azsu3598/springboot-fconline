package me.projects.fconlinedemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
// objectmapper.readvalue를 통하여 클래스에 역직렬화할때 해당JSON파일과 매핑이 안되는 값은 저장하지 않기위해
// @JsonIgnoreProperties(ignoreUnknown = true) 사용 
// 해당 애너테이션을 사용하지 않고 하면 부족할 시에 오류 발생
public class UsermatchInfo {
    private String matchId;
    private LocalDateTime matchDate;
    private List<MatchDetails> matchInfo;
}
