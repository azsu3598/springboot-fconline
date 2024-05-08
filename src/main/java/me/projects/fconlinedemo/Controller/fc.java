package me.projects.fconlinedemo.Controller;

import lombok.RequiredArgsConstructor;
import me.projects.fconlinedemo.Service.GetUserInfo;
import me.projects.fconlinedemo.Service.UserMatch;
import me.projects.fconlinedemo.dto.UserIdResponse;
import me.projects.fconlinedemo.dto.UserInfo;
import me.projects.fconlinedemo.dto.Usermatch;
import me.projects.fconlinedemo.dto.UsermatchInfo;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class fc {
    final GetUserInfo getUserInfo;
    final UserMatch userMatch;
    @GetMapping("/api/fc/info")
    public String info(){
        return "info";
    }

    @GetMapping("/test")
    public String test(@RequestParam String nickname, Model model) throws UnsupportedEncodingException {
        UserIdResponse userIdResponse =  getUserInfo.getUserId(nickname);
        UserInfo userInfo = getUserInfo.getUserInfo(userIdResponse.getOuid());
        Usermatch[] usermatches = userMatch.getMatches(userInfo.getOuid());
        List<UsermatchInfo> usermatchInfo = userMatch.MatchDetails(usermatches);
        model.addAttribute("userinfo", userInfo);
        model.addAttribute("matches", usermatches);
        model.addAttribute("matchinfo", usermatchInfo);
        return "user";
    }
}
