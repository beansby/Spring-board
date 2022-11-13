package mini.springboard.controller;

import lombok.Getter;
import lombok.Setter;
import mini.springboard.domain.Member;
import mini.springboard.domain.Question;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
public class AnswerForm {

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String answer;

}
