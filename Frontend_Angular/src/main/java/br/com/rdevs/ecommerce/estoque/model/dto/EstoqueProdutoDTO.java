package br.com.rdevs.ecommerce.estoque.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class EstoqueProdutoDTO {

    private BigInteger cdFilial;
//    private String nmFilial;
    private Integer qtEstoque;
    private Integer qtEmpenho;

}
