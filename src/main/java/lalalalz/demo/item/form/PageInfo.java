package lalalalz.demo.item.form;

import lalalalz.demo.item.Item;
import lombok.Data;

import java.util.List;

@Data
public class PageInfo {

    private List<Item> itemList;
    private Integer groupNumber;

    private Integer total;
    private Integer start;
    private Integer end;

    public void setStartAndEnd(Integer pageNumber) {
        int x = 1;

        while (true) {
            if (x <= pageNumber && pageNumber <= x + 9) {
                start = x;

                if (x <= total && total <= x + 9) {
                    end = total;
                }
                else {
                    end = x + 9;
                }

                break;
            }
            x += 10;
        }
    }
}
