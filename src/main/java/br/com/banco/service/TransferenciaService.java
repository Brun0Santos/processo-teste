package br.com.banco.service;

import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.exceptions.OperadorNotFoundException;
import br.com.banco.exceptions.ParserDataException;
import br.com.banco.repository.TransferenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository repository;

    public List<TransferenciaEntity> getAllDadosTransferencias() {
        List<TransferenciaEntity> dadosTransferencia = repository.findAll();
        if (dadosTransferencia.isEmpty()) {
            ResponseEntity.noContent();
        }
        return repository.findAll();
    }

    public List<TransferenciaEntity> getTransferenciaEntreDatas(String dataInicio, String dataFim) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate inicio = LocalDate.parse(dataInicio, pattern);
            LocalDate fim = LocalDate.parse(dataFim, pattern);
            return repository.findByDataTransferenciaBetween(inicio, fim);
        } catch (DateTimeParseException ex) {
            log.error(ex.getMessage());
            throw new ParserDataException();
        }
    }

    public List<TransferenciaEntity> getDadosFromNomeOperador(String nameOperador) {
        Optional<List<TransferenciaEntity>> dadosOperadorTransacao = Optional.of(
                repository.findNomeOperadorTransacao(nameOperador));
        if (dadosOperadorTransacao.get().isEmpty()) {
            throw new OperadorNotFoundException("Operador Nao Encontrado");
        }
        return dadosOperadorTransacao.get();
    }
}
