package org.example

class Entraineur {
    var id: Int = 0
    var nom: String = ""
    var argent: Int = 0
    constructor(id: Int, nom: String, argent: Int) {
        this.id = id
        this.nom = nom
        this.argent = argent
        var equipeMonstre: MutableList<individuMonstre> = mutableListOf()
        var boiteMonstre: MutableList<individuMonstre> = mutableListOf()

    }

    fun afficheDetail(){
        println("${this.id}")
        println("${this.nom}")
        println("${this.argent}")
    }
}

