package co.pesexpo.webfluxgateway.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    private Long id;

    private String serviceName;

    private String description;
}
