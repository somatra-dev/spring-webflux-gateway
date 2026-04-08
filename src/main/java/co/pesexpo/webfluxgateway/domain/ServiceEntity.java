package co.pesexpo.webfluxgateway.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "services")
public class ServiceEntity {

    @Id
    private Long id;

    @Column("service_name")
    private String serviceName;

    private String description;

    private ApiRouteEntity apiRoute;
}
