/**
 * TESTES INICIAIS, PODE OCORRER ERROS DE ID
 * UTILIZAR TESTECOMPLETO
 */

package br.edu.ifsul.teste.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Locatario;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersistirLocatario {
    EntityManager em;
    
    public TestePersistirLocatario() {
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
            Locatario l = new Locatario();
            l.setNome("Joao");
            l.setCpf("717.501.471-65");
            l.setEmail("asd@asd.asd");
            l.setTelefone("12345678");
            l.setRenda(1000.0);
            l.setLocaltrabalho("Centro");
            l.setTelefonetrabalho("87654321");
            
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado(falso) com o que ocorreu
        Assert.assertEquals(false, exception);
        
    }
 
}
