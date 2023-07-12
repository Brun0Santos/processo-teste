package br.com.banco.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conta")
@Getter
@NoArgsConstructor
public class ContaEntity {

    @Column(name = "id_conta")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConta;

    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "conta_id")
    private List<TransferenciaEntity> transferencia;

    public ContaEntity(String nomeResponsavel, List<TransferenciaEntity> transferencia) {
        this.nomeResponsavel = nomeResponsavel;
        this.transferencia = transferencia;
    }

    @Override
    public String toString() {
        return "ContaEntity{" +
                "idConta=" + idConta +
                ", nomeResponsavel='" + nomeResponsavel + '\'' +
                ", transferencia=" + transferencia +
                '}';
    }
}
