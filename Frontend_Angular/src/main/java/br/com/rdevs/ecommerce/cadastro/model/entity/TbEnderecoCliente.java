package br.com.rdevs.ecommerce.cadastro.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@IdClass(TbEnderecoClientePK.class)
@Data
@Table(name="TB_ENDERECO_CLIENTE")
@Entity
public class TbEnderecoCliente implements Serializable {

    @Id
    @Column(name = "ID_CLIENTE")
    private BigInteger idCliente;

    @Id
    @JsonIgnore
    @Column(name = "ID_ENDERECO")
    private BigInteger idEndereco;


}
