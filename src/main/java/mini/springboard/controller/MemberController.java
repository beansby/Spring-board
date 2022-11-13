package mini.springboard.controller;

import lombok.RequiredArgsConstructor;
import mini.springboard.domain.Member;
import mini.springboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 회원 가입
     * @return
     */
    @GetMapping("/signup")
    public String signup(MemberForm memberForm) {
        return "member_signup"; //회원정보 등록 화면
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberForm memberForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "member_signup";
        }
        if (!memberForm.getPassword1().equals(memberForm.getPassword2())){
            bindingResult.rejectValue("password2","passwordIncorrect","패스워드가 일치하지 않습니다.");
            return "member_signup";
        }

        try {
            memberService.Signup(memberForm.getUsername(), memberForm.getEmail(), memberForm.getPassword1());
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed","이미 등록된 사용자입니다.");
            return "member_signup";
        } catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "member_signup";
        }

        return "redirect:/question/list";
//        memberService.Signup(memberForm.getUsername(), memberForm.getEmail(), memberForm.getPassword1());

    }

    /**
     * 로그인
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "member_login";
    }
}
