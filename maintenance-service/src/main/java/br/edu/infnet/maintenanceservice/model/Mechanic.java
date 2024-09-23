package br.edu.infnet.maintenanceservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Mechanic {
    @Id
    private String id;
    private String name;
    private String avatarUrl;
    private String bio;
}
