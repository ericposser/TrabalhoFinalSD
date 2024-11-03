package com.example.demo.Controller;

import com.example.demo.Model.Produto;
import com.example.demo.Repository.ProdutoRepository;
import com.example.demo.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Transactional // Garante que todas as operações dentro do método estejam em uma transação
    @PostMapping("/delete/{id}")
    public String deleteProduto(@PathVariable Long id, Model model) {
        // Verifica se o produto existe
        Produto produto = produtoRepository.findById(id).orElse(null);
        if (produto == null) {
            model.addAttribute("mensagem", "Produto não encontrado.");
            return listarTodos(model); // Redireciona para a listagem de produtos com a mensagem de erro
        }

        // Exclui todas as vendas associadas ao produto
        vendaRepository.deleteByProdutoId(id);

        // Exclui o produto
        produtoRepository.delete(produto);

        model.addAttribute("mensagem", "Produto e suas vendas associadas foram excluídos com sucesso.");
        return listarTodos(model); // Redireciona para a listagem de produtos com a mensagem de sucesso
    }

    @GetMapping
    public String listarTodos(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos); // Adiciona a lista de produtos ao modelo
        return "produto"; // Retorna o nome da view Thymeleaf
    }

    @GetMapping("/create")
    public String criarProdutoForm(Model model) {
        model.addAttribute("produto", new Produto());
        return "createProduto"; // Retorna a view createProduto.html
    }

    @PostMapping
    public String criarProduto(@ModelAttribute Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    @GetMapping("/edit/{id}")
    public String editarProdutoForm(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id).orElse(null);
        model.addAttribute("produto", produto);
        return "editProduto"; // Retorna a view editProduto.html
    }

    @PostMapping("/edit/{id}")
    public String editarProduto(@PathVariable Long id, @ModelAttribute Produto produto) {
        produto.setId(id); // Define o ID do produto a ser atualizado
        produtoRepository.save(produto);
        return "redirect:/produtos"; // Redireciona para a lista de produtos após a edição
    }
}



