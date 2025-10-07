package com.turkcell.order_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;

import java.util.Locale;

@Configuration
public class EmbeddedKafkaConfig {
    private static final String[] TOPICS = {"orders","payments"};

    @Bean(destroyMethod = "destroy")
    public EmbeddedKafkaBroker embeddedKafkaBroker(ConfigurableEnvironment env) {
        Locale.setDefault(Locale.US);
        System.setProperty("user.language", "en");
        System.setProperty("user.country", "US");

        EmbeddedKafkaBroker broker = new EmbeddedKafkaKraftBroker(1, 1, TOPICS);
        broker.afterPropertiesSet();

        String brokers = broker.getBrokersAsString();

        System.setProperty("spring.embedded.kafka.brokers", brokers);
        env.getSystemProperties().put("spring.cloud.stream.kafka.binder.brokers", brokers);

        System.out.println("Embedded Kafka Broker started " + brokers);
        return broker;
    }
}
