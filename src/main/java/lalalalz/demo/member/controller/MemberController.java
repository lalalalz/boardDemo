package lalalalz.demo.member.controller;

import lalalalz.demo.member.service.MemberService;
import lalalalz.demo.member.form.JoinForm;
import lalalalz.demo.member.form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("memberId");
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/items/1") String redirectURL,
                        HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "member/loginForm";
        }

        if (memberService.canLogin(loginForm, httpSession) == false) {
            bindingResult.reject(null, "아이디 또는 비밀번호가 올바르지 않아요 :(");
            return "member/loginForm";
        }

        return "redirect:" + redirectURL;
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm", joinForm);
        return "member/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute JoinForm joinForm,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/joinForm";
        }

        if (memberService.join(joinForm) == false) {
            bindingResult.rejectValue("id", null, "누군가가 동일한 아이디를 사용하고 있어요 :(");
            return "member/joinForm";
        }

        return "redirect:/member/login";
    }
}
