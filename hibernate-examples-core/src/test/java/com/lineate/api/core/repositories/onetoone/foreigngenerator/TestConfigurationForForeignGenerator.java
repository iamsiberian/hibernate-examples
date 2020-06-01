package com.lineate.api.core.repositories.onetoone.foreigngenerator;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
@AutoConfigurationPackage
@ComponentScan(basePackages = "com.lineate.api.core.repositories.onetoone.foreigngenerator")
@EntityScan(basePackages = "com.lineate.api.core.domain.onetoone.foreigngenerator")
public class TestConfigurationForForeignGenerator {
}
