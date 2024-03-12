package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService MemberService;
    private final hello.hellospring.service.MemberService memberService;

    @Autowired
    public MemberController(hello.hellospring.service.MemberService memberService) {
        MemberService = memberService;

        //생성자에서 Autowired를 사용하면, 컨트롤러가 생성이 될때 스프링 빈에 등록되어 있는 멤버서비스의 객체를 가져다가 넣어줌 ->의존관계 주입
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
    return "/members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member="+member.getName());
        memberService.join(member);

        return "redirect:/";
//        회원가입 끝나서 홈화면으로 보내버림
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        //멤버 리스트 자체를 모델에 담아서 화면에 넘길예정
        return "members/memberList";

    }

}
