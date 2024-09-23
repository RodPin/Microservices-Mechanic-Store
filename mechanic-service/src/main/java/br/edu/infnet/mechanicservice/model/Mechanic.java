package br.edu.infnet.mechanicservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "mechanics")
public class Mechanic {
    @Id
    private String id;
    private String name;
    private String avatarUrl;
    private String bio;
    private Double average;
}
