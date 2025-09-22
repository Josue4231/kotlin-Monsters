//package org.example
//interface Utilisable {
//    fun utiliser()
//}
//
//
//open class item1 (val id: Int, val nom: String, val description: String)
//
//// Classe Entraineur
//class Entraineur1(val nom: String)
//
//// Classe Badge (pas utilisable)
//class Badge(id: Int, nom: String, description: String, val champion: Entraineur1) : Item(id, nom, description)
//
//// Classe Potion (utilisable)
//class Potion(id: Int, nom: String, description: String, val effet: String) : Item(id, nom, description), Utilisable {
//    override fun utiliser() {
//        println("Vous utilisez une potion : $effet")
//    }
//}
//class Kube(id: Int, nom: String, description: String) : Item(id, nom, description), Utilisable {
//    override fun utiliser() {
//        println("Vous utilisez un kube pour capturer un monstre.")
//    }
//}
//fun main(){
//
//
//        val potion = Potion(1, "Potion de soin", "Rend 20 PV", "Restaure 20 PV")
//        val kube = Kube(2, "Kube standard", "Permet de capturer un monstre.")
//        val champion = Entraineur1("Roxane")
//        val badge = Badge(3, "Badge Roche", "Gagné contre Roxane", champion)
//        // Liste d'objets avec polymorphisme sur l'interface Utilisable
//        val objetsUtilisables: List<Utilisable> = listOf(potion, kube)
//
//        // On peut parcourir les objets utilisables et appeler utiliser()
//        for (objet in objetsUtilisables) {
//            objet.utiliser()
//        }
//
//        // Le badge n’est pas utilisable :
//        // badge.utiliser() // ⚠️ Erreur : la méthode n'existe pas
//    }
