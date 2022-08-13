package lalalalz.demo.item.repository;

import lalalalz.demo.item.Item;

import java.util.List;

public interface ItemRepository {

    Item save(Item post);
    List<Item> findById(Long id);
    List<Item> findAll();
    List<Item> findTenItems(Integer page);
    Long getSize();
}
