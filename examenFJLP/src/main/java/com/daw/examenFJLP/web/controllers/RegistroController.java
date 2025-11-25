package com.daw.examenFJLP.web.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.daw.examenFJLP.services.exceptions.RegistroException;
import com.daw.examenFJLP.services.exceptions.RegistroNotFoundException;
import com.daw.examenFJLP.persistence.entities.Registro;
import com.daw.examenFJLP.services.RegistroService;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping
    public ResponseEntity<List<Registro>> list() {
        return ResponseEntity.ok(this.registroService.findAll());
    }

    @PutMapping("/{idRegistro}")
    public ResponseEntity<?> update(
            @PathVariable int idRegistro,
            @RequestBody Registro registro
    ) {
        try {
            return ResponseEntity.ok(this.registroService.update(idRegistro, registro));
        } catch (RegistroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RegistroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idRegistro}/precipitacion")
    public ResponseEntity<?> modificarPrecipitacion(
            @PathVariable int idRegistro,
            @RequestParam double anterior,
            @RequestParam double nueva
    ) {
        try {
            return ResponseEntity.ok(this.registroService.modificarPrecipitacion(idRegistro, anterior, nueva));
        } catch (RegistroNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (RegistroException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Registro>> buscar(
            @RequestParam String ubicacion,
            @RequestParam String inicio,
            @RequestParam String fin
    ) {
        LocalDate fechaInicio = LocalDate.parse(inicio);
        LocalDate fechaFin = LocalDate.parse(fin);

        return ResponseEntity.ok(this.registroService.buscar(ubicacion, fechaInicio, fechaFin));
    }
}
