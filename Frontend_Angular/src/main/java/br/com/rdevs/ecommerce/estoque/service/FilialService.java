package br.com.rdevs.ecommerce.estoque.service;

import br.com.rdevs.ecommerce.estoque.model.dto.EstoqueFilialDTO;
import br.com.rdevs.ecommerce.estoque.model.entity.TbFilial;
import br.com.rdevs.ecommerce.estoque.repository.EstoqueRepository;
import br.com.rdevs.ecommerce.estoque.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class FilialService {
    @Autowired
    FilialRepository filialRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    public List<TbFilial> buscarPorFilial(BigInteger cdFilial){
        return  filialRepository.findByCdFilial(cdFilial);
    }

    public List<TbFilial> buscarEstoqueProdutoFilial(EstoqueFilialDTO estoqueFilialDTO){
        return filialRepository.findByCdFilialAndEstoqueProdutoFilialCdProduto(estoqueFilialDTO.getCdFilial(),estoqueFilialDTO.getCdProduto());

    }

}
