/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */

@Entity
@Table(name = "unidadecondominial")
public class UnidadeCondominial implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_unidcond", sequenceName = "sequence_unidcond_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_unidcond", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Length(max = 20, message = "O número não pode ter mais de {max} caracteres")
    @NotNull(message = "O número não pode ser nulo")
    @NotBlank(message = "O número não pode ser em branco")
    @Column(name = "numero", nullable = false, length = 20)
    private String numero;
    @NotNull(message = "A descricao não pode ser nulo")
    @NotBlank(message = "A descricao não pode ser em branco")
    @Column(name = "descricao", nullable = false, columnDefinition = "text")
    private String descricao;
    @NotNull(message = "A area não pode ser nulo")
    @Column(name = "area", nullable = false)
    private Double area;
    @NotNull(message = "O Nº do quarto não pode ser nulo")
    @Column(name = "numeroquarto", nullable = false, length = 50)     
    private Integer numeroquarto;
    @OneToMany(mappedBy = "unidadeCond", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Aluguel> alugueis = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;
    @ManyToOne
    @JoinColumn(name="condominio_id", referencedColumnName = "id", nullable = false)
    private Condominio condominio;

    public List<Aluguel> getAlugueis() {
        return alugueis;
    }

    public void setAlugueis(List<Aluguel> alugueis) {
        this.alugueis = alugueis;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
    public void adicionarAluguel(Aluguel obj){
        obj.setUnidadeCond(this);
        this.alugueis.add(obj);
    }

    public void removerAluguel(int index){
        this.alugueis.remove(index);
    }

    

    public UnidadeCondominial() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getNumeroquarto() {
        return numeroquarto;
    }

    public void setNumeroquarto(Integer numeroquarto) {
        this.numeroquarto = numeroquarto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final UnidadeCondominial other = (UnidadeCondominial) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
   
}