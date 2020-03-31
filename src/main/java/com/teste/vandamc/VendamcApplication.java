package com.teste.vandamc;

import java.text.SimpleDateFormat;
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
import com.teste.vandamc.domain.Pagamento;
import com.teste.vandamc.domain.PagamentoComBoleto;
import com.teste.vandamc.domain.PagamentoComCartao;
import com.teste.vandamc.domain.Pedido;
import com.teste.vandamc.domain.Produto;
import com.teste.vandamc.repositories.CategoriaRepository;
import com.teste.vandamc.repositories.CidadeRepository;
import com.teste.vandamc.repositories.ClienteRepository;
import com.teste.vandamc.repositories.EnderecoRepository;
import com.teste.vandamc.repositories.EstadoRepository;
import com.teste.vandamc.repositories.PagamentoRepository;
import com.teste.vandamc.repositories.PedidoRepository;
import com.teste.vandamc.repositories.ProdutoRepository;
import com.teste.vandamc.repositories.enums.EstadoPagamento;
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
	@Autowired
	private PedidoRepository repoPedido;
	@Autowired
	private PagamentoRepository repoPagamento;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32:25"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35:45"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00:00") , null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		repoPedido.saveAll(Arrays.asList(ped1,ped2));
		repoPagamento.saveAll(Arrays.asList(pagto1, pagto2));
	}
}
