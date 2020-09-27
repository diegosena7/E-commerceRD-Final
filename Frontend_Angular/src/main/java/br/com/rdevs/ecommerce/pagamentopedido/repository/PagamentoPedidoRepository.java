package br.com.rdevs.ecommerce.pagamentopedido.repository;

import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbPagamentoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface PagamentoPedidoRepository extends JpaRepository<TbPagamentoPedido, BigInteger> {
//    List<TbPagamentoPedido> findByNmNomeTitular(String nmNomeTitular); // select da tabela onde nome for = ?
}