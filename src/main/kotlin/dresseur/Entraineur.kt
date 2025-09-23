package dresseur

import org.example.IndividuMonstre
import org.example.Item

class Entraineur(
    var id: Int,
    var nom: String,
    var argent: Int
) {

    var equipeMonstre: MutableList<IndividuMonstre> = mutableListOf()
    var boiteMonstre: MutableList<IndividuMonstre> = mutableListOf()
    var SacAItems: MutableList<Item> = mutableListOf()


    fun afficheDetail() {
        println("${this.id}")
        println("${this.nom}")
        println("${this.argent}")
    }
}