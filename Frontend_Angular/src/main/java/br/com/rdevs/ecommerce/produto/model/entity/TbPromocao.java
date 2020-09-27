package br.com.rdevs.ecommerce.produto.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name= "TB_PROMOCAO")
@Data
public class TbPromocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ID_PROMOCAO")
    private Long idPromocao;

    @Column(name= "DS_PROMOCAO")
    private String dsPromocao;

    @Column(name= "PC_PROMOCAO")
    private BigDecimal pcPromocao;

    @Column(name= "DT_VALIDADE")
    private Date dtValidadePromocao;

}
