package com.example.projetointegrador3

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Aula(
    val dia: LocalDate,
    val hora: LocalTime,
    val diaEHora: LocalDateTime
)
