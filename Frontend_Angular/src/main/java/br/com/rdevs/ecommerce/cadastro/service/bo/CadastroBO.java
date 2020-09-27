package br.com.rdevs.ecommerce.cadastro.service.bo;

import br.com.rdevs.ecommerce.cadastro.model.dto.ClienteDTO;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCategoriaCliente;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class CadastroBO{


    public ClienteDTO parseToDTO(TbCliente clienteEntity) {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdCliente(clienteEntity.getIdCliente());
        clienteDTO.setNmCliente(clienteEntity.getNmCliente());
        clienteDTO.setNrCpf(clienteEntity.getNrCpf());
        clienteDTO.setDtNasc(clienteEntity.getDtNasc());
        clienteDTO.setDsEmail(clienteEntity.getDsEmail());


        clienteDTO.setDsGenero(clienteEntity.getDsGenero());
        clienteDTO.setNrTelefone1(clienteEntity.getNrTelefone1());
        clienteDTO.setNrTelefone2(clienteEntity.getNrTelefone2());
        clienteDTO.setPwCliente(clienteEntity.getPwCliente());
        clienteDTO.setIdCategoriaCliente(clienteEntity.getCategoriaCliente().getIdCategoriaCliente());
        clienteDTO.setPcDescontoEcommerce(clienteEntity.getCategoriaCliente().getPcDescontoEcommerce());

        return clienteDTO;
    }

    public TbCliente parseToEntity(ClienteDTO clienteDTO,TbCliente clienteEntity){
        if(clienteEntity == null)
            clienteEntity = new TbCliente();

        if(clienteDTO == null)
            return clienteEntity;

        clienteEntity = new TbCliente();
        clienteEntity.setIdCliente(clienteDTO.getIdCliente());
        clienteEntity.setNmCliente(clienteDTO.getNmCliente());
        clienteEntity.setNrCpf(clienteDTO.getNrCpf());
        clienteEntity.setDtNasc(clienteDTO.getDtNasc());
        clienteEntity.setDsEmail(clienteDTO.getDsEmail());
        clienteEntity.setDsGenero(clienteDTO.getDsGenero());
        clienteEntity.setNrTelefone1(clienteDTO.getNrTelefone1());
        clienteEntity.setNrTelefone2(clienteDTO.getNrTelefone2());

        clienteEntity.setPwCliente(Base64.getEncoder().encodeToString(clienteDTO.getPwCliente().getBytes()));

        //TODO Criar validação por data para clientes acima de 60 anos serem setados como 2

        GregorianCalendar calendar = new GregorianCalendar();

        TbCategoriaCliente categoriaCliente = new TbCategoriaCliente();
        int anoNasc = clienteEntity.getDtNasc().getYear();
        int anoAtual= calendar.getTime().getYear();

        if ((anoAtual-anoNasc)>=60){
            categoriaCliente.setIdCategoriaCliente(BigInteger.valueOf(2L));
        }else {
            categoriaCliente.setIdCategoriaCliente(BigInteger.valueOf(1L));
        }
        clienteDTO.setIdCategoriaCliente(categoriaCliente.getIdCategoriaCliente());
        clienteEntity.setDtCadastro(calendar.getTime());

        clienteEntity.setCategoriaCliente(categoriaCliente);



        return clienteEntity;
    }
}
