package com.daw.examenFJLP.persistence.entities;

import java.time.LocalDate;

import com.daw.examenFJLP.persistence.entities.enums.Unidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registro")
@Getter
@Setter
@NoArgsConstructor
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_lectura")
    private LocalDate fechaLectura;

    private String ubicacion;

    private Double temperatura;

    @Enumerated(EnumType.STRING)
    private Unidad unidad; 
    private Double precipitacion;
}
