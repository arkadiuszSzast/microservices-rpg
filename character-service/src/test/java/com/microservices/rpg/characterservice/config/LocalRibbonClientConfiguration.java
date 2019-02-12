package com.microservices.rpg.characterservice.config;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.junit.ClassRule;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;


@TestConfiguration
@ContextConfiguration(classes = {LocalRibbonClientConfiguration.class})
public class LocalRibbonClientConfiguration {

    @ClassRule
    public static WireMockClassRule wiremock = new WireMockClassRule(WireMockConfiguration.options().dynamicPort());

    @Bean
    public ServerList<Server> ribbonServerList() {
        wiremock.start();
        return new StaticServerList<>(new Server("localhost", wiremock.port()));
    }
}