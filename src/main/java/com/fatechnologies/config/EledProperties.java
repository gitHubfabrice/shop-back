package com.fatechnologies.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(value = "aguima")
@Getter
@Setter
public class EledProperties {
    private String returnUrl;
    private String notifyUrl;
    private String homeUrl;
    private String apiKey;
    private String siteId;
    private String urlActivate;
    private String urlNotValid;
    private String url;
    private String urlInvited;
    private String urlAccountInvited;
    private String dirFile;

}
