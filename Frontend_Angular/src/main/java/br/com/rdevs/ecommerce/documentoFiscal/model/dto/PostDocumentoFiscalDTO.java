package br.com.rdevs.ecommerce.documentoFiscal.model.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class PostDocumentoFiscalDTO {

    private BigInteger idCliente;

    private BigInteger idEndereco;

    private BigInteger idFormaPagamento;

    private String nrNumeroCartao;

    private Boolean salvarCartao = false;

    private String nmNomeTitular;

    private List<PostDocumentoFiscalItemDTO> itensDTOPost;

}
