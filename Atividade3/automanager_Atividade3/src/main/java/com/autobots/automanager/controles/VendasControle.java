package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.DadosCadastroVenda;
import com.autobots.automanager.dto.DadosVenda;
import com.autobots.automanager.dto.RetornoLisgemVenda;
import com.autobots.automanager.dto.RetornoVenda;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import com.autobots.automanager.repositorios.RepositorioVenda;
import com.autobots.automanager.servicos.RetornoListagemVenda;
import com.autobots.automanager.servicos.VendaCadastrar;
import com.autobots.automanager.servicos.VendaGeraLink;
import com.autobots.automanager.servicos.VendaListar;

@RestController
@RequestMapping("/venda")
public class VendasControle {

	@Autowired
	private RepositorioUsuario repositorio_usuario;
	
	@Autowired
	private RepositorioVeiculo repositori_veiculo;
	
	@Autowired
	private RepositorioVenda repositorio_venda;
	
	@Autowired
	private VendaListar vendaConsulta;
	
	@Autowired
	private VendaCadastrar vendaCadastrar;
	
	@Autowired
	private VendaGeraLink geraLink;
	
	@GetMapping("/{id_venda}")
	public ResponseEntity<RetornoVenda> listarVenda(@PathVariable Long id_venda){
		Optional<Venda> vendaOptional = repositorio_venda.findById(id_venda);
		if(vendaOptional.isPresent()) {
			Venda venda = vendaOptional.get();
			DadosVenda dadosVenda = new DadosVenda(
					id_venda,
					venda.getCliente().getId(),
					venda.getFuncionario().getId(),
					venda.getVeiculo().getId(),
					venda.getCadastro(),
					venda.getMercadorias(),
					venda.getServicos());
			
			
			return ResponseEntity.ok(new RetornoVenda(dadosVenda, geraLink.gerar()));
		}
		return ResponseEntity.badRequest().body(new RetornoVenda(null, geraLink.gerar()));
	}
	
	@GetMapping("/cliente/{id_usuario}")
	public ResponseEntity<RetornoLisgemVenda> listarVendasClientes(@PathVariable Long id_usuario){
		Optional<Usuario> clienteOptional = repositorio_usuario.findById(id_usuario);
		if(clienteOptional.isPresent()) {
			if(clienteOptional.get().getPerfis().contains(PerfilUsuario.CLIENTE)){
				List<Venda> vendas = repositorio_venda.findByCliente(clienteOptional.get());
				RetornoLisgemVenda retorno = new RetornoLisgemVenda(vendaConsulta.converterLista(vendas), geraLink.gerar());
				return ResponseEntity.ok(retorno);
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/funcionario/{id_usuario}")
	public ResponseEntity<RetornoLisgemVenda> listarVendasFuncionario(@PathVariable Long id_usuario){
		Optional<Usuario> clienteOptional = repositorio_usuario.findById(id_usuario);
		if(clienteOptional.isPresent()) {
			if(clienteOptional.get().getPerfis().contains(PerfilUsuario.FUNCIONARIO)) {
				List<Venda> vendas = repositorio_venda.findByFuncionario(clienteOptional.get());
				RetornoLisgemVenda retorno = new RetornoLisgemVenda(vendaConsulta.converterLista(vendas), geraLink.gerar());
				return ResponseEntity.ok(retorno);
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping
	public ResponseEntity<RetornoLisgemVenda> listarTodasVendas(){
		List<DadosVenda> listagem = vendaConsulta.listarTudo();
		return ResponseEntity.ok(new RetornoLisgemVenda(listagem, geraLink.gerar()));
	}
	
	@PostMapping
	public ResponseEntity<List<Link>> cadastrarVenda(@RequestBody DadosCadastroVenda venda) {
		Optional<Usuario> funcionarioOptional = repositorio_usuario.findById(venda.id_funcionario());
		Optional<Usuario> clienteOptional = repositorio_usuario.findById(venda.id_cliente());
		if(funcionarioOptional.isPresent()) {
			if(clienteOptional.isPresent()) {
				if(venda.id_veiculo() != null) {
					Optional<Veiculo> veiculoOptional = repositori_veiculo.findById(venda.id_veiculo());
					if(veiculoOptional.isPresent()) {
						vendaCadastrar.cadastrar(
								venda.mercadoria(),
								venda.servico(),
								funcionarioOptional.get(),
								clienteOptional.get(),
								veiculoOptional.get());
						return ResponseEntity.ok().body(geraLink.gerar());
					}
					return ResponseEntity.badRequest().body(geraLink.gerar());
				}
				vendaCadastrar.cadastrar(
						venda.mercadoria(),
						venda.servico(),
						funcionarioOptional.get(),
						clienteOptional.get(),
						null);
				return ResponseEntity.ok().body(geraLink.gerar());
			}
			return ResponseEntity.badRequest().body(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
	
}
