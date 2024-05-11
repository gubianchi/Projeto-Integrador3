package com.example.projetointegrador3.registros

data class Aula(
    var horaFim: String = "",
    var horaInicio: String = "",
    var data: String = "",
    var local: String = "",
    var nomeDisciplina: String = "",
    var pontoBatido: Boolean = false
)
