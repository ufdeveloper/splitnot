package com.megshan.splitnot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Shantanu on 6/28/18.
 */
@Configuration
@ConfigurationProperties(prefix = "com.megshan.splitnot.config", ignoreUnknownFields = false)
@Data
public class ApplicationProperties {

    private final DynamoDB dynamoDB = new DynamoDB();

    @Data
    public static class DynamoDB {
        private String tableNamePrefix;
        private String accessKeyId;
        private String secretAccessKey;
    }
}
