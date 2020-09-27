package br.com.rdevs.ecommerce.estoque.repository;


import br.com.rdevs.ecommerce.estoque.model.entity.TbFilial;
import br.com.rdevs.ecommerce.estoque.model.entity.TbProdutoFilialEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface FilialRepository extends JpaRepository<TbFilial, BigInteger> {

    List<TbFilial> findByCdFilial (BigInteger cdFilial);


//

    List<TbFilial> findByCdFilialAndEstoqueProdutoFilialCdProduto (BigInteger cdFilial, BigInteger cdProduto);

}
