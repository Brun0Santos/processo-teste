package br.com.banco.controller;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/transferencia")
public class TransferenciaController {
    @Autowired
    private TransferenciaService service;

    @Autowired
    private TransferenciaRepository repository;

    @GetMapping
    @Operation(summary = "Dados da transferencia", description = "Buscar todos os dados")
    public ResponseEntity<List<TransferenciaDto>> todosDadosTransferencia() {
        var allDadosTransferencias = service.getAllDadosTransferencias();
        return ResponseEntity.ok().body(allDadosTransferencias);
    }

    @GetMapping("/data/{dataInicio}/{dataFim}/{operador}")
    @Operation(summary = "Dados de transferencias por datas", description = "Buscar todos os dados em um periodo de tempo")
    public ResponseEntity<List<TransferenciaDto>> getTransferenciaEntreDatas(@PathVariable("dataInicio") String dataInicio,
                                                                             @PathVariable("dataFim") String dataFim) {
        System.out.println("Acimaaa");
        var transferenciaEntreDatas = service.getTransferenciaEntreDatas(dataInicio, dataFim);
        return ResponseEntity.ok().body(transferenciaEntreDatas);
    }

    @GetMapping("/operador/{nomeOperador}")
    @Operation(summary = "Dados do operador", description = "Buscar dados por um operador especifico")
    public ResponseEntity<List<TransferenciaDto>> getDadosOperador(@PathVariable("nomeOperador") String nomeOperador) {
        List<TransferenciaDto> operadorTransferencias = service.getDadosFromNomeOperador(nomeOperador);
        return ResponseEntity.ok().body(operadorTransferencias);
    }

    @GetMapping("/teste/{nome}")
    public Optional<List<TransferenciaEntity>> umTeste(@PathVariable("nome") String nome, @RequestParam(value = "data", defaultValue = "") String data) {
        System.out.println(nome);
        System.out.println(data.length());
        Optional<List<TransferenciaEntity>> byDataHoraEOperador = repository.findByDataHoraEOperador(
                LocalDate.of(2020, 01, 01), LocalDate.of(2023, 01, 01), "");
        return byDataHoraEOperador;

    }
}