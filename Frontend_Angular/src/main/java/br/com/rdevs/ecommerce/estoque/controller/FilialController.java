package br.com.rdevs.ecommerce.estoque.controller;



import br.com.rdevs.ecommerce.estoque.model.dto.EstoqueFilialDTO;
import br.com.rdevs.ecommerce.estoque.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class FilialController {

        @Autowired
        private FilialService service;

        @GetMapping("/filiais/{cdFilial}")
        public ResponseEntity buscarTodos(@PathVariable("cdFilial") BigInteger cdFilial){
            return ResponseEntity.ok().body(service.buscarPorFilial(cdFilial));
        }

        @PostMapping("/estoquePorFilial")
        public ResponseEntity buscarEstoquePorFilial(@RequestBody EstoqueFilialDTO estoqueFilialDTO){
                return ResponseEntity.ok().body(service.buscarEstoqueProdutoFilial(estoqueFilialDTO));
        }


}
