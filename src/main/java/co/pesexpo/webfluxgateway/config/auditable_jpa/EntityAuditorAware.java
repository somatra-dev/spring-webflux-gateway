package co.pesexpo.webfluxgateway.config.auditable_jpa;

import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class EntityAuditorAware implements AuditorAware<String> {


    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("itp");
    }
}
