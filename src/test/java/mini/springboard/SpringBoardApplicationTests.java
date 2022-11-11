package mini.springboard;

import mini.springboard.domain.Answer;
import mini.springboard.domain.Question;
import mini.springboard.repository.JpaAnswerRepository;
import mini.springboard.repository.JpaQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBoardApplicationTests {

	@Autowired
	private JpaQuestionRepository questionRepository;

	@Test
	void textJpa() {
		Question q1 = new Question();
		q1.setTitle("질문이 무엇인가요?");
		q1.setContent("질문은 이것입니다.");
		q1.setDate(LocalDateTime.now());
		this.questionRepository.save(q1); // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setTitle("질문2이 무엇인가요?");
		q2.setContent("질문2은 이것입니다.");
		q2.setDate(LocalDateTime.now());
		this.questionRepository.save(q2); // 두번째 질문 저장
	}

	@Test
	void testFindall() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(12, all.size());

		Question q = all.get(0);
		assertEquals("질문1", q.getTitle()); //findall
	}

	@Test
	void testFindbyId(){
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
			assertEquals("질문1", q.getTitle());
		}
	}

	@Test
	void testFindbyTitle(){
		Question q = this.questionRepository.findByTitle("질문1");
		assertEquals(1, q.getId());
	}

	@Test
	void testByTitleandCon(){
		Question q = this.questionRepository.findByTitleAndContent("질문1","등록되나요?");
		assertEquals(1, q.getId());
	}

	@Test
	void testByTitlelike(){
		List<Question> qList = this.questionRepository.findByTitleLike("테스트%");
		Question q = qList.get(0);
		assertEquals("테스트", q.getTitle());
	}

	@Test
	void testUpdate(){
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setTitle("수정된 제목");
		this.questionRepository.save(q);

	}

	@Test
	void testDelete(){
		assertEquals(12,this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(11, this.questionRepository.count());
	}

	@Autowired
	private JpaAnswerRepository answerRepository;
	@Test
	void testSaveA(){
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setAnswer("넵 자동으로 생성됩니다.");
		a.setQuestion(q);
		a.setDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}

	@Test
	void testFindA(){
		Optional<Answer> oa = this.answerRepository.findById(1);
		assertTrue(oa.isPresent());
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId());
	}



}
