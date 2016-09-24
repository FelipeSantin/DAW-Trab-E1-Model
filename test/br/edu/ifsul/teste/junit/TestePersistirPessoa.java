/**
 * TESTES INICIAIS, PODE OCORRER ERROS DE ID
 * UTILIZAR TESTECOMPLETO
 */

package br.edu.ifsul.teste.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pessoa;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Felipe
 */
public class TestePersistirPessoa {
    EntityManager em;
    
    public TestePersistirPessoa() {
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
            Pessoa p = new Pessoa();
            p.setNome("Joao");
            p.setCpf("717.501.471-65");
            p.setEmail("asd@asd.asd");
            p.setTelefone("12345678");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado(falso) com o que ocorreu
        Assert.assertEquals(false, exception);
        
    }
 
}
