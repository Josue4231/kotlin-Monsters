package org.example

fun main() {
    println(changeCouleur("Hello","rouge"))
    println(changeCouleur("World","bleu"))
    println("Hello ${changeCouleur("my","jaune")} World")
    println(changeCouleur("Truc","marron"))
    println(changeCouleur("Pokemon","magenta"))
    println(changeCouleur("Pikachu","jaune"))
    println("pokemon ce nomme ${changeCouleur("Pikachu", "vert")} incroyable")
    println(changeCouleur("Pikachu","marron"))
    println(changeCouleur("Pikachu","noir"))
    println(changeCouleur("dragon de feu ","rose"))

}
fun changeCouleur(message: String, couleur:String=""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"            // Pour les couleur chatgpt
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron" -> "\u001B[38;5;94m"
        "rose" -> "\u001B[38;5;213m"

        else -> " impossible " // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}
