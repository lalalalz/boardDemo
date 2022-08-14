package lalalalz.demo.item.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class EditForm {

    private Long itemId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
