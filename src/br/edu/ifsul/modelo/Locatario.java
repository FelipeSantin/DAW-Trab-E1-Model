/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Felipe
 */

@Entity
@Table(name = "locatario")
public class Locatario extends Pessoa implements Serializable {
    private Double renda;
    @Length(max = 50, message = "O local de trabalho não pode ter mais de {max} caracteres")
    @NotNull(message = "O local de trabalho não pode ser nulo")
    @NotBlank(message = "O local de trabalho não pode ser em branco")
    @Column(name = "localtrabalho", nullable = false, length = 50)
    private String localtrabalho;
    @Length(max = 16, message = "O telefone do trabalho não pode ter mais de {max} caracteres")
    @NotNull(message = "O O telefone do trabalho não pode ser nulo")
    @NotBlank(message = "O O telefone do trabalho não pode ser em branco")
    @Column(name = "telefonetrabalho", nullable = false, length = 16)
    private String telefonetrabalho;
    
    public Locatario() {
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }

    public String getLocaltrabalho() {
        return localtrabalho;
    }

    public void setLocaltrabalho(String localtrabalho) {
        this.localtrabalho = localtrabalho;
    }

    public String getTelefonetrabalho() {
        return telefonetrabalho;
    }

    public void setTelefonetrabalho(String telefonetrabalho) {
        this.telefonetrabalho = telefonetrabalho;
    }

}