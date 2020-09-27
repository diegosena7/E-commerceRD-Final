package br.com.rdevs.ecommerce.estoque.model.dto;


import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class EstoqueDTO {

    private BigInteger cdProduto;
    private BigInteger cdCategoria;
    private String dsProduto;
    private Integer qtEstoque;
    private Integer qtEmpenho;
    private BigInteger cdEstoque;





}
