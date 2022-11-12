package mini.springboard.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull @Column(length = 30, unique = true)
    private String user_id;

    @Column @NotNull
    private String password;

    @Column @NotNull
    private String email;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.REMOVE)
    private List<Question> questionList;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}


