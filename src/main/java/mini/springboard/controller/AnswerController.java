package mini.springboard.controller;

import lombok.RequiredArgsConstructor;
import mini.springboard.domain.Answer;
import mini.springboard.domain.Member;
import mini.springboard.domain.Question;
import mini.springboard.service.AnswerService;
import mini.springboard.service.MemberService;
import mini.springboard.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MemberService memberService;

    /**
     * 답변 저장
     * @param model
     * @param idx
     * @param answerForm
     * @param bindingResult
     * @param principal
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/write/{idx}")
    public String saveAnswer(Model model, @PathVariable("idx") Integer idx, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal){
        Question question = this.questionService.questionFind(idx);
        Member member = this.memberService.getMember(principal.getName());

        if(bindingResult.hasErrors()){
            model.addAttribute("question", question);
            return "question";
        }
        this.answerService.answerSave(question, answerForm.getAnswer(), member);
        return String.format("redirect:/question/detail/%s",idx);

    }

    /**
     * 답변수정
     * @param answerForm
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping ("/update/{id}")
    public String answerUpdate(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal){
        Answer answer = this.answerService.getAnswer(id);
        if(!answer.getMember().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setAnswer(answer.getAnswer());
        return "answer_write";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String answerUpdate(@Valid AnswerForm answerForm, BindingResult bindingResult, @PathVariable("id") Integer id, Principal principal){
        if (bindingResult.hasErrors()){
            return "answer_write";
        }

        Answer answer = this.answerService.getAnswer(id);
        if (!answer.getMember().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.answerService.answerUpdate(answer, answerForm.getAnswer());
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getIdx());
    }
}
