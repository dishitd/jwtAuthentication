package com.helloworld.jwt.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.AppRoleAuthentication;
import org.springframework.vault.authentication.AppRoleAuthenticationOptions;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.support.VaultToken;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "vault.appconfig")
public class VaultConfig extends AbstractVaultConfiguration {

   private String token;

    @Override
    public ClientAuthentication clientAuthentication() {
        final VaultToken initialToken = VaultToken.of(token);
        final AppRoleAuthenticationOptions options = AppRoleAuthenticationOptions
                .builder()
                .appRole("pg_service_1")
                .roleId(AppRoleAuthenticationOptions.RoleId.pull(initialToken))
                .secretId(AppRoleAuthenticationOptions.SecretId.pull(initialToken))
                .build();

        return new AppRoleAuthentication(options, this.restOperations());
    }

    @Override
    public VaultEndpoint vaultEndpoint() {
        final VaultEndpoint vaultEndpoint = VaultEndpoint.create("localhost", 8200);
        vaultEndpoint.setScheme("http");
        return vaultEndpoint;
    }

}