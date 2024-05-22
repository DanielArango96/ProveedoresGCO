package com.example.proveedor.modelos;

import com.example.proveedor.Helpers.ValidacionCiudad;
import jakarta.persistence.*;

@Entity
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idciudades")
    private Long id;

    @Column(name = "nombreciudad")
    private String nombreCiudad;

    @ManyToOne
    @JoinColumn(name = "iddepartamento", nullable = false)
    private Departamento departamento;

    public Ciudad() {}

    public Ciudad(Long id, String nombreCiudad, Departamento departamento) {
        this.id = id;
        setNombreCiudad(nombreCiudad);
        setDepartamento(departamento);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        ValidacionCiudad.validateNotNull(id, "ID");
        this.id = id;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        ValidacionCiudad.validateNotNull(nombreCiudad, "Nombre de la Ciudad");
        ValidacionCiudad.validateNoNumbers(nombreCiudad, "Nombre de la Ciudad");
        this.nombreCiudad = nombreCiudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        ValidacionCiudad.validateNotNull(departamento, "Departamento");
        this.departamento = departamento;
    }
}
