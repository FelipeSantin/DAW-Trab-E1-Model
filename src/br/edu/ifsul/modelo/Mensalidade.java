/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Felipe
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "mensalidade")
public class Mensalidade implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_Mensalidade", sequenceName = "seq_Mensalidade_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_Mensalidade", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O Valor não pode ser nulo")
    @Column(name = "valor", nullable = false)
    private Double valor;
    @NotNull(message = "A Data não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "vencimento", nullable = false)
    private Calendar vencimento;
    @NotNull(message = "O Valor Pagamento não pode ser nulo")
    @Column(name = "valorPagamento", nullable = false)
    private Double valorPagamento;
    @NotNull(message = "O Vencimento Pagamento não pode ser nulo")
    @Temporal(TemporalType.DATE)
    @Column(name = "vencimentoPagamento", nullable = false)
    private Calendar vencimentoPagamento;
    @NotNull(message = "O Aluguel não pode ser nulo")
    @ManyToOne
    @JoinColumn(name="aluguel_id", referencedColumnName = "id", nullable = false)
    private Aluguel aluguel;
    
    public Mensalidade() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Calendar getVencimentoPagamento() {
        return vencimentoPagamento;
    }

    public void setVencimentoPagamento(Calendar vencimentoPagamento) {
        this.vencimentoPagamento = vencimentoPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensalidade other = (Mensalidade) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

}
