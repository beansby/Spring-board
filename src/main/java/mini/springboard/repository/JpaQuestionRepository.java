package mini.springboard.repository;

import mini.springboard.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaQuestionRepository extends JpaRepository<Question, Integer> {
    Question findByTitle(String title);
    Question findByTitleAndContent(String title, String content);
    List<Question> findByTitleLike(String title);
}
