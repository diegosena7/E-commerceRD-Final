package br.com.rdevs.ecommerce.pagamentopedido.service.bo;

import br.com.rdevs.ecommerce.pagamentopedido.model.dto.TipoPagamentoDTO;
import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbTipoPagamento;
import org.springframework.stereotype.Component;

@Component
public class TipoPagamentoBO {
    public TipoPagamentoDTO parseToDTO(TbTipoPagamento entity){
        TipoPagamentoDTO dto = new TipoPagamentoDTO();

        if(entity == null)
            return dto;

        dto.setIdTipoPagamento(entity.getIdTipoPagamento());
        dto.setDsTipoPagamento(entity.getDsTipoPagamento());

        return dto;
    }

    public TbTipoPagamento parseToEntity(TipoPagamentoDTO dto, TbTipoPagamento entity){
        if(entity == null)
            entity = new TbTipoPagamento();

        if (dto == null)
            return entity;

        entity.setIdTipoPagamento(dto.getIdTipoPagamento());
        entity.setDsTipoPagamento(dto.getDsTipoPagamento());

        return entity;
    }
}

