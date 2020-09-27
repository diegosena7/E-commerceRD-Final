package br.com.rdevs.ecommerce.estoque.model.entity;


import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="TB_PRODUTO_FILIAL_ESTOQUE")
@Data
public class TbProdutoFilialEstoque implements Serializable{


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CD_ESTOQUE")
    private BigInteger cdEstoque;

    @Column(name = "CD_FILIAL")
    private BigInteger cdFilial;


    @OneToOne
    @JoinColumn(name = "CD_PRODUTO")
    @JsonIgnore
    private TbProduto produtoFilial;

    @Column(name = "QT_ESTOQUE")
    private Integer qtEstoque;

    @Column(name = "QT_EMPENHO")
    private Integer qtEmpenho;

    



}
