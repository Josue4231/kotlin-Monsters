package org.example
open class Item(
    val id: Int,
    val nom: String,
    val description: String
) {
    fun afficherInfo() {
        println("Item: $nom (ID: $id) - $description")
    }
    class Badge(id: Int, nom: String, description: String) : Item(id, nom, description) {
    }

}
fun main() {
    val badge1 = Item.Badge(1, "Badge de Feu", "Obtient après avoir vaincu le champion du type Feu.")

    badge1.afficherInfo()
}

