package br.com.rdevs.ecommerce.documentoFiscal.model.entity;

import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "TB_DOCUMENTO_FISCAL")
@Data
public class TbDocumentoFiscal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOCUMENTO_FISCAL")
    private BigInteger idDocumentoFiscal;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_CLIENTE")
    private TbCliente tbCliente;

    @Column(name = "FL_NF")
    private Long flNf;

    @Column(name = "NR_CHAVE_ACESSO")
    private Long nrChaveAcesso;

    @Column(name = "NR_NF")
    private Long nrNF;

    @Column(name = "NR_SERIE")
    private Long nrSerie;

    @Column(name = "DT_EMISSAO")
    private Date dtEmissao;

    @Column(name = "CD_FILIAL")
    private BigInteger cdFilial;

    @Column(name = "CD_OPERACAO")
    private BigInteger cdOperacao;

    @Column(name = "VL_DOCUMENTO_FISCAL")
    private BigDecimal vlDocumentoFiscal;

    @OneToMany(mappedBy = "documentoFiscal", cascade= CascadeType.ALL)
    private List<TbDocumentoItem> itensNf;


}
