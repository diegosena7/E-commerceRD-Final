package br.com.rdevs.ecommerce.pagamentopedido.repository;

import br.com.rdevs.ecommerce.pagamentopedido.model.entity.TbTipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TbTipoPagamento, BigInteger> {
}
