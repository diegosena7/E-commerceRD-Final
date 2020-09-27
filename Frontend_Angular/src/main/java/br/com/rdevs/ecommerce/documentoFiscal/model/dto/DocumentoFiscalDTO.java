package br.com.rdevs.ecommerce.documentoFiscal.model.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class DocumentoFiscalDTO {

   private String titulo = "RaiaDrogasil SA";

   private String nrCpf;

   private BigInteger idNF;

   private Long nrChaveAcesso;

   private Long nrNF;

   private Date dtEmissao;

   private BigInteger nrPedido;

   private String nrNumeroCartao;

   private String nmNomeTitular;

   private BigDecimal valorTotalNota;

   private Long QtItens;

   private List<DocumentoFiscalItemDTO> itensDocumento;





}
