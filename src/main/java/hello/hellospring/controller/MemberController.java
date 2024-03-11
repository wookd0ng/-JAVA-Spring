package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller
public class MemberController {
    private final MemberService MemberService;
@Autowired
    public MemberController(hello.hellospring.service.MemberService memberService) {
        MemberService = memberService;

        //생성자에서 Autowired를 사용하면, 컨트롤러가 생성이 될때 스프링 빈에 등록되어 있는 멤버서비스의 객체를 가져다가 넣어줌 ->의존관계 주입
    }
}
