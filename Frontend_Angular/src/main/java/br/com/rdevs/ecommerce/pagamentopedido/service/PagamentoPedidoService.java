package br.com.rdevs.ecommerce.pagamentopedido.service;

import br.com.rdevs.ecommerce.pagamentopedido.model.dto.PagamentoPedidoDTO;
import br.com.rdevs.ecommerce.pagamentopedido.model.dto.PostPagamentoPedidoDTO;
import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbPagamentoPedido;
import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbTipoPagamento;
import br.com.rdevs.ecommerce.pagamentopedido.repository.PagamentoPedidoRepository;
import br.com.rdevs.ecommerce.pagamentopedido.service.bo.PagamentoPedidoBO;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedido;
import br.com.rdevs.ecommerce.pedido.service.bo.TbPedidoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PagamentoPedidoService {

    @Autowired
    private PagamentoPedidoRepository repository;

    @Autowired
    private PagamentoPedidoBO pagamentoPedidoBO;

    @Autowired
    private TbPedidoBO tbPedidoBO;

//    public List<PagamentoPedidoDTO> buscarTodosPedidos() {
//        List<PagamentoPedidoDTO> pagamentoPedidosDTOs = new ArrayList<>();
//        List<TbPagamentoPedido> pagamentosPedidosEntitys = repository.findAll();
//
//        for (TbPagamentoPedido pagamentoEntity : pagamentosPedidosEntitys) {
//            PagamentoPedidoDTO pagamentoPedidoDTO = new PagamentoPedidoDTO();
//
//            pagamentoPedidoDTO.setIdPagamentoPedido(pagamentoEntity.getIdPagamentoPedido());
//            pagamentoPedidoDTO.setNmNomeTitular(pagamentoEntity.getNmNomeTitular());
//            pagamentoPedidoDTO.setNrNumeroCartao(pagamentoEntity.getNrNumeroCartao());
//
//            TipoPagamentoDTO tipoPagamentoDTO = new TipoPagamentoDTO();
//            tipoPagamentoDTO.setIdTipoPagamento(pagamentoEntity.getTipoPagamento().getIdTipoPagamento());
//            tipoPagamentoDTO.setDsTipoPagamento(pagamentoEntity.getTipoPagamento().getDsTipoPagamento());
//
//            pagamentoPedidoDTO.setTipoPagamentoDTO(tipoPagamentoDTO);
//
//            PedidoDTO pedidoPagamento = new PedidoDTO();
//
//            pedidoPagamento.setIdPedido(pagamentoEntity.getTbPedidoEntity().getIdPedido());
//            pedidoPagamento.setDtCompra(pagamentoEntity.getTbPedidoEntity().getDtCompra());
//            pedidoPagamento.setVlFrete(pagamentoEntity.getTbPedidoEntity().getVlFrete());
//            pedidoPagamento.setVlTotalPedido(pagamentoEntity.getTbPedidoEntity().getVlTotalPedido());
//            pedidoPagamento.setQtItensPedido(pagamentoEntity.getTbPedidoEntity().getQtItensPedido());
//            pedidoPagamento.setIdCliente(pagamentoEntity.getTbPedidoEntity().getCliente().getIdCliente());
//            pedidoPagamento.setCdStatusPedido(pagamentoEntity.getTbPedidoEntity().getStatusPedido().getCdStatusPedido());
//
//            List<PedidoItemDTO> itensDTO = new ArrayList<>();
//            for (TbPedidoItem pedidoEtity : pagamentoEntity.getTbPedidoEntity().getItens()) {
//                PedidoItemDTO pedidoItemDTO = new PedidoItemDTO();
//
//                pedidoItemDTO.setIdPedido(pedidoEtity.getPedido().getIdPedido());
//                pedidoItemDTO.setDsProduto(pedidoEtity.getProduto().getDsProduto());
//                pedidoItemDTO.setVlPedidoItem(pedidoEtity.getVlPedidoItem());
//                pedidoItemDTO.setCdProduto(pedidoEtity.getProduto().getCdProduto());
//                pedidoItemDTO.setNrItemPedido(pedidoEtity.getNrItemPedido());
//
//                itensDTO.add(pedidoItemDTO);
//            }
//            pedidoPagamento.setItems(itensDTO);
//
//            pagamentoPedidoDTO.setPedidoPagamento(pedidoPagamento);
//
//            pagamentoPedidosDTOs.add(pagamentoPedidoDTO);
//
//        }
//        return pagamentoPedidosDTOs;
//
//    }

    public List<TbPagamentoPedido> buscarTodosPedidos() {
        return repository.findAll();
    }

    public TbPagamentoPedido enviarPedido(@RequestBody PostPagamentoPedidoDTO pagamentoPedidoDTO) {

        TbPagamentoPedido pagamentoPedidoEntity = new TbPagamentoPedido();
        TbTipoPagamento tipoPagamentoEntity = new TbTipoPagamento();

        TbPedido tbPedido = new TbPedido();
        tbPedido.setIdPedido(pagamentoPedidoDTO.getIdPedido());
        pagamentoPedidoEntity.setTbPedidoEntity(tbPedido);
        pagamentoPedidoEntity.setIdPagamentoPedido(pagamentoPedidoDTO.getIdPedido());
        tipoPagamentoEntity.setIdTipoPagamento(pagamentoPedidoDTO.getIdTipoPagameto());
        pagamentoPedidoEntity.setTipoPagamento(tipoPagamentoEntity);
        pagamentoPedidoEntity.setNrNumeroCartao(pagamentoPedidoDTO.getNrNumeroCartao());
        pagamentoPedidoEntity.setNmNomeTitular(pagamentoPedidoDTO.getNmNomeTitular());

        return repository.save(pagamentoPedidoEntity);
    }










}






