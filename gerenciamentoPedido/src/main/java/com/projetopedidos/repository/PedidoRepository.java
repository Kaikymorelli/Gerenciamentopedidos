package com.projetopedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetopedidos.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
