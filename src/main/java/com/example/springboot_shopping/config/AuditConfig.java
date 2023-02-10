package com.example.springboot_shopping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing//1
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {//2
        return new AuditorAwareImpl();
    }

}

/*
1.JPA의  Auditing 기능을 활성화합니다

2.등록자와 수정자를 처리해주는 AuditorAware을 빈으로 등록합니다
 */
