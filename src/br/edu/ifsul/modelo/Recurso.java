/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "recurso")
public class Recurso implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_Recurso", sequenceName = "seq_Recurso_id",
            allocationSize = 1)
    @GeneratedValue(generator = "seq_Recurso", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Length(max = 40, message = "A descricao não pode ter mais de {max} caracteres")
    @NotNull(message = "A descricao não pode ser nulo")
    @NotBlank(message = "A descricao não pode ser em branco")
    @Column(name = "descricao", length = 40, nullable = false)
    private String descricao;

    public Recurso() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Recurso other = (Recurso) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
