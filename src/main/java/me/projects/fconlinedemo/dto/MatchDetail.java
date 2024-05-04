package me.projects.fconlinedemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchDetail {
    private int seasonId;
    private String matchResult;
    private int foul;
    private int injury;
    private int redCards;
    private int yellowCards;
}
