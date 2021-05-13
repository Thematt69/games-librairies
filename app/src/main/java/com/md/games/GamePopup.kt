package com.md.games

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.md.games.adapter.GamesAdapter


class GamePopup(
    private val adapter: GamesAdapter,
    private val currentGame: GamesModel,
) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_game_details)
        setupComponents()
        setupCloseButton()
    }

    private fun setupCloseButton() {
        val closeButton = findViewById<ImageView>(R.id.close_button)
        closeButton.setOnClickListener {
            // Fermer la popup
            dismiss()
        }
    }

    private fun setupComponents() {
        // Définition des éléménts
        val popupGameTitle = findViewById<TextView>(R.id.popup_game_title)
        val popupGameStatus = findViewById<CheckBox>(R.id.popup_game_status)
        val popupGameHeaderImageUrl = findViewById<ImageView>(R.id.popup_game_headerImageUrl)
        val popupGameDescription = findViewById<TextView>(R.id.popup_game_description)
        val popupGameDevelopmentTeam = findViewById<TextView>(R.id.popup_game_developmentTeam)
        val popupGameReleaseDate = findViewById<TextView>(R.id.popup_game_releaseDate)
        val popupGameEditorialTeam = findViewById<TextView>(R.id.popup_game_editorialTeam)
        val popupGamePrice = findViewById<TextView>(R.id.popup_game_price)
        val popupGameSteamUrl = findViewById<TextView>(R.id.popup_game_steamUrl)

        Glide.with(adapter.context)
            .load(Uri.parse(currentGame.headerImageUrl))
            .into(popupGameHeaderImageUrl)

        popupGameTitle.text = currentGame.title
        popupGameStatus.isChecked = currentGame.status
        popupGameDescription.text = currentGame.description
        popupGameDevelopmentTeam.text = currentGame.developmentTeam
        popupGameReleaseDate.text = currentGame.releaseDate
        popupGameEditorialTeam.text = currentGame.editorialTeam
        popupGamePrice.text = currentGame.price.toString() + " €"
        popupGameSteamUrl.setOnClickListener {
            // On crée un intent avec le lien du jeu
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(currentGame.steamUrl)
            adapter.context.startActivity(openURL)
        }
        popupGameStatus.setOnClickListener {
            // TODO - Modifier en bdd
        }
    }

}