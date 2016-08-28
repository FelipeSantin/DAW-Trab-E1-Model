/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    @OneToMany(mappedBy = "locatario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Aluguel> alugueis = new ArrayList<>();
    
    public void adicionarAlugueis(Aluguel obj){
        obj.setLocatario(this);
        this.alugueis.add(obj);
    }

    public void removerAlugueis(int index){
        this.alugueis.remove(index);
    }

    
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