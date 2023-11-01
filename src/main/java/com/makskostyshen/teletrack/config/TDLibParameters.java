package com.makskostyshen.teletrack.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@Data
@ConfigurationProperties(prefix = "app.telegram.client")
@ConfigurationPropertiesScan
@AllArgsConstructor
@NoArgsConstructor
public class TDLibParameters {
    private String databaseDirectory;
    private Boolean useMessageDatabase;
    private Boolean useSecretChats;
    private Integer apiId;
    private String apiHash;
    private String systemLanguageCode;
    private String deviceModel;
    private String applicationVersion;
    private Boolean enableStorageOptimizer;
}
