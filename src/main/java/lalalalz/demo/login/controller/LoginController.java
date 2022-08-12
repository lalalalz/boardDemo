package lalalalz.demo.login.controller;

import lalalalz.demo.login.service.LoginService;
import lalalalz.demo.login.form.JoinForm;
import lalalalz.demo.login.form.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        if (loginService.canLogin(loginForm) == false) {
            bindingResult.reject(null, "아이디 또는 비밀번호가 올바르지 않아요 :(");
            return "login/loginForm";
        }

        return "board/main";
    }

    @GetMapping("/join")
    public String joinForm(Model model) {
        JoinForm joinForm = new JoinForm();
        model.addAttribute("joinForm", joinForm);
        return "login/joinForm";
    }

    @PostMapping("/join")
    public String join(@Validated @ModelAttribute JoinForm joinForm,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/joinForm";
        }

        if (loginService.join(joinForm) == false) {
            bindingResult.rejectValue("id", null, "누군가가 동일한 아이디를 사용하고 있어요 :(");
            return "login/joinForm";
        }

        return "redirect:/login";
    }
}
