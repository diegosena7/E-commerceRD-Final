package br.com.rdevs.ecommerce.cadastro.model.dto;

import lombok.Data;

import java.math.BigInteger;


@Data
public class CartaoCreditoDTO {

    private BigInteger idCartaoCredito;

    private String nrNumeroCartao;

    private String nmNomeTitular;

    private BigInteger idCliente;





}
