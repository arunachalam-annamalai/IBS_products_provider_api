package com.example.demo.config;
import com.example.demo.providerdata.ProviderConfig;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public List<ProviderConfig> providerConfigs() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/providers.json");

        Map<String, List<ProviderConfig>> map = mapper.readValue(is, new TypeReference<Map<String, List<ProviderConfig>>>() {});
        return map.get("providers");
    }
}
