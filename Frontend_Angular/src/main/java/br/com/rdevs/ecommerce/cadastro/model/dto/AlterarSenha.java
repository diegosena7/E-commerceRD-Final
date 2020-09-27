package br.com.rdevs.ecommerce.cadastro.model.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AlterarSenha {

    private BigInteger idCliente;
    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;

}
