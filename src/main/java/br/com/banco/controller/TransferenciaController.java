package br.com.banco.controller;

import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transferencia")
public class TransferenciaController {
    @Autowired
    private TransferenciaService service;

    @GetMapping
    public ResponseEntity<List<TransferenciaEntity>> todosDadosTransferencia() {
        var allDadosTransferencias = service.getAllDadosTransferencias();
        return ResponseEntity.ok().body(allDadosTransferencias);
    }

    @GetMapping("/data/{dataInicio}/{dataFim}")
    public ResponseEntity<List<TransferenciaEntity>> getTransferenciaEntreDatas(@PathVariable("dataInicio") String dataInicio,
                                                                                @PathVariable("dataFim") String dataFim) {
        var transferenciaEntreDatas = service.getTransferenciaEntreDatas(dataInicio, dataFim);
        return ResponseEntity.ok().body(transferenciaEntreDatas);
    }

    @GetMapping("/operador/{nomeOperador}")
    public ResponseEntity<List<TransferenciaEntity>> getDadosOperador(@PathVariable("nomeOperador") String nomeOperador) {
        List<TransferenciaEntity> operadorTransferencias = service.getDadosFromNomeOperador(nomeOperador);
        return ResponseEntity.ok().body(operadorTransferencias);
    }
}