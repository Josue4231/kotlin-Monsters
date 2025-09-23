package monde

import org.example.EspeceMonstre

class Zone(
    val id: Int,
    val nom: String,
    val expZone: Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    // TODO: faire la méthode genereMonstre()
    fun genereMonstre() {
        // À implémenter
    }

    // TODO: faire la méthode rencontreMonstre()
    fun rencontreMonstre() {
        // À implémenter
    }
}