package com.example.proveedor.Servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.proveedor.modelos.Departamento;
import com.example.proveedor.Repositorio.DepartamentoRepositorio;

@Service
public class DepartamentoServicio {

    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    public List<Departamento> obtenerTodosLosDepartamentos() {
        return departamentoRepositorio.findAll();
    }

    public Optional<Departamento> obtenerDepartamentoPorId(Long id) {
        return departamentoRepositorio.findById(id);
    }

    public Departamento guardarDepartamento(Departamento departamento) {
        return departamentoRepositorio.save(departamento);
    }

    public void eliminarDepartamento(Long id) {
        departamentoRepositorio.deleteById(id);
    }
}
