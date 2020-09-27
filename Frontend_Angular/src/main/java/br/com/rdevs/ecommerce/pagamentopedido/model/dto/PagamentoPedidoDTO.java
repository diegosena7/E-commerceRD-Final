package br.com.rdevs.ecommerce.pagamentopedido.model.dto;

import br.com.rdevs.ecommerce.pedido.model.dto.PedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
public class PagamentoPedidoDTO {

    //    private Long idDocumentoFiscal;
    private BigInteger idPagamentoPedido;

    private TipoPagamentoDTO tipoPagamentoDTO;

    private BigInteger idTipoPagamento;

    private PedidoDTO pedidoPagamento;

    private BigInteger idPedido;

    private String nrNumeroCartao;

    private String nmNomeTitular;



}
