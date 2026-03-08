package co.pesexpo.webfluxgateway.repository;

import co.pesexpo.webfluxgateway.domain.ApiRouteEntity;
import org.jspecify.annotations.NonNull;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ApiRouteRepository extends R2dbcRepository<ApiRouteEntity, Long> {

    @NonNull
    Mono<ApiRouteEntity> findById(Long id);

    Mono<ApiRouteEntity> deleteApiRoutesById(Long id);


}
