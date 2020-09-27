package br.com.rdevs.ecommerce.documentoFiscal.repository;

import br.com.rdevs.ecommerce.documentoFiscal.model.entity.TbDocumentoFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface DocumentoFiscalRepository extends JpaRepository<TbDocumentoFiscal, BigInteger> {

    List<TbDocumentoFiscal> findByTbClienteIdCliente(BigInteger idCliente);
    TbDocumentoFiscal findByIdDocumentoFiscal(BigInteger idDocumentoFiscal);

}
