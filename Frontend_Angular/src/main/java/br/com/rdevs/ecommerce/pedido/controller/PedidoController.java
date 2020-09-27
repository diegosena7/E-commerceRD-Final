package br.com.rdevs.ecommerce.pedido.controller;

import br.com.rdevs.ecommerce.documentoFiscal.service.DocumentoFiscalService;
import br.com.rdevs.ecommerce.pedido.model.dto.PedidoDTO;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedido;
import br.com.rdevs.ecommerce.pedido.service.TbPedidoService;
import br.com.rdevs.ecommerce.produto.model.dto.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class PedidoController {

@Autowired
private TbPedidoService service;

@Autowired
private DocumentoFiscalService documentoFiscalService;

    @ApiOperation(value = "buscar Pedidos pela id do cliente")
    @GetMapping("/pedidos/cliente/{idCliente}")
        public ResponseEntity<Object> buscarPedidoPorIdCliente(@PathVariable("idCliente") BigInteger idCliente) {
        return ResponseEntity.ok().body(documentoFiscalService.listarPorIdCliente(idCliente));
    }

    @ApiOperation(value = "Incluir novo pedido")
    @PostMapping("/pedido")
    public ResponseEntity<Object> inserirPedidos(@RequestBody PedidoDTO pedidoDTO){
        ResultData resultData = null;
        if(pedidoDTO.getIdCliente() == null)
            resultData = new ResultData(HttpStatus.BAD_REQUEST.value(),"Campo: idCliente não informado!");

        if(resultData != null)
            return ResponseEntity.badRequest().body(resultData);
        else {
            try {
                TbPedido pedido = service.inserirPedido(pedidoDTO);
                resultData = new ResultData<TbPedido>(HttpStatus.OK.value(), "Nota Fiscal registrada com sucesso!", pedido);
                return ResponseEntity.status(HttpStatus.CREATED).body(resultData);
            } catch (Exception e) {
                resultData = new ResultData(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro ao registrar o pedido", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(resultData);
            }
        }
    }


    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<Object> buscarPedidoPorIdPedido(@PathVariable("idPedido") BigInteger idPedido) {
        return ResponseEntity.ok().body(service.buscarPedidoPorIdPedido(idPedido));
    }
}