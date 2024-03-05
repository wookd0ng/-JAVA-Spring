package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach(){
        repository.clearStore(); //테스트는 서로 의존관계없이 설계가 되어야 함 그래서 공용 데이터들을 지워주는 코드 설정(테스트 케이스)
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result= repository.fingById(member.getId()).get(); //옵셔널에서 바로 뺄려면 뒤에 .get하면 됨
//        new랑 result랑 같아야 검증이 된거
//        System.out.println("result= "+ (result==member)); 계속 일일이 확인 못함
//        Assertions.assertEquals(member,result); 이것보다 편한게 있음
        assertThat(member).isEqualTo(result);

//        이거는 멤버는 result와 같다.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.fingByName("spring1").get();
        assertThat(result).isEqualTo(member1);
        }

        @Test
    public void findAll(){
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            List<Member> result = repository.findAll();
            assertThat(result.size()).isEqualTo(2);
        }
}
