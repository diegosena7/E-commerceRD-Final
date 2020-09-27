package br.com.rdevs.ecommerce.produto.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
public class TbTcCupomItemPK implements Serializable {
    private BigInteger idCupomItem;

    private BigInteger idCupom;

}
