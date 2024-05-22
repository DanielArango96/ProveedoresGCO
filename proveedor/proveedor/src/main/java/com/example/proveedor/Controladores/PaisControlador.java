package com.example.proveedor.Controladores;

import com.example.proveedor.modelos.Pais;
import com.example.proveedor.Servicios.PaisServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paises")
public class PaisControlador {

    @Autowired
    private PaisServicio paisServicio;

    @GetMapping("/")
    public ResponseEntity<List<Pais>> obtenerTodosLosPaises() {
        List<Pais> paises = paisServicio.obtenerTodosLosPaises();
        return new ResponseEntity<>(paises, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> obtenerPaisPorId(@PathVariable("id") Long id) {
        Optional<Pais> pais = paisServicio.obtenerPaisPorId(id);
        return pais.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<Pais> guardarPais(@RequestBody Pais pais) {
        Pais nuevoPais = paisServicio.guardarPais(pais);
        return new ResponseEntity<>(nuevoPais, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPais(@PathVariable("id") Long id) {
        paisServicio.eliminarPais(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
