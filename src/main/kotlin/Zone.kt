package org.example

class Zone (
    val id: Int,
    val nom: String,
    val expZone: Int,
    val especesMonstres: MutableList<String> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
)
