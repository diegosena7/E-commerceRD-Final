package br.com.rdevs.ecommerce.cadastro.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "TB_ENDERECO")
@Data
public class TbEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private BigInteger idEndereco;
    @Column(name = "DS_ENDERECO")
    private String dsEndereco;
    @Column(name = "NR_ENDERECO")
    private String nrEndereco;
    @Column(name = "NR_CEP")
    private String nrCep;
    @Column(name = "DS_BAIRRO")
    private String dsBairro;
    @Column(name = "DS_CIDADE")
    private String dsCidade;
    @Column(name = "SG_ESTADO")
    private String sgEstado;
    @Column(name = "NM_COMPLEMENTO")
    private String nmCompleto;

}
