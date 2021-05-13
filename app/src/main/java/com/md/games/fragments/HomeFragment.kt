package com.md.games.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.md.games.GamesModel
import com.md.games.MainActivity
import com.md.games.R
import com.md.games.adapter.GamesAdapter


class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Création de la liste de jeux
        val gamesList = arrayListOf<GamesModel>()

        // Récupération des documents depuis la collection "games" de FireStore
        val db = FirebaseFirestore.getInstance()
         db.collection("games")
             // On configure un écouteurs sur la collection
             .addSnapshotListener { result, exception ->
                 // Si il y a une exception, on log
                 if (exception != null) {
                     Log.w("Firestore", "Listen failed.", exception)
                     return@addSnapshotListener
                 }
                 // Si le résultat est vide, on log
                 else if (result == null) {
                     Log.d("Firestore", "No such document")
                 }

                 // On vide la liste à chaque déclenchement de l'écouteur
                 gamesList.clear()

                 // On parcours les résultats
                 for (document in result!!) {
                     Log.d("Firestore", "${document.id} => ${document.data}")
                     // On ajoute à notre liste de jeux chaque document
                     gamesList.add(
                         GamesModel(
                             document.id,
                             document.data["description"] as String,
                             document.data["developmentTeam"] as String,
                             document.data["editorialTeam"] as String,
                             document.data["headerImageUrl"] as String,
                             document.data["price"] as Number,
                             document.data["releaseDate"] as String,
                             document.data["status"] as Boolean,
                             document.data["steamUrl"] as String,
                             document.data["title"] as String,
                         )
                     )
                 }

                 // Récupération du recyclerView
                 val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
                 recyclerView.adapter = GamesAdapter(context, gamesList)
             }
        return view;
    }

}