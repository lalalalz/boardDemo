package lalalalz.demo;

import lalalalz.demo.item.Item;
import lalalalz.demo.item.form.AddForm;
import lalalalz.demo.item.repository.ItemRepository;
import lalalalz.demo.item.service.ItemService;
import lalalalz.demo.member.Member;
import lalalalz.demo.member.form.JoinForm;
import lalalalz.demo.member.repository.MemberRepository;
import lalalalz.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final MemberService memberService;
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final MemberRepository memberRepository;

    @GetMapping("/")
    public String home() {
//        JoinForm joinForm = new JoinForm();
//        joinForm.setId("lalalalz");
//        joinForm.setPassword("1234");
//        joinForm.setName("최진수");

//        memberService.join(joinForm);
//
//        for (int i = 0; i < 200; i++) {
//            AddForm addForm = new AddForm();
//            addForm.setTitle("test" + i);
//            addForm.setContent("test" + i);
//
//            itemService.addItem(addForm, "lalalalz");
//        }

        return "redirect:/member/login";
    }
}
