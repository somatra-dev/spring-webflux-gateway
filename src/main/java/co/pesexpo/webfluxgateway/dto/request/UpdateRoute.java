package co.pesexpo.webfluxgateway.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateRoute(
        Long id,
        String uri,
        String path,
        String method,
        String description,
        @JsonProperty("group_code") String groupCode,
        String status
) {
}
