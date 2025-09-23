package item

import dresseur.Entraineur
import org.example.Item


class Badge(id: Int, nom: String, description: String, val champion: Entraineur) :
    Item(id, nom, description)