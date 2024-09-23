package br.edu.infnet.mechanicservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Review {
    @Id
    private String id;
    private String mechanicId;
    private String comment;
    private Number score;
}
