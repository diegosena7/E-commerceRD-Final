package br.com.rdevs.ecommerce.cadastro.service.bo;

import br.com.rdevs.ecommerce.cadastro.model.dto.EnderecoDTO;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbEndereco;
import org.springframework.stereotype.Component;

@Component
public class EnderecoBO {

    public EnderecoDTO parseToDTO(TbEndereco enderecoEntity){
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setIdEndereco(enderecoEntity.getIdEndereco());
        enderecoDTO.setDsEndereco(enderecoEntity.getDsEndereco());
        enderecoDTO.setNrEndereco(enderecoEntity.getNrEndereco());
        enderecoDTO.setNrCep(enderecoEntity.getNrCep());
        enderecoDTO.setDsBairro(enderecoEntity.getDsBairro());
        enderecoDTO.setDsCidade(enderecoEntity.getDsCidade());
        enderecoDTO.setSgEstado(enderecoEntity.getSgEstado());
        enderecoDTO.setNmCompleto(enderecoEntity.getNmCompleto());

        return  enderecoDTO;
    }

    public TbEndereco parseToEntity(EnderecoDTO enderecoDTO,TbEndereco enderecoEntity){
        if(enderecoEntity == null)
            enderecoEntity = new TbEndereco();

        if(enderecoDTO == null)
            return enderecoEntity;

        enderecoEntity = new TbEndereco();

        enderecoEntity.setIdEndereco(enderecoDTO.getIdEndereco());
        enderecoEntity.setDsEndereco(enderecoDTO.getDsEndereco());
        enderecoEntity.setNrEndereco(enderecoDTO.getNrEndereco());
        enderecoEntity.setNrCep(enderecoDTO.getNrCep());
        enderecoEntity.setDsBairro(enderecoDTO.getDsBairro());
        enderecoEntity.setDsCidade(enderecoDTO.getDsCidade());
        enderecoEntity.setSgEstado(enderecoDTO.getSgEstado());
        enderecoEntity.setNmCompleto(enderecoDTO.getNmCompleto());

        return  enderecoEntity;
    }


}
