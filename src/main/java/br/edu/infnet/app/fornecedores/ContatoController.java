package br.edu.infnet.app.fornecedores;

import br.edu.infnet.domain.fornecedores.Contato;
import br.edu.infnet.domain.fornecedores.Fornecedor;
import br.edu.infnet.infra.fornecedores.ContatoRepository;
import br.edu.infnet.infra.fornecedores.FornecedorRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContatoController {

    @Autowired
    private ContatoRepository cr;

    @Autowired
    private FornecedorRepository fr;

    @RequestMapping("/contatos/listar")
    public ModelAndView listarContatos() {

        ModelAndView retorno = new ModelAndView("contatos/index");
        List<Contato> lista = cr.listar();

//        Contato contato = new Contato();
        if (lista != null && !lista.isEmpty()) {
            retorno.addObject("contatos", lista);
//            contato = lista.get(0);
            System.out.println("[ContatoController] lista.size = " + lista.size());
            for (Contato c : lista) {
                System.out.println("[ContatoController] Contato: " + c.toText());
            }
//            lista.forEach( (temp) -> { System.out.println(temp); });  // OUTRA FORMA DE PRINTAR CONTEUDO DA LISTA USANDO 'FOREACH'
        } else {
            retorno.addObject("mensagem", "Nao existem contatos para serem listados");
            System.out.println("[ContatoController] lista eh null ou esta vazia (sem contatos na DB)");
        }
        // - - - - - - - - - - - - - - - - - - - - - - - - - 
        List<Fornecedor> fornecedores = fr.listar();

        if (fornecedores != null && !fornecedores.isEmpty()) {
            retorno.addObject("fornecedores", fornecedores);
        }

        return retorno;
    }

    @RequestMapping("/contatos/excluir")
    public ModelAndView excluirContato(int id) {

        ModelAndView retorno = new ModelAndView("contatos/index");

//        Contato contato = new Contato();
//
//        contato = cr.listarPorId(id);
//
//        System.out.println("[ContatoController] ID passado por parametro : " + id);
//        System.out.println("[ContatoController] ID retornado pelo repository : " + contato.toText());
//
//        contato.setId(id);
        cr.excluir(id);

        return retorno;
    }

    @RequestMapping("/contatos/inserir")
    public ModelAndView inserirContato(Contato contato, Integer fornecedorId) {

        ModelAndView retorno = new ModelAndView("contatos/index");

        if (contato.getId() == null) { // FAZENDO INSERT DE NOVO REGISTRO DE CONTATO
            System.out.println("[ContatoController] ContatoID == null, inserindo novo registro de contato");
            try {
                Fornecedor fornecedor = fr.listarPorId(fornecedorId);
                ArrayList<Contato> contatos = new ArrayList<>();

                contatos.add(contato);
                fornecedor.setContatos(contatos);
                contato.setFornecedor(fornecedor);
                cr.inserir(contato);
            } catch (ConstraintViolationException e) {
//            retorno.addObject("mensagem", e.getMessage());
                System.out.println("[ContatoController] ConstraintViolationException ao inserir novo contato");
                System.out.println("[ContatoController] Exception.getmessage = " + e.getMessage());

                // TRANSFORMA O SET DE CONSTRAINTVIOLATION EM ARRAYLIST
                Set<ConstraintViolation<?>> camposInvalidos = e.getConstraintViolations();

                Set<String> listaErrosValidacao = new HashSet<>(camposInvalidos.size());
                listaErrosValidacao.addAll(camposInvalidos.stream()
                        .map(constraintViolation -> String.format("%s invalido(a). %s",
                        constraintViolation.getPropertyPath(),
                        constraintViolation.getMessage()))
                        .collect(Collectors.toList()));

//            List<String> listaErrosValidacao = new ArrayList<>();
//            listaErrosValidacao.addAll(messages);
                retorno.addObject("erros", listaErrosValidacao);

            } catch (PersistenceException e) {
                if (e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                    SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e.getCause().getCause();
                    System.out.println("[ContatoController] SQLIntegrityConstraintViolationException ao inserir");
                    System.out.println("[ContatoController] Exception.getmessage = " + sql_violation_exception.getMessage());
                    retorno.addObject("erros", sql_violation_exception.getMessage());
                }
            }
        } else { // FAZENDO UPDATE DE REGISTRO DE CONTATO JA EXISTENTE
            System.out.println("[ContatoController] ContatoID != null, salvando edicao de registro ja existente");

            try {
                cr.editar(contato);
            } catch (DataIntegrityViolationException e) {
                if (e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println("[ContatoController] DataIntegrityViolationException ao salvar alteracao em registro de contato existente");
                    String causa = e.getCause().getCause().toString();
                    System.out.println("[ContatoController] ConstraintViolationException " + causa);

                    if (causa.contains("contatos.nome_UNIQUE")) {
                        retorno.addObject("erros", "O nome digitado ja pertence a outro contato");
                    } else if (causa.contains("contatos.email_UNIQUE")) {
                        retorno.addObject("erros", "O email digitado ja pertence a outro contato");
                    } else if (causa.contains("contatos.fone_UNIQUE")) {
                        retorno.addObject("erros", "O telefone digitado ja pertence a outro contato");
                    } else {
                        retorno.addObject("erros", "ConstraintViolationException ao salvar alteracao em registro de contato existente");
                    }

                } else {
                    System.out.println("[ContatoController] DataIntegrityViolationException desconhecida ao salvar alteracao em registro de contato existente");
                    retorno.addObject("erros", "DataIntegrityViolationException desconhecida ao salvar alteracao em registro de contato existente");
                }
            } catch (TransactionSystemException e) {
//            retorno.addObject("mensagem", e.getMessage());
                System.out.println("[ContatoController] TransactionSystemException ao editar contato existente");
//                System.out.println("[ContatoController] Exception.getmessage = " + e.getMessage());
//                System.out.println("[ContatoController] Exception.getCause = " + e.getCause());
                if (e.getCause() != null && e.getCause().getCause() instanceof ConstraintViolationException) {
                    System.out.println("[ContatoController] Dentro do IF");
                    String causa = e.getCause().getCause().toString();
                    System.out.println("[ContatoController] Exception.getCause.getCause = " + causa);

                    if (causa.contains("propertyPath=nome")) {
                        retorno.addObject("erros", "O campo nome deve ter entre 1 e 50 caracteres");
                    } else if (causa.contains("propertyPath=email")) {
                        retorno.addObject("erros", "O campo email deve ter entre 1 e 50 caracteres");
                    } else if (causa.contains("propertyPath=fone")) {
                        retorno.addObject("erros", "O campo telefone deve ter entre 1 e 20 algarismos");
                    } else {
                        retorno.addObject("erros", "TransactionSystemException ao salvar alteracao em registro de contato existente: Alguma informacao editada do contato eh invalida (Nome, Email ou Telefone)");
                    }
                }

            }
        }
        return retorno;
    }

    @RequestMapping("/contatos/editar")
    public ModelAndView editarContato(int id) {

        ModelAndView retorno = new ModelAndView("contatos/index");
        Contato contato = cr.listarPorId(id);
        System.out.println("[ContatoController] editar contato: " + contato.toText());
        retorno.addObject("contato", contato);
        return retorno;
    }

}
