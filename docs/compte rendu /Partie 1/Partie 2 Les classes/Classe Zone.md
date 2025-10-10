# 🧭 Sprint 2 – Partie 3 : La classe `Zone`

## 🎯 Objectif
La classe `Zone` représente un **endroit du monde** dans lequel le joueur peut :
- Explorer (ex : route, caverne, mer…)
- Rencontrer des monstres sauvages
- Se déplacer vers une **zone suivante** ou **précédente**

Chaque zone est donc **liée à d’autres zones** et contient une **liste d’espèces de monstres** susceptibles d’y apparaître.

---

## 🧩 Définition de la classe `Zone`

### 📄 Description
Un objet `Zone` contient plusieurs propriétés :
- `id` : identifiant unique de la zone
- `nom` : nom de la zone (ex : "Route", "Caverne", etc.)
- `expZone` : niveau d’expérience moyen des monstres qu’on peut y rencontrer
- `especesMonstres` : liste mutable des espèces de monstres présentes dans la zone
- `zoneSuivante` : référence vers la prochaine zone (ou `null` si aucune)
- `zonePrecedente` : référence vers la zone précédente (ou `null` si aucune)
- `joueur` : l’entraîneur qui explore cette zone

---

## 🧠 Code de la classe `Zone`

```kotlin

/**
 * Représente une zone du monde dans laquelle le joueur peut se déplacer et rencontrer des monstres.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone (ex : route, mer, montagne...).
 * @property expZone Niveau d’expérience moyen des monstres rencontrés.
 * @property especesMonstres Liste mutable d’espèces de monstres présentes dans la zone.
 * @property zoneSuivante Référence vers la zone suivante (null si aucune).
 * @property zonePrecedente Référence vers la zone précédente (null si aucune).
 * @property joueur Référence vers le joueur (Entraineur) qui explore la zone.
 */
class Zone(
    val id: Int,
    val nom: String,
    val expZone: Int,
    val especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null,
    val joueur: Entraineur
) {
    // TODO : faire la méthode genereMonstre()
    // TODO : faire la méthode rencontreMonstre()

    /**
     * Génère un monstre sauvage aléatoire à partir des espèces présentes dans la zone.
     *
     * @return Un objet IndividuMonstre représentant le monstre généré.
     */
    fun genereMonstre(): IndividuMonstre {
        val especeChoisie = especesMonstres.random()

        val expMin = expZone * 0.8
        val expMax = expZone * 1.2
        val expAleatoire = (expMin..expMax).randomDouble()

        return IndividuMonstre(
            id = 1,
            nom = especeChoisie.nom,
            espece = especeChoisie,
            expInit = expAleatoire
        ).apply {
            pv = pvMax
        }
    }
}
```
🧱 Création des objets Zone

Nous créons ici plusieurs zones dans le fichier Main.kt (après la déclaration des espèces de monstres et avant la fonction main()).
```kotlin
val zonemonstre = Zone(
    id = 10,
    nom = "Zone de monstres",
    expZone = 10,
    joueur = joueur,
    especesMonstres = mutableListOf(especeAquamy, especeFlamkip, especeSpringleaf)
)

val route1 = Zone(
    id = 11,
    nom = "Route",
    expZone = 10,
    joueur = joueur
)

val route2 = Zone(
    id = 12,
    nom = "Autoroute",
    expZone = 10,
    joueur = joueur
)
```
▶️ Tests dans la fonction main()
La fonction main() permet ici :

De relier les zones entre elles,

Et d’afficher les liens de navigation entre les zones.

```kotlin
fun main() {
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1

    println("${zonemonstre.nom} : ${zonemonstre.zoneSuivante?.nom}")
}
```

💡 Remarques importantes

Les méthodes genereMonstre() et rencontreMonstre() sont encore à implémenter.

La classe Zone dépend d’autres classes :
Entraineur, EspeceMonstre et IndividuMonstre.

Le lien entre zones est bidirectionnel :
une zone peut connaître à la fois sa précédente et sa suivante.
