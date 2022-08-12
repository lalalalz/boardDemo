package lalalalz.demo.domain.repository;

import lalalalz.demo.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    List<Member> findById(String id);

}
