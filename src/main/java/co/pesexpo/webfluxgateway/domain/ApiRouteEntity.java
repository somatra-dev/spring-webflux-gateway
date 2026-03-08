package co.pesexpo.webfluxgateway.domain;


import co.pesexpo.webfluxgateway.config.auditable_jpa.Auditable;
import jakarta.persistence.Column;
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
@Table(name = "api_routes")
public class ApiRouteEntity extends Auditable<String> {

    @Id
    private Long id;

    private String uri;
    private String path;
    private String method;
    private String description;

    @Column(name = "group_code")
    private String groupCode;
    private String status;

}
