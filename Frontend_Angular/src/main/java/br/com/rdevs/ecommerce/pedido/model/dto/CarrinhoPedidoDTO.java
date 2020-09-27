package br.com.rdevs.ecommerce.pedido.model.dto;

import br.com.rdevs.ecommerce.produto.model.dto.ProdutoDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class CarrinhoPedidoDTO {

    private List<ProdutoDTO> produtosDTO;
}
