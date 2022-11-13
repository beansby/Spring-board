package mini.springboard.service;

import mini.springboard.domain.Member;
import mini.springboard.domain.Question;
import mini.springboard.repository.JpaAnswerRepository;
import mini.springboard.repository.JpaQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuestionService {
    @Autowired
    private final JpaQuestionRepository questionRepository;

    public QuestionService(JpaQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * 질문 등록
     * @param title
     * @param content
     */
    public void questionSave(String title, String content, Member user_id){
        Question question = new Question();
        question.setTitle(title);
        question.setContent(content);
        question.setDate(LocalDateTime.now());
        question.setUser_id(user_id);
        this.questionRepository.save(question);
    }

    /**
     * 질문 수정
     * @param question
     * @return
     */
    public void questionUpdate(Question question, String title, String content){
        question.setTitle(title);
        question.setContent(content);
        question.setDateModify(LocalDateTime.now());
        this.questionRepository.save(question);
    }
//
//    /**
//     * 질문 삭제
//     * @param idx
//     */
//    public void questionDelete(Integer idx){
//        questionRepository.deleteById(idx);
//    }

    /**
     * 글 번호로 질문 조회 (단일)
     * @param idx
     * @return
     */
    public Question questionFind(Integer idx) {
        Optional<Question> questionlist = questionRepository.findById(idx);
        if (questionlist.isPresent()) {
            return questionlist.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    /**
     * 전체 질문 조회
     * @return
     */
    public List<Question> AllquetionsFind(){
        List<Question> questions = questionRepository.findAll();
        return  questions;

    }

}
