package co.pesexpo.webfluxgateway.service;

import co.pesexpo.webfluxgateway.dto.request.CreateRoute;
import co.pesexpo.webfluxgateway.dto.request.UpdateRoute;
import co.pesexpo.webfluxgateway.dto.response.RouteResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApiRouteService {

    Mono<RouteResponse> createRoute(CreateRoute createRoute);

    Mono<RouteResponse> updateRoute(UpdateRoute updateRoute);

    Mono<RouteResponse> getRouteById(Long id);

    Flux<RouteResponse> findAllRoutes();

    Mono<RouteResponse> deleteRoute(String routeId);
}
