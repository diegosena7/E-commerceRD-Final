package br.com.rdevs.ecommerce.produto.model.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class TcCupomDTO {

    private BigInteger idCupom;

    private String nmCliente;

    private Date dtFinalCupom;

    private List<TcCupomItemDTO> itensCupom;



}
