package br.com.banco.service;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.exceptions.OperadorNotFoundException;
import br.com.banco.exceptions.ParserDataException;
import br.com.banco.repository.TransferenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<TransferenciaDto> getAllDadosTransferencias() {
        List<TransferenciaEntity> dadosTransferencia = repository.findAll();
        if (dadosTransferencia.isEmpty()) {
            throw new OperadorNotFoundException("Lista de transferencia do operador vazia");
        }
        return dadosTransferencia.stream().map(
                transferencia -> mapper.map(transferencia, TransferenciaDto.class)).collect(Collectors.toList());
    }

    public List<TransferenciaDto> getTransferenciaEntreDatas(String dataInicio, String dataFim) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDate inicio = LocalDate.parse(dataInicio, pattern);
            LocalDate fim = LocalDate.parse(dataFim, pattern);
            List<TransferenciaEntity> byDataTransferenciaBetween = repository.findByDataTransferenciaBetween(inicio, fim);
            return byDataTransferenciaBetween.stream().map(
                            transferenciasPorTempo -> mapper.map(transferenciasPorTempo, TransferenciaDto.class))
                    .collect(Collectors.toList());
        } catch (DateTimeParseException ex) {
            log.error(ex.getMessage());
            throw new ParserDataException();
        }
    }

    public List<TransferenciaDto> getDadosFromNomeOperador(String nameOperador) {
        Optional<List<TransferenciaEntity>> dadosOperadorTransacao = Optional.of(
                repository.findNomeOperadorTransacao(nameOperador));
        if (dadosOperadorTransacao.get().isEmpty()) {
            throw new OperadorNotFoundException("Operador Nao Encontrado");
        }
        return dadosOperadorTransacao.get().stream().map(
                dadosTransferencia -> mapper.map(dadosTransferencia, TransferenciaDto.class)
        ).collect(Collectors.toList());
    }

}
