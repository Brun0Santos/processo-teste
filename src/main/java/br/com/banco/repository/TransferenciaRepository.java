package br.com.banco.repository;

import br.com.banco.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.dataTransferencia LIKE CONCAT('%',:dataTransferencia,'%')")
    List<TransferenciaEntity> findDataTransferencia(@Param("dataTransferencia") String dataTransferencia);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.dataTransferencia BETWEEN :dataInicio AND :dataFim")
    List<TransferenciaEntity> findByDataTransferenciaBetween(@Param("dataInicio") LocalDate dataInicio,
                                                             @Param("dataFim") LocalDate dataFim);

    //@Query("SELECT t FROM TransferenciaEntity t WHERE t.nomeOperadorTransacao = :nomeOperador")
    @Query(nativeQuery = true, value = "SELECT t.*, c.* "
    + "FROM transferencia t "
    + "INNER JOIN conta c "
    + "ON t.CONTA_ID=c.ID_CONTA "
    + "WHERE  NOME_OPERADOR_TRANSACAO = :nomeOperador"
    )
    //List<TransferenciaEntity> findNomeOperadorTransacao(@Param("nomeOperador") String nomeOperador);
    List<TransferenciaEntity> findNomeOperadorTransacao(@Param("nomeOperador") String nomeOperador);
}
