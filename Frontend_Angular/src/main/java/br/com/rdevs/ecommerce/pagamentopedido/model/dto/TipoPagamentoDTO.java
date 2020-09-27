package br.com.rdevs.ecommerce.pagamentopedido.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoPagamentoDTO {

    private BigInteger idTipoPagamento;

    private String dsTipoPagamento;
}
