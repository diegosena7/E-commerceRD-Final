package br.com.rdevs.ecommerce.produto.repository;

import br.com.rdevs.ecommerce.produto.model.entity.TbTcCupomItem;
import br.com.rdevs.ecommerce.produto.model.entity.TbTcCupomItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CupomItemRepository extends JpaRepository<TbTcCupomItem, BigInteger> {

    TbTcCupomItem findByTcCupomClienteIdClienteAndProdutoCpCdProduto(BigInteger idCliente, BigInteger cdProduto);

}
