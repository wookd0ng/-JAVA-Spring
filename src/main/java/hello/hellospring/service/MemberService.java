package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;//서비스를 만들려면 레파지토리가 1순위

    /*service가 붙어있으면 스프링 컨테이너에 저장하고, 생성자(MemberService) 호출-> 근데 Autowirde 붙어있어서 멤버 서비스가 레파지토리가 필요하구나
     라고 인식해서 컨테이너에 있는 멤버 리포지토리를 넣어줌.

    */

@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        //외부에서 memberRepository를 넣어줌
    }

    //회원가입
    public Long join(Member member){
    //같은 이름이 있는 중복회원은 안됨

    //result가 null이 아니라 값이 있으면 해당 구문 수행 (옵셔널이라서 가능함)
        //추가적으로 orelseget은 값이 있으면 꺼내고 없으면 디폴트 값을 넣어서 꺼내. 라는 의미로 수행을 많이 함
        vaildateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
            return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.fingByName(member.getName())//커맨드 옵션 V
        .ifPresent(m-> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.fingById(memberId);
    }


}
/*
    public Long join(Member member){
            Optional<Member> result = memberRepository.fingByName(member.getName());//커맨드 옵션 V
result.ifPresent(m-> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                    });
            memberRepository.save(member);
            return member.getId();
    }

}
여기서 옵셔널을 바로 반환하는게 별로 안이뻐서 위 처럼 바꿈
 */