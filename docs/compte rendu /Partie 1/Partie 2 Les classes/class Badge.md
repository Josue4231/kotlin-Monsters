# Sprint 2 – Partie 6 : La classe Badge

## Définition

Les badges sont un sous-type d’Item, comme les MonsterKubes ou les potions.  
En programmation orientée objet, on utilise **l’héritage** pour créer ce type de relation.

- **Classe parent (super classe)** : `Item`  
- **Classe enfant (sous-classe)** : `Badge`  
- Une classe enfant hérite des propriétés et méthodes de la classe parent (`id`, `nom`, `description` ici).  
- Le constructeur de la classe enfant utilise le constructeur du parent.

---
Explications :

Les paramètres id, nom et description ne sont pas précédés de val ou var car ils sont déjà définis dans la classe parent Item.

La syntaxe : Item(id, nom, description) indique que :

Badge hérite de Item.

Le constructeur de Badge utilise le constructeur de Item.

La propriété champion est spécifique à Badge.

Elle est précédée de val car elle est propre à la sous-classe et n’est pas définie dans Item.

## Code de la classe Badge

```kotlin

/**
 * Représente un badge obtenu après avoir vaincu un champion.
 *
 * @property champion Le dresseur à battre pour obtenir le badge.
 */
class Badge(
    id: Int,
    nom: String,
    description: String,
    val champion: Entraineur
) : Item(id, nom, description)
```

Test rapide dans main()
```kotlin
fun main() {
    val championFeu = Entraineur(10, "Flamina", 15)
    val badgeFeu = Badge(1, "Badge de Feu", "Obtenu après avoir vaincu le champion du type Feu.", championFeu)

    println("Badge : ${badgeFeu.nom}")
    println("Description : ${badgeFeu.description}")
    println("Champion associé : ${badgeFeu.champion.nom}")
}
```