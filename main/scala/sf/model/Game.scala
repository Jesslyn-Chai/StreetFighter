package sf.model

import scalafx.beans.property.{BooleanProperty, StringProperty}

class Game(val player1Name: String, val player1Character: Character, val player2Name: String, val player2Character: Character) {
  val playerLeft = new Player(player1Name, player1Character)
  val playerRight = new Player(player2Name, player2Character)

  var endGame: BooleanProperty = BooleanProperty(false)
  var gameStatus: StringProperty = new StringProperty()

  def progress(activity : => Unit): Unit = {
    activity
    if (isEndGame) {
      endGame.value = true
      gameStatus.value = "Player " + winner.name + " Won!"
      if (playerLeft.lost && playerRight.lost) {
        gameStatus.value = "It's a Tie"
      }
    }
  }

  def isEndGame: Boolean = {
    playerLeft.lost || playerRight.lost
  }

  def winner: Player = {
    if (playerLeft.lost) {
      playerRight
    } else if (playerRight.lost) {
      playerLeft
    } else {
      null
    }
  }

  def loser: Player = {
    if (playerLeft.lost) {
      playerLeft
    } else if (playerRight.lost) {
      playerRight
    } else {
      null
    }
  }
}