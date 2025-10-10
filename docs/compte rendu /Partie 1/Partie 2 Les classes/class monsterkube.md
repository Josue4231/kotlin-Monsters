🏁 Sprint 2 – Partie 8 : Classe MonsterKube
🎯 Concept

MonsterKube est un objet capturable pour les monstres :

C’est un Item → hérite de la classe Item.

Il est utilisable en combat → implémente l’interface Utilisable.

Possède une propriété spécifique : chanceCapture qui définit la probabilité de capturer un monstre.

⚠️ Les badges et autres items non-utilisables n’implémentent pas cette interface.

📌 Définition de la classe
```kotlin

/**
 * Classe représentant un MonsterKube utilisable pour capturer un monstre.
 *
 * @param id Identifiant unique
 * @param nom Nom de l'objet
 * @param description Description textuelle
 * @param chanceCapture Probabilité de capture (0.0 à 1.0)
 */
class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double // propriété spécifique au kube pour définir la probabilité de capture
) : Item(id, nom, description), utilisable { // héritage de Item + implémentation de l'interface utilisable

    /**
     * Tente de capturer un [IndividuMonstre].
     *
     * @param cible Le monstre ciblé
     * @return true si la capture réussit, false sinon
     */
    override fun utiliser(cible: IndividuMonstre): Boolean {
        // Calcul du ratio de vie du monstre (entre 0 et 1)
        val ratioVie = cible.pv.toDouble() / cible.pvMax

        // Calcul de la chance effective en fonction de la vie du monstre
        val chanceEffective = (chanceCapture * (1.5 - ratioVie)).coerceAtLeast(0.05)
        // - Si PV plein (ratioVie = 1) → chance réduite
        // - Si PV faible → chance augmentée
        // - Minimum 5% pour éviter une chance nulle

        // Détermination aléatoire du succès
        val réussite = Math.random() < chanceEffective
        if (réussite) {
            println("$nom a capturé ${cible.nom} ! (Chance: ${(chanceEffective * 100).toInt()}%)")
        } else {
            println("$nom a raté la capture de ${cible.nom}... (Chance: ${(chanceEffective * 100).toInt()}%)")
        }

        return réussite // retourne true si capture réussie
    }
}

```



🧩 Explications

Héritage (: Item(...)) :
MonsterKube récupère toutes les propriétés de Item (id, nom, description) et peut les utiliser.

Interface utilisable :
Garantit que la classe possède une méthode utiliser(cible: IndividuMonstre).

Calcul de chanceEffective :

```kotlin
val ratioVie = cible.pv.toDouble() / cible.pvMax
val chanceEffective = (chanceCapture * (1.5 - ratioVie)).coerceAtLeast(0.05)
```

📌 À retenir

MonsterKube est à la fois un Item et un objet utilisable.

La capture dépend de la vie du monstre et de la propriété chanceCapture.

Intégré au sac du joueur via :
```kotlin
var sacAItems: MutableList<Item> = mutableListOf()
```
