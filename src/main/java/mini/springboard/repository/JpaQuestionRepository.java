package mini.springboard.repository;

import mini.springboard.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQuestionRepository extends JpaRepository<Question, Long> {
}
