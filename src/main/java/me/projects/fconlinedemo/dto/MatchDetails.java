package me.projects.fconlinedemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetails {
    private String ouid;
    private String nickname;
    private MatchDetail matchDetail;
}
