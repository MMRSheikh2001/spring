package com.emranhss.SAAS.service;

import com.emranhss.SAAS.repository.TokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenCleanupService {

    private final TokenRepository tokenRepository;

    public TokenCleanupService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // Run every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteOldTokens() {
        Instant cutoff = Instant.now().minus(30, ChronoUnit.DAYS);
        tokenRepository.deleteAllByCreatedAtBefore(cutoff);
    }
}
