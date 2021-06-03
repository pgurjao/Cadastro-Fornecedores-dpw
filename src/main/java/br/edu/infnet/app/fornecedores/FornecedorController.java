package br.edu.infnet.app.fornecedores;

import br.edu.infnet.domain.fornecedores.Fornecedor;
import br.edu.infnet.infra.fornecedores.FornecedorRepository;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FornecedorController {

    @Autowired
    private FornecedorRepository fr;

    @RequestMapping("/fornecedores/listar")
    public ModelAndView listarFornecedores() {

        ModelAndView retorno = new ModelAndView("fornecedores/index");
        List<Fornecedor> lista = fr.listar();

//        Fornecedor fornecedor = new Fornecedor();
        if (lista != null && !lista.isEmpty()) {
            retorno.addObject("fornecedores", lista);
//            fornecedor = lista.get(0);
            System.out.println("[FornecedorController] lista.size = " + lista.size());
            for (Fornecedor f : lista) {
                System.out.println("[FornecedorController] Fornecedor: " + f.toText());
            }
//            lista.forEach( (temp) -> { System.out.println(temp); });  // OUTRA FORMA DE PRINTAR CONTEUDO DA LISTA USANDO 'FOREACH'
        } else {
            retorno.addObject("mensagem", "Nao existem fornecedores para serem listados");
            System.out.println("[FornecedorController] lista eh null ou esta vazia (sem fornecedores na DB)");
        }

        return retorno;
    }

    @RequestMapping("/fornecedores/excluir")
    public ModelAndView excluirFornecedor(int id) {

        ModelAndView retorno = new ModelAndView("fornecedores/index");

        Fornecedor fornecedor = new Fornecedor();

        fornecedor = fr.listarPorId(id);

        System.out.println("[fornecedorController] ID passado por parametro : " + id);
        System.out.println("[fornecedorController] ID retornado pelo repository : " + fornecedor.toText());

        fornecedor.setId(id);

        fr.excluir(fornecedor);

        return retorno;
    }

    @RequestMapping("/fornecedores/inserir")
    public ModelAndView inserirFornecedor(Fornecedor fornecedor) {

        ModelAndView retorno = new ModelAndView("fornecedores/index");

        try {
            fr.inserir(fornecedor);
        } catch (ConstraintViolationException e) {
            retorno.addObject("mensagem", e.getMessage());
            System.out.println("[fornecedorController] ConstraintViolationException ao inserir");
            System.out.println("[fornecedorController] getmessage = " + e.getMessage());
            System.out.println("[fornecedorController] getcause = " + e.getCause());
            System.out.println("[fornecedorController] getlocalized message = " + e.getLocalizedMessage());
        }

        return retorno;
    }

}
