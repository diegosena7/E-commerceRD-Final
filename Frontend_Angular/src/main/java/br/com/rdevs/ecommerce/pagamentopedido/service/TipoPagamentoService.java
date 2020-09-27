package br.com.rdevs.ecommerce.pagamentopedido.service;

import br.com.rdevs.ecommerce.pagamentopedido.model.dto.TipoPagamentoDTO;
import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbTipoPagamento;
import br.com.rdevs.ecommerce.pagamentopedido.repository.TipoPagamentoRepository;
import br.com.rdevs.ecommerce.pagamentopedido.service.bo.TipoPagamentoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class TipoPagamentoService {

    @Autowired
    private TipoPagamentoRepository repository;

    @Autowired
    private TipoPagamentoBO tipoPagamentoBO;

    public List<TipoPagamentoDTO> buscarTodas(){
        List<TbTipoPagamento> listEntity = repository.findAll();
        List<TipoPagamentoDTO> listDTO = new ArrayList<>();

        for(TbTipoPagamento entity : listEntity){
            TipoPagamentoDTO dto = tipoPagamentoBO.parseToDTO(entity);
            listDTO.add(dto);
        }
        return listDTO;
    }

    public TipoPagamentoDTO buscarporId(BigInteger idPedido) {
        return tipoPagamentoBO.parseToDTO(repository.getOne(idPedido));
    }

//    public TbTipoPagamento inserir(TipoPagamentoDTO tipoPagamentoDTO) throws Exception{
//        TbTipoPagamento tipoPagamento = repository.getOne(tipoPagamentoDTO.getIdTipoPagamento());
//        if(tipoPagamento == null || tipoPagamento.getDsTipoPagamento().isEmpty())
//            throw new Exception(("IdTipoPagamento: " +tipoPagamentoDTO.getIdTipoPagamento()) + " não encontrado!");
//
//        TbTipoPagamento tipoPag = new TbTipoPagamento();
//        tipoPag.setIdTipoPagamento(tipoPagamentoDTO.getIdTipoPagamento());
//        tipoPag.setDsTipoPagamento(tipoPagamentoDTO.setDsTipoPagamento();
//
//
//    }


}
