package br.com.rdevs.ecommerce.estoque.model.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class EstoqueFilialDTO {
    private BigInteger cdFilial;
    private BigInteger cdProduto;
}
