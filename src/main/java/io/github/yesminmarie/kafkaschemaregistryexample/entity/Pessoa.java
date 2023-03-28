package io.github.yesminmarie.kafkaschemaregistryexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    private String nome;
    private String sobrenome;
}
