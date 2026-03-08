package co.pesexpo.webfluxgateway.controller;

import co.pesexpo.webfluxgateway.dto.request.CreateRoute;
import co.pesexpo.webfluxgateway.dto.response.RouteResponse;
import co.pesexpo.webfluxgateway.exception.ApiResponse;
import co.pesexpo.webfluxgateway.service.ApiRouteService;
import co.pesexpo.webfluxgateway.service.impl.GatewayRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(path = "/api/routers",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public class ApiRouteController {

    private final ApiRouteService apiRouteService;
    private final GatewayRouteService gatewayRouteService;

    @PostMapping("/refresh-routes")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> refreshRoutes() {
        log.info("Refreshing routes");
        gatewayRouteService.refreshRoutes();
        return Mono.empty();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public Mono<ApiResponse<RouteResponse>> create(@RequestBody CreateRoute createRoute) {
        log.info("Creating route: {}", createRoute);
        return apiRouteService.createRoute(createRoute)
                .map(ApiResponse::success)
                .onErrorResume(e -> {
                    log.error("Error occurred during route creation: {}", e.getMessage());
                    return Mono.just(ApiResponse.error("500", "Failed to create route: " + e.getMessage()));
                });
    }
}