package com.lineate.api.core.repositories.examples.onetomany.jointable;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@AutoConfigurationPackage
@ComponentScan(basePackages = "com.lineate.api.core.repositories.examples.onetomany.jointable")
@EntityScan(basePackages = "com.lineate.api.core.domain.examples.onetomany.jointable")
public class TestConfigurationForJoinTable {
}
