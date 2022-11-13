package mini.springboard.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String answer;

    @CreatedDate
    private LocalDateTime date;

    private LocalDateTime dateModify;

    @ManyToOne
    @JoinColumn(name="idx")
    private Question question;

    @ManyToOne
    @JoinColumn(name="user_id")
    private Member member;
}