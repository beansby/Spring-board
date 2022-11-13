package mini.springboard.controller;

import mini.springboard.domain.Member;
import mini.springboard.domain.Question;
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
import java.util.List;


@Controller
public class QuestionController {

    private QuestionService questionService;
    private MemberService memberService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/")
    public String home(){
        return "question_list";
    }

    /**
     * 질문 목록, 전체 조회
     * @param model
     * @return
     */
    @GetMapping ("/question/list")
    public String questionList(Model model){
        List<Question> questionList = this.questionService.AllquetionsFind();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    /**
     * 질문별 화면
     * @param model
     * @param idx
     * @return
     */
    @GetMapping("/question/detail/{idx}")
    public String questionDetail(Model model, @PathVariable("idx") Integer idx, AnswerForm answerForm){
        Question question = this.questionService.questionFind(idx);
        model.addAttribute("question", question);
        return "question";
    }

    /**
     * 질문 작성
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping ("/question/write")
    public String questionWrite(QuestionForm questionForm){
        return "question_write";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping ("/question/write")
    public String questionWrite (@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()){
            return "question_write";
        }
        Member member = this.memberService.getMember(principal.getName());
        this.questionService.questionSave(questionForm.getTitle(),questionForm.getContent(), member);
        return "redirect:/question/list";
    }

    /**
     * 질문 수정
     * @param questionForm
     * @param idx
     * @param principal
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping ("/question/update/{idx}")
    public String questionUpdate(QuestionForm questionForm, @PathVariable("idx") Integer idx, Principal principal){
        Question question = this.questionService.questionFind(idx);
        if(!question.getUser_id().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setTitle(question.getTitle());
        questionForm.setContent(question.getContent());
        return "question_write";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping ("/question/update/{idx}")
    public String questionUpdate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal, @PathVariable("idx") Integer idx){
        if(bindingResult.hasErrors()){
            return "question_write";
        }
        Question question = this.questionService.questionFind(idx);
        if (!question.getUser_id().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.questionService.questionUpdate(question, questionForm.getTitle(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s, idx");
    }


//
//    /**
//     * 질문 삭제
//     * @param id
//     * @param model
//     * @return
//     */
//    @PostMapping ("/question/delete")
//    public String questionDelete(Integer id, Model model){
//        questionService.questionDelete(id);
//        return "redirect:/question/list";
//    }


}
