package br.com.rdevs.ecommerce.pedido.repository;

import br.com.rdevs.ecommerce.pedido.model.entity.TbStatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface StatusPedidoRepository extends JpaRepository<TbStatusPedido, BigInteger> {
}
