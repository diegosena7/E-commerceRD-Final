package br.com.rdevs.ecommerce.pedido.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItemDTO {

    private BigInteger idPedido;
    private BigInteger nrItemPedido;
    private BigInteger cdProduto;
    private Integer qtProduto;
    private String dsProduto;
    private BigDecimal vlPedidoItem;

}
