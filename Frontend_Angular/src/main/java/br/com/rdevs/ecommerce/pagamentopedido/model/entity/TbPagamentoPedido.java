package br.com.rdevs.ecommerce.pagamentopedido.model.entity;


import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbEndereco;
import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoFiscal;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "TB_PAGAMENTO_PEDIDO")
public class TbPagamentoPedido implements Serializable {


    @Id
    @Column(name = "ID_FORMA_PAGAMENTO")
    private BigInteger idPagamentoPedido;

    @Column(name = "NR_NUMERO_CARTAO")
    private String nrNumeroCartao;

    @Column(name = "NM_NOME_TITULAR")
    private String nmNomeTitular;

    @OneToOne
    @JoinColumn(name = "ID_DOCUMENTO_FISCAL")
    private TbDocumentoFiscal documentoFiscal;


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "ID_PEDIDO")
    private TbPedido tbPedidoEntity;

    @OneToOne
    @JoinColumn(name = "ID_TIPO_PAGAMENTO")
    private TbTipoPagamento tipoPagamento;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_CLIENTE")
    private TbCliente tbCliente;


    @ManyToOne
    @JoinColumn(name = "ID_ENDERECO")
    private TbEndereco tbEndereco;


}