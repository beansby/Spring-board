package mini.springboard.controller;

import mini.springboard.domain.Question;
import mini.springboard.repository.JpaQuestionRepository;
import mini.springboard.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QuestionController {

    private final JpaQuestionRepository questionRepository;
    private QuestionService questionService;
    public QuestionController(JpaQuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping ("/questionlist")
    public String questionList(Model model){
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "questionList";
    }


}
