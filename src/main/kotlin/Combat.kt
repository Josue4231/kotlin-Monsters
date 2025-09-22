import org.example.Entraineur
import org.example.EspeceMonstre
import org.example.individuMonstre

class Combat(
    val equipeJoueur: List<individuMonstre>,
    val monstreSauvage: List<individuMonstre>
) {
    var round: Int = 1

    /**
     * Vérifie si le joueur a perdu le combat.
     *
     * Condition de défaite :
     * - Aucun monstre de l'équipe du joueur n'a de PV > 0.
     *
     * @return `true` si le joueur a perdu, sinon `false`.
     */
    fun gameOver(): Boolean {
            // Retourne vrai si aucun monstre n'a de pv > 0
            return equipeJoueur.none { it.pv > 0 }
        }
    fun individuMonstre(id: Int, nom: String, espece: EspeceMonstre, entraineur: Entraineur) {}

}


fun main(){
    val entraineur1 = Entraineur(1, "Bernard", 100)
    val entraineur2 = Entraineur(2, "Arnaud", 200)
    val monstre4 = individuMonstre(
        5,
        "Pika",
        EspeceMonstre(
            1,
            nom = "Aquamy",
            type = "Meteo",
            10, 11, 9, 14, 14, 55,
            9.0, 10.0, 7.5, 12.0, 12.0, 27.0,
            "Créature vaporeuse semblable à un nuage, produit des gouttes pures.",
            "Fait baisser la température en s’endormant.",
            "Calme, rêveur, mystérieux"
        ),
        entraineur1,)
    val monstre5 =individuMonstre(
        1,
        "Aqaman",
        EspeceMonstre(
            4,nom="Flamkip",type="Animal",12,8,13,16,7,50,10.0,5.5,
            9.5,9.5,6.5,22.0,"Petit animal entouré de flammes, déteste le froid.",
            "Sa flamme change d’intensité selon son énergie.","Impulsif, joueur, loyal"
        ),
        entraineur2,)
    println("${monstre5}")
    monstre5.gameOver()


//    val monstre2 = individuMonstre("Bulbizarre", 0, 100)
//    val equipe = listOf(monstre1, monstre2)
//    val monstreSauvage = individuMonstre("Salamèche", 50, 100)
//
//    val combat = Combat(equipe, monstreSauvage)
//
//    println("Le joueur a perdu ? ${combat.gameOver()}")  // true car tous KO
//}
}

fun individuMonstre(id: Int, nom: String, espece: EspeceMonstre, entraineur: Entraineur) {}
