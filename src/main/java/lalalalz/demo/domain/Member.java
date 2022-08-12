package lalalalz.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String password;
    private String name;


}