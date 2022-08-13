package lalalalz.demo.member.repository;

import lalalalz.demo.member.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    List<Member> findById(String id);

}
