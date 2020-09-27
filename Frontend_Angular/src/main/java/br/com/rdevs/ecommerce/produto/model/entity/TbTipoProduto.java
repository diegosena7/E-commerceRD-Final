package br.com.rdevs.ecommerce.produto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "TB_TIPO_PRODUTO")
@Data
public class TbTipoProduto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PRODUTO")
    @Id
    private BigInteger idTipoProduto;

    @Column(name = "DS_TIPO_PRODUTO")
    private String dsTipoProduto;

//    @OneToMany(mappedBy = "tipoProduto", cascade = CascadeType.ALL)
//    private List<TbProduto> produtos;
}
