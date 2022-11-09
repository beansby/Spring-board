package mini.springboard.service;

import mini.springboard.domain.Question;
import mini.springboard.repository.JpaAnswerRepository;
import mini.springboard.repository.JpaQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class QuestionService {
    @Autowired
    private final JpaQuestionRepository repository;

    public QuestionService(JpaQuestionRepository repository) {
        this.repository = repository;
    }

    /**
     * 질문 등록
     * @param question
     * @return
     */
    public Integer questionSave(Question question){
        return repository.save(question).getId();
    }

    /**
     * 질문 수정
     * @param question
     * @return
     */
    public Integer questionUpdate(Question question){
        return repository.save(question).getId();
    }

    /**
     * 질문 삭제
     * @param id
     */
    public void questionDelete(Integer id){
        repository.deleteById(id);
    }

    /**
     * 한건의 질문정보 조회
     * @param id
     * @return
     */
    public Question question(Integer id){
        Optional<Question> questionlist = repository.findById(id);
        return questionlist.get();
    }

    /**
     * 전체 질문 조회
     * @return
     */
    public List<Question> findAllQuestions(){
        List<Question> questions = repository.findAll();
        return questions;
    }

}
