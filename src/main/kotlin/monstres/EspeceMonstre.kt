package org.example

import java.io.File

class EspeceMonstre (
    var id : Int=0,
    var nom: String,
    var type: String="",
    val baseAttaque: Int=0,
    val baseDefense: Int=0,
    val baseVitesse: Int=0,
    val baseAttaqueSpe: Int=0,
    val baseDefenseSpe: Int=0,
    val basePv: Int=0,
    val modAttaque: Double=0.0,
    val modDefense: Double=0.0,
    val modVitesse: Double=0.0,
    val modAttaqueSpe: Double=0.0,
    val modDefenseSpe: Double=0.0,
    val modPv: Double=0.0,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = "")
{
    fun afficheArt(deFace: Boolean=true): String{
        val nomFichier = if(deFace) "front" else "back";
        val art=  File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }

}