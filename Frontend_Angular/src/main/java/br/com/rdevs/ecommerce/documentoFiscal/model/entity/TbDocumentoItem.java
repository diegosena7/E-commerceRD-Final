package br.com.rdevs.ecommerce.documentoFiscal.model.entity;


import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "TB_DOCUMENTO_ITEM")
public class TbDocumentoItem implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DOCUMENTO_FISCAL")
    @JsonIgnore
    private TbDocumentoFiscal documentoFiscal;

    @Id
    @Column(name = "NR_ITEM_DOCUMENTO")
    private BigInteger nrItemDocumento;

    @ManyToOne
    @JoinColumn(name = "CD_PRODUTO")
    private TbProduto produto;

    @Column(name = "QT_ITEM")
    private Integer qtItem;

    @Column(name = "VL_ITEM")
    private BigDecimal vlItem;

    @Column(name = "PC_ICMS")
    private BigDecimal pcIcms;

    @Column(name = "VL_ICMS")
    private BigDecimal vlIcms;





}
