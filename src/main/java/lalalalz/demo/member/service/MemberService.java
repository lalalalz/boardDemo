package lalalalz.demo.member.service;

import lalalalz.demo.member.Member;
import lalalalz.demo.member.repository.MemberRepository;
import lalalalz.demo.member.form.JoinForm;
import lalalalz.demo.member.form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public boolean canLogin(LoginForm loginForm, HttpSession httpSession) {
        String id = loginForm.getId();
        String password = loginForm.getPassword();

        Member findMember = memberRepository.findById(id).stream()
                .filter(member -> member.getPassword().equals(password))
                .findAny()
                .orElse(null);

        if (findMember == null) {
            return false;
        }

        httpSession.setAttribute("memberId", id);
        return true;
    }


    @Transactional
    public boolean join(JoinForm joinForm) {
        String id = joinForm.getId();
        List<Member> findMemberList = memberRepository.findById(id);

        if (findMemberList.isEmpty()) {
            Member newMember = joinForm.toEntity();
            memberRepository.save(newMember);
            return true;
        }

        return false;
    }

}
