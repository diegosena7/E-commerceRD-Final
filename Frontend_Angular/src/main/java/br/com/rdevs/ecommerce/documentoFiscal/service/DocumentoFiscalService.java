package br.com.rdevs.ecommerce.documentoFiscal.service;

import br.com.rdevs.ecommerce.cadastro.model.dto.CartaoCreditoDTO;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCartaoCredito;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import br.com.rdevs.ecommerce.cadastro.model.entity.TbEndereco;
import br.com.rdevs.ecommerce.cadastro.repository.CadastroRepository;
import br.com.rdevs.ecommerce.cadastro.repository.CartaoRepository;
import br.com.rdevs.ecommerce.cadastro.repository.EnderecoRepository;
import br.com.rdevs.ecommerce.cadastro.service.bo.CartaoCreditoBO;
import br.com.rdevs.ecommerce.documentoFiscal.model.dto.DocumentoFiscalDTO;
import br.com.rdevs.ecommerce.documentoFiscal.model.dto.DocumentoFiscalItemDTO;
import br.com.rdevs.ecommerce.documentoFiscal.model.dto.PostDocumentoFiscalDTO;
import br.com.rdevs.ecommerce.documentoFiscal.model.dto.PostDocumentoFiscalItemDTO;
import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoFiscal;
import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoItem;
import br.com.rdevs.ecommerce.documentoFiscal.repository.DocumentoFiscalRepository;
import br.com.rdevs.ecommerce.estoque.model.entity.TbProdutoFilialEstoque;
import br.com.rdevs.ecommerce.estoque.repository.EstoqueRepository;
import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbPagamentoPedido;
import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbTipoPagamento;
import br.com.rdevs.ecommerce.pagamentopedido.repository.PagamentoPedidoRepository;
import br.com.rdevs.ecommerce.pagamentopedido.repository.TipoPagamentoRepository;
import br.com.rdevs.ecommerce.pedido.model.dto.PedidoDTO;
import br.com.rdevs.ecommerce.pedido.model.dto.PedidoItemDTO;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedido;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedidoItem;
import br.com.rdevs.ecommerce.pedido.model.entity.TbStatusPedido;
import br.com.rdevs.ecommerce.pedido.repository.PedidoRepository;
import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import br.com.rdevs.ecommerce.produto.model.entity.TbTcCupom;
import br.com.rdevs.ecommerce.produto.model.entity.TbTcCupomItem;
import br.com.rdevs.ecommerce.produto.repository.CupomItemRepository;
import br.com.rdevs.ecommerce.produto.repository.CupomRepository;
import br.com.rdevs.ecommerce.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

@Service
public class DocumentoFiscalService {
    @Autowired
    DocumentoFiscalRepository documentoFiscalRepository;

    @Autowired
    PagamentoPedidoRepository pagamentoPedidoRepository;

    @Autowired
    CadastroRepository cadastroRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CupomItemRepository cupomItemRepository;

    @Autowired
    CupomRepository cupomRepository;

    @Autowired
    TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    EstoqueRepository estoqueRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CartaoCreditoBO cartaoCreditoBO;


    public TbDocumentoFiscal listaDocumentosPorID(BigInteger idDocumentoFiscal){
        DocumentoFiscalDTO documentoFiscalDTO = new DocumentoFiscalDTO();
        TbDocumentoFiscal documentoFiscalEntity =  documentoFiscalRepository.findByIdDocumentoFiscal(idDocumentoFiscal);
        documentoFiscalDTO.setNrCpf(documentoFiscalEntity.getTbCliente().getNrCpf());
        documentoFiscalDTO.setNrChaveAcesso(documentoFiscalEntity.getNrChaveAcesso());
        documentoFiscalDTO.setNrNF(documentoFiscalEntity.getNrNF());
        documentoFiscalDTO.setDtEmissao(documentoFiscalEntity.getDtEmissao());

        List<DocumentoFiscalItemDTO> itensDTO = new ArrayList<>();
        for (TbDocumentoItem itensEntity: documentoFiscalEntity.getItensNf()){
            DocumentoFiscalItemDTO itemDTO = new DocumentoFiscalItemDTO();

            itemDTO.setNrItemDocumento(itensEntity.getNrItemDocumento());
            itemDTO.setCdProduto(itensEntity.getProduto().getCdProduto());
            itemDTO.setQtItem(itensEntity.getQtItem());
            itemDTO.setNmProduto(itensEntity.getProduto().getNomeFantasia());
            itemDTO.setVlItemUnitario(itensEntity.getVlItem());
            Double valorRefaturado = itensEntity.getVlItem().doubleValue()*itensEntity.getQtItem();
            itemDTO.setVlTotalItem(BigDecimal.valueOf(valorRefaturado));
            itemDTO.setVlIcms(itensEntity.getVlIcms());
            itemDTO.setPcIcms(itensEntity.getPcIcms());

            itensDTO.add(itemDTO);
        }
        documentoFiscalDTO.setItensDocumento(itensDTO);


        return documentoFiscalEntity;
    }

    public List<PedidoDTO> listarPorIdCliente(BigInteger idCliente){
        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        List<TbDocumentoFiscal> documentoFiscalList = documentoFiscalRepository.findByTbClienteIdCliente(idCliente);


        for(TbDocumentoFiscal documentoFiscal: documentoFiscalList){
            PedidoDTO pedidoDTO = new PedidoDTO();

            pedidoDTO.setIdPedido(documentoFiscal.getIdDocumentoFiscal());
            pedidoDTO.setDtCompra(documentoFiscal.getDtEmissao());
            pedidoDTO.setVlFrete(BigDecimal.valueOf(0L));


            pedidoDTO.setIdCliente(idCliente);
            pedidoDTO.setDsStatusPedido("Vendido");

            List<PedidoItemDTO> pedidoItensDto = new ArrayList<>();

            Long quatidadeItens = 0L;
            for (TbDocumentoItem itemNF: documentoFiscal.getItensNf()){
                PedidoItemDTO pedidoItemDTO = new PedidoItemDTO();

                pedidoItemDTO.setIdPedido(itemNF.getDocumentoFiscal().getIdDocumentoFiscal());
                pedidoItemDTO.setDsProduto(itemNF.getProduto().getNomeFantasia());
                pedidoItemDTO.setNrItemPedido(itemNF.getNrItemDocumento());
                pedidoItemDTO.setCdProduto(itemNF.getProduto().getCdProduto());


                pedidoItemDTO.setVlPedidoItem(itemNF.getVlItem());

                if (itemNF.getQtItem()==null){
                    pedidoItemDTO.setQtProduto(1);
                }else {
                    pedidoItemDTO.setQtProduto(itemNF.getQtItem());
                }


                quatidadeItens += pedidoItemDTO.getQtProduto();

                pedidoItensDto.add(pedidoItemDTO);
            }
            pedidoDTO.setItems(pedidoItensDto);

            if (documentoFiscal.getCdOperacao()==BigInteger.valueOf(9)){

                pedidoDTO.setTipoVenda("Comprado no Ecommerce");
            }else{
                pedidoDTO.setTipoVenda("Comprado em Loja");
            }


            pedidoDTO.setQtItensPedido(Math.toIntExact(quatidadeItens));
            pedidoDTO.setVlTotalPedido(documentoFiscal.getVlDocumentoFiscal());


            pedidosDTO.add(pedidoDTO);
        }


        return pedidosDTO;
    }

    public DocumentoFiscalDTO inserirItem(@RequestBody PostDocumentoFiscalDTO dfDTO){

        TbDocumentoFiscal dfEntity = new TbDocumentoFiscal();
        TbPedido pedidoEntity = new TbPedido();
        TbPagamentoPedido pagamentoPedidoEntity = new TbPagamentoPedido();
        DocumentoFiscalDTO documentoFiscalDTO = new DocumentoFiscalDTO();

        TbTcCupom tcCupom = cupomRepository.findByClienteIdCliente(dfDTO.getIdCliente());

        TbEndereco endereco = enderecoRepository.getOne(dfDTO.getIdEndereco());
        TbTipoPagamento tipoPagamento = tipoPagamentoRepository.getOne(dfDTO.getIdFormaPagamento());

        Calendar data = Calendar.getInstance();
        Random random = new Random();
        Long numeroAleatorio =Math.abs(random.nextLong()*100);

        TbCliente tbCliente = cadastroRepository.getOne(dfDTO.getIdCliente());
        documentoFiscalDTO.setNrCpf(tbCliente.getNrCpf());

        dfEntity.setTbCliente(tbCliente);
        pedidoEntity.setCliente(tbCliente);


        dfEntity.setCdOperacao(BigInteger.valueOf(9l));
        dfEntity.setFlNf(1l);
        dfEntity.setCdFilial(BigInteger.valueOf(4l));
        dfEntity.setNrChaveAcesso(numeroAleatorio);
        documentoFiscalDTO.setNrChaveAcesso(numeroAleatorio);

        dfEntity.setNrNF(2001L);
        documentoFiscalDTO.setNrNF(2001L);

        dfEntity.setDtEmissao(data.getTime());
        documentoFiscalDTO.setDtEmissao(data.getTime());
        pedidoEntity.setDtCompra(data.getTime());


        List<TbDocumentoItem> itensNF = new ArrayList<>();
        List<TbPedidoItem> itensPedido = new ArrayList<>();
        List<DocumentoFiscalItemDTO> itensNfDTOS = new ArrayList<>();

        double valor = 0d;
        Integer contador = 0;
        Long contadorItens = 0L;
        double calculoIcms = 0d;
        for (PostDocumentoFiscalItemDTO itemDTO: dfDTO.getItensDTOPost()){
            Double pcDensconto = 1D;
            Double valorConvertido = null;
            TbDocumentoItem itemNF = new TbDocumentoItem();
            TbPedidoItem itemPedido = new TbPedidoItem();
            DocumentoFiscalItemDTO itemNfDTO = new DocumentoFiscalItemDTO();

            TbProduto produto = produtoRepository.getOne(itemDTO.getCdProduto());

            TbTcCupomItem tcCupomItem = cupomItemRepository.findByTcCupomClienteIdClienteAndProdutoCpCdProduto(dfDTO.getIdCliente(),itemDTO.getCdProduto());
            itemNF.setProduto(produto);
            itemPedido.setProduto(produto);
            itemNfDTO.setCdProduto(itemDTO.getCdProduto());


            itemNF.setQtItem(itemDTO.getQtProduto());
            itemPedido.setQtProduto(itemDTO.getQtProduto());
            itemNfDTO.setQtItem(itemDTO.getQtProduto());
            itemNfDTO.setNmProduto(produto.getNomeFantasia());



            TbProdutoFilialEstoque estoque = estoqueRepository.findByProdutoFilialCdProdutoAndCdFilial(produto.getCdProduto(), BigInteger.valueOf(4L));


            //TODO validar a quantidade de itens na compra
            Integer quantidadeComprada = itemDTO.getQtProduto();
            contadorItens += quantidadeComprada;


            Integer estoqueNovo = estoque.getQtEstoque() - quantidadeComprada;
            estoque.setQtEstoque(estoqueNovo);
            estoqueRepository.save(estoque);

            itemNF.setDocumentoFiscal(dfEntity);
            itemPedido.setPedido(pedidoEntity);

            contador++;
            itemNF.setNrItemDocumento(BigInteger.valueOf(contador));
            itemPedido.setNrItemPedido(BigInteger.valueOf(contador));
            itemNfDTO.setNrItemDocumento(BigInteger.valueOf(contador));



            if (tcCupom==null||tcCupomItem==null) {
                pcDensconto -= tbCliente.getCategoriaCliente().getPcDescontoEcommerce().doubleValue();
            }else if(tcCupomItem.getPcDesconto().doubleValue()<tbCliente.getCategoriaCliente().getPcDescontoEcommerce().doubleValue()){
                pcDensconto -= tbCliente.getCategoriaCliente().getPcDescontoEcommerce().doubleValue();
            }else if(tcCupomItem.getPcDesconto().doubleValue()>tbCliente.getCategoriaCliente().getPcDescontoEcommerce().doubleValue()){
                pcDensconto -= tcCupomItem.getPcDesconto().doubleValue();
            }else {
                pcDensconto -= tbCliente.getCategoriaCliente().getPcDescontoEcommerce().doubleValue();
            }

            valorConvertido = produto.getValorUnidade().doubleValue()*pcDensconto;

            Double valorRefaturado = valorConvertido * quantidadeComprada;

            itemNfDTO.setVlTotalItem(BigDecimal.valueOf(valorRefaturado).setScale(2, RoundingMode.HALF_EVEN));
            itemNF.setVlItem(BigDecimal.valueOf(valorConvertido).setScale(2, RoundingMode.HALF_EVEN));
            itemNfDTO.setVlItemUnitario(BigDecimal.valueOf(valorConvertido).setScale(2, RoundingMode.HALF_EVEN));
            itemPedido.setVlPedidoItem(BigDecimal.valueOf(valorConvertido).setScale(2, RoundingMode.HALF_EVEN));



            valor += valorRefaturado;

            itemNF.setPcIcms(BigDecimal.valueOf(0.17));
            itemNfDTO.setPcIcms(BigDecimal.valueOf(0.17));

            calculoIcms = valorRefaturado*0.17;
            itemNF.setVlIcms(BigDecimal.valueOf(calculoIcms));
            itemNfDTO.setVlIcms(BigDecimal.valueOf(calculoIcms).setScale(2, RoundingMode.HALF_EVEN));

            itensNF.add(itemNF);
            itensPedido.add(itemPedido);
            itensNfDTOS.add(itemNfDTO);

        }

        dfEntity.setItensNf(itensNF);
        pedidoEntity.setItens(itensPedido);
        documentoFiscalDTO.setItensDocumento(itensNfDTOS);

        dfEntity.setVlDocumentoFiscal(BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_EVEN));
        documentoFiscalDTO.setValorTotalNota(BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_EVEN));
        documentoFiscalDTO.setQtItens(contadorItens);

        pedidoEntity.setVlTotalPedido(dfEntity.getVlDocumentoFiscal());

        pedidoEntity.setQtItensPedido(contadorItens.intValue());

        TbStatusPedido tbStatusPedido = new TbStatusPedido();
        tbStatusPedido.setCdStatusPedido(BigInteger.valueOf(2L));
        pedidoEntity.setStatusPedido(tbStatusPedido);



        documentoFiscalRepository.save(dfEntity);

        pedidoRepository.save(pedidoEntity);

        documentoFiscalDTO.setNrPedido(pedidoEntity.getIdPedido());

        documentoFiscalDTO.setIdNF(dfEntity.getIdDocumentoFiscal());

        pagamentoPedidoEntity.setTbEndereco(endereco);
        if (dfDTO.getNrNumeroCartao()!=null) {
            pagamentoPedidoEntity.setNmNomeTitular(dfDTO.getNmNomeTitular());
            documentoFiscalDTO.setNmNomeTitular(dfDTO.getNmNomeTitular());

            String cartaoConvertido = dfDTO.getNrNumeroCartao();
            List<TbCartaoCredito> cartaoCredito = cartaoRepository.findByClienteCartaoIdCliente(dfDTO.getIdCliente());

            if (cartaoConvertido.contains("*")) {
                for (TbCartaoCredito i : cartaoCredito) {
                    byte[] decodedBytes = Base64.getDecoder().decode(i.getNrNumeroCartao());
                    String decodedString = new String(decodedBytes);

                    cartaoConvertido = decodedString;
                    String ultimosDigitos = "****.****.****." + decodedString.substring(decodedString.length() - 4);

                    pagamentoPedidoEntity.setNrNumeroCartao(Base64.getEncoder().encodeToString(cartaoConvertido.getBytes()));

                    documentoFiscalDTO.setNrNumeroCartao(ultimosDigitos);
                }
            } else {
                String ultimosDigitos = "****.****.****." + cartaoConvertido.substring(cartaoConvertido.length() - 4);
                pagamentoPedidoEntity.setNrNumeroCartao(Base64.getEncoder().encodeToString(cartaoConvertido.getBytes()));

                documentoFiscalDTO.setNrNumeroCartao(ultimosDigitos);
            }
        }


        pagamentoPedidoEntity.setTbCliente(tbCliente);
        pagamentoPedidoEntity.setTbPedidoEntity(pedidoEntity);
        pagamentoPedidoEntity.setIdPagamentoPedido(pedidoEntity.getIdPedido());
        pagamentoPedidoEntity.setDocumentoFiscal(dfEntity);
        pagamentoPedidoEntity.setTipoPagamento(tipoPagamento);
        pagamentoPedidoRepository.save(pagamentoPedidoEntity);


        return documentoFiscalDTO;
    }





}
