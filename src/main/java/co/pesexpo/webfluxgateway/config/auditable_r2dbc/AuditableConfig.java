package co.pesexpo.webfluxgateway.config.auditable_r2dbc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import java.util.Objects;

@Configuration
@EnableR2dbcAuditing
public class AuditableConfig {

    @Bean
    ReactiveAuditorAware<String> auditorAware() {
        return () -> ReactiveSecurityContextHolder.getContext()
                .map(ctx -> Objects.requireNonNull(ctx.getAuthentication()).getName())
                .defaultIfEmpty("system");  // fallback if no auth context
    }
}