package mini.springboard.service;

import mini.springboard.domain.Answer;
import mini.springboard.repository.JpaAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    private final JpaAnswerRepository repository;

    public AnswerService(JpaAnswerRepository repository) {
        this.repository = repository;
    }

    /**
     * 답변 저장, 수정
     * @param answer
     * @return
     */
    public Integer answerSave(Answer answer){
        Answer save = repository.save(answer);
        return save.getId();
    }

    /**
     * 답변 삭제
     * @param id
     */
    public void  answerDelete(Integer id){
        repository.deleteById(id);
    }

}
