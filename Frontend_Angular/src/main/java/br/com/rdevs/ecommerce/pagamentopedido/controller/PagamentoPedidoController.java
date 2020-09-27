package br.com.rdevs.ecommerce.pagamentopedido.controller;

import br.com.rdevs.ecommerce.pagamentopedido.model.dto.PagamentoPedidoDTO;
import br.com.rdevs.ecommerce.pagamentopedido.model.dto.PostPagamentoPedidoDTO;
import br.com.rdevs.ecommerce.pagamentopedido.service.PagamentoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PagamentoPedidoController {

    @Autowired
    private PagamentoPedidoService service;

    @GetMapping("/pagamentos")
    public ResponseEntity buscarTodas(){
        return ResponseEntity.ok().body(service.buscarTodosPedidos());
    }

    @PostMapping("/enviarPedido")
    public ResponseEntity enviarPedido(@RequestBody PostPagamentoPedidoDTO pagamentoPedidoDTO){
        return ResponseEntity.ok().body(service.enviarPedido(pagamentoPedidoDTO));
    }

//
//    @GetMapping("/pagamento/{idPedido}")
//    public ResponseEntity buscarPorId(@PathVariable("idPedido") Long idPedido){
//        return ResponseEntity.ok().body(service.buscarporId(idPedido));
//    }
}

