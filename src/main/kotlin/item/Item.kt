package org.example

import dresseur.Entraineur

open class Item(
    val id: Int,
    val nom: String,
    val description: String
) {
    fun afficherInfo() {
        println("Item: $nom (ID: $id) - $description")
    }

//    class Badge1(id: Int, nom: String, description: String) : Item(id, nom, description) {
        //    }
//    class item.Badge(truc: Int, machin: String, bidule: String): Item(truc,machin,bidule) {
//    }
        class Badge(id: Int, nom: String, description: String, var champion: Entraineur) : Item(id, nom, description) {
        }


    }

    fun main() {
        val badge1 = Item.Badge(1, "item.Badge de Feu", "Obtient après avoir vaincu le champion du type Feu.", champion = Entraineur(10, "Flamina", 15))
        val championFeu = Entraineur(10, "Flamina", 15)
        val badgeFeu =
            Item.Badge(1, "item.Badge de Feu", "Obtenu après avoir vaincu le champion du type Feu.", championFeu)

        println("item.Badge : ${badgeFeu.nom}")
        println("Description : ${badgeFeu.description}")
        println("Champion associé : ${badgeFeu.champion.nom}")
        badge1.afficherInfo()
    }


