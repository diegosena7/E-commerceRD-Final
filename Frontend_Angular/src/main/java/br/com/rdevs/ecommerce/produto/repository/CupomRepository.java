package br.com.rdevs.ecommerce.produto.repository;

import br.com.rdevs.ecommerce.produto.model.entity.TbTcCupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CupomRepository extends JpaRepository<TbTcCupom , BigInteger> {
    TbTcCupom findByClienteIdCliente (BigInteger idCliente);
}
