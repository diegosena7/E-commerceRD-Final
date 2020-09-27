package br.com.rdevs.ecommerce.pedido.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private BigInteger idPedido;
    private Date dtCompra;
    private BigDecimal vlFrete;
    private BigDecimal vlTotalPedido;
    private Integer qtItensPedido;
    private BigInteger idCliente;
    private String dsStatusPedido;
    private String tipoVenda;
    private List<PedidoItemDTO> items;
}
