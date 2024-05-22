package com.example.proveedor.Controladores;

import com.example.proveedor.modelos.Departamento;
import com.example.proveedor.Servicios.DepartamentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoControlador {

    @Autowired
    private DepartamentoServicio departamentoServicio;

    @GetMapping("/")
    public ResponseEntity<List<Departamento>> obtenerTodosLosDepartamentos() {
        List<Departamento> departamentos = departamentoServicio.obtenerTodosLosDepartamentos();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> obtenerDepartamentoPorId(@PathVariable("id") Long id) {
        Optional<Departamento> departamento = departamentoServicio.obtenerDepartamentoPorId(id);
        return departamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Departamento> guardarDepartamento(@RequestBody Departamento departamento) {
        Departamento nuevoDepartamento = departamentoServicio.guardarDepartamento(departamento);
        return new ResponseEntity<>(nuevoDepartamento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDepartamento(@PathVariable("id") Long id) {
        departamentoServicio.eliminarDepartamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
