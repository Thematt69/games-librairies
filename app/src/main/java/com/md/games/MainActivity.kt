package com.md.games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.md.games.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MD","onCreate => Home")
        setContentView(R.layout.activity_main)

        // Injection du fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLayout,HomeFragment(this))
        transaction.addToBackStack(null)
        transaction.commit()

    }
}