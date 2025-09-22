package org.example

import kotlin.random.Random
import kotlin.math.round

class individuMonstre(
    val id: Int,
    var nom: String,
    val espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit: Double
) {
    var experience: Double = 0.0
        set(value) {
            field = value
            // Tant que l'expérience dépasse le palier du niveau suivant, on levelUp
            while (field >= palierExp(niveau + 1)) {
                levelUp()
            }
        }
    fun palierExp(niveau: Int): Double {
        return 100 * Math.pow((niveau - 1).toDouble(), 2.0)
    }


    var niveau: Int = 1

    var attaque: Int = espece.baseAttaque + Random.nextInt(-2, 3)
    var defense: Int = espece.baseDefense + Random.nextInt(-2, 3)
    var vitesse: Int = espece.baseVitesse + Random.nextInt(-2, 3)
    var attaqueSpe: Int = espece.baseAttaqueSpe + Random.nextInt(-2, 3)
    var defenseSpe: Int = espece.baseDefenseSpe + Random.nextInt(-2, 3)

    var pvMax: Int = espece.basePv + Random.nextInt(-5, 6)
    var potentiel: Int = Random.nextInt(0, 3) // potentiel positif (0,1,2)

    var pv: Int = pvMax
        set(value) {
            field = when {
                value < 0 -> 0
                value > pvMax -> pvMax
                else -> value
            }
        }

    init {
        this.experience = expInit // applique le setter, déclenche potentiellement levelUp()
    }

    fun levelUp() {
        niveau += 1

        val modCaracteristique = 2.0  // tu peux ajuster cette valeur ou la passer en paramètre

        fun calcCarac(): Int {
            val base = round(modCaracteristique * potentiel).toInt()
            val alea = Random.nextInt(-2, 3)
            return base + alea
        }

        fun calcPvMax(): Int {
            val base = round(modCaracteristique * potentiel).toInt()
            val alea = Random.nextInt(-5, 6)
            return base + alea
        }

        attaque += calcCarac()
        defense += calcCarac()
        vitesse += calcCarac()
        attaqueSpe += calcCarac()
        defenseSpe += calcCarac()

        val pvMaxGagne = calcPvMax()
        pvMax += pvMaxGagne
        pv += pvMaxGagne

        // Clamp pour éviter les négatifs
        attaque = maxOf(attaque, 0)
        defense = maxOf(defense, 0)
        vitesse = maxOf(vitesse, 0)
        attaqueSpe = maxOf(attaqueSpe, 0)
        defenseSpe = maxOf(defenseSpe, 0)
        pvMax = maxOf(pvMax, 1)
        pv = pv.coerceIn(0, pvMax)

        println("Level up ! Nouveau niveau : $niveau")
    }
    fun attaquer(cible: individuMonstre) {
        val degatsBruts = attaque - (cible.defense / 2)
        val degats = if (degatsBruts < 1) 1 else degatsBruts
        cible.pv -= degats
        println("${nom} attaque ${cible.nom} et inflige $degats dégâts !")
        println("${cible.nom} a maintenant ${cible.pv}/${cible.pvMax} PV.")
    }
    fun renommer() {
        print("Entrez un nouveau nom pour ${nom} (laissez vide pour garder le même) : ")
        val nouveauNom = readLine()?.trim() ?: ""
        if (nouveauNom.isNotEmpty()) {
            nom = nouveauNom
            println("Le monstre a été renommé en $nom")
        } else {
            println("Le nom n'a pas été modifié.")
        }
    }
    fun afficheDetail() {
        // 1. Récupérer l’art ASCII et normaliser les retours à la ligne
        val art = espece.afficheArt().replace("\r\n", "\n").replace("\r", "\n")
        val artLines = art.lines()

        // 2. Construire les détails (statistiques complètes)
        val details = listOf(
            "Nom : $nom",
            "Espèce : ${espece.nom}",
            "Type : ${espece.type}",
            "Niveau : $niveau",
            "PV : $pv/$pvMax",
            "Attaque : $attaque",
            "Défense : $defense",
            "Vitesse : $vitesse",
            "Attaque Spé : $attaqueSpe",
            "Défense Spé : $defenseSpe",
            "Expérience : ${"%.2f".format(experience)}",
            "Entraîneur : ${entraineur?.nom ?: "Aucun"}"
        )

        // 3. Largeur max de l’art (ignorer les codes couleur ANSI pour le calcul)
        val ansiRegex = "\u001B\\[[;\\d]*m".toRegex()
        val maxArtWidth = artLines.maxOfOrNull { it.replace(ansiRegex, "").length } ?: 0

        // 4. Nombre total de lignes à afficher
        val maxLines = maxOf(artLines.size, details.size)

        // 5. Affichage côte à côte
        for (i in 0 until maxLines) {
            val artLine = if (i < artLines.size) artLines[i] else ""
            val detailLine = if (i < details.size) details[i] else ""
            val paddedArt = artLine.padEnd(maxArtWidth + 4)
            println(paddedArt + detailLine)
        }
    }

}
fun main() {
    val espece = EspeceMonstre(1,nom="Aquamy",type="Meteo",10,11,9,14,14,55,9.0,10.0,
        7.5,12.0,12.0,27.0,"Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
        "Fait baisser la température en s’endormant.","Calme, rêveur, mystérieux")

    val monstre = individuMonstre(
        id = 1,
        nom = "Drako",
        espece = espece,
        expInit = 0.0
    )

    println("Niveau initial: ${monstre.niveau}, Exp initiale: ${monstre.experience}")

    // Donne beaucoup d'expérience pour tester le level up multiple
    monstre.experience += 500.0

    println("Niveau après gain d'expérience: ${monstre.niveau}")
    println("Stats après level up: ATQ ${monstre.attaque}, PV ${monstre.pv}/${monstre.pvMax}")
}






