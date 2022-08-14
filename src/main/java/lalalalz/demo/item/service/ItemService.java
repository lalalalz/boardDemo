package lalalalz.demo.item.service;

import lalalalz.demo.item.Item;
import lalalalz.demo.item.form.AddForm;
import lalalalz.demo.item.form.EditForm;
import lalalalz.demo.item.form.PageInfo;
import lalalalz.demo.item.repository.ItemRepository;
import lalalalz.demo.member.Member;
import lalalalz.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public void addItem(AddForm addForm, String memberId) {
        List<Member> findMembers = memberRepository.findById(memberId);

        if (findMembers.isEmpty()) {
            throw new IllegalArgumentException("올바르지 않은 회원 아이디 입니다.");
        }

        findMembers.forEach(member -> {
            Item newItem = addForm.toEntity(member);
            itemRepository.save(newItem);
        });
    }

    @Transactional
    public Optional<Item> findItem(Long itemId) {
        Optional<Item> findItem = itemRepository.findById(itemId)
                .stream()
                .findAny();

        findItem.ifPresent(i -> i.incrementViews());
        return findItem;
    }

    public PageInfo generatedPageInfo(Integer pageNumber) {
        PageInfo pageInfo = new PageInfo();

        pageInfo.setItemList(itemRepository.findTenItems(pageNumber - 1));
        pageInfo.setTotal((int)(itemRepository.getSize() - 1) / 10 + 1);
        pageInfo.setStartAndEnd(pageNumber);

        return pageInfo;
    }

    public boolean canEdit(String memberId, Long itemId) {
        Item findItem = itemRepository.findById(itemId)
                .stream()
                .findFirst()
                .orElse(null);

        if(findItem == null) return false;
        return findItem.getMember()
                .getId()
                .equals(memberId);
    }

    @Transactional
    public void updateItem(Long itemId, EditForm editForm) {
        Item findItem = itemRepository.findById(itemId)
                .stream()
                .findFirst()
                .orElse(null);

        if (findItem == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물 입니다.");
        }

        findItem.setTitle(editForm.getTitle());
        findItem.setContent(editForm.getContent());
    }
}

