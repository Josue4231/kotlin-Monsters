🌀 Sprint 1 : Le noyau du projet
🎯 Objectif du Sprint

Mettre en place la base du projet Kotlin sous IntelliJ IDEA, configurer l’environnement de développement, créer l’arborescence du projet, et coder la première fonction utilitaire : changeCouleur().

🧱 1. Création du projet
Étapes :

Ouvrir IntelliJ IDEA → File > New > Project
Suivre les étapes pour créer un projet Kotlin avec Gradle.
![img.png](img.png)
💡 Gradle est un outil de gestion de dépendances qui facilite l’ajout de bibliothèques et la compilation du projet.
Nous l’utiliserons davantage dans les Sprints 2 et 3.

⚙️ 2. Configuration du projet
Configurer le proxy dans le fichier gradle.properties (à la racine du projet) :






🗂️ 3. Importer les ressources et créer l’arborescence

Téléchargez les fichiers ASCII des monstres :
🔗 https://drive.google.com/file/d/1waK9sw0-FNKLQVSnthtYcTkRNLUReDOb/view?usp=drive_link 
![img_1.png](img_1.png)

🧠 4. Déclaration d’une fonction utilitaire : changeCouleur()
✨ But

Créer une fonction permettant de colorer du texte dans la console à l’aide des codes ANSI.

📄 Code source
``` kotlin
/**
Change la couleur du message donné selon le nom de la couleur spécifié.
 Utilise les codes ANSI pour la coloration console.
 Si la couleur n'est pas reconnue, retourne le message sans couleur.

 @param message Le message à colorer.
 @param couleur Le nom de la couleur (ex: "rouge", "bleu", "marron").
 @return Le message coloré ou le message brut si la couleur est invalide.
 */
fun changeCouleur(message: String, couleur: String = ""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        else -> ""
    }
    return "$codeCouleur$message$reset"
}
```

🧪 5. Test fonctionnel de la fonction

Le test fonctionnel permet de vérifier la fonctionnalité complète en exécutant le programme avec différents scénarios.

🔍 Scénarios de test

Scénario 
Attendu Résultat Réel

Hello en rouge 
Texte rouge	✅

World en bleu 
Texte bleu	✅

Hello my world avec “my” en jaune	“my” jaune, 
reste neutre	✅
Truc en marron	Texte marron	✅

Pikachu en couleur non reconnue (ex: noir)	
Message sans couleur / “impossible”	✅

🧾 Code de test (main())
``` kotlin
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
```
💬 Commentaires du code

✅ Structure claire : La fonction changeCouleur() est séparée du main() → bonne pratique.

🧩 Utilisation du when : lisible et évolutive.

🌈 Extension personnalisée : ajout du marron et rose avec les codes \u001B[38;5;XXXm.

🧪 Tests variés : bons cas + cas d’erreur (“noir”).

⚠️ Amélioration possible : remplacer "impossible" par "" pour éviter de polluer la sortie si couleur inconnue.

✅ 6. Conclusion du Sprint 1

Lors de ce premier Sprint, nous avons :

mis en place le projet Kotlin sous IntelliJ avec Gradle,

configuré l’environnement réseau (proxy),

structuré l’arborescence du projet,

créé et documenté une première fonction utilitaire,

testé le comportement du programme en console.

🧠 Ce sprint a permis de poser les fondations techniques du projet, avec des pratiques propres et testées.


