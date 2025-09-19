package org.example

class corps {
     val niveau: Int=0
    val attaque:Int=0
    val defence:Int=0
    val vitesse:Int=0
    val attaqueSpe:Int=0
    val defenseSpe:Int=0
    val pvMax:Int=0
    val potentiel: Double=0.0
    val exp: Int=0
    /**
     *  @property pv  Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = when {
                nouveauPv < 0 -> 0
                nouveauPv > pvMax -> pvMax
                else -> nouveauPv
            }
        }
    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */


}