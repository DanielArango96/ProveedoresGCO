package com.example.proveedor.modelos;

import com.example.proveedor.Helpers.ValidacionProveedor;
import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproveedores")
    private Long id;

    @Column(name = "personeriajuridica")
    private String personeriaJuridica;

    @Column(name = "nitrut")
    private String nitRut;

    @Column(name = "razonsocial")
    private String razonSocial;

    @Column(name = "representantelegal")
    private String representanteLegal;

    @Column(name = "telefonocontacto")
    private String telefonoContacto;

    @Column(name = "emailcontacto")
    private String emailContacto;

    @ManyToOne
    @JoinColumn(name = "idciudad")
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "iddepartamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "idpais")
    private Pais pais;

    @Column(name = "direccion")
    private String direccion;

    public Proveedor() {}

    public Proveedor(Long id, String personeriaJuridica, String nitRut, String razonSocial,
                     String representanteLegal, String telefonoContacto, String emailContacto,
                     Ciudad ciudad, Departamento departamento, Pais pais, String direccion) {
        this.id = id;
        setPersoneriaJuridica(personeriaJuridica);
        setNitRut(nitRut);
        setRazonSocial(razonSocial);
        setRepresentanteLegal(representanteLegal);
        setTelefonoContacto(telefonoContacto);
        setEmailContacto(emailContacto);
        setCiudad(ciudad);
        setDepartamento(departamento);
        setPais(pais);
        setDireccion(direccion);
    }

    // Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersoneriaJuridica() {
        return personeriaJuridica;
    }

    public void setPersoneriaJuridica(String personeriaJuridica) {
        this.personeriaJuridica = personeriaJuridica;
    }

    public String getNitRut() {
        return nitRut;
    }

    public void setNitRut(String nitRut) {
        this.nitRut = nitRut;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
