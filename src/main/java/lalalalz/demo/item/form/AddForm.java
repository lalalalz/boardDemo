package lalalalz.demo.item.form;

import lalalalz.demo.item.Item;
import lalalalz.demo.member.Member;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddForm {

    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public Item toEntity(Member member) {
        return new Item(member, title, content);
    }
}
