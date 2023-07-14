package br.com.banco.repository;

import br.com.banco.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.dataTransferencia BETWEEN :dataInicio AND :dataFim")
    List<TransferenciaEntity> findByTransferenciaEntreDatas(@Param("dataInicio") LocalDate dataInicio,
                                                            @Param("dataFim") LocalDate dataFim);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.nomeOperadorTransacao = :nomeOperador")
    List<TransferenciaEntity> findNomeOperadorTransacao(@Param("nomeOperador") String nomeOperador);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.conta.id = :contaIdd")
    Optional<List<TransferenciaEntity>> findContaBancaria(@Param("contaIdd") Integer contaIdd);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.dataTransferencia BETWEEN :dataInicio AND :dataFim AND t.nomeOperadorTransacao = :nomeOperador")
    List<TransferenciaEntity> findPorTodosFiltros(@Param("dataInicio") LocalDate dataInicio,
                                                  @Param("dataFim") LocalDate dataFim,
                                                  @Param("nomeOperador") String nomeOperador);
}
