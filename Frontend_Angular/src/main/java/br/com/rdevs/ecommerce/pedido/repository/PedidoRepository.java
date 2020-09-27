package br.com.rdevs.ecommerce.pedido.repository;

import br.com.rdevs.ecommerce.cadastro.model.entity.TbCliente;
import br.com.rdevs.ecommerce.pedido.model.entity.TbPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<TbPedido, BigInteger> {

     List<TbPedido> findByClienteIdCliente(BigInteger idCliente);

     TbPedido findByIdPedido(BigInteger idPedido);

}
