package br.com.vitorruiz.jokenpokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.vitorruiz.jokenpokemon.api.ClientApi
import br.com.vitorruiz.jokenpokemon.api.RankingService
import br.com.vitorruiz.jokenpokemon.api.UserRankingVO
import br.com.vitorruiz.jokenpokemon.api.getRankingService
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val score = 400L

        btnPlayAgain.setOnClickListener {
            getRankingService().sendPontuacao(UserRankingVO("", etUsername.text.toString(), score))
        }
    }
}
