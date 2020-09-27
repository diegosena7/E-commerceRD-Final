package br.com.rdevs.ecommerce.produto.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "TB_SUB_CATEGORIA_PRODUTO")
@Data
public class TbSubCategoriaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SUB_CATEGORIA")
    private BigInteger idSubCategoria;

    @Column(name = "DS_SUB_CATEGORIA")
    private String dsSubCategoria;


}
