package lalalalz.demo.item;

import lalalalz.demo.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private Integer views;

    public Item() {}

    public Item(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.views = 1;
        this.createdTime = LocalDateTime.now();
    }

    public void incrementViews() {
        this.views++;
    }
}
