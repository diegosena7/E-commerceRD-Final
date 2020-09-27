package br.com.rdevs.ecommerce.produto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name= "TB_CATEGORIA_PRODUTO")
@Data
public class TbCategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private BigInteger idCategoriaProduto;

    @Column(name = "DS_CATEGORIA")
    private String dsCategoriaProduto;

//    @OneToOne
//    @JoinColumn(name = "ID_SUB_CATEGORIA")
//    private TbSubCategoriaProduto subCategoriaProduto;

    @OneToMany(mappedBy = "categoriaProduto", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TbProduto> produtos;
}
