package com.example.proveedor.Servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proveedor.modelos.Proveedor;
import com.example.proveedor.Repositorio.ProveedorRepositorio;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepositorio.findAll();
    }

    public Optional<Proveedor> obtenerProveedorPorId(Long id) {
        return proveedorRepositorio.findById(id);
    }

    public Proveedor crearProveedor(Proveedor proveedor) {
        return proveedorRepositorio.save(proveedor);
    }

    public void eliminarProveedor(Long id) {
        proveedorRepositorio.deleteById(id);
    }
}
