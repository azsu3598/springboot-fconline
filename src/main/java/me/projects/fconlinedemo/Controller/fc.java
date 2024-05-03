package me.projects.fconlinedemo.Controller;

import lombok.RequiredArgsConstructor;
import me.projects.fconlinedemo.Service.GetUserInfo;
import me.projects.fconlinedemo.dto.UserIdResponse;
import me.projects.fconlinedemo.dto.UserInfo;
import me.projects.fconlinedemo.dto.Usermatch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class fc {
    final GetUserInfo getUserInfo;

    @GetMapping("/api/fc/info")
    public String info(){
        return "info";
    }

    @GetMapping("/test")
    public String test(@RequestParam String nickname, Model model) throws UnsupportedEncodingException {
        UserIdResponse userIdResponse =  getUserInfo.getUserId(nickname);
        UserInfo userInfo = getUserInfo.getUserInfo(userIdResponse.getOuid());
        List<Usermatch> usermatches = getUserInfo.getMatches(userIdResponse.getOuid());
        model.addAttribute("userinfo", userInfo);
        model.addAttribute("matches", usermatches);
        return "user";
    }
}
