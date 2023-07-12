package br.com.banco.entity;

import br.com.banco.enums.Tipo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transferencia")
@Getter
@NoArgsConstructor
public class TransferenciaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_transferencia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataTransferencia;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @Column(name = "conta_id")
    private Integer contaId;

    public TransferenciaEntity(Integer id, LocalDate dataTransferencia, Double valor, Tipo tipo, String nomeOperadorTransacao, Integer contaId) {
        this.id = id;
        this.dataTransferencia = dataTransferencia;
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.contaId = contaId;
    }

    @Override
    public String toString() {
        return "TransferenciaEntity{" +
                "id=" + id +
                ", dataTransferencia=" + dataTransferencia +
                ", valor=" + valor +
                ", tipo=" + tipo +
                ", nomeOperadorTransacao='" + nomeOperadorTransacao + '\'' +
                ", contaId=" + contaId +
                '}';
    }
}