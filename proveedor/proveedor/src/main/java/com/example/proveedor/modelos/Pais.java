package com.example.proveedor.modelos;

import com.example.proveedor.Helpers.ValidacionPais;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Pais() {}

    public Pais(Long id, String name) {
        this.id = id;
        setName(name); // Usar el setter para aplicar validaciones
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        ValidacionPais.validateNotNull(id, "ID");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidacionPais.validateNotNull(name, "Name");
        ValidacionPais.validateNoNumbers(name, "Name");
        this.name = name;
    }
}
