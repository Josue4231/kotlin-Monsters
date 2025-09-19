package org.example

import kotlin.random.Random
import kotlin.math.round

class individuMonstre(
    val id: Int,
    val nom: String,
    val espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit: Double
) {
    var experience: Double = 0.0
        set(value) {
            field = value
            // Tant que l'expérience dépasse le palier du niveau suivant, on levelUp
            while (field >= palierExp(niveau + 1)) {
                levelUp()
            }
        }
    fun palierExp(niveau: Int): Double {
        return 100 * Math.pow((niveau - 1).toDouble(), 2.0)
    }


    var niveau: Int = 1

    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 3)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 3)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 3)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 3)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 3)

    var pvMax: Int = espece.basePv + Random.nextInt(-5, 6)
    var potentiel: Int = Random.nextInt(0, 3) // potentiel positif (0,1,2)

    var pv: Int = pvMax
        set(value) {
            field = when {
                value < 0 -> 0
                value > pvMax -> pvMax
                else -> value
            }
        }

    init {
        this.experience = expInit // applique le setter, déclenche potentiellement levelUp()
    }

    fun levelUp() {
        niveau += 1

        val modCaracteristique = 2.0  // tu peux ajuster cette valeur ou la passer en paramètre

        fun calcCarac(): Int {
            val base = round(modCaracteristique * potentiel).toInt()
            val alea = Random.nextInt(-2, 3)
            return base + alea
        }

        fun calcPvMax(): Int {
            val base = round(modCaracteristique * potentiel).toInt()
            val alea = Random.nextInt(-5, 6)
            return base + alea
        }

        attaque += calcCarac()
        defense += calcCarac()
        vitesse += calcCarac()
        attaqueSpe += calcCarac()
        defenseSpe += calcCarac()

        val pvMaxGagne = calcPvMax()
        pvMax += pvMaxGagne
        pv += pvMaxGagne

        // Clamp pour éviter les négatifs
        attaque = maxOf(attaque, 0)
        defense = maxOf(defense, 0)
        vitesse = maxOf(vitesse, 0)
        attaqueSpe = maxOf(attaqueSpe, 0)
        defenseSpe = maxOf(defenseSpe, 0)
        pvMax = maxOf(pvMax, 1)
        pv = pv.coerceIn(0, pvMax)

        println("Level up ! Nouveau niveau : $niveau")
    }

}
fun main() {
    val espece = EspeceMonstre(1,nom="Aquamy",type="Meteo",10,11,9,14,14,55,9.0,10.0,
        7.5,12.0,12.0,27.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
        "Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")

    val monstre = individuMonstre(
        id = 1,
        nom = "Drako",
        espece = espece,
        expInit = 0.0
    )

    println("Niveau initial: ${monstre.niveau}, Exp initiale: ${monstre.experience}")

    // Donne beaucoup d'expérience pour tester le level up multiple
    monstre.experience += 500.0

    println("Niveau après gain d'expérience: ${monstre.niveau}")
    println("Stats après level up: ATQ ${monstre.attaque}, PV ${monstre.pv}/${monstre.pvMax}")
}






