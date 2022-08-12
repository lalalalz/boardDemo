package lalalalz.demo.login.service;

import lalalalz.demo.domain.Member;
import lalalalz.demo.domain.repository.MemberRepository;
import lalalalz.demo.login.form.JoinForm;
import lalalalz.demo.login.form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public boolean canLogin(LoginForm loginForm) {
        String id = loginForm.getId();
        String password = loginForm.getPassword();

        Member findMember = memberRepository.findById(id).stream()
                .filter(member -> member.getPassword().equals(password))
                .findAny().orElse(null);

        return findMember == null ? false : true;
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
