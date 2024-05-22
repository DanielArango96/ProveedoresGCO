package com.example.proveedor.Repositorio;

import com.example.proveedor.modelos.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepositorio extends JpaRepository<Ciudad, Long> {}
