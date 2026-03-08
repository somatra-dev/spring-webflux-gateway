package co.pesexpo.webfluxgateway.service.impl;


import co.pesexpo.webfluxgateway.domain.ApiRouteEntity;
import co.pesexpo.webfluxgateway.repository.ApiRouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class RouteLocatorDetail implements RouteLocator {

    private final RouteLocatorBuilder routeLocatorBuilder;
    private final ApiRouteRepository apiRouteRepository;

    @NonNull
    @Override
    public Flux<Route> getRoutes() {
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();

        return apiRouteRepository.findAll()
                .filter(apiRoute -> apiRoute.getUri() != null && !apiRoute.getUri().isBlank())
                .map(apiRoute -> builder.route(
                        apiRoute.getId().toString(),
                        predicateSpec -> setPredicateSpec(apiRoute, predicateSpec)))
                .onErrorContinue((ex, obj) ->
                        log.warn("Skipping invalid route [{}]: {}", obj, ex.getMessage()))
                .collectList()
                .flatMapMany(builders -> builder.build().getRoutes());
    }

    @NonNull
    @Override
    public Flux<Route> getRoutesByMetadata(@NonNull Map<String, Object> metadata) {
        return RouteLocator.super.getRoutesByMetadata(metadata);
    }

    private Buildable<Route> setPredicateSpec(ApiRouteEntity apiRoute, PredicateSpec predicateSpec) {
        String uri = apiRoute.getUri();

        if (!uri.startsWith("http://")
                && !uri.startsWith("https://")
                && !uri.startsWith("lb://")) {
            throw new IllegalArgumentException(
                    "Invalid URI [" + uri + "] — must start with http://, https://, or lb://");
        }

        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getPath());

        if (apiRoute.getMethod() != null && !apiRoute.getMethod().isBlank()) {
            booleanSpec.and().method(apiRoute.getMethod());
        }

        return booleanSpec.uri(uri);
    }
}
