package com.example.springboot_shopping.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // SecurityContextHolder 안에 SecurityContext 관리 하고 있는 Authentication이 존재함.
        String userId = "";
        if(authentication != null) {
            userId = authentication.getName();
        }
        return Optional.of(userId);
    }
}


//    JPA와 AuditorAware를 사용하면 다음과 같이 간단한 매핑을 통해 특정 필드에 지금 로그인한 사람의 정보로 등록자를 자동으로 입력 해줄 수 있다.
