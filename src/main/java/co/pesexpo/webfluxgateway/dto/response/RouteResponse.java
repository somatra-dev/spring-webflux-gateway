package co.pesexpo.webfluxgateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record RouteResponse(
        Long id,
        String uri,
        String path,
        String method,
        String description,
        @JsonProperty("group_code") String groupCode,
        String status
) {
}
