package br.com.rdevs.ecommerce.pedido.model.entity;

import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "TB_PEDIDO_ITEM")

public class TbPedidoItem implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PEDIDO")
    @JsonIgnore
    @Id
    private TbPedido pedido;

    @Id
    @Column(name = "NR_ITEM_PEDIDO")
    private BigInteger nrItemPedido;

    @Column(name = "VL_PEDIDO_ITEM")
    private BigDecimal vlPedidoItem;

    @Column(name = "QT_PRODUTO")
    private Integer qtProduto;

    @ManyToOne
    @JoinColumn(name = "CD_PRODUTO")
    private TbProduto produto;
}
