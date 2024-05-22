package com.example.proveedor.Servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proveedor.modelos.Ciudad;
import com.example.proveedor.Repositorio.CiudadRepositorio;

@Service
public class CiudadServicio {

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    public List<Ciudad> obtenerTodasLasCiudades() {
        return ciudadRepositorio.findAll();
    }

    public Optional<Ciudad> obtenerCiudadPorId(Long id) {
        return ciudadRepositorio.findById(id);
    }

    public Ciudad guardarCiudad(Ciudad ciudad) {
        return ciudadRepositorio.save(ciudad);
    }

    public void eliminarCiudad(Long id) {
        ciudadRepositorio.deleteById(id);
    }
}
