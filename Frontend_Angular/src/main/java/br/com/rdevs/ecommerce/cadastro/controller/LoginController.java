package br.com.rdevs.ecommerce.cadastro.controller;

import br.com.rdevs.ecommerce.cadastro.model.dto.ClienteDTO;
import br.com.rdevs.ecommerce.cadastro.model.dto.Login;
import br.com.rdevs.ecommerce.cadastro.model.dto.ResultData;
import br.com.rdevs.ecommerce.cadastro.repository.CadastroRepository;
import br.com.rdevs.ecommerce.cadastro.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.Base64;

@RestController
public class LoginController {

    @Autowired
    private ClienteService service;
    @Autowired
    private CadastroRepository repository;


    @PostMapping("/login")
    @RolesAllowed(value = "ADMIN")
    public ResponseEntity loginCadastro(@RequestBody Login login) throws Exception {
        ResultData resultData = null;
        ClienteDTO cliente = service.loginCadastro(login);
        if (cliente == null) {
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(), "CPF ou Email invalido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultData);
        }else {
            try {
                byte[] decodedBytes = Base64.getDecoder().decode(cliente.getPwCliente());
                String decodedString = new String(decodedBytes);

                if (login.getSenha().equals(decodedString)) {
                    resultData = new ResultData<ClienteDTO>(HttpStatus.OK.value(), "Login efetivado!",cliente);
                    return ResponseEntity.status(HttpStatus.OK).body(resultData);
                } else {
                    resultData = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Senha ou email incorreto!!");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(resultData);
                }
            } catch (Exception e) {
                resultData = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Senha ou email incorreto!!", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(resultData);
            }
        }

    }


}
