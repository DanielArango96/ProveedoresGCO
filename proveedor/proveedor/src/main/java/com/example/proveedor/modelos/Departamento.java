package com.example.proveedor.modelos;

import com.example.proveedor.Helpers.ValidacionDepartamento;
import jakarta.persistence.*;

@Entity
@Table(name = "departamentos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddepartamentos")
    private Long id;

    @Column(name = "nombredepartamento")
    private String nombreDepartamento;

    @ManyToOne
    @JoinColumn(name = "idpais")
    private Pais pais;

    // Constructores, Getters y Setters

    // Getters y setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        ValidacionDepartamento.validarNotNull(id, "ID");
        this.id = id;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        ValidacionDepartamento.validarNotNull(nombreDepartamento, "Nombre del Departamento");
        ValidacionDepartamento.validateNoNumbers(nombreDepartamento, "Nombre del Departamento");
        this.nombreDepartamento = nombreDepartamento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        ValidacionDepartamento.validarNotNull(pais, "Pais");
        this.pais = pais;
    }
}
