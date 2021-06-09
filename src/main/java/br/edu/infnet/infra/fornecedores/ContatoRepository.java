package br.edu.infnet.infra.fornecedores;

import br.edu.infnet.domain.fornecedores.Contato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.edu.infnet.domain.fornecedores.Fornecedor;
//import java.sql.SQLIntegrityConstraintViolationException;
import javax.validation.ConstraintViolationException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement
@Repository
public class ContatoRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<Contato> listar() {

        return em.createNamedQuery("Contato.findAll").getResultList();

    }

    @Transactional
    public Contato listarPorId(int id) {

//        List<Fornecedor> lista = null;
        Contato contato = new Contato();

//        fornecedor = (Fornecedor) em.createNamedQuery("Fornecedor.findById").setParameter("id", id).getResultList().get(0); // JEITO TOSCO QUE CONSEGUI RETORNAR
        contato = (Contato) em.createNamedQuery("Contato.findById").setParameter("id", id).getSingleResult(); // JEITO UTILIZADO PELO PROFESSOR

        if (contato == null) {
            return null;
        }
        System.out.println("[FornecedorRepository] Contato: " + contato.toText());

        return contato;

    }

    @Transactional
    public void excluir(int id) {

        em.remove(this.listarPorId(id));

    }

    @Transactional
    public void inserir(Contato contato) throws ConstraintViolationException {

            em.persist(contato);

    }
    
    @Transactional
    public void editar(Contato contato) throws ConstraintViolationException {

            em.merge(contato);

    }
    
}
