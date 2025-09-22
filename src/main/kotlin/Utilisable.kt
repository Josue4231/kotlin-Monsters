package org.example

// Interface Utilisable
interface utilisable {
    /**
     * Applique l'effet de l'objet sur un IndividuMonstre.
     * @param cible Le monstre ciblé
     * @return true si l'action a eu un effet, false sinon
     */
    fun utiliser(cible: IndividuMonstre): Boolean
}

// Classe de base pour les objets
open class Item1(val id: Int, val nom: String, val description: String)

// Classe représentant un monstre
class IndividuMonstre(val nom: String, var pv: Int, val pvMax: Int) {
    fun estKO(): Boolean = pv <= 0

    fun soigner(valeur: Int) {
        if (!estKO()) {
            pv = (pv + valeur).coerceAtMost(pvMax)
            println("$nom est soigné de $valeur PV (PV actuels : $pv/$pvMax)")
        } else {
            println("$nom est KO et ne peut pas être soigné.")
        }
    }
}

// Potion - objet utilisable
class Potion(id: Int, nom: String, description: String, val effetSoin: Int) :
    Item1(id, nom, description), utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        return if (!cible.estKO()) {
            cible.soigner(effetSoin)
            true
        } else {
            println("Impossible d'utiliser une potion sur ${cible.nom}, il est KO.")
            false
        }
    }
}

// Kube - objet utilisable
class Kube(id: Int, nom: String, description: String) :
    Item1(id, nom, description), utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        val captureRéussie = (0..100).random() < 50 // 50% de réussite
        if (captureRéussie) {
            println("${cible.nom} a été capturé avec succès !")
        } else {
            println("La tentative de capture de ${cible.nom} a échoué.")
        }
        return captureRéussie
    }
}

// Badge - objet non-utilisable
class Entraineur1(val nom: String)

class Badge(id: Int, nom: String, description: String, val champion: Entraineur1) :
    Item1(id, nom, description)



fun main() {
    val monstre = IndividuMonstre("Pikachu", 40, 100)

    val potion = Potion(1, "Potion de soin", "Rend 30 PV", 30)
    val kube = Kube(2, "Kube standard", "Permet de capturer un monstre")
    val champion = Entraineur1("Ondine")
    val badge = Badge(3, "Badge Cascade", "Gagné contre Ondine", champion)

    println("=== Situation initiale ===")
    println("${monstre.nom} : ${monstre.pv}/${monstre.pvMax} PV\n")

    println("=== Utilisation de la potion ===")
    potion.utiliser(monstre)

    println("\n=== Utilisation du kube ===")
    kube.utiliser(monstre)

    println("\n=== Tentative d’utilisation du badge ===")


    println("\n=== Liste des objets utilisables ===")
    val objets: List<utilisable> = listOf(potion, kube)
    for (objet in objets) {
        objet.utiliser(monstre)
    }
}
