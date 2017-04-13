package com.megshan.splitnot.config;

import com.plaid.client.DefaultPlaidPublicClient;
import com.plaid.client.PlaidPublicClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shantanu on 4/12/17.
 */

@Configuration
@ConfigurationProperties(prefix = "plaid.client")
@Getter
@Setter
public class PlaidClientConfig {

    private String clientId;
    private String clientSecret;

    @Bean
    public PlaidPublicClient plaidPublicClient() {

        return new DefaultPlaidPublicClient.Builder()
                .withClientId(clientId)
                .withSecret(clientSecret)
                .build();
    }
}
