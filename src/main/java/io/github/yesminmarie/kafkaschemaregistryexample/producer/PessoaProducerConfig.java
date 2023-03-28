package io.github.yesminmarie.kafkaschemaregistryexample.producer;

import io.github.yesminmarie.kafkaschemaregistryexample.entity.PessoaDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class PessoaProducerConfig {

    @Bean
    public KafkaTemplate<String, PessoaDTO> pessoaDTOTemplate(ProducerFactory<String, PessoaDTO> factory){
        return new KafkaTemplate<>(factory);
    }
}
