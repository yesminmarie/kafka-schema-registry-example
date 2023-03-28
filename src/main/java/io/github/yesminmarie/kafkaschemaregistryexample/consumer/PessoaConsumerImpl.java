package io.github.yesminmarie.kafkaschemaregistryexample.consumer;

import io.github.yesminmarie.kafkaschemaregistryexample.entity.Pessoa;
import io.github.yesminmarie.kafkaschemaregistryexample.entity.PessoaDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PessoaConsumerImpl {

    @KafkaListener(topics = {"pessoa"}, groupId = "pessoa-consumer")
    public void consumer(@Payload PessoaDTO pessoaDTO){
        Pessoa pessoa = new Pessoa(pessoaDTO.getNome().toString(), pessoaDTO.getSobrenome().toString());
        log.info("Pessoa recebida");
        log.info(pessoa.toString());
    }
}
