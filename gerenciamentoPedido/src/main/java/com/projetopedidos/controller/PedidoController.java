package com.projetopedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetopedidos.entities.Pedido;
import com.projetopedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "pedido", description = "API REST DE GERECIAMENTO DE CLIENTES")
@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	private final PedidoService pedidoService;

	@Autowired
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	@GetMapping("/{id}")
	@Operation(summary = "Localiza um produto por ID")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
		Pedido Pedido = pedidoService.getPedidoById(id);
		if (Pedido != null) {
			return ResponseEntity.ok(Pedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	@Operation(summary = "Apresenta todos os produto")
	public ResponseEntity<List<Pedido>> getAllLivro() {
		List<Pedido> Pedido = pedidoService.getAllPedido();
		return ResponseEntity.ok(Pedido);
	}

	@PostMapping("/")
	@Operation(summary = "Cadastra um produto")
	public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid Pedido pedido) {
		Pedido criarPedido = pedidoService.salvarPedido(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarPedido);
	}


	@PutMapping("/{id}")
	@Operation(summary = "Alterar um produto")
	public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
		Pedido updatedPedido = pedidoService.updatePedido(id, pedido);
		if (updatedPedido != null) {
			return ResponseEntity.ok(updatedPedido);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta um livro")
	public ResponseEntity<String> deletePedido(@PathVariable Long id) {
		boolean deleted = pedidoService.deletePedido(id);
		if (deleted) {
			return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
