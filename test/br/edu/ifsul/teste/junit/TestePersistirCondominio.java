/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.teste.junit;

import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Mensalidade;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersistirCondominio {
        
    EntityManagerFactory emf;
    EntityManager em;
    
    public TestePersistirCondominio() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("DAW-Trab-E1-ModelPU");
        em = emf.createEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
        emf.close();
    }
    
    @Test
    public void teste(){
        boolean exception = false; // variavel que vai armazenar o resultado do teste
        try {
            Condominio c = new Condominio();
            c.setNome("Condominio 1");
            c.setEndereco("Centro");
            c.setNumero("2");
            c.setCep("12093");
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado(falso) com o que ocorreu
        Assert.assertEquals(false, exception);
        
    }
 
}
