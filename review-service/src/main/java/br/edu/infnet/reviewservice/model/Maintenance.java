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
public class Maintenance {
    @Id
    private String id;
    private String title;
    private String description;
    private String photoUrl;
    private String mechanicId;
}

