/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.teste.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.UnidadeCondominial;
import java.util.Calendar;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersistirUnidadeCondominial {
    EntityManager em;
    
    public TestePersistirUnidadeCondominial() {
    }
    
    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();    
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test
    public void teste(){
        boolean exception = false; // variavel que vai armazenar o resultado do teste
        try {
            Pessoa p = em.find(Pessoa.class, 1);
            Condominio c = em.find(Condominio.class, 1);
            Locatario l = em.find(Locatario.class, 2);
            
            UnidadeCondominial uc = new UnidadeCondominial();
            uc.setDescricao("predio");
            uc.setNumero("11");
            uc.setNumeroquarto(15);
            uc.setArea(400.0);
            uc.setPessoa(p);
            uc.setCondominio(c);
            Aluguel a = new Aluguel();
            a.setInicioContrato(Calendar.getInstance());
            a.setFimContrato(Calendar.getInstance());
            a.setValor(20.0);
            a.setDiaVencimento(1);
            a.setLocatario(l);
            uc.adicionarAluguel(a);
            em.getTransaction().begin();
            em.persist(uc);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado(falso) com o que ocorreu
        Assert.assertEquals(false, exception);
        
    }
 
}
