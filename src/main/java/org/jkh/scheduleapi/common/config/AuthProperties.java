package org.jkh.scheduleapi.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "auth")
@Component
@Getter
@Setter
public class AuthProperties {
    private List<String> whitelist;
}
