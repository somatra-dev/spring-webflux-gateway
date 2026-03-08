package co.pesexpo.webfluxgateway.config.auditable_jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditableConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }

}