package br.com.rdevs.ecommerce.pagamentopedido.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Data
public class PostPagamentoPedidoDTO {

    private BigInteger idPedido;
    private BigInteger idDocumentoFiscal;
    private BigInteger idTipoPagameto;
    private String nrNumeroCartao;
    private String nmNomeTitular;

}
