package com.airgear.config.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqProperties {

    @NotBlank(message = "exchange name must not be blank")
    private String exchange;

    @NotBlank(message = "queue name must not be blank")
    private String queue;

    @NotBlank(message = "routing key must not be blank")
    private String key;

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
