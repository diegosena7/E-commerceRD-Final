package br.com.rdevs.ecommerce.produto.model.dto;


import lombok.Data;


import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class TcCupomItemDTO {

    private BigInteger idTcCupom;

    private BigInteger idCupomItem;

    private BigDecimal pcDesconto;

    private ProdutoDTO produtoCp;

}
