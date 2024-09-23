package br.edu.infnet.reviewservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mechanic {
    @Id
    private String id;
    private String name;
    private String avatarUrl;
    private String bio;
}
