package jeu

import org.example.IndividuMonstre
import org.example.Item
import especeAquamy
import especeFlamkip
import joueur
import org.example.utilisable
data class espece(
    val nom: String,
    val asciiFace: String,
    val asciiDos: String,
    val pvMaxBase: Int
)
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
            monstreSauvage.attaquer(monstreJoueur)
            println("${monstreSauvage.nom} attaque ${monstreJoueur.nom} !")
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

    fun afficheCombat() {
        println("===================================")
        println(" Début du round $round")
        println("===================================")
        // Monstre sauvage (en haut)
        println("Monstre sauvage : ${monstreSauvage.nom}")
        println("Niveau : ${monstreSauvage.niveau}")
        println("PV : ${monstreSauvage.pv} / ${monstreSauvage.pvMax}")
//        println(monstreSauvage.espece.asciiFace)
//        println(monstreSauvage.espece.asciiDace)

        println()
        //Monstre Joeuer
        println(" Monstre Joueur : ${monstreJoueur.nom}")
        println("Niveau : ${monstreJoueur.niveau}")
        println("PV : ${monstreJoueur.pv} / ${monstreJoueur.pvMax}")
    }
    fun jouer() {
        if (monstreJoueur.vitesse >= monstreSauvage.vitesse) {
            // Le monstre du joueur agit en premier
            if (!this.actionJoueur()) {
                return  // Si actionJoueur() retourne false, on arrête le tour
            }
            // Si combat toujours en cours, le monstre sauvage agit
            if (!monstreCapture && monstreSauvage.pv > 0) {
                this.actionAdversaire()
            }
        } else {
            // Le monstre sauvage agit en premier
            if (monstreSauvage.pv > 0) {
                this.actionAdversaire()
            }
            // Si le joueur n'est pas KO, il agit ensuite
            if (!this.gameOver()) {
                if (!this.actionJoueur()) {
                    return
                }
            }
        }
    }
    fun lanceCombat() {
        while (!gameOver() && !joueurGagne()) {
            this.jouer()
            println("======== Fin du Round : $round ========")
            round++
        }
        if (gameOver()) {
            joueur.equipeMonstre.forEach { it.pv = it.pvMax }
            println("Game Over !")
        }
    }

}




        fun main() {
            // Assure-toi que especeFlamkip, especeAquamy et joueur sont initialisés

//            val monstreJoueur = IndividuMonstre(5, "Pika", especeFlamkip, joueur, expInit = 0.0).apply {
//                pv = 100
//                pvMax = 100
//            }
//
//            val monstreSauvage = IndividuMonstre(1, "Aqaman", especeAquamy, expInit = 0.0).apply {
//                pv = 100
//                pvMax = 100
//            }
//
//            joueur.equipeMonstre.add(monstreJoueur)
//
//            val combat = CombatMonstre(monstreJoueur, monstreSauvage)
//
//            println("Combat débuté entre ${monstreJoueur.nom} et ${monstreSauvage.nom}")
//
//            // Tests simulés...
//
//            // Remettre les PV à fond pour combat réel
//            monstreJoueur.pv = 100
//            monstreSauvage.pv = 100
//
//            while (true) {
//                combat.afficheCombat()
//
//                if (!combat.actionJoueur()) break
//                if (combat.joueurGagne()) break
//
//                combat.actionAdversaire()
//
//                if (combat.gameOver()) break
//
//                combat.round++
//            }
//
//            println("\nFin du combat !")
//            if (combat.joueurGagne()) println("Le joueur a gagné !")
//            else if (combat.gameOver()) println("Le joueur a perdu !")
//            else println("Combat interrompu.")
//        }
//            val monstreJoueur = IndividuMonstre(5, "Pika", especeFlamkip, joueur, expInit = 0.0).apply {
//                pv = 100
//                pvMax = 100
//                vitesse = 30
//            }
//
//            val monstreSauvage = IndividuMonstre(1, "Aqaman", especeAquamy, expInit = 0.0).apply {
//                pv = 100
//                pvMax = 100
//                vitesse = 25
//            }
//
//            joueur.equipeMonstre.add(monstreJoueur)
//
//            val combat = CombatMonstre(monstreJoueur, monstreSauvage)
//
//            println("Début du combat entre ${monstreJoueur.nom} et ${monstreSauvage.nom}")
//
//            // Boucle principale du combat utilisant jouer()
//            while (true) {
//                combat.jouer()  // Fait jouer les deux monstres
//
//                if (combat.gameOver()) {
//                    println("Le joueur a perdu le combat.")
//                    break
//                }
//
//                if (combat.joueurGagne()) {
//                    println("Le joueur a gagné le combat !")
//                    break
//                }
//
//                combat.round++
//            }
//            combat.lanceCombat()
//
//            println("Fin du combat.")


                // Initialisation des monstres et du joueur (à adapter selon ton code)
                val monstreJoueur = IndividuMonstre(5, "Pika", especeFlamkip, joueur, expInit = 0.0).apply {
                    pv = 100
                    pvMax = 100
                    vitesse = 30
                }

                val monstreSauvage = IndividuMonstre(1, "Aqaman", especeAquamy, expInit = 0.0).apply {
                    pv = 100
                    pvMax = 100
                    vitesse = 25
                }

                joueur.equipeMonstre.add(monstreJoueur)
                val combat = CombatMonstre(monstreJoueur, monstreSauvage)
                println("Début du combat entre ${monstreJoueur.nom} et ${monstreSauvage.nom}")

            // Boucle principale du combat utilisant jouer()
            while (true) {
                combat.jouer()  // Fait jouer les deux monstres

                if (combat.gameOver()) {
                    println("Le joueur a perdu le combat.")
                    break
                }

                if (combat.joueurGagne()) {
                    println("Le joueur a gagné le combat !")
                    break
                }

                combat.round++
            }

                println("Combat débuté entre ${monstreJoueur.nom} et ${monstreSauvage.nom}")

                // Lance le combat avec la méthode dédiée
                combat.lanceCombat()

                println("Fin du combat.")
            }








