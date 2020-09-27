package br.com.rdevs.ecommerce.pedido.model.entity;

import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "TB_PEDIDO")
public class TbPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private BigInteger idPedido;

    @Column(name = "DT_COMPRA")
    private Date dtCompra;

    @Column(name = "VL_FRETE")
    private BigDecimal vlFrete;

    @Column(name = "VL_TOTAL_PEDIDO")
    private BigDecimal vlTotalPedido;


    @Column(name = "QT_ITENS_PEDIDO")
    private Integer qtItensPedido;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private TbCliente cliente;

    @OneToOne
    @JoinColumn(name = "CD_STATUS_PEDIDO")
    private TbStatusPedido statusPedido;

    @OneToMany(mappedBy = "pedido", cascade= CascadeType.ALL)
    private List<TbPedidoItem> itens;

}
