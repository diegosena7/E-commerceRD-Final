package br.com.rdevs.ecommerce.cadastro.service.bo;

import br.com.rdevs.ecommerce.cadastro.model.dto.CartaoCreditoDTO;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCartaoCredito;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class CartaoCreditoBO {


    public CartaoCreditoDTO parseToDTO(TbCartaoCredito cartaoCreditoEntity){

        CartaoCreditoDTO cartaoCreditoDTO = new CartaoCreditoDTO();
        cartaoCreditoDTO.setIdCartaoCredito(cartaoCreditoEntity.getIdCartaoCredito());

        byte[] decodedBytes = Base64.getDecoder().decode(cartaoCreditoEntity.getNrNumeroCartao());
        String decodedString = new String(decodedBytes);

        String cartaoCompleto = decodedString;


        String ultimosDigitos ="****.****.****." + cartaoCompleto.substring(cartaoCompleto.length()-4);
        cartaoCreditoDTO.setNrNumeroCartao(ultimosDigitos);


        cartaoCreditoDTO.setNmNomeTitular(cartaoCreditoEntity.getNmNomeTitular());

        return cartaoCreditoDTO;
    }

    public TbCartaoCredito parseToEntity(CartaoCreditoDTO cartaoCreditoDTO,TbCartaoCredito cartaoCreditoEntity){
        if(cartaoCreditoEntity == null)
            cartaoCreditoEntity = new TbCartaoCredito();

        if(cartaoCreditoDTO == null)
            return cartaoCreditoEntity;


        cartaoCreditoEntity = new TbCartaoCredito();

        cartaoCreditoEntity.setIdCartaoCredito(cartaoCreditoDTO.getIdCartaoCredito());


        cartaoCreditoEntity.setNrNumeroCartao(Base64.getEncoder().encodeToString(cartaoCreditoDTO.getNrNumeroCartao().getBytes()));

        cartaoCreditoEntity.setNmNomeTitular(cartaoCreditoDTO.getNmNomeTitular());

        return cartaoCreditoEntity;
    }

}
