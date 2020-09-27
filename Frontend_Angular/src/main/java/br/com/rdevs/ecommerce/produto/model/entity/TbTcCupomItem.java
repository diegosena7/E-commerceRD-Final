package br.com.rdevs.ecommerce.produto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name= "TB_TC_CUPOM_ITEM")
@Data
public class TbTcCupomItem implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "ID_CUPOM")
    private TbTcCupom tcCupom;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUPOM_ITEM")
    private BigInteger idCupomItem;

    @Column(name = "PC_DESCONTO")
    private BigDecimal pcDesconto;

    @ManyToOne
    @JoinColumn(name = "CD_PRODUTO")
    private TbProduto produtoCp;

}
