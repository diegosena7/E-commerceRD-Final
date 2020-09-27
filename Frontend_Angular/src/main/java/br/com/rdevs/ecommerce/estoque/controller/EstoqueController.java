package br.com.rdevs.ecommerce.estoque.controller;


import br.com.rdevs.ecommerce.estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class EstoqueController {


    @Autowired
    private EstoqueService service;

    @GetMapping("/estoque")
    public ResponseEntity<Object> listarTodas(){
        return ResponseEntity.ok().body(service.buscarTodas());
    }

    @GetMapping("/estoque/{cdProduto}")
    public ResponseEntity<Object> listarEstoquePorProduto(@PathVariable("cdProduto") BigInteger cdProduto){
        return ResponseEntity.ok().body(service.buscarEstoquesPorProduto(cdProduto));
    }




    // LISTAR AS FILIIAS QUE POSSUEM UM PRODUTO EM ESTOQUE


    // LISTAR O ESTOQUE TOTAL DE UM PRODUTO




    }

