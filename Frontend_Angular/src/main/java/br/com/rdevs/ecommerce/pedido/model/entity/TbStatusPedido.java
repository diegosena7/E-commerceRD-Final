package br.com.rdevs.ecommerce.pedido.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "TB_STATUS_PEDIDO")

public class TbStatusPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_STATUS_PEDIDO")
    private BigInteger cdStatusPedido;

    @Column(name = "DS_STATUS_PEDIDO")
    private String dsStatusPedido;
}
