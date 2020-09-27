package br.com.rdevs.ecommerce.produto.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name="TB_PRODUTO_IMAGEM")
@Data
public class TbProdutoImagem implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PRODUTO")
    @JsonIgnore
    @Id
    private TbProduto produtoImagem;

    @Id
    @Column(name = "ID_IMAGEM")
    private BigInteger idImagem;

    @Column(name = "DS_URL")
    private String dsUrl;
}
