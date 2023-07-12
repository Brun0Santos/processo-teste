package br.com.banco.controller;

import br.com.banco.entity.ContaEntity;
import br.com.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContaController {
    @Autowired
    ContaService service;

    @GetMapping(value = "/conta", params = "contaid")
    public ResponseEntity<ContaEntity> getDadosTransferenciaFromIdConta(@RequestParam("contaid") Integer contaid) {
        var dadosTransferencia = service.getDadosTransferencia(contaid);
        return ResponseEntity.ok().body(dadosTransferencia);
    }

    @GetMapping("/conta")
    public ResponseEntity<List<ContaEntity>> dadosTransferencia() {
        List<ContaEntity> allDadosTransferencia = service.getAllDadosTransferencia();
        return ResponseEntity.ok().body(allDadosTransferencia);
    }
}
