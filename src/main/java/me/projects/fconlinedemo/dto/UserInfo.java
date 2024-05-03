package me.projects.fconlinedemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserInfo {
    private String ouid;
    private String nickname;
    private int level;
}
