package lalalalz.demo.domain.repository;

import lalalalz.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Member save(Member member) {
        entityManager.persist(member);
        return member;
    }

    @Override
    public List<Member> findById(String id) {
        String jpql = "select m from Member m where m.id = :id";

        return entityManager.createQuery(jpql, Member.class)
                .setParameter("id", id)
                .getResultList();
    }
}
