package br.edu.ifsul.teste.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.modelo.Mensalidade;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Recurso;
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
public class TesteCompleto {
    EntityManager em;
    
    public TesteCompleto() {
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
            //Cria a pessoa
            Pessoa p = new Pessoa();
            p.setNome("Pessoa teste");
            p.setCpf("717.501.471-65");
            p.setEmail("asd@asd.asd");
            p.setTelefone("12345678");
            
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            
            p = (Pessoa) em.createQuery("from Pessoa order by id desc").getResultList().get(0);
            
            //Cria o Locatario
            Locatario l = new Locatario();
            l.setNome("Locatario teste");
            l.setCpf("717.501.471-65");
            l.setEmail("asd@asd.asd");
            l.setTelefone("12345678");
            l.setRenda(1000.0);
            l.setLocaltrabalho("Centro");
            l.setTelefonetrabalho("87654321");
            
            em.getTransaction().begin();
            em.persist(l);
            em.getTransaction().commit();
            l = (Locatario) em.createQuery("from Locatario order by id desc").getResultList().get(0);
            
            //Cria o Recurso
            Recurso r = new Recurso();
            r.setDescricao("recurso teste");
            
            em.getTransaction().begin();
            em.persist(r);
            em.getTransaction().commit();
            r = (Recurso) em.createQuery("from Recurso order by id desc").getResultList().get(0);
            
            //Cria o Condominio
            Condominio c = new Condominio();
            c.setNome("Condominio teste");
            c.setEndereco("Centro");
            c.setNumero("2");
            c.setCep("12093");
            
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            c = (Condominio) em.createQuery("from Condominio order by id desc").getResultList().get(0);
            
            //Cria a Unidade Condominial
            UnidadeCondominial uc = new UnidadeCondominial();
            uc.setDescricao("predio");
            uc.setNumero("11");
            uc.setNumeroquarto("15");
            uc.setArea(400.0);
            uc.setPessoa(p);
            uc.setCondominio(c);
            
            em.getTransaction().begin();
            em.persist(uc);
            em.getTransaction().commit();
            uc = (UnidadeCondominial) em.createQuery("from UnidadeCondominial order by id desc").getResultList().get(0);
            
            //Cria o Aluguel
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
            a = (Aluguel) em.createQuery("from Aluguel order by id desc").getResultList().get(0);
            
            //Cria a Mensalidade
            Mensalidade m = new Mensalidade();
            m.setValor(2.0);
            m.setValorPagamento(0.0);
            m.setVencimento(Calendar.getInstance());
            m.setVencimentoPagamento(Calendar.getInstance());
            m.setAluguel(a);
            
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            m = (Mensalidade) em.createQuery("from Mensalidade order by id desc").getResultList().get(0);
            
            
            em.getTransaction().begin();
            
            p.setNome(p.getNome() + "1");
            em.merge(p);
            
            l.setNome(l.getNome() + "1");
            em.merge(l);
            
            r.setDescricao(r.getDescricao()+ "1");
            em.merge(r);
            c.setNome(c.getNome() + "1");
            c.getCond_Rec().add(r);
            em.merge(c);
            
            uc.setDescricao(uc.getDescricao()+ "1");
            em.merge(uc);
            
            a.setDiaVencimento(a.getDiaVencimento() + 2);
            em.merge(a);
            
            m.setValor(m.getValor() * 2);
            em.merge(m);
            
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.remove(m);
            em.remove(a);
            em.remove(uc);
            em.remove(c);
            em.remove(r);
            em.remove(l);
            em.remove(r);
            em.remove(p);
            em.getTransaction().commit();
            
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        // comparo o resultado esperado(falso) com o que ocorreu
        Assert.assertEquals(false, exception);
        
    }
    
}
