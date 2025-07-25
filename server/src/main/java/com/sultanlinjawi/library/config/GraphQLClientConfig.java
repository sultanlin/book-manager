package com.sultanlinjawi.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpSyncGraphQlClient;
import org.springframework.web.client.RestClient;

@Configuration
public class GraphQLClientConfig {

    @Bean
    public HttpSyncGraphQlClient graphQlClient() {
        var restClient = RestClient.create("https://api.hardcover.app/v1/graphql");
        return HttpSyncGraphQlClient.builder(restClient)
                .headers((headers) -> headers.add("authorization", System.getenv("AUTHORIZATION")))
                .build();
    }
}
