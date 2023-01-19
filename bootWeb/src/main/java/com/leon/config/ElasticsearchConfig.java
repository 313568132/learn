package com.leon.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
@Slf4j
public class ElasticsearchConfig {

    // @Value("${es.cluster_name}")
    private String clusterName = "cluster.name";

    // @Value("${es.cluster_name_value}")
    private String clusterNameValue = "ecms-es";

    // @Value("${es.host}")
    private String host = "47.88.139.229";

    // @Value("${es.port}")
    private Integer port = 9300;

    @Bean
    public TransportClient transportClient() {
        log.info("开始创建elasticsearch client");
        Settings settings = Settings.builder().put(clusterName, clusterNameValue).build();
        TransportClient client = null;
        try {
            client = TransportClient
                    .builder()
                    .settings(settings)
                    .build()
                    .addTransportAddress(
                            new InetSocketTransportAddress(
                                    InetAddress.getByName(host), port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }
}
