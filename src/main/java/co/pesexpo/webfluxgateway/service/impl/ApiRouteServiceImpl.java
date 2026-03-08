package co.pesexpo.webfluxgateway.service.impl;

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

    public ApiRouteServiceImpl(ApiRouteRepository apiRouteRepository) {
        this.apiRouteRepository = apiRouteRepository;
    }

    @Override
    public Mono<RouteResponse> createRoute(CreateRoute createRoute) {
        return null;
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
}
