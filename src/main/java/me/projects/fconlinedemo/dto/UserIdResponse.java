package me.projects.fconlinedemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
@Data
public class UserIdResponse {
    private String ouid;
}
