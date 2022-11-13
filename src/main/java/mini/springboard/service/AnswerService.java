package mini.springboard.service;

import lombok.RequiredArgsConstructor;
import mini.springboard.domain.Answer;
import mini.springboard.domain.Member;
import mini.springboard.domain.Question;
import mini.springboard.repository.JpaAnswerRepository;
import mini.springboard.repository.JpaQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@RequiredArgsConstructor
@Service
public class AnswerService {

    @Autowired
    private final JpaAnswerRepository answerRepository;


    /**
     * 답변 저장, 작성
     * @param question
     * @param answer
     */
    public void answerSave(Question question, String answer, Member member){
        Answer a = new Answer();
        a.setAnswer(answer);
        a.setDate(LocalDateTime.now());
        a.setQuestion(question);
        a.setMember(member);
        this.answerRepository.save(a);
    }

    /**
     * 답변 조회
     * @param id
     * @return
     */
    public Answer getAnswer(Integer id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()){
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    /**
     * 답변 수정
     * @param answer
     * @param content
     */
    public void answerUpdate(Answer answer, String content){
        answer.setAnswer(content);
        answer.setDateModify(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

//    /**
//     * 답변 삭제
//     * @param id
//     */
//    public void  answerDelete(Integer id){
//        repository.deleteById(id);
//    }
//
//    /**
//     * 답변 목록
//     * @return
//     */
//    public List<Answer> findAllAnswers() {
//        List<Answer> answers = repository.findAll();
//        return answers;
//    }

}
