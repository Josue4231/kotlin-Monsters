package org.example
open class Item(
    val id: Int,
    val nom: String,
    val description: String
) {
    fun afficherInfo() {
        println("Item: $nom (ID: $id) - $description")
    }

    class Badge1(id: Int, nom: String, description: String) : Item(id, nom, description) {
        //    }
//    class Badge(truc: Int, machin: String, bidule: String): Item(truc,machin,bidule) {
//    }
        class Badge(id: Int, nom: String, description: String, var champion: Entraineur) : Item(id, nom, description) {
        }


    }

    fun main() {
        val badge1 = Item.Badge1(1, "Badge de Feu", "Obtient après avoir vaincu le champion du type Feu.",)
        val championFeu = Entraineur(10, "Flamina", 15)
        val badgeFeu =
            Badge1.Badge(1, "Badge de Feu", "Obtenu après avoir vaincu le champion du type Feu.", championFeu)

        println("Badge : ${badgeFeu.nom}")
        println("Description : ${badgeFeu.description}")
        println("Champion associé : ${badgeFeu.champion.nom}")
        badge1.afficherInfo()
    }
}

