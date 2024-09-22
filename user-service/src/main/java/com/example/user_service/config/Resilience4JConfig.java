package com.example.user_service.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfig {
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()

                // CircuitBreaker을 열지 결정하는 failure rate
                // default: 50, 10번 중 5번을 실패하면 연다
                .failureRateThreshold(4)

                // CircuitBreaker을 open한 상태를 유지하는 지속 시간
                // default: 60s
                .waitDurationInOpenState(Duration.ofMillis(1000))

                // CircuitBreaker가 닫힐 때 결과를 기록하는데 사용되는 유형을 구성
                // 카운트 or 시간 기반
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)

                // CircuitBreaker가 닫힐 때 결괄르 기록하는데 사용되는 슬라이딩 창 크기 구성
                // default: 100
                .slidingWindowSize(2)
                .build();

        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()

                // time limit을 정하는 api, 서플라이어가 어느정도까지 문제가 생겼을 때 오류로 간주할건지
                // default: 1s
                .timeoutDuration(Duration.ofSeconds(4))
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeLimiterConfig)
                .circuitBreakerConfig(circuitBreakerConfig)
                .build()
        );
    }
}
