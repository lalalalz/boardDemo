package lalalalz.demo.item.controller;

import lalalalz.demo.item.Item;
import lalalalz.demo.item.form.AddForm;
import lalalalz.demo.item.form.EditForm;
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

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/items/{pageNumber}")
    public String items(@PathVariable Integer pageNumber, Model model) {
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

    @GetMapping("/item/edit/{itemId}")
    public String editForm(@PathVariable Long itemId, Model model, HttpSession httpSession) {
        String memberId = (String) httpSession.getAttribute("memberId");

        if (!itemService.canEdit(memberId, itemId)) {
            return "redirect:/item/{itemId}";
        }

        EditForm editForm = new EditForm();
        itemService.findItem(itemId).ifPresent(item -> {
            editForm.setItemId(item.getId());
            editForm.setTitle(item.getTitle());
            editForm.setContent(item.getContent());
        });

        model.addAttribute("editForm", editForm);
        System.out.println("editForm");
        return "/item/editForm";
    }

    @PostMapping("/item/edit/{itemId}")
    public String edit(@PathVariable Long itemId,
                       @Validated @ModelAttribute EditForm editForm,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "item/edit";
        }

        itemService.updateItem(itemId, editForm);
        return "redirect:/item/" + itemId;
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
