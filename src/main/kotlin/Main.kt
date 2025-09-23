package org.example

import dresseur.Entraineur
import item.MonsterKube
import monde.Zone
import org.example.Item.Badge
// Entraineurs
var joueur = Entraineur(1, "Sacha", 100)
var rival = Entraineur(2, "Regis", 200)
//Espeeces
var especeAquamy = EspeceMonstre(1,nom="Aquamy",type="Meteo",10,11,9,14,14,55,9.0,10.0,
7.5,12.0,12.0,27.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    "Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")
var especeFlamkip = EspeceMonstre(4,nom="Flamkip",type="Animal",12,8,13,16,7,50,10.0,5.5,
    9.5,9.5,6.5,22.0,"Petit animal entouré de flammes, déteste le froid.",
    "Sa flamme change d’intensité selon son énergie.","Impulsif, joueur, loyal")
var especeSpringleaf = EspeceMonstre (1,"Springleaf","Graine",9,
    11, 10,12,13,60,
    6.5,9.0,8.0,7.0,10.0,
    34.0,"Petit monstre espiègle rond comme une graine, adore le soleil.",
    "Sa feuille sur la tête indique son humeur.","Curieux, amical, timide"
)
val especeDrako = EspeceMonstre(1,nom="Aquamy",type="Dragon",10,11,9,14,14,55,9.0,10.0,
    7.5,12.0,12.0,27.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
    "Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")

//Zones
val zonemonstre= Zone(10, "Zone de monstres", 10, mutableListOf(especeAquamy, especeFlamkip, especeSpringleaf))
val route1= Zone(11, "Route", 10)
val route2= Zone(12, "autoroute", 10)

//items
val kube = MonsterKube(2, "Kube standard", "Permet de capturer un monstre",30.0)

val badge = Badge(3, "item.Badge Cascade", "Gagné contre Ondine", joueur)


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

    println(" Dresseur : ${joueur.nom}")
    println(" Argent : ${joueur.nom}")
    println(" Dresseur : ${rival.nom}")
    println(" Argent : ${rival.argent}")
    joueur.argent+=50
    println("Argent: ${joueur.argent}")
    joueur.afficheDetail()
    rival.afficheDetail()
     println(especeAquamy.afficheArt())
    println(especeFlamkip.afficheArt())
    println(especeSpringleaf.afficheArt())
    println("${zonemonstre.nom} : ${zonemonstre.zoneSuivante?.nom}")
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1
    val monstre1 = IndividuMonstre(1,"springleaf",especeSpringleaf,joueur,0.0)
    val monstre2 = IndividuMonstre(2, "flamkip",  especeFlamkip,joueur,0.0)
    val monstre3 = IndividuMonstre(3, "aquamy", especeAquamy,joueur,0.0)
     println("Monstre 1 : ${monstre1.nom} : ${monstre1.espece.nom} : ${monstre1.experience} :${monstre1.entraineur?.nom}")
    println("Monstre 2 : ${monstre2.nom} : ${monstre2.espece.nom}: ${monstre2.experience} : ${monstre2.entraineur?.nom}" )
     println("Monstre 3 : ${monstre3.nom} : ${monstre3.espece.nom}: ${monstre3.experience} : ${monstre3.entraineur?.nom}")
    monstre1.attaquer(monstre2)
    monstre2.attaquer(monstre1)
    monstre1.attaquer(monstre3)
    monstre3.attaquer(monstre1)
    monstre1.renommer()
    monstre2.renommer()
    monstre3.renommer()
    monstre1.afficheDetail()
    monstre2.afficheDetail()
    monstre3.afficheDetail()
//    val badge1 = Item.item.Badge(1, "item.Badge de Feu", "Obtient après avoir vaincu le champion du type Feu.")

    val badge1 = Item.Badge(1, "item.Badge de Feu", "Obtient après avoir vaincu le champion du type Feu.", joueur)
    val championFeu = Entraineur(10, "Flamina", 15)
    val badgeFeu =
        Badge(1, "item.Badge de Feu", "Obtenu après avoir vaincu le champion du type Feu.", championFeu)

    println("item.Badge : ${badgeFeu.nom}")
    println("Description : ${badgeFeu.description}")
    println("Champion associé : ${badgeFeu.champion.nom}")
    badge1.afficherInfo()
    badge1.afficherInfo()
    val kube = MonsterKube(1, "Kube standard", "Permet de capturer un monstre.",30.0)

    val v1 = MonsterKube(1, "Kube de monstre", "Un kube de monstre", 0.5)
    v1.utiliser(monstre1)






    val badge = Badge(3, "item.Badge Cascade", "Gagné contre Ondine", rival)

    println("=== Situation initiale ===")
    println("${monstre1.nom} : ${monstre1.pv}/${monstre1.pvMax} PV\n")



    println("\n=== Utilisation du kube ===")
    kube.utiliser(monstre1)

    println("\n=== Tentative d’utilisation du badge ===")


    println("\n=== Liste des objets utilisables ===")
    val objets: List<utilisable> = listOf( kube)
    for (objet in objets) {
        objet.utiliser(monstre1)
    }

    val v3 = MonsterKube(1, "Kube de monstre", "Un kube de monstre", 0.5)
    v1.utiliser(monstre2)
    val kubeFeu = MonsterKube(
        id = 1,
        nom = "MonsterKube Feu",
        description = "Peut capturer un monstre sauvage",
        chanceCapture = 25.0
    )

    joueur.SacAItems.add(kubeFeu)














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
        "rose" -> "\u001B[38;5;213m"     // rose clair

        else -> " impossible " // pas de couleur si non reconnu
    }
    return "$codeCouleur$message$reset"
}
