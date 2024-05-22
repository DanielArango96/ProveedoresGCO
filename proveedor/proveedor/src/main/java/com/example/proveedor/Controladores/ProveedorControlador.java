package com.example.proveedor.Controladores;

import com.example.proveedor.modelos.Proveedor;
import com.example.proveedor.modelos.Ciudad;
import com.example.proveedor.modelos.Departamento;
import com.example.proveedor.modelos.Pais;
import com.example.proveedor.Servicios.ProveedorServicio;
import com.example.proveedor.Repositorio.CiudadRepositorio;
import com.example.proveedor.Repositorio.DepartamentoRepositorio;
import com.example.proveedor.Repositorio.PaisRepositorio;
import com.example.proveedor.Helpers.ValidacionProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "http://localhost:3000")
public class ProveedorControlador {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @Autowired
    private CiudadRepositorio ciudadRepositorio;

    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    @Autowired
    private PaisRepositorio paisRepositorio;

    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = proveedorServicio.obtenerTodosLosProveedores();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable("id") Long id) {
        Optional<Proveedor> proveedor = proveedorServicio.obtenerProveedorPorId(id);
        return proveedor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            // Validar el proveedor antes de guardarlo
            validarProveedor(proveedor);

            // Validar que las entidades relacionadas existen
            if (!ciudadRepositorio.existsById(proveedor.getCiudad().getId())) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            if (!departamentoRepositorio.existsById(proveedor.getDepartamento().getId())) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            if (!paisRepositorio.existsById(proveedor.getPais().getId())) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            // Si la validación es exitosa, intenta crear el proveedor
            Proveedor nuevoProveedor = proveedorServicio.crearProveedor(proveedor);
            return new ResponseEntity<>(nuevoProveedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Manejar excepciones de validación
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Manejar otras excepciones
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarProveedor(@PathVariable("id") Long id) {
        try {
            proveedorServicio.eliminarProveedor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para validar el proveedor antes de guardarlo
    private void validarProveedor(Proveedor proveedor) {
        ValidacionProveedor.validateNotNull(proveedor, "Proveedor");
        ValidacionProveedor.validateNotNull(proveedor.getPersoneriaJuridica(), "PersoneriaJuridica");
        // Agregar más validaciones según sea necesario
    }
}
