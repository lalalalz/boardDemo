package lalalalz.demo.member.form;

import lalalalz.demo.member.Member;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class JoinForm {

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    public Member toEntity() {
        Member member = new Member();
        member.setId(id);
        member.setPassword(password);
        member.setName(name);
        return member;
    }
}
