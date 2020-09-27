package br.com.rdevs.ecommerce.produto.service.bo;

import br.com.rdevs.ecommerce.produto.model.dto.ProdutoDTO;
import br.com.rdevs.ecommerce.produto.model.dto.ProdutoImagemDTO;
import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import br.com.rdevs.ecommerce.produto.model.entity.TbProdutoImagem;
import org.springframework.stereotype.Component;

@Component
public class ProdutoImagemBo {


    public ProdutoImagemDTO parseToDTO(TbProdutoImagem produtoImagemEntity){
        ProdutoImagemDTO produtoImagemDTO= new ProdutoImagemDTO();

        produtoImagemDTO.setCdProduto(produtoImagemEntity.getProdutoImagem().getCdProduto());
        produtoImagemDTO.setIdImagem(produtoImagemEntity.getIdImagem());
        produtoImagemDTO.setDsUrl(produtoImagemEntity.getDsUrl());

        return produtoImagemDTO;
    }


}
