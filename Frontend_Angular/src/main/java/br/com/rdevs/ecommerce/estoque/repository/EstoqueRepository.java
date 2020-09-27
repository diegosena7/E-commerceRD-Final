package br.com.rdevs.ecommerce.estoque.repository;



import br.com.rdevs.ecommerce.estoque.model.entity.TbProdutoFilialEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<TbProdutoFilialEstoque, BigInteger> {
  List<TbProdutoFilialEstoque> findByProdutoFilialCdProduto(BigInteger cdProduto);

  TbProdutoFilialEstoque findByCdFilial(BigInteger cdFilial);

  TbProdutoFilialEstoque findByProdutoFilialCdProdutoAndCdFilial(BigInteger cdProduto,BigInteger cdFilial);

}
