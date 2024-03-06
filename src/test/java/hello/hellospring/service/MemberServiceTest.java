package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
/*      MemoryMemberRepository를 먼저 만들고,MemoryMemberRepository memberRepository에 넣어줌
        그 다음 memberService에서 new MemberService(memberRepository)에 넣어줌


 */
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore(); //테스트는 서로 의존관계없이 설계가 되어야 함 그래서 공용 데이터들을 지워주는 코드 설정(테스트 케이스)
    }
    @Test
    void 회원가입() {
        //given 어떤 상황이 주어졌을때
        Member member = new Member();
        member.setName("hello");
        //when 이걸 실행했을때
        Long saveId= memberService.join(member);
        //then 이렇게 나와야 돼
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외(){
        //given 상황이 주어지면
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when 이걸 실행했을때
            memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            try {
//                memberService.join(member2);
//                fail();
//            } catch (IllegalStateException e){
//                assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//            }
        //then 이렇게 나와야 돼
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}