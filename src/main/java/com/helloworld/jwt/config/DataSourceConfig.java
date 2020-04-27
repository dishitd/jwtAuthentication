package com.helloworld.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultToken;

import java.util.Map;

@Primary
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig extends DataSourceProperties {

    @Autowired
    private VaultConfig vaultConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        final VaultToken login = this.vaultConfig.clientAuthentication().login();

        final VaultTemplate vaultTemplate = new VaultTemplate(this.vaultConfig.vaultEndpoint(),
                new TokenAuthentication(login.getToken()));
        final VaultKeyValueOperations operations = vaultTemplate.opsForKeyValue("database",
                VaultKeyValueOperationsSupport.KeyValueBackend.versioned());
        final Map<String, Object> data = operations.get("postgres/service_1").getData();

        this.setUsername((String) data.get("username"));
        this.setUrl((String) data.get("url"));
        this.setPassword((String) data.get("password"));
        super.afterPropertiesSet();
    }
}
