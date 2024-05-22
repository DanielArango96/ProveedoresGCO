package com.example.proveedor.Servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proveedor.modelos.Pais;
import com.example.proveedor.Repositorio.PaisRepositorio;

@Service
public class PaisServicio {

    @Autowired
    private PaisRepositorio paisRepositorio;

    public List<Pais> obtenerTodosLosPaises() {
        return paisRepositorio.findAll();
    }

    public Optional<Pais> obtenerPaisPorId(Long id) {
        return paisRepositorio.findById(id);
    }

    public Pais guardarPais(Pais pais) {
        return paisRepositorio.save(pais);
    }

    public void eliminarPais(Long id) {
        paisRepositorio.deleteById(id);
    }
}
