🏋️ Sprint 2 : Classe EspeceMonstre — Partie 2
🎯 Objectif

Créer une classe Kotlin pour représenter les espèces de monstres, avec toutes leurs caractéristiques et une méthode pour afficher leur ASCII art.

📖 Définition de la classe

Dans le package monstres, créez une nouvelle classe Kotlin :
New > Kotlin Class/File > EspeceMonstre

⚠️ Nom de classe : commencez toujours par une majuscule et assurez-vous que le nom du fichier correspond exactement au nom de la classe.

✨ But de la classe

Représenter les espèces et non les individus.

Stocker les caractéristiques de base (attaque, défense, vitesse, PV, modificateurs, description, particularités, caractères).

Permettre l’affichage de l’ASCII art via une méthode.

📄 Code source complet

``` kotlin
package org.example

import java.io.File

/**
 * Représente une espèce de monstre.
 *
 * Chaque espèce a des caractéristiques de base, des modificateurs et des informations descriptives.
 *
 * @property id Identifiant unique de l'espèce.
 * @property nom Nom de l'espèce.
 * @property type Type du monstre (ex: "Feu", "Eau", "Plante").
 * @property baseAttaque Valeur de base de l'attaque physique.
 * @property baseDefense Valeur de base de la défense physique.
 * @property baseVitesse Valeur de base de la vitesse.
 * @property baseAttaqueSpe Valeur de base de l'attaque spéciale.
 * @property baseDefenseSpe Valeur de base de la défense spéciale.
 * @property basePv Points de vie de base.
 * @property modAttaque Modificateur d'attaque.
 * @property modDefense Modificateur de défense.
 * @property modVitesse Modificateur de vitesse.
 * @property modAttaqueSpe Modificateur d'attaque spéciale.
 * @property modDefenseSpe Modificateur de défense spéciale.
 * @property modPv Modificateur de PV.
 * @property description Description textuelle de l'espèce.
 * @property particularites Particularités spécifiques à l'espèce.
 * @property caractères Traits de caractère typiques de l'espèce.
 */
class EspeceMonstre(
    var id: Int = 0,
    var nom: String,
    var type: String = "",
    val baseAttaque: Int = 0,
    val baseDefense: Int = 0,
    val baseVitesse: Int = 0,
    val baseAttaqueSpe: Int = 0,
    val baseDefenseSpe: Int = 0,
    val basePv: Int = 0,
    val modAttaque: Double = 0.0,
    val modDefense: Double = 0.0,
    val modVitesse: Double = 0.0,
    val modAttaqueSpe: Double = 0.0,
    val modDefenseSpe: Double = 0.0,
    val modPv: Double = 0.0,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = ""
) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false). Défaut : true.
     * @return Chaîne contenant l'art ASCII du monstre avec les codes couleur ANSI.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back"
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}

```

🧪 Création d’objets et tests
``` kotlin
// Création de plusieurs espèces de monstres
var especeAquamy = EspeceMonstre(
1, "Aquamy", "Meteo", 10, 11, 9, 14, 14, 55,
9.0, 10.0, 7.5, 12.0, 12.0, 27.0,
"Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
"Fait baisser la température en s’endormant.",
"Calme, rêveur, mystérieux"
)

var especeFlamkip = EspeceMonstre(
4, "Flamkip", "Animal", 12, 8, 13, 16, 7, 50,
10.0, 5.5, 9.5, 9.5, 6.5, 22.0,
"Petit animal entouré de flammes, déteste le froid.",
"Sa flamme change d’intensité selon son énergie.",
"Impulsif, joueur, loyal"
)

var especeSpringleaf = EspeceMonstre(
1, "Springleaf", "Graine", 9, 11, 10, 12, 13, 60,
6.5, 9.0, 8.0, 7.0, 10.0, 34.0,
"Petit monstre espiègle rond comme une graine, adore le soleil.",
"Sa feuille sur la tête indique son humeur.",
"Curieux, amical, timide"
)

// Tests : affichage ASCII art
println(especeAquamy.afficheArt())       // Front
println(especeFlamkip.afficheArt())      // Front
println(especeSpringleaf.afficheArt())   // Front
```