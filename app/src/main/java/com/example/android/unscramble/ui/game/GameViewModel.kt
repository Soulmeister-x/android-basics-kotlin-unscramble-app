package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

private val TAG="GameFragment"
class GameViewModel: ViewModel() {

    private lateinit var currentWord: String

    /*
    The way I use the wordList is:
        - copy all words to wordList
        - remove used words
     */
    private var wordsList: MutableList<String>

    private var _score = 0
    private var _currentWordCount = 0
    private var _currentScrambledWord = "test"

    // backing property get methods
    val currentScrambledWord: String
        get() = _currentScrambledWord
    val score: Int
        get() = _score
    val currentWordCount: Int
        get() = _currentWordCount


    init {
        // TODO: remove following line
        wordsList = allWordsList.toMutableList()
        getNextWord()
    }

    override fun onCleared() {
        Log.d(TAG, "GameViewModel destroyed")
        super.onCleared()
    }

    /*
     * Updates currentWord and currentScrambledWord with the next word.
     */
    private fun getNextWord() {
        _currentWordCount++

        // Make sure you don't show the same word twice during the game.
        //  -> mutableList.removeAt() gives word and removes it from collection
        currentWord = wordsList.random()
        wordsList.remove(currentWord)
        val newWord = currentWord.toCharArray()
        // Create a scrambled word by scrambling the letters in the currentWord and assign it to the currentScrambledWord
        // Handle the case where the scrambled word is the same as the unscrambled word.
        do {
            newWord.shuffle()
            _currentScrambledWord = String(newWord)
        } while (currentWord == _currentScrambledWord)

        Log.d(TAG, "new word: $currentWord -> $_currentScrambledWord " +
                "wordcount: $_currentWordCount")

    }

    /********** HELPER METHODS **********/

    /*
    * Returns true if the current word count is less than MAX_NO_OF_WORDS.
    * Updates the next word.
    */
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isPlayerWordCorrect(playerWord: String): Boolean {
        if (playerWord.equals(currentWord, true)) {
            increaseScore()
            return true
        }
        return false
    }

    /*
     * Re-initializes the game data to restart the game.
     */
    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        // TODO: change to wordsList.clear()
        wordsList = allWordsList.toMutableList()
        getNextWord()
    }



}