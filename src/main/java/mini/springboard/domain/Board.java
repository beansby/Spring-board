package mini.springboard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String name;
    private LocalDateTime date;
}

//번호 제목 글쓴이 작성일시 : 게시판 목록
//번호 제목 내용 작성일시 : 질문
//답변번호 질문 답변내용 작성일시 :