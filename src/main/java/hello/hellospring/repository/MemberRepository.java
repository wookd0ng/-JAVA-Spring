package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> fingById(Long id);
    Optional<Member> fingByName(String name);
//    findById나 findByName으로 값을 가져오는데 NULL일 경우를 대비해서 Optional로 한번 더 감싸서 가져오는걸 선호
    List<Member> findAll();


}
