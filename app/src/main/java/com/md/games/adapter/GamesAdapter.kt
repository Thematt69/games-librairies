package com.md.games.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.md.games.GamePopup
import com.md.games.GamesModel
import com.md.games.MainActivity
import com.md.games.R

class GamesAdapter(
    val context: MainActivity,
    private val gamesList: List<GamesModel>,
) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gameImage:ImageView? = view.findViewById(R.id.imageView)
        val gameTitle:TextView? = view.findViewById(R.id.textViewTitle)
        val gameReleaseDate:TextView? = view.findViewById(R.id.textViewReleaseDate)
        val gameStatus:TextView? = view.findViewById(R.id.textViewStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_vertical_book,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Récupère l'information du jeux
        val currentGame = gamesList[position]

        // Utilisation de Glide pour récupérer l'image depuis le lien url
        holder.gameImage?.let {
            Glide.with(context)
                .load(Uri.parse(currentGame.headerImageUrl))
                .into(it)
        }
        holder.gameTitle?.text = currentGame.title
        holder.gameReleaseDate?.text = currentGame.releaseDate
        holder.gameStatus?.text = if(currentGame.status) "Déjà joué" else "Pas encore joué"

        // Clique sur une ligne
        holder.itemView.setOnClickListener {
            // Afficher la popup
            GamePopup(this,currentGame).show()
        }

    }

    override fun getItemCount(): Int = gamesList.size

}