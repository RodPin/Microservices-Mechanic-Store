package br.edu.infnet.maintenanceservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "maintenances")
public class Maintenance {
    @Id
    private String id;
    private String title;
    private String description;
    private String photoUrl;
    private String mechanicId;
}
