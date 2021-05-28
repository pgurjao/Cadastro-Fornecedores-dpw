package br.edu.infnet.infra.fornecedores;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import br.edu.infnet.domain.fornecedores.Fornecedor;

@Repository
public class FornecedorRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    public List<Fornecedor> listar() {
        
        return em.createNamedQuery("Fornecedor.findAll").getResultList();
        
    }
    
}
