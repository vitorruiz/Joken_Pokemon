package br.com.vitorruiz.jokenpokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    private val TAG = "GameActivity"

    private val elements = arrayListOf("FIRE", "WATER", "LEAF")
    private var playerSelected: String? = null
        set(value) {
            val pcSelected = randomElement()

            when (pcSelected) {
                "FIRE" -> ivPCChoosed.setImageResource(R.drawable.charmander)
                "WATER" -> ivPCChoosed.setImageResource(R.drawable.squirtle)
                else -> ivPCChoosed.setImageResource(R.drawable.bulbasaur)
            }

            processResult(value!!, pcSelected)
        }
    private var winScore = 0
    private var drawScore = 0
    private var loseScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        ivOptionBulba.setOnClickListener {
            ivPlayerChoosed.setImageResource(R.drawable.bulbasaur)
            playerSelected = "LEAF"
        }

        ivOptionCharmander.setOnClickListener {
            ivPlayerChoosed.setImageResource(R.drawable.charmander)
            playerSelected = "FIRE"
        }

        ivOptionSquitle.setOnClickListener {
            ivPlayerChoosed.setImageResource(R.drawable.squirtle)
            playerSelected = "WATER"
        }
    }

    fun processResult(playerSelected: String, pcSelected: String) {
        if (playerSelected == pcSelected) {
            drawScore += 1
        } else {
            if (playerSelected == "FIRE") {
                if (pcSelected == "WATER") {
                    loseScore += 1
                } else {
                    winScore += 1
                }
            } else if (playerSelected == "WATER") {
                if (pcSelected == "LEAF") {
                    loseScore += 1
                } else {
                    winScore += 1
                }
            } else {
                if (pcSelected == "FIRE") {
                    loseScore += 1
                } else {
                    winScore += 1
                }
            }
        }

        tvWinScore.text = winScore.toString()
        tvDrawScore.text = drawScore.toString()
        tvLoseScore.text = loseScore.toString()
    }

    fun randomElement(): String {
        val r = Random()
        val low = 0
        val high = 3
        val result = r.nextInt(high - low) + low

        return elements[result]
    }
}
