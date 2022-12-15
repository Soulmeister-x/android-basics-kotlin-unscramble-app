package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

private val TAG="GameFragment"
class GameViewModel: ViewModel() {

    private var score = 0
    private var currentWordCount = 0
    private var _currentScrambledWord = "test"

    val currentScrambledWord: String
        get() = _currentScrambledWord


    init {
        Log.d(TAG, "init called, GameViewModel created!")
    }

    override fun onCleared() {
        Log.d(TAG, "GameViewModel destroyed")
        super.onCleared()
    }



}