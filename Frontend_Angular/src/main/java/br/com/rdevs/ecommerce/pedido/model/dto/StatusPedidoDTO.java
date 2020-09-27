package br.com.rdevs.ecommerce.pedido.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusPedidoDTO {
    private BigInteger cdStatusPedido;
    private String dsStatusPedido;
}
