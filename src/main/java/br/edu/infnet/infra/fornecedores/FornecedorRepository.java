package br.edu.infnet.infra.fornecedores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.edu.infnet.domain.fornecedores.Fornecedor;
import javax.validation.ConstraintViolationException;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@EnableTransactionManagement
@Repository
public class FornecedorRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<Fornecedor> listar() {

        return em.createNamedQuery("Fornecedor.findAll").getResultList();

    }

    @Transactional
    public Fornecedor listarPorId(int id) {

//        List<Fornecedor> lista = null;
        Fornecedor fornecedor = new Fornecedor();

        fornecedor = (Fornecedor) em.createNamedQuery("Fornecedor.findById").setParameter("id", id).getResultList().get(0);

        System.out.println("[FornecedorRepository] fornecedor: " + fornecedor.toText());

        if (fornecedor == null) {
            return null;
        }

//        return em.createNamedQuery("Fornecedor.findById").setParameter("id", id).getResultList();
        return fornecedor;

    }

    @Transactional
    public void excluir(Fornecedor fornecedor) {

        em.remove(this.listarPorId(fornecedor.getId() ) );

    }
    @Transactional
    public void inserir(Fornecedor fornecedor) throws ConstraintViolationException {

            em.persist(fornecedor);
            
    }
}
