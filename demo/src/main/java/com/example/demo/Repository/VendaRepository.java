package com.example.demo.Repository;

import com.example.demo.Model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    void deleteByProdutoId(Long produtoId);
}
