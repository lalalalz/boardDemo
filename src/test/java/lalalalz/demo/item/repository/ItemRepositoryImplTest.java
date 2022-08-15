package lalalalz.demo.item.repository;

import lalalalz.demo.item.form.EditForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryImplTest {

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    @Test
    void test1() {

        EditForm editForm = itemRepository.findById2(1L, new EditForm());

        System.out.println("editForm = " + editForm);

    }
}