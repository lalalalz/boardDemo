package lalalalz.demo.item.repository;

import lalalalz.demo.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Item save(Item item) {
        entityManager.persist(item);
        return item;
    }

    @Override
    public List<Item> findById(Long id) {
        String jpql = "select i from Item i where i.id = :id";
        return entityManager.createQuery(jpql, Item.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Item> findAll() {
        String jpql = "select i from Item i order by i.createdTime desc";
        return entityManager.createQuery(jpql)
                .getResultList();
    }

    @Override
    public List<Item> findTenItems(Integer groupNumber) {
        String jpql = "select i from Item i order by i.createdTime desc";
        return entityManager.createQuery(jpql)
                .setFirstResult(groupNumber * 10)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public Long getSize() {
        String jpql = "select count(i) from Item i";
        return entityManager.createQuery(jpql, Long.class)
                .getSingleResult();
    }
}
