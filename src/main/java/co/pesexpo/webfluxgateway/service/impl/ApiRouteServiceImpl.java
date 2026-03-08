package co.pesexpo.webfluxgateway.service.impl;

import co.pesexpo.webfluxgateway.domain.ApiRouteEntity;
import co.pesexpo.webfluxgateway.dto.request.CreateRoute;
import co.pesexpo.webfluxgateway.dto.request.UpdateRoute;
import co.pesexpo.webfluxgateway.dto.response.RouteResponse;
import co.pesexpo.webfluxgateway.repository.ApiRouteRepository;
import co.pesexpo.webfluxgateway.service.ApiRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ApiRouteServiceImpl implements ApiRouteService {

    private final ApiRouteRepository apiRouteRepository;
    private final GatewayRouteService gatewayRouteService;

    public ApiRouteServiceImpl(ApiRouteRepository apiRouteRepository,
                               GatewayRouteService gatewayRouteService) {
        this.apiRouteRepository = apiRouteRepository;
        this.gatewayRouteService = gatewayRouteService;
    }

    @Override
    public Mono<RouteResponse> createRoute(CreateRoute createRoute) {
        ApiRouteEntity apiRoute = convertRouteApiRequestToApiRoute(createRoute);
        return apiRouteRepository.save(apiRoute)
                .doOnSuccess(newRoute -> gatewayRouteService.refreshRoutes())
                .map(this::convertApiRouteToRouteApiResponse)
                .onErrorMap(e -> {
                    log.error("Error occurred while creating route: {}", e.getMessage());
                    throw new RuntimeException("An error occurred while creating route");
                });
    }

    @Override
    public Mono<RouteResponse> updateRoute(UpdateRoute updateRoute) {
        return null;
    }

    @Override
    public Mono<RouteResponse> getRouteById(Long id) {
        return null;
    }

    @Override
    public Flux<RouteResponse> findAllRoutes() {
        return null;
    }

    @Override
    public Mono<RouteResponse> deleteRoute(String routeId) {
        return null;
    }

    public ApiRouteEntity convertRouteApiRequestToApiRoute(CreateRoute createRoute) {
        return ApiRouteEntity.builder()
                .uri(createRoute.uri())
                .path(createRoute.path())
                .method(createRoute.method())
                .description(createRoute.description())
                .groupCode(createRoute.groupCode())
                .status(createRoute.status())
                // ❌ don't set id — let DB generate it
                // ❌ don't set audit fields — let Spring populate them
                .build();
    }

    public RouteResponse convertApiRouteToRouteApiResponse(ApiRouteEntity apiRoute) {
        return RouteResponse.builder()
                .id(apiRoute.getId())
                .uri(apiRoute.getUri())
                .path(apiRoute.getPath())
                .method(apiRoute.getMethod())
                .description(apiRoute.getDescription())
                .groupCode(apiRoute.getGroupCode())
                .status(apiRoute.getStatus())
                .createdAt(apiRoute.getCreatedDate().toString())
                .createdBy(apiRoute.getCreatedBy())
                .updatedAt(apiRoute.getLastModifiedDate().toString())
                .updatedBy(apiRoute.getLastModifiedBy())
                .build();
    }
}
