package br.com.rdevs.ecommerce.produto.service.bo;

import br.com.rdevs.ecommerce.cadastro.model.dto.ClienteDTO;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import br.com.rdevs.ecommerce.estoque.model.entity.TbProdutoFilialEstoque;
import br.com.rdevs.ecommerce.estoque.repository.EstoqueRepository;
import br.com.rdevs.ecommerce.produto.model.dto.ProdutoDTO;
import br.com.rdevs.ecommerce.produto.model.dto.ProdutoDTO2;
import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProdutoBo {

    @Autowired
    EstoqueRepository estoqueRepository;

    public ProdutoDTO parseToDTO(TbProduto produtoEntity){
        ProdutoDTO produtoDTO= new ProdutoDTO();

        produtoDTO.setCdProduto(produtoEntity.getCdProduto());
        produtoDTO.setIdStatusProduto(produtoEntity.getIdStatusProduto());
        produtoDTO.setNomeFantasia(produtoEntity.getNomeFantasia());
        produtoDTO.setNomeFabricante(produtoEntity.getNomeFabricante());
        produtoDTO.setDsProduto(produtoEntity.getDsProduto());
        produtoDTO.setValorUnidade(produtoEntity.getValorUnidade());

        return produtoDTO;
    }

    public ProdutoDTO2 parseToDTO2(TbProduto produtoEntity){
        ProdutoDTO2 produtoDTO2 = new ProdutoDTO2();

        produtoDTO2.setCdProduto(produtoEntity.getCdProduto());
        produtoDTO2.setIdStatusProduto(produtoEntity.getIdStatusProduto());
        produtoDTO2.setNomeFantasia(produtoEntity.getNomeFantasia());
        produtoDTO2.setNomeFabricante(produtoEntity.getNomeFabricante());
        produtoDTO2.setDsProduto(produtoEntity.getDsProduto());
        produtoDTO2.setValorUnidade(produtoEntity.getValorUnidade());
        produtoDTO2.setValorSemDesconto(produtoEntity.getValorUnidade());
        produtoDTO2.setDsUrl(produtoEntity.getImagens().get(0).getDsUrl());

        produtoDTO2.setIdSubCategoria(produtoEntity.getSubCategoriaProduto().getIdSubCategoria());
        produtoDTO2.setDsSubCategoria(produtoEntity.getSubCategoriaProduto().getDsSubCategoria());
        produtoDTO2.setCdCategoria(produtoEntity.getCategoriaProduto().getIdCategoriaProduto());
        produtoDTO2.setDsCategoriaProduto(produtoEntity.getCategoriaProduto().getDsCategoriaProduto());
//        TbProdutoFilialEstoque produtoEstoqueEntity = estoqueRepository.findByProdutoFilialCdProdutoAndCdFilial(produtoEntity.getCdProduto(), 4L);
//        produtoDTO2.setQtEstoque(produtoEstoqueEntity.getQtEmpenho());
//        produtoDTO2.setQtEmpenho(produtoEstoqueEntity.getQtEmpenho());
//        produtoDTO2.setCdEstoque(produtoEstoqueEntity.getCdEstoque());


        return produtoDTO2;
    }
}
