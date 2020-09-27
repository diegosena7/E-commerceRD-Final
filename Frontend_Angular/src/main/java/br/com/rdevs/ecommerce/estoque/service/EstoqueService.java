package br.com.rdevs.ecommerce.estoque.service;


import br.com.rdevs.ecommerce.estoque.model.dto.EstoqueDTO;
import br.com.rdevs.ecommerce.estoque.model.dto.FilialDTO;
import br.com.rdevs.ecommerce.estoque.model.entity.TbFilial;
import br.com.rdevs.ecommerce.estoque.model.entity.TbProdutoFilialEstoque;
import br.com.rdevs.ecommerce.estoque.repository.EstoqueRepository;
import br.com.rdevs.ecommerce.estoque.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private FilialRepository repository;

    public List<FilialDTO> buscarTodas(){

        List<FilialDTO> listaDTO = new ArrayList<>();
        List<TbFilial> listaEntity =  repository.findAll();

        for(TbFilial produtoEstoque : listaEntity){
            FilialDTO filialEStoque = new FilialDTO();
            filialEStoque.setCdFilial(produtoEstoque.getCdFilial());
            filialEStoque.setNmFilial(produtoEstoque.getNmFilial());

            List<EstoqueDTO> produtos = new ArrayList<>();

            for(TbProdutoFilialEstoque prod : produtoEstoque.getEstoque()){
                EstoqueDTO estDTO = new EstoqueDTO();

                estDTO.setCdProduto(prod.getProdutoFilial().getCdProduto());
                estDTO.setCdCategoria(prod.getProdutoFilial().getCategoriaProduto().getIdCategoriaProduto());
                estDTO.setDsProduto(prod.getProdutoFilial().getNomeFantasia());
                estDTO.setQtEstoque(prod.getQtEstoque());
                estDTO.setQtEmpenho(prod.getQtEmpenho());

                produtos.add(estDTO);
            }

            filialEStoque.setEstoque(produtos);

            listaDTO.add(filialEStoque);
      }

        return listaDTO;
    }

    public List<TbProdutoFilialEstoque> buscarEstoquesPorProduto(BigInteger cdProduto){
        return estoqueRepository.findByProdutoFilialCdProduto(cdProduto);
    }

}
