package com.example.clienteLocador.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private int cpf;
    private String nome;
    private String sobrenome;
    private int idade;

}
