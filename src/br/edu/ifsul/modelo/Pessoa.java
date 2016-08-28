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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Felipe
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pessoa")
public class Pessoa implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "sequence_pessoa_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @CPF(message = "O CPF deve ser válido")
    @Length(max = 14, message = "O CPF não pode ter mais de {max} caracteres")
    @NotNull(message = "O CPF não pode ser nulo")
    @NotBlank(message = "O CPF não pode ser em branco")
    @Column(name = "cpf", nullable = false, length = 14)
    private String cpf;
    @Column(name = "telefone", nullable = false, length = 50)
    private String telefone;
    @Email(message = "O email deve ser válido")
    @Length(max = 50, message = "O email não pode ter mais de {max} caracteres")
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ser em branco")
    @Column(name = "email", nullable = false, length = 50)     
    private String email;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UnidadeCondominial> unidadeCond = new ArrayList<>();
    
    public void adicionarUnidadeCond(UnidadeCondominial obj){
        obj.setPessoa(this);
        this.unidadeCond.add(obj);
    }

    public void removerUnidadeCond(int index){
        this.unidadeCond.remove(index);
    }


    public Pessoa() {
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<UnidadeCondominial> getUnidadeCond() {
        return unidadeCond;
    }

    public void setUnidadeCond(List<UnidadeCondominial> unidadeCond) {
        this.unidadeCond = unidadeCond;
    }
    
   
}