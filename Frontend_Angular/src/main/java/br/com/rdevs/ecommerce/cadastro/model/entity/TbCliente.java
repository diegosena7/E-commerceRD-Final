package br.com.rdevs.ecommerce.cadastro.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "TB_CLIENTE")
@Data
public class TbCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private BigInteger idCliente;
    @Column(name = "NM_CLIENTE")
    private String nmCliente;
    @Column(name = "NR_CPF")
    private String nrCpf;
    @Column(name = "DS_EMAIL")
    private String dsEmail;
    @Column(name = "DT_CADASTRO")
    private Date dtCadastro;
    @Column(name = "NR_RG")
    private String nrRg;
    @Column(name = "DT_NASC")
    private Date dtNasc;
    @Column(name = "DS_GENERO")
    private String dsGenero;
    @Column(name = "NR_TELEFONE1")
    private String nrTelefone1;

    @Column(name = "NR_TELEFONE2")
    private String nrTelefone2;
    @Column(name = "PW_CLIENTE")
    private String pwCliente;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA_CLIENTE")
    private TbCategoriaCliente categoriaCliente;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "TB_ENDERECO_CLIENTE",
            joinColumns = @JoinColumn(name = "ID_CLIENTE"),
            inverseJoinColumns = @JoinColumn(name = "ID_ENDERECO")
    )
    private List<TbEndereco> enderecos;

    @OneToMany(mappedBy = "clienteCartao", cascade = CascadeType.ALL)
    @Column(name = "ID_CLIENTE")
    private List<TbCartaoCredito> cartoesCredito;




}
