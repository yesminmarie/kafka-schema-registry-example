package io.github.yesminmarie.kafkaschemaregistryexample;

import io.github.yesminmarie.kafkaschemaregistryexample.entity.Pessoa;
import io.github.yesminmarie.kafkaschemaregistryexample.producer.PessoaProducerImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaSchemaRegistryExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSchemaRegistryExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(PessoaProducerImpl pessoaProducer){
		return args -> {
			Pessoa pessoa = new Pessoa("Yesmin", "Lahoud");
			Thread.sleep(5000);
			pessoaProducer.persist("12345", pessoa);
		};
	}

}
