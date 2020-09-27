package br.com.rdevs.ecommerce.produto.model.dto;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ProdutoImagemDTO {

    private BigInteger cdProduto;

    private BigInteger idImagem;

    private String dsUrl;
}
