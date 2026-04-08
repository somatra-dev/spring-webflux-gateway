package co.pesexpo.webfluxgateway.domain;


import co.pesexpo.webfluxgateway.config.auditable_r2dbc.Auditable;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("api_routes")
public class ApiRouteEntity extends Auditable<String> {

    @Id
    private Long id;

    private String uri;
    private String path;
    private String method;
    private String description;
    private String groupCode;
    private String status;

    @Version
    private Long version;
}
