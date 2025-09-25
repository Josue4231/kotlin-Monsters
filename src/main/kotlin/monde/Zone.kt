package monde

import Joueur
import dresseur.Entraineur
import jeu.CombatMonstre
import org.example.EspeceMonstre
import org.example.IndividuMonstre

class Zone(
    val id: Int,
    val nom: String,
    val expZone: Int,
    val joueur: Joueur,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null,
) {
    fun genereMonstre(): IndividuMonstre {
        val especeChoisie = especesMonstres.random()

        val expMin = expZone * 0.8
        val expMax = expZone * 1.2
        val expAleatoire = (expMin..expMax).randomDouble()

        return IndividuMonstre(
            id = 1,
            nom = especeChoisie.nom,
            espece = especeChoisie,
            expInit = expAleatoire
        ).apply {
            pv = pvMax
        }
    }

    fun rencontreMonstre() {
        val monstreSauvage = genereMonstre()
        val premierMonstreJoueur = joueur.equipeMonstre.firstOrNull { it.pv > 0 }

        if (premierMonstreJoueur == null) {
            println("Aucun monstre du joueur n'a de PV > 0 pour combattre.")
            return
        }

        val combat = CombatMonstre(premierMonstreJoueur, monstreSauvage)
        combat.lanceCombat()
    }
}

private fun ClosedFloatingPointRange<Double>.randomDouble(): Double {
    return start + Math.random() * (endInclusive - start)
}

fun main() {
    val especeFlamkip = EspeceMonstre(
        id = 1,
        nom = "Flamkip",
        baseVitesse = 10,
        basePv = 50,
        baseAttaque = 20,
        description = "Un monstre de feu vif."
    )

    val especeAquamy = EspeceMonstre(
        id = 2,
        nom = "Aquamy",
        baseVitesse = 8,
        basePv = 60,
        baseDefense = 15,
        description = "Un monstre aquatique calme."
    )

    val joueur = Joueur().apply {
        equipeMonstre.add(
            IndividuMonstre(
                id= 5,
                nom = "Flamkip",
                espece = especeFlamkip,
                expInit = 3.0
            ).apply {
                pv = 100
                pvMax = 100
            }
        )
    }

    val zone = Zone(
        id = 1,
        nom = "Forêt Mystique",
        expZone = 100,
        joueur =joueur,
        especesMonstres = mutableListOf(especeFlamkip, especeAquamy),
    )

    val monstreSauvage = zone.genereMonstre()
    println("Monstre sauvage généré : ${monstreSauvage.nom}, Niveau : ${monstreSauvage.niveau}, PV : ${monstreSauvage.pv}")

    // Lancer une rencontre
    zone.rencontreMonstre()
}



