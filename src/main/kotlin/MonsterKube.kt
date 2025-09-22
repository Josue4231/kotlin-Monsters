import org.example.IndividuMonstre
import org.example.Item1
import org.example.utilisable

class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double
) : Item1(id, nom, description), utilisable {
    override fun utiliser(cible: IndividuMonstre): Boolean {
        val ratioVie = cible.pv.toDouble() / cible.pvMax
        val chanceEffective = (chanceCapture * (1.5 - ratioVie)).coerceAtLeast(0.05)

        val réussite = Math.random() < chanceEffective
        if (réussite) {
            println("$nom a capturé ${cible.nom} ! (Chance: ${(chanceEffective * 100).toInt()}%)")
        } else {
            println("$nom a raté la capture de ${cible.nom}... (Chance: ${(chanceEffective * 100).toInt()}%)")
        }
        return réussite
    }
}


fun main() {
    val v3 = MonsterKube(1, "Kube de monstre", "Un kube de monstre", 0.5)
    v3.utiliser(IndividuMonstre("Individu de test", 10, 80))

}






