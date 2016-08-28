/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "aluguel")
public class Aluguel implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_aluguel", sequenceName = "sequence_aluguel_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_aluguel", strategy = GenerationType.SEQUENCE)
    private Integer id;
    //@NotNull(message = "O valor não pode ser nulo")
    @Column(name = "valor", nullable = false)
    private Double valor;
    @Temporal(TemporalType.DATE)
    @Column(name = "iniciocontrato", nullable = false)
    private Calendar inicioContrato;
    @Temporal(TemporalType.DATE)
    @Column(name = "fimcontrato", nullable = false)
    private Calendar fimContrato;
    @NotNull(message = "O dia do vencimento não pode ser nulo")
    @Column(name = "diavencimento", nullable = false)
    private Integer diaVencimento;
    @OneToMany(mappedBy = "aluguel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Mensalidade> mensalidades = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="locatario_id", referencedColumnName = "id", nullable = false)
    private Locatario locatario;
    @ManyToOne
    @JoinColumn(name="unidadecond_id", referencedColumnName = "id", nullable = false)
    private UnidadeCondominial unidadeCond;
    
    public void adicionarMensalidade(Mensalidade obj){
        obj.setAluguel(this);
        this.mensalidades.add(obj);
    }

    public void removerMensalidade(int index){
        this.mensalidades.remove(index);
    }

    public Aluguel() {
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

    public Calendar getInicioContrato() {
        return inicioContrato;
    }

    public void setInicioContrato(Calendar inicioContrato) {
        this.inicioContrato = inicioContrato;
    }

    public Calendar getFimContrato() {
        return fimContrato;
    }

    public void setFimContrato(Calendar fimContrato) {
        this.fimContrato = fimContrato;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Aluguel other = (Aluguel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Mensalidade> getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(List<Mensalidade> mensalidades) {
        this.mensalidades = mensalidades;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    public UnidadeCondominial getUnidadeCond() {
        return unidadeCond;
    }

    public void setUnidadeCond(UnidadeCondominial unidadeCond) {
        this.unidadeCond = unidadeCond;
    }
    
}