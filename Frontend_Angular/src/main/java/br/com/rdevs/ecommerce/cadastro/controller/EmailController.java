package br.com.rdevs.ecommerce.cadastro.controller;

import br.com.rdevs.ecommerce.cadastro.model.dto.ClienteDTO;
import br.com.rdevs.ecommerce.cadastro.model.dto.ResultData;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import br.com.rdevs.ecommerce.cadastro.repository.CadastroRepository;
import br.com.rdevs.ecommerce.cadastro.service.EmailService;
import br.com.rdevs.ecommerce.documentoFiscal.model.dto.DocumentoFiscalDTO;
import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoFiscal;
import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoItem;
import br.com.rdevs.ecommerce.documentoFiscal.service.DocumentoFiscalService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Base64;

@RestController
public class EmailController{

    @Autowired
    private EmailService service;

    @Autowired
    private CadastroRepository cadastroRepository;

    @Autowired
    private CadastroController cadastroController;

    @Autowired
    private DocumentoFiscalService documentoFiscalService;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {

        SimpleMailMessage message = new SimpleMailMessage();
        TbDocumentoFiscal documentoFiscalEntity = documentoFiscalService.listaDocumentosPorID(BigInteger.valueOf(622L));



        String mensagem = ("Compra realizada com sucesso! \n " +
                        "Valor Totatl da Compra: "+documentoFiscalEntity.getVlDocumentoFiscal().toString()+ "\n"+
                        "Data da Compra: " +documentoFiscalEntity.getDtEmissao().toString()+ "\n"+
                        "Numero da Chave de Acesso: " +documentoFiscalEntity.getNrChaveAcesso().toString()+ "\n"+
                        "Numero da nota: " +documentoFiscalEntity.getIdDocumentoFiscal().toString() + "\n"+
                        "numero   cd   qt    ds      vl    vlIcms  pcIcms%  " + "\n");

//        for (TbDocumentoItem documentoItem: documentoFiscalEntity.getItensNf()){
//            mensagem.concat(documentoItem.getNrItemDocumento().toString()+" "+
//                            documentoItem.getProduto().getCdProduto().toString()+" "+
//                            documentoItem.getQtItem().toString()+" "+
//                            documentoItem.getProduto().getNomeFantasia() +" "+
//                            documentoItem.getVlItem().toString()+ " "+
//                            documentoItem.getVlIcms().toString()+ " "+
//                            documentoItem.getPcIcms().toString()+ " \n");
//        }
//
//        message.setText(mensagem.concat("Raia drogasil gente que cuida de Gente!"));

        message.setTo("andremouraer@gmail.com");
        message.setFrom("andremouraer@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    @PutMapping(value = "/esqueciSenha/{login}")
    public ResponseEntity trocarSenha(@PathVariable("login") String login){
        String email = service.esqueciSenha(login);
        ResultData resultData = null;
        if (email==null) {
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "Email ou cpf não cadastrado!!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }else {
            TbCliente cliente = cadastroRepository.findByDsEmail(email);
            String[] carct = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                    "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B",
                    "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

            String senha = "";

            for (int x = 0; x < 10; x++) {
                int j = (int) (Math.random() * carct.length);
                senha += carct[j];
            }

            cliente.setPwCliente(Base64.getEncoder().encodeToString(senha.getBytes()));
            cadastroRepository.save(cliente);


            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("Essa é uma mensagem automatica favor não responder!!\n");
            message.setText("Foi solicitado a alteração da senha, sua nova senha é: " + senha);
            message.setSubject("Alteração de senha:");
            message.setTo(email);
            message.setFrom("ecommerceraiadrogasil1@gmail.com");


            try {
                mailSender.send(message);
                resultData = new ResultData<ClienteDTO>(HttpStatus.OK.value(), "Email enviado com sucesso!");
                return ResponseEntity.status(HttpStatus.OK).body(resultData);
            } catch (Exception e) {
                e.printStackTrace();
                resultData = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Email não cadastrado!!", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(resultData);
            }
        }

    }
}