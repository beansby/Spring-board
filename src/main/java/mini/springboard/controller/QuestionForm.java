package mini.springboard.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionForm {
    private Integer id;
    private String title;
    private String content;
    private LocalDateTime date;

}
