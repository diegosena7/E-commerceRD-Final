package br.com.rdevs.ecommerce.produto.model.entity;

import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "TB_TC_CUPOM")
@Data
public class TbTcCupom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUPOM")
    private BigInteger idCupom;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "ID_CLIENTE")
    private TbCliente cliente;

    @Column(name = "DT_INICIAL_CUPOM")
    private Date dtInicialCupom;

    @Column(name = "DT_FINAL_CUPOM")
    private Date dtFinalCupom;

    @OneToMany(mappedBy = "tcCupom", cascade = CascadeType.ALL)
    private List<TbTcCupomItem> itensCupom;

}
