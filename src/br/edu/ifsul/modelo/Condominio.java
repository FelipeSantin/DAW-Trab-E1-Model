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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "condominio")
public class Condominio implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_Condominio", sequenceName = "seq_Condominio_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_Condominio", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @Length(max = 50, message = "O endereço não pode ter mais de {max} caracteres")
    @NotNull(message = "O endereço não pode ser nulo")
    @NotBlank(message = "O endereço não pode ser em branco")
    @Column(name = "endereco", nullable = false, length = 50)    
    private String endereco;
    @Length(max = 30, message = "O numero não pode ter mais de {max} caracteres")
    @NotNull(message = "O numero não pode ser nulo")
    @NotBlank(message = "O numero não pode ser em branco")
    @Column(name = "numero", nullable = false, length = 30)     
    private String numero;
    @Length(max = 9, message = "O cep não pode ter mais de {max} caracteres")
    @NotNull(message = "O cep não pode ser nulo")
    @NotBlank(message = "O cep não pode ser em branco")
    @Column(name = "cep", nullable = false, length = 9)         
    private String cep;
    @ManyToMany
    @JoinTable(name = "condrec",
               joinColumns = 
                    @JoinColumn(name = "condominio", referencedColumnName = "id", nullable = false),
               inverseJoinColumns = 
                    @JoinColumn(name = "recurso", referencedColumnName = "id", nullable = false))
    private List<Recurso> Cond_Rec = new ArrayList<>();
    @OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UnidadeCondominial> unidadesCond = new ArrayList<>();

    public void adicionarUnidadesCond(UnidadeCondominial obj){
        obj.setCondominio(this);
        this.unidadesCond.add(obj);
    }

    public void removerUnidadesCond(int index){
        this.unidadesCond.remove(index);
    }
    public Condominio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Condominio other = (Condominio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Recurso> getCond_Rec() {
        return Cond_Rec;
    }

    public void setCond_Rec(List<Recurso> Cond_Rec) {
        this.Cond_Rec = Cond_Rec;
    }

    public List<UnidadeCondominial> getUnidadesCond() {
        return unidadesCond;
    }

    public void setUnidadesCond(List<UnidadeCondominial> unidadesCond) {
        this.unidadesCond = unidadesCond;
    }
    
    
}
