/**
 * TESTES INICIAIS, PODE OCORRER ERROS DE ID
 * UTILIZAR TESTECOMPLETO
 */

package br.edu.ifsul.teste.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Locatario;
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
public class TestePersistirAluguel {
    EntityManager em;
    
    public TestePersistirAluguel() {
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
            Locatario l = em.find(Locatario.class, 2);
            UnidadeCondominial uc = em.find(UnidadeCondominial.class, 2);
            
            Aluguel a = new Aluguel();
            a.setInicioContrato(Calendar.getInstance());
            a.setFimContrato(Calendar.getInstance());
            a.setValor(20.0);
            a.setDiaVencimento(1);
            a.setLocatario(l);
            a.setUnidadeCond(uc);
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado(falso) com o que ocorreu
        Assert.assertEquals(false, exception);
        
    }
 
}
