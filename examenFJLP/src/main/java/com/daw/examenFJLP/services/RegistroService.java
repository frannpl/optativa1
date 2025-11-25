package com.daw.examenFJLP.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.examenFJLP.persistence.entities.Registro;
import com.daw.examenFJLP.persistence.repositories.RegistroRepository;
import com.daw.examenFJLP.services.exceptions.RegistroException;
import com.daw.examenFJLP.services.exceptions.RegistroNotFoundException;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> findAll() {
        return this.registroRepository.findAll();
    }

    public Registro findById(int id) {
        if (!this.registroRepository.existsById(id)) {
            throw new RegistroNotFoundException("El registro no existe.");
        }
        return this.registroRepository.findById(id).get();
    }

    public Registro update(int id, Registro registro) {
        if (!this.registroRepository.existsById(id)) {
            throw new RegistroNotFoundException("No existe el registro");
        }

        Registro registroBD = this.findById(id);

        if (registro.getFechaLectura() != null) {
            throw new RegistroException("No se puede modificar la fecha de lectura");
        }

        if (registro.getPrecipitacion() != null) {
            throw new RegistroException("No se puede modificar la precipitación");
        }

        registroBD.setUbicacion(registro.getUbicacion());
        registroBD.setTemperatura(registro.getTemperatura());
        registroBD.setUnidad(registro.getUnidad());

        return this.registroRepository.save(registroBD);
    }

    public Registro modificarPrecipitacion(int id, double anterior, double nueva) {
        if (!this.registroRepository.existsById(id)) {
            throw new RegistroNotFoundException("Registro no encontrado");
        }

        if (anterior == nueva) {
            throw new RegistroException("La precipitación anterior y nueva son iguales");
        }

        Registro r = this.findById(id);

        if (r.getPrecipitacion() != anterior) {
            throw new RegistroException("La precipitación anterior no coincide con la BBDD");
        }

        r.setPrecipitacion(nueva);
        return this.registroRepository.save(r);
    }

    public List<Registro> buscar(String ubicacion, LocalDate inicio, LocalDate fin) {
        return this.registroRepository.findByUbicacionAndFechaLecturaBetween(
            ubicacion, inicio, fin
        );
    }
}
