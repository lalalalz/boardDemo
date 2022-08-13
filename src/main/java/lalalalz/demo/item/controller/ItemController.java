package lalalalz.demo.item.controller;

import lalalalz.demo.item.Item;
import lalalalz.demo.item.form.AddForm;
import lalalalz.demo.item.form.PageInfo;
import lalalalz.demo.item.repository.ItemRepository;
import lalalalz.demo.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/items/{pageNumber}")
    public String items(@PathVariable Integer pageNumber, Model model) {
//        List<Item> itemList = itemRepository.findTenItems(groupNumber);
//        model.addAttribute("itemList", itemList);

        PageInfo pageInfo = itemService.generatedPageInfo(pageNumber);
        System.out.println("pageInfo = " + pageInfo);
        model.addAttribute("pageInfo", pageInfo);
        return "item/items";
    }

    @GetMapping("/item/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = itemService.findItem(itemId)
                .orElse(null);

        if (findItem != null) {
            findItem.incrementViews();
        }

        model.addAttribute("item", findItem);
        return "item/item";
    }

    @GetMapping("/item/add")
    public String addForm(Model model) {
        AddForm addForm = new AddForm();
        model.addAttribute("addForm", addForm);
        return "item/addForm";
    }

    @PostMapping("/item/add")
    public String add(@Validated @ModelAttribute AddForm addForm,
                      BindingResult bindingResult,
                      HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "item/addForm";
        }

        String memberId = (String) httpSession.getAttribute("memberId");
        itemService.addItem(addForm, memberId);

        return "redirect:/items/1";
    }
}
