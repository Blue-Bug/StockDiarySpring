package com.bluebug.StockDiary.web;

import com.bluebug.StockDiary.domain.member.Member;
import com.bluebug.StockDiary.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model){
        //세션에 회원 데이터가 없으면 home 뷰
        if(loginMember == null){
            return "home";
        }

        //세션이 있으면 로그인 인증된 화면
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}
