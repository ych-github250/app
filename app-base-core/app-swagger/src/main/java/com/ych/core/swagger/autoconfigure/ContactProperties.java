package com.ych.core.swagger.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "swagger.contact")
@Getter
@Setter
public class ContactProperties {

    private String name;

    private String url;

    private String email;

}
