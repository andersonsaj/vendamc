package com.teste.vandamc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.teste.vandamc.domain.Categoria;
import com.teste.vandamc.domain.Cidade;
import com.teste.vandamc.domain.Cliente;
import com.teste.vandamc.domain.Endereco;
import com.teste.vandamc.domain.Estado;
import com.teste.vandamc.domain.Produto;
import com.teste.vandamc.repositories.CategoriaRepository;
import com.teste.vandamc.repositories.CidadeRepository;
import com.teste.vandamc.repositories.ClienteRepository;
import com.teste.vandamc.repositories.EnderecoRepository;
import com.teste.vandamc.repositories.EstadoRepository;
import com.teste.vandamc.repositories.ProdutoRepository;
import com.teste.vandamc.repositories.enums.TipoCliente;

@SpringBootApplication
public class VendamcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private ProdutoRepository repoProduto;
	
	@Autowired
	private CidadeRepository repoCidade;
	@Autowired
	private EstadoRepository repoEstado;
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private EnderecoRepository repoEndereco;
	
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
		
		Estado est1 = new Estado(null, "MinasGerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1= new Cidade(null, "Uberlândia", est1);
		Cidade c2= new Cidade(null, "São Paulo", est2);
		Cidade c3= new Cidade(null, "Campinas", est2);
		
		repoCategoria.saveAll(Arrays.asList(cat1, cat2));
		repoProduto.saveAll(Arrays.asList(p1,p2,p3));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		repoEstado.saveAll(Arrays.asList(est1, est2));
		repoCidade.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "616748481", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("167999498", "94949494"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 300", "Jardim", "26494949", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "34894949", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		repoCliente.saveAll(Arrays.asList(cli1));
		repoEndereco.saveAll(Arrays.asList(e1,e2));
		
		
		
	}
}
