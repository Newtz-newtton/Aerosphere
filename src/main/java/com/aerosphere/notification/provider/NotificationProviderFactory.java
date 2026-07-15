package com.aerosphere.notification.provider;

import com.aerosphere.notification.entity.ProviderType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

/**
 * Purpose:
 * Provides the appropriate notification provider
 * based on the requested provider type.
 *
 * Responsibilities:
 * - Select provider implementation.
 * - Hide provider selection logic.
 * - Support future provider extensions.
 *
 * Module:
 * Notification
 */
@Component
@RequiredArgsConstructor
public class NotificationProviderFactory {

    private final LoggingNotificationProvider loggingProvider;
    private final EmailNotificationProvider emailProvider;
    private final SmsNotificationProvider smsProvider;

    private Map<ProviderType, NotificationProvider> providers;

    @jakarta.annotation.PostConstruct
    void initialize() {

        providers = new EnumMap<>(ProviderType.class);

        providers.put(
                ProviderType.LOGGING,
                loggingProvider);

        providers.put(
                ProviderType.EMAIL,
                emailProvider);

        providers.put(
                ProviderType.SMS,
                smsProvider);
    }

    /**
     * Returns the provider for the requested type.
     */
    public NotificationProvider getProvider(
            ProviderType providerType) {

        NotificationProvider provider =
                providers.get(providerType);

        if (provider == null) {

            throw new IllegalArgumentException(
                    "Unsupported provider: " + providerType);
        }

        return provider;
    }

}