package com.qf.endOneKm.queue;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminQueueConfig {

    @Bean
    public Exchange myAdminExchange(){
        return ExchangeBuilder.topicExchange("myAdminExchange").durable(true).build();
    }


    @Bean
    public Queue putAdminQueue(){
        return new Queue("putAdminQueue",true,false,false,null);
    }

    @Bean
    public Queue delAdminQueue(){
        return new Queue("delAdminQueue",true,false,false,null);
    }


    @Bean
    public Binding BINDING_QUEUE_PUT(@Qualifier("putAdminQueue") Queue queue,
                                              @Qualifier("myAdminExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("/put").noargs();
    }

    @Bean
    public Binding BINDING_QUEUE_DEL(@Qualifier("delAdminQueue") Queue queue,
                                              @Qualifier("myAdminExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("/del").noargs();
    }

}
