package com.example.proveedor.Controladores;

import com.example.proveedor.Helpers.ValidacionCiudad;
import com.example.proveedor.modelos.Ciudad;
import com.example.proveedor.Servicios.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudades")
@CrossOrigin(origins = "http://localhost:3000")
public class CiudadControlador {

    @Autowired
    private CiudadServicio ciudadServicio;

    @GetMapping
    public ResponseEntity<List<Ciudad>> obtenerTodasLasCiudades() {
        List<Ciudad> ciudades = ciudadServicio.obtenerTodasLasCiudades();
        return new ResponseEntity<>(ciudades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> obtenerCiudadPorId(@PathVariable("id") Long id) {
        Optional<Ciudad> ciudad = ciudadServicio.obtenerCiudadPorId(id);
        return ciudad.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Ciudad> guardarCiudad(@RequestBody Ciudad ciudad) {
        try {
            // Validar la ciudad antes de guardarla
            validarCiudad(ciudad);

            // Si la validación es exitosa, intenta guardar la ciudad
            Ciudad nuevaCiudad = ciudadServicio.guardarCiudad(ciudad);
            return new ResponseEntity<>(nuevaCiudad, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Manejar excepciones de validación
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Manejar otras excepciones
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarCiudad(@PathVariable("id") Long id) {
        try {
            ciudadServicio.eliminarCiudad(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para validar la ciudad antes de guardarla
    private void validarCiudad(Ciudad ciudad) {
        ValidacionCiudad.validateNotNull(ciudad, "Ciudad");
        ValidacionCiudad.validateNotNull(ciudad.getNombreCiudad(), "Nombre de la Ciudad");
        ValidacionCiudad.validateNotNull(ciudad.getDepartamento(), "Departamento");
        // Agregar más validaciones según sea necesario
    }
}
