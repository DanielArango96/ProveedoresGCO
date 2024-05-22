import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Swal from 'sweetalert2';

const FormularioProveedor = () => {
  const [formData, setFormData] = useState({
    personeriaJuridica: '',
    nitRut: '',
    razonSocial: '',
    representanteLegal: '',
    telefonoContacto: '',
    emailContacto: '',
    ciudadId: '',
    paisId: '',
    departamentoId: '',
    direccion: '',
    rutFile: null
  });
  const [paises, setPaises] = useState([]);
  const [departamentos, setDepartamentos] = useState([]);
  const [ciudades, setCiudades] = useState([]);

  useEffect(() => {
    // Fetch countries from your backend
    axios.get('/paises')
      .then(response => setPaises(response.data))
      .catch(error => console.error('Error fetching countries:', error));
  }, []);

  useEffect(() => {
    if (formData.paisId) {
      // Fetch departments based on selected country
      axios.get(`/departamentos?paisId=${formData.paisId}`)
        .then(response => setDepartamentos(response.data))
        .catch(error => console.error('Error fetching departments:', error));
    } else {
      setDepartamentos([]);
      setCiudades([]);
    }
  }, [formData.paisId]);

  useEffect(() => {
    if (formData.departamentoId) {
      // Fetch cities based on selected department
      axios.get(`/ciudades?departamentoId=${formData.departamentoId}`)
        .then(response => setCiudades(response.data))
        .catch(error => console.error('Error fetching cities:', error));
    } else {
      setCiudades([]);
    }
  }, [formData.departamentoId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFileChange = (e) => {
    setFormData({ ...formData, rutFile: e.target.files[0] });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formDataToSend = new FormData();
      for (const key in formData) {
        formDataToSend.append(key, formData[key]);
      }

      await axios.post('/proveedores', formDataToSend, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });

      Swal.fire({
        icon: 'success',
        title: '¡Registro exitoso!',
        text: 'Proveedor registrado exitosamente'
      });
    } catch (error) {
      console.error('Error registrando proveedor:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Hubo un error registrando el proveedor. Por favor, intenta nuevamente.'
      });
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center">
      <div className="max-w-4xl mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg bg-opacity-90">
        <h2 className="text-2xl font-semibold mb-4 text-center">GCO Registro de Proveedores</h2>
        <form onSubmit={handleSubmit}>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label className="block mb-2 text-sm font-bold">Personería Jurídica</label>
              <select
                name="personeriaJuridica"
                value={formData.personeriaJuridica}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              >
                <option value="">Seleccione</option>
                <option value="Natural">Persona Natural</option>
                <option value="Juridica">Persona Jurídica</option>
              </select>
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">NIT o RUT</label>
              <input
                type="text"
                name="nitRut"
                value={formData.nitRut}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              />
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Razón Social</label>
              <input
                type="text"
                name="razonSocial"
                value={formData.razonSocial}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              />
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Representante Legal</label>
              <input
                type="text"
                name="representanteLegal"
                value={formData.representanteLegal}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              />
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Teléfono de Contacto</label>
              <input
                type="text"
                name="telefonoContacto"
                value={formData.telefonoContacto}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              />
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Email de Contacto</label>
              <input
                type="email"
                name="emailContacto"
                value={formData.emailContacto}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              />
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">País</label>
              <select
                name="paisId"
                value={formData.paisId}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              >
                <option value="">Seleccione un país</option>
                {Array.isArray(paises) && paises.length > 0 && paises.map((pais) => (
                  <option key={pais.id} value={pais.id}>{pais.nombre}</option>
                ))}
              </select>
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Departamento</label>
              <select
                name="departamentoId"
                value={formData.departamentoId}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              >
                <option value="">Seleccione un departamento</option>
                {Array.isArray(departamentos) && departamentos.length > 0 && departamentos.map((departamento) => (
                  <option key={departamento.id} value={departamento.id}>{departamento.nombre}</option>
                ))}
              </select>
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Ciudad</label>
              <select
                name="ciudadId"
                value={formData.ciudadId}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              >
                <option value="">Seleccione una ciudad</option>
                {Array.isArray(ciudades) && ciudades.length > 0 && ciudades.map((ciudad) => (
                  <option key={ciudad.id} value={ciudad.id}>{ciudad.nombre}</option>
                ))}
              </select>
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Dirección</label>
              <input
                type="text"
                name="direccion"
                value={formData.direccion}
                onChange={handleChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              />
            </div>
            <div>
              <label className="block mb-2 text-sm font-bold">Cargar RUT (PDF)</label>
              <input
                type="file"
                name="rutFile"
                accept=".pdf"
                onChange={handleFileChange}
                className="w-full px-3 py-2 border rounded-lg"
                required
              />
            </div>
          </div>
          <div className="mt-6 text-center">
            <button type="submit" className="px-6 py-2 bg-blue-600 text-white rounded-lg">
              Registrar Proveedor
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default FormularioProveedor;
