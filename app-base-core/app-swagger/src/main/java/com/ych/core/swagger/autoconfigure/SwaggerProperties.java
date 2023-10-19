package com.ych.core.swagger.autoconfigure;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerProperties {

    private String title = "API文档";

    private String description = "API文档";

    private String version = "1.0";

    private String basePackage = "com.ych";

}
