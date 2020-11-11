package com.xyz.basketballscore

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Lloyd Dcosta
 * This Activity is used to update the score of 2 teams playing basket ball
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var scoreViewModel: ScoreViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewsAndListeners()
        /*
        Intialize the view model here
         */
        scoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)
        getInitialScoresAndUpdate()
    }

    /**
     * This method is used to get the initial scores of 2 teams and set the score to the text views
     */
    private fun getInitialScoresAndUpdate() {
        tvPhoenixScore.text = scoreViewModel.getPhoenixScore().toString()
        tvTrojansScore.text = scoreViewModel.getTrojansScore().toString()
    }

    private fun initViewsAndListeners() {
        btnTrojansThreePoints.setOnClickListener(this)
        btnTrojansTwoPoints.setOnClickListener(this)
        btnPhoenixThreePoints.setOnClickListener(this)
        btnPhoenixTwoPoints.setOnClickListener(this)
        btnResetScore.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnTrojansThreePoints -> {
                scoreViewModel.updateTrojansScore(3)
                tvTrojansScore.text = scoreViewModel.getTrojansScore().toString()
            }
            R.id.btnTrojansTwoPoints -> {
                scoreViewModel.updateTrojansScore(2)
                tvTrojansScore.text = scoreViewModel.getTrojansScore().toString()
            }
            R.id.btnPhoenixThreePoints -> {
                scoreViewModel.updatePhoenixScore(3)
                tvPhoenixScore.text = scoreViewModel.getPhoenixScore().toString()
            }
            R.id.btnPhoenixTwoPoints -> {
                scoreViewModel.updatePhoenixScore(2)
                tvPhoenixScore.text = scoreViewModel.getPhoenixScore().toString()
            }
            R.id.btnResetScore -> {
                val resetScore = 0
                tvTrojansScore.text = resetScore.toString()
                tvPhoenixScore.text = resetScore.toString()
            }
        }
    }
}