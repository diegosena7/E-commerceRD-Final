package br.com.rdevs.ecommerce.pedido.service;
//Classe para inserir as regras de negócio e metodos a serem inseridos na classe Controller
import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import br.com.rdevs.ecommerce.cadastro.repository.CadastroRepository;
import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoFiscal;
import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoItem;
import br.com.rdevs.ecommerce.documentoFiscal.repository.DocumentoFiscalRepository;
import br.com.rdevs.ecommerce.estoque.model.dto.EstoqueProdutoDTO;
import br.com.rdevs.ecommerce.estoque.model.entity.TbProdutoFilialEstoque;
import br.com.rdevs.ecommerce.estoque.repository.EstoqueRepository;
import br.com.rdevs.ecommerce.pedido.model.dto.CarrinhoPedidoDTO;
import br.com.rdevs.ecommerce.pedido.model.dto.PedidoDTO;
import br.com.rdevs.ecommerce.pedido.model.dto.PedidoItemDTO;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedido;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedidoItem;
import br.com.rdevs.ecommerce.pedido.model.entity.TbStatusPedido;
import br.com.rdevs.ecommerce.pedido.repository.PedidoRepository;
import br.com.rdevs.ecommerce.pedido.service.bo.TbPedidoBO;
import br.com.rdevs.ecommerce.produto.model.dto.CategoriaProdutoDTO;
import br.com.rdevs.ecommerce.produto.model.dto.ProdutoDTO;
import br.com.rdevs.ecommerce.produto.model.dto.ProdutoImagemDTO;
import br.com.rdevs.ecommerce.produto.model.dto.SubCategoriaProdutoDTO;
import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import br.com.rdevs.ecommerce.produto.model.entity.TbProdutoImagem;
import br.com.rdevs.ecommerce.produto.repository.ProdutoRepository;
import br.com.rdevs.ecommerce.produto.service.bo.ProdutoBo;
import br.com.rdevs.ecommerce.produto.service.bo.ProdutoImagemBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@Service
public class TbPedidoService {
    //implementar inclusão, exclusão e consulta
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CadastroRepository clienteRepository;

    @Autowired
    private DocumentoFiscalRepository documentoFiscalRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoBo produtoBo;

    @Autowired
    private ProdutoImagemBo produtoImagemBo;

    @Autowired
    private TbPedidoBO pedidoBO;


    @PersistenceContext
    private EntityManager em;

    //Método de buscar Pedidos pela id do cliente
    public List<PedidoDTO> buscarPedidoPorIdCliente(BigInteger idCliente) {
        List<PedidoDTO> pedidosDTO = new ArrayList<>();
        List<TbPedido> pedidos = pedidoRepository.findByClienteIdCliente(idCliente);

        for(TbPedido tbPedido : pedidos){
            PedidoDTO pedidoDTO = pedidoBO.parseToDTO(tbPedido);
            pedidoDTO.setIdCliente(idCliente);
            pedidoDTO.setDsStatusPedido(tbPedido.getStatusPedido().getDsStatusPedido());

            List<PedidoItemDTO> pedidoItensDto = new ArrayList<>();

            for (TbPedidoItem pedidoItem : tbPedido.getItens()){
                PedidoItemDTO pedidoItemDTO = new PedidoItemDTO();
                pedidoItemDTO.setIdPedido(tbPedido.getIdPedido());
                pedidoItemDTO.setDsProduto(pedidoItem.getProduto().getNomeFantasia());
                pedidoItemDTO.setNrItemPedido(pedidoItem.getNrItemPedido());
                pedidoItemDTO.setCdProduto(pedidoItem.getProduto().getCdProduto());
                pedidoItemDTO.setVlPedidoItem(pedidoItem.getVlPedidoItem());

                if (pedidoItem.getQtProduto()==null){
                    pedidoItemDTO.setQtProduto(1);
                }else {
                    pedidoItemDTO.setQtProduto(pedidoItem.getQtProduto());
                }

                pedidoItensDto.add(pedidoItemDTO);
            }
            pedidoDTO.setItems(pedidoItensDto);

            pedidosDTO.add(pedidoDTO);
        }


        return pedidosDTO;
    }


    public List<ProdutoDTO> buscarPedidoPorIdPedido(BigInteger idPedido) {

        TbDocumentoFiscal documentoFiscal = documentoFiscalRepository.findByIdDocumentoFiscal(idPedido);

        List<ProdutoDTO> produtosDTOS = new ArrayList<>();

        for (TbDocumentoItem produto : documentoFiscal.getItensNf()){
            TbProduto produtoEtity = produtoRepository.findByCdProduto(produto.getProduto().getCdProduto());
            ProdutoDTO produtoDTO = produtoBo.parseToDTO(produtoEtity);


            CategoriaProdutoDTO catdto = new CategoriaProdutoDTO();
            SubCategoriaProdutoDTO subCategoriaProduto = new SubCategoriaProdutoDTO();

            subCategoriaProduto.setIdSubCategoria(produtoEtity.getSubCategoriaProduto().getIdSubCategoria());
            subCategoriaProduto.setDsSubCategoria(produtoEtity.getSubCategoriaProduto().getDsSubCategoria());
            catdto.setIdCategoriaProduto(produtoEtity.getCategoriaProduto().getIdCategoriaProduto());
            catdto.setDsCategoriaProduto(produtoEtity.getCategoriaProduto().getDsCategoriaProduto());

            produtoDTO.setSubCategoriaProduto(subCategoriaProduto);
            produtoDTO.setCategoriaProduto(catdto);

            List<ProdutoImagemDTO> imagemsProdutodto = new ArrayList<>();
            for (TbProdutoImagem produtoImagemEntity : produtoEtity.getImagens()) {
                ProdutoImagemDTO imagemDTO = produtoImagemBo.parseToDTO(produtoImagemEntity);

                imagemsProdutodto.add(imagemDTO);
            }
            produtoDTO.setImagens(imagemsProdutodto);

            TbProdutoFilialEstoque produtoEstoqueEntity = estoqueRepository.findByProdutoFilialCdProdutoAndCdFilial(produtoEtity.getCdProduto(), BigInteger.valueOf(4L));
            EstoqueProdutoDTO estoqueProdutoDTO = new EstoqueProdutoDTO();
            estoqueProdutoDTO.setCdFilial(produtoEstoqueEntity.getCdFilial());
            estoqueProdutoDTO.setQtEstoque(produtoEstoqueEntity.getQtEstoque());
            estoqueProdutoDTO.setQtEmpenho(produtoEstoqueEntity.getQtEmpenho());
            produtoDTO.setEstoques(estoqueProdutoDTO);

            produtosDTOS.add(produtoDTO);
        }


        return produtosDTOS;
    }

    //Método de inserir Pedidos
    public TbPedido inserirPedido(PedidoDTO dto) throws Exception{
        TbPedido pedidoEntity = pedidoBO.parseToEntity(dto,null);
        TbCliente cliente = new TbCliente();
        cliente.setIdCliente(dto.getIdCliente());
        pedidoEntity.setCliente(cliente);

        List<TbPedidoItem> itemsEntity = new ArrayList<>();

        double valor = 0d;
        Long quantidade = 0l;
        for(PedidoItemDTO itemDTO : dto.getItems()){
            TbPedidoItem itemEntity = new TbPedidoItem();
            itemEntity.setNrItemPedido(itemDTO.getNrItemPedido());
            itemEntity.setProduto(produtoRepository.getOne(itemDTO.getCdProduto()));
            itemEntity.setVlPedidoItem(itemEntity.getProduto().getValorUnidade());

            valor += itemEntity.getVlPedidoItem().doubleValue();
            quantidade++;
            itemEntity.setNrItemPedido(BigInteger.valueOf(quantidade));
            itemEntity.setPedido(pedidoEntity);
            itemsEntity.add(itemEntity);
        }
        pedidoEntity.setVlTotalPedido(BigDecimal.valueOf(valor));
        pedidoEntity.setQtItensPedido(quantidade.intValue());
        pedidoEntity.setItens(itemsEntity);

        TbStatusPedido tbStatusPedido = new TbStatusPedido();
        tbStatusPedido.setCdStatusPedido(BigInteger.valueOf(2L));
        pedidoEntity.setStatusPedido(tbStatusPedido);

        return pedidoRepository.save(pedidoEntity);
        }




}





