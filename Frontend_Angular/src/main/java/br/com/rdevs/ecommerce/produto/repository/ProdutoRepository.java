package br.com.rdevs.ecommerce.produto.repository;

import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<TbProduto, BigInteger> {
    List<TbProduto> findByNomeFantasiaContaining(String nomeFantasia);
    List<TbProduto> findByNomeFabricante(String nomeFabricante);
    List<TbProduto> findByCategoriaProdutoIdCategoriaProduto(BigInteger idCategoriaProduto);
    List<TbProduto> findByCategoriaProdutoIdCategoriaProdutoAndSubCategoriaProdutoIdSubCategoria(BigInteger idCategoriaProduto, BigInteger idSubCategoria);
    List<TbProduto> findBySubCategoriaProdutoIdSubCategoria(BigInteger idSubCategoria);
    TbProduto findByCdProduto(BigInteger cdProduto);

}
