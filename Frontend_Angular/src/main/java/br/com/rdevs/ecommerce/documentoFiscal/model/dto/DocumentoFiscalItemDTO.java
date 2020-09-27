package br.com.rdevs.ecommerce.documentoFiscal.model.dto;

import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoFiscal;
import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class DocumentoFiscalItemDTO {

    private BigInteger nrItemDocumento;

    private BigInteger cdProduto;

    private Integer qtItem;

    private String nmProduto;

    private BigDecimal vlItemUnitario;

    private BigDecimal vlTotalItem;

    private BigDecimal pcIcms;

    private BigDecimal vlIcms;
}
