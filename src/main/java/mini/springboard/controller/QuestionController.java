package mini.springboard.controller;

import mini.springboard.domain.Question;
import mini.springboard.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuestionController {

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @GetMapping("/")
    public String home(){
        return "home";
    }

    /**
     * 질문 목록
     * @param model
     * @return
     */
    @GetMapping ("/questionlist")
    public String questionList(Model model){
        List<Question> questionList = questionService.findAllQuestions();
        model.addAttribute("questionList", questionList);
        return "questionList";
    }

    /**
     * 질문별 화면
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(Model model, @PathVariable("id") Integer id){
        Question question = questionService.question(id);
        model.addAttribute("question", question);
        return "question";
    }

    /**
     * 질문 작성
     * @return
     */
    @GetMapping ("/questionwrite")
    public String questionWrite(){
        return "questionWrite";
    }

    @PostMapping ("/questionwrite")
    public String questionPost (QuestionForm form, Model model){
        Question question = new Question();
        question.setTitle(form.getTitle());
        question.setContent(form.getContent());
        questionService.questionSave(question);
        System.out.println("질문 등록 완료");
        return "redirect:/questionList";
    }

    /**
     * 질문 수정
     * @param form
     * @param model
     * @return
     */
    @PostMapping ("/questionUpdate")
    public String questionUpdate(QuestionForm form, Model model){
        Question question = questionService.question(form.getId());
        question.setTitle(form.getTitle());
        question.setContent(form.getContent());
        return "redirect:/questionList";
    }

    /**
     * 질문 삭제
     * @param id
     * @param model
     * @return
     */
    @PostMapping ("/questionDelete")
    public String questionDelete(Integer id, Model model){
        questionService.questionDelete(id);
        return "redirect:/questionList";
    }


}
