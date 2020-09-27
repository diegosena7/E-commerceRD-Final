package br.com.rdevs.ecommerce.cadastro.model.dto;


import lombok.Data;

import java.math.BigInteger;


@Data
public class EnderecoDTO {

    private BigInteger idEndereco;

    private String dsEndereco;

    private String nrEndereco;

    private String nrCep;

    private String dsBairro;

    private String dsCidade;

    private String sgEstado;

    private String nmCompleto;

}
