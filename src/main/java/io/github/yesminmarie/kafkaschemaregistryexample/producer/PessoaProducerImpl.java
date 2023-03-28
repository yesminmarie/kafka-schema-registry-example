package io.github.yesminmarie.kafkaschemaregistryexample.producer;

import io.github.yesminmarie.kafkaschemaregistryexample.entity.Pessoa;
import io.github.yesminmarie.kafkaschemaregistryexample.entity.PessoaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class PessoaProducerImpl {
    private final KafkaTemplate<String, PessoaDTO> pessoaTemplate;

    private String topicName = "pessoa";

    public void persist(String messageId, Pessoa payload){
        PessoaDTO dto = createDTO(payload);
        sendPessoaMessage(messageId, dto);
    }

    private PessoaDTO createDTO(Pessoa payload) {
        return PessoaDTO.newBuilder()
                .setNome(payload.getNome())
                .setSobrenome(payload.getSobrenome())
                .build();
    }

    private void sendPessoaMessage(String messageId, PessoaDTO dto) {
        Message<PessoaDTO> message = createMessageWithHeader(messageId, dto, topicName);

        ListenableFuture<SendResult<String, PessoaDTO>> future = pessoaTemplate.send(message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, PessoaDTO>>() {

            @Override
            public void onSuccess(SendResult<String, PessoaDTO> result) {
                log.info("Pessoa enviada. MessageId: {}", messageId);
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Erro no envio. MessageId: {}", messageId);
            }
        });
    }

    private Message<PessoaDTO> createMessageWithHeader(String messageId, PessoaDTO pessoaDto, String topicName) {
        return MessageBuilder.withPayload(pessoaDto)
                .setHeader("hash", pessoaDto.hashCode())
                .setHeader("version", "1.0.0")
                .setHeader("endOfLife", LocalDate.now().plusDays(1))
                .setHeader("type", "fct")
                .setHeader("cid", messageId)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .setHeader(KafkaHeaders.MESSAGE_KEY, messageId)
                .build();
    }

}
