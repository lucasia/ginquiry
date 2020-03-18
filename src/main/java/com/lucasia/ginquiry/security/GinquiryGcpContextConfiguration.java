package com.lucasia.ginquiry.security;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gcp.autoconfigure.core.GcpContextAutoConfiguration;
import org.springframework.cloud.gcp.autoconfigure.core.GcpProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(GcpProperties.class)
public class GinquiryGcpContextConfiguration extends GcpContextAutoConfiguration {

    @Value("${spring.cloud.gcp.core.enabled:false}")
    private boolean gcpCoreEnabled;

    public GinquiryGcpContextConfiguration(GcpProperties gcpProperties) {
        super(gcpProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public CredentialsProvider googleCredentials() throws Exception {
        if (gcpCoreEnabled) {
            return super.googleCredentials();
        }

        return NoCredentialsProvider.create();
    }
}
