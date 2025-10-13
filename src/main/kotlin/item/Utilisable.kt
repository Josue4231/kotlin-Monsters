package org.example

import especeDrako
import kube

// Interface Utilisable
interface utilisable {
    /**
     * Applique l'effet de l'objet sur un IndividuMonstre.
     * @param cible Le monstre ciblé
     * @return true si l'action a eu un effet, false sinon
     */
    fun utiliser(cible: IndividuMonstre): Boolean
}



fun main() {
    val monstre = IndividuMonstre(
        id = 1,
        nom = "Drako",
        espece = especeDrako,
        expInit = 0.0
    )




    println("=== Situation initiale ===")
    println("${monstre.nom} : ${monstre.pv}/${monstre.pvMax} PV\n")



    println("\n=== Utilisation du kube ===")
    kube.utiliser(monstre)

    println("\n=== Tentative d’utilisation du badge ===")


    println("\n=== Liste des objets utilisables ===")
    val objets: List<utilisable> = listOf( kube)
    for (objet in objets) {
        objet.utiliser(monstre)
    }
}
