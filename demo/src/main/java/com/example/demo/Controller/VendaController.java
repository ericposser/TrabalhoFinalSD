package com.example.demo.Controller;

import ch.qos.logback.core.model.Model;
import com.example.demo.Model.Produto;
import com.example.demo.Model.Venda;
import com.example.demo.Repository.ProdutoRepository;
import com.example.demo.Repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Método para adicionar produtos ao modelo
    @ModelAttribute("produtos")
    public List<Produto> populateProdutos() {
        return produtoRepository.findAll(); // Obtém todos os produtos
    }

    // Método para renderizar a página de criação de vendas
    @GetMapping("/create")
    public String criarVendaForm() {
        return "createVenda"; // Retorna a view createVenda.html
    }

    // Método para criar uma venda
    @PostMapping
    public String criarVenda(@ModelAttribute Venda venda, RedirectAttributes redirectAttributes) {
        // Verifica se o produto existe
        Produto produto = produtoRepository.findById(venda.getProdutoId()).orElse(null);
        if (produto == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Produto não encontrado.");
            return "redirect:/vendas/create"; // Redireciona de volta para o formulário
        }

        // Verifica se a quantidade solicitada é maior que o estoque
        if (produto.getQuantidade() < venda.getQuantidade()) {
            redirectAttributes.addFlashAttribute("mensagem", "Quantidade solicitada excede o estoque disponível.");
            return "redirect:/vendas/create"; // Redireciona de volta para o formulário
        }

        // Atualiza o estoque do produto
        produto.setQuantidade(produto.getQuantidade() - venda.getQuantidade());
        produtoRepository.save(produto); // Salva as alterações no estoque

        // Define a data da venda
        venda.setDataVenda(LocalDate.now());

        // Salva a venda
        vendaRepository.save(venda);

        redirectAttributes.addFlashAttribute("mensagem", "Venda registrada com sucesso!");
        return "redirect:/produtos"; // Redireciona para a listagem de produtos
    }

    @GetMapping
    public ModelAndView listarVendas() {
        List<Venda> vendas = vendaRepository.findAll(); // Obtém todas as vendas
        ModelAndView modelAndView = new ModelAndView("listaVendas"); // Cria um ModelAndView para a view
        List<Produto> produtos = produtoRepository.findAll(); // Obtém todos os produtos

        // Mapeia produtos para um mapa para fácil acesso
        Map<Long, String> produtosMap = produtos.stream()
                .collect(Collectors.toMap(Produto::getId, Produto::getNome)); // Supondo que a classe Produto tenha um método getNome()

        modelAndView.addObject("vendas", vendas);
        modelAndView.addObject("produtosMap", produtosMap); // Adiciona o mapa de produtos ao ModelAndView
        return modelAndView; // Retorna o ModelAndView
    }

}









