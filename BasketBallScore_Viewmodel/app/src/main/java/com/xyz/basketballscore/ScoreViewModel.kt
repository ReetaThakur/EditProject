package com.xyz.basketballscore

import androidx.lifecycle.ViewModel

/**
 * @author Lloyd Dcosta
 * This is a view model class which is used to retain data and it survives the orientation change
 */
class ScoreViewModel : ViewModel() {

    private var trojansScore: Int = 0
    private var phoenixScore: Int = 0

    /**
     * This method is called whenever the scores are changed and it updates the trojans score
     */
    fun updateTrojansScore(score: Int) {
        trojansScore += score
    }

    /**
     * This method is called whenever the scores are changed and it updates the phoenix score
     */
    fun updatePhoenixScore(score: Int) {
        phoenixScore += score
    }

    /**
     * This method gives the latest score for trojans
     */
    fun getTrojansScore(): Int {
        return trojansScore
    }

    /**
     * This method gives the latest score for phoenix
     */
    fun getPhoenixScore(): Int {
        return phoenixScore
    }
}