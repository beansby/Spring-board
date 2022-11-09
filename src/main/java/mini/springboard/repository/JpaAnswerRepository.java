package mini.springboard.repository;

import mini.springboard.domain.Answer;
import mini.springboard.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAnswerRepository extends JpaRepository<Answer, Long> {

}
