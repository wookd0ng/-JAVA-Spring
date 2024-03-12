package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class MemoryMemberRepository implements MemberRepository{
//    save를 할때 저장할 곳
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence =0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence); //store에 넣기전에 member의 id 값을 세팅해주고 이름은 넘어온 상태(회원이 입력해서)
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> fingById(Long id) {
        return Optional.ofNullable(store.get(id)); //null 반환 할 경우를 대비해서 optional로 감싸면 클라이언트에서 뭘할수있음
    }

    @Override
    public Optional<Member> fingByName(String name) {
       return store.values().stream()
//               member.getname이 파라미터로 넘어온 name과 같은지 확인하는 구문
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
//        스토어에 있는 멤버들이 반환됨.
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    public void clearStore(){
        store.clear();
    }
}
