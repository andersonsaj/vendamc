package com.teste.vandamc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.teste.vandamc.domain.Categoria;
import com.teste.vandamc.domain.Produto;
import com.teste.vandamc.repositories.CategoriaRepository;
import com.teste.vandamc.repositories.ProdutoRepository;

@SpringBootApplication
public class VendamcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private ProdutoRepository repoProduto;
	
	public static void main(String[] args) {
		SpringApplication.run(VendamcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategoria().addAll(Arrays.asList(cat1));
		p2.getCategoria().addAll(Arrays.asList(cat1,cat2));
		p3.getCategoria().addAll(Arrays.asList(cat1));
		
		repoCategoria.saveAll(Arrays.asList(cat1, cat2));
		repoProduto.saveAll(Arrays.asList(p1,p2,p3));
		
	}
}
