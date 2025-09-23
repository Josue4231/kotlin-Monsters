package jeu

import org.example.IndividuMonstre
import org.example.Item
import org.example.especeAquamy
import org.example.especeFlamkip
import org.example.joueur
import org.example.utilisable

class CombatMonstre(
    val monstreJoueur: IndividuMonstre,
    val monstreSauvage: IndividuMonstre
) {
    var round: Int = 1
    var monstreCapture: Boolean = false

    /**
     * Vérifie si le joueur a perdu le combat.
     * Condition de défaite :
     * - Le monstre du joueur n'a plus de PV > 0.
     *
     * @return true si le joueur a perdu, sinon false.
     */
    fun gameOver(): Boolean {
        return monstreJoueur.pv <= 0
    }

    /**
     * Indique si le joueur a gagné le combat.
     * Il y a deux façons de gagner :
     * - Capturer le monstre sauvage (monstreCapture == true)
     * - Amener les PV du monstre sauvage à 0
     *
     * Le monstre du joueur gagne de l'expérience seulement dans le deuxième cas.
     *
     * @return true si le joueur a gagné, sinon false.
     */
    fun joueurGagne(): Boolean {
        if (monstreCapture) {
            return true
        }
        if (monstreSauvage.pv <= 0) {
            // Ici tu peux éventuellement ajouter du gain d'expérience
            // monstreJoueur.gagnerExperience(...)
            return true
        }
        return false
    }

    /**
     * Exemple de méthode pour capturer le monstre sauvage
     */
    fun capturerMonstre() {
        monstreCapture = true
    }
    fun actionAdversaire() {
        if (monstreSauvage.pv > 0) {

            val degats = 10
            monstreJoueur.pv -= degats

            if (monstreJoueur.pv < 0) monstreJoueur.pv = 0

            println("${monstreSauvage.nom} attaque ${monstreJoueur.nom} et inflige $degats dégâts !")
            println("${monstreJoueur.nom} a maintenant ${monstreJoueur.pv} PV.")
        } else {
            println("${monstreSauvage.nom} ne peut pas attaquer car il est KO.")
        }
    }
    fun actionJoueur(): Boolean {
        if (gameOver()) return false

        println("Que souhaitez-vous faire ?")
        println("1 : Attaquer")
        println("2 : Utiliser un item")
        println("3 : Changer de monstre")
        print("Entrez votre choix : ")

        val choix = readLine()?.toIntOrNull() ?: 0

        when (choix) {
            1 -> {
                monstreJoueur.attaquer(monstreSauvage)
                println("${monstreJoueur.nom} attaque ${monstreSauvage.nom} !")
            }

            2 -> {
                if (!utiliserItem()) {
                    println("Action échouée, le combat continue.")
                }
            }

            3 -> {
                changerMonstre()
            }

            else -> {
                println("Choix invalide.")
            }
        }

        return !(monstreSauvage.pv <= 0 || gameOver())
    }

    fun utiliserItem(): Boolean {
        if (joueur.SacAItems.isEmpty()) { // corrigé sacItems -> sacAKube
            println("Votre sac est vide !")
            return false
        }

        println("Voici vos items :")
        joueur.SacAItems.forEachIndexed { index, item ->
            println("$index : ${item.nom} - ${item.description}") // item doit avoir nom et description
        }

        print("Entrez l'indice de l'item à utiliser : ")
        val index = readLine()?.toIntOrNull() ?: -1

        if (index in joueur.SacAItems.indices) {
            val item: Item = joueur.SacAItems[index]
            if (item is utilisable) {
                val succes = item.utiliser(monstreSauvage)
                if (succes) {
                    println("Vous avez utilisé ${item.nom} avec succès.")
                    return true
                } else {
                    println("L'utilisation de ${item.nom} a échoué.")
                }
            } else {
                println("Cet item ne peut pas être utilisé.")
            }
        } else {
            println("Choix invalide.")
        }
        return false
    }

    fun changerMonstre() {
        println("Équipe de monstres disponibles :")
        joueur.equipeMonstre.forEachIndexed { index, monstre ->
            println("$index : ${monstre.nom} (PV: ${monstre.pv})")
        }

        print("Entrez l'indice du monstre à envoyer : ")
        val index = readLine()?.toIntOrNull() ?: -1

        if (index in joueur.equipeMonstre.indices) {
            val choixMonstre = joueur.equipeMonstre[index]
            if (choixMonstre.pv > 0) {
                println("${choixMonstre.nom} remplace ${monstreJoueur.nom} !")
//                monstreSauvage = choixMonstre
            } else {
                println("Impossible ! Ce monstre est KO.")
            }
        } else {
            println("Choix invalide.")
        }
    }

}
fun main() {



    // Création des espèces de monstres


    // Création des monstres individuels avec PV (par exemple 100)
    val monstreJoueur = IndividuMonstre(5, "Pika", especeFlamkip, joueur, expInit = 0.0 ).apply {
        pv = 100
    }
    val monstreSauvage = IndividuMonstre(1, "Aqaman", especeAquamy, expInit = 0.0).apply {
        pv = 100
    }
    joueur.equipeMonstre.add(monstreJoueur)
    // Création du combat
    val combat = CombatMonstre(monstreJoueur, monstreSauvage)

    // Affichage état initial
    println("Combat débuté entre ${monstreJoueur.nom} et ${monstreSauvage.nom}")
    println("Le joueur a perdu ? ${combat.gameOver()}")
    println("Le joueur a gagné ? ${combat.joueurGagne()}")

    // Simulons une attaque qui met KO le monstre sauvage
    monstreSauvage.pv = 0
    println("\nAprès avoir mis KO le monstre sauvage:")
    println("Le joueur a perdu ? ${combat.gameOver()}")
    println("Le joueur a gagné ? ${combat.joueurGagne()}")

    // Simulons que le joueur est mis KO
    monstreJoueur.pv = 0
    println("\nAprès que le joueur est KO:")
    println("Le joueur a perdu ? ${combat.gameOver()}")
    println("Le joueur a gagné ? ${combat.joueurGagne()}")

    // Simulons capture du monstre sauvage
    monstreJoueur.pv = 100 // joueur vivant
    monstreSauvage.pv = 50 // monstre sauvage pas KO
    combat.capturerMonstre()
    println("\nAprès capture du monstre sauvage:")
    println("Le joueur a perdu ? ${combat.gameOver()}")
    println("Le joueur a gagné ? ${combat.joueurGagne()}")

    combat.actionAdversaire()

    // Affichage du statut
    println("Le joueur a perdu ? ${combat.gameOver()}")
    println("Le joueur a gagné ? ${combat.joueurGagne()}")

}


