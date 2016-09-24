/**
 * TESTES INICIAIS, PODE OCORRER ERROS DE ID
 * UTILIZAR TESTECOMPLETO
 */

package br.edu.ifsul.teste.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Recurso;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersisirCondRec {
    EntityManager em;
    
    public TestePersisirCondRec() {
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
            Condominio c = em.find(Condominio.class, 1);
            
            Recurso r = em.find(Recurso.class, 1);
            
            c.getCond_Rec().add(r);
            
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
