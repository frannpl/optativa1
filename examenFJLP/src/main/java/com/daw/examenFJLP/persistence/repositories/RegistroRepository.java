package com.daw.examenFJLP.persistence.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.daw.examenFJLP.persistence.entities.Registro;

public interface RegistroRepository extends ListCrudRepository<Registro, Integer> {

    List<Registro> findByUbicacionAndFechaLecturaBetween(
            String ubicacion,
            LocalDate inicio,
            LocalDate fin
    );
}
