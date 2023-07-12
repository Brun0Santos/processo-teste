package br.com.banco.service;

import br.com.banco.entity.ContaEntity;
import br.com.banco.exceptions.IdContaNotFoundException;
import br.com.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    private ContaRepository repository;

    public ContaEntity getDadosTransferencia(Integer id) {

        var dadosTransferencia = repository.findById(id).orElseThrow(IdContaNotFoundException::new);


//        dadosTransferencia.getTransferencia().forEach(t -> {
//            contaTransferenciaDto.setId(t.getId());
//            contaTransferenciaDto.setIdConta(id);
//            contaTransferenciaDto.setDataTransferencia(t.getDataTransferencia());
//            contaTransferenciaDto.setValor(t.getValor());
//            contaTransferenciaDto.setTipo(t.getTipo());
//            contaTransferenciaDto.setNomeOperadorTransacao(t.getNomeOperadorTransacao());
//            contaTransferenciaDto.setContaId(t.getContaId());
//
//        });


        return dadosTransferencia;
    }

    public List<ContaEntity> getAllDadosTransferencia() {
        List<ContaEntity> allTransferencias = repository.findAll();
        return allTransferencias;
    }
}
