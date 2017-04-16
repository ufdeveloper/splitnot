package com.megshan.splitnot.config;

import com.plaid.client.PlaidClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shantanu on 4/12/17.
 */

@Configuration
@Getter
@Setter
public class PlaidClientConfig {

    @Value("${plaid.client.id}")
    private String clientId;

    @Value("${plaid.secret}")
    private String clientSecret;

    @Value("${plaid.public.key}")
    private String publicKey;

    @Bean
    public PlaidClient getPlaidClient() {
        return PlaidClient.newBuilder()
                .clientIdAndSecret(clientId, clientSecret)
                .publicKey(publicKey) // optional. only needed to call endpoints that require a public key
                .sandboxBaseUrl() // or equivalent, depending on which environment you're calling into
                .build();
    }
}
