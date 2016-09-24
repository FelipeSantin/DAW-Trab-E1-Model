/**
 * TESTES INICIAIS, PODE OCORRER ERROS DE ID
 * UTILIZAR TESTECOMPLETO
 */

package br.edu.ifsul.teste.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Condominio;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersistirCondominio {
    EntityManager em;
    
    public TestePersistirCondominio() {
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
