package br.com.rdevs.ecommerce.produto.model.dto;

import lombok.Data;

import java.math.BigInteger;


@Data
public class SubCategoriaProdutoDTO {

    private BigInteger idSubCategoria;

    private String dsSubCategoria;
}
