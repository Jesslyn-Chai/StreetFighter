package sf.view

import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, Label, ProgressIndicator}
import scalafx.scene.layout.HBox
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.scene.text.Text
import scalafxml.core.macros.sfxml
import sf.MainApp
import sf.model._

import scala.util.Random

@sfxml
class KenGameController(
                       private var hpLeft: ProgressIndicator,
                       private var hpLeftText: Label,
                       private var hpRight: ProgressIndicator,
                       private var hpRightText: Label,
                       private var hBoxLeft: HBox,
                       private var hBoxRight: HBox,
                       private var consoleText: Text,
                       private var playAgainButton: Button,
                       private var exitButton: Button
                       ) {
  var media = new Media(getClass.getResource("../bgm/ken_theme.mp3").toURI.toString)
  val mediaPlayer = new MediaPlayer(media = media)

  var announceMedia = new Media(getClass.getResource("../bgm/start.mp3").toURI.toString)
  val announceMediaPlayer = new MediaPlayer(media = announceMedia)

  var koMedia = new Media(getClass.getResource("../bgm/ko.mp3").toURI.toString)
  val koMediaPlayer = new MediaPlayer(media = koMedia)

  val selectMedia = new Media(getClass.getResource("../bgm/select.mp3").toURI.toString)
  val selectMediaPlayer = new MediaPlayer(media = selectMedia)

  var fightSound = new Media(getClass.getResource("../bgm/fight.mp3").toURI.toString)
  val fightSoundPlayer = new MediaPlayer(media = fightSound)

  mediaPlayer.setCycleCount(MediaPlayer.Indefinite)
  mediaPlayer.play()
  announceMediaPlayer.play()

  //Initialize Game
  var game = new Game("1", new Ken, "2", new ChunLi)
  consoleText.text <== game.gameStatus
  updateHPBar(game.playerLeft.character.hp, game.playerLeft.character.maxHP, hpLeft, hpLeftText)
  updateHPBar(game.playerRight.character.hp, game.playerRight.character.maxHP, hpRight, hpRightText)

  game.endGame.onChange ((_, old, newV) => {
    hBoxLeft.disable.value = true
    hBoxRight.disable.value = true
    playAgainButton.setVisible(true)
    exitButton.setVisible(true)
    koMediaPlayer.play()
  })

  game.playerLeft.characterHP.onChange((_, oldHP, newHP) => {
    updateHPBar(newHP.doubleValue(), game.playerLeft.character.maxHP, hpLeft, hpLeftText)
  })

  game.playerRight.characterHP.onChange((_, oldHP, newHP) => {
    updateHPBar(newHP.doubleValue(), game.playerRight.character.maxHP, hpRight, hpRightText)
  })

  def updateHPBar(hp: Double, maxHP: Double, hpBar: ProgressIndicator, hpText: Label): Unit = {
    val hpValue = hp / maxHP
    hpBar.progress = hpValue

    val style =
      if (hpValue >= 0.8) "-fx-accent: YellowGreen;"
      else if (hpValue >= 0.6) "-fx-accent: Gold;"
      else if (hpValue >= 0.4) "-fx-accent: Orange;"
      else "-fx-accent: Tomato;"
    hpBar.setStyle(style)
    hpText.text = hp + "/" + maxHP
  }

  def computerTurn(): Unit = {
    val random = Random.nextInt(3)

    random match {
      case 0 => game.gameStatus.value = game.playerRight.character.attack(game.playerRight.character.move1, game.playerLeft)
      case 1 => game.gameStatus.value = game.playerRight.character.attack(game.playerRight.character.move2, game.playerLeft)
      case 2 => game.gameStatus.value = game.playerRight.character.attack(game.playerRight.character.move3, game.playerLeft)
      case _ => game.gameStatus.value = game.playerRight.character.attack(game.playerRight.character.move1, game.playerLeft)
    }
  }

  //Player Buttons
  def handleMove1Left (action: ActionEvent): Unit = {
    fightSoundPlayer.stop()
    fightSoundPlayer.play()
    game.progress {
      game.gameStatus.value = game.playerLeft.character.attack(game.playerLeft.character.move1, game.playerRight)
      computerTurn()
    }
  }

  def handleMove2Left (action: ActionEvent): Unit = {
    fightSoundPlayer.stop()
    fightSoundPlayer.play()
    game.progress {
      game.gameStatus.value = game.playerLeft.character.attack(game.playerLeft.character.move2, game.playerRight)
      computerTurn()
    }
  }

  def handleMove3Left (action: ActionEvent): Unit = {
    fightSoundPlayer.stop()
    fightSoundPlayer.play()
    game.progress {
      game.gameStatus.value = game.playerLeft.character.attack(game.playerLeft.character.move3, game.playerRight)
      computerTurn()
    }
  }

  def handlePlayAgain(action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    mediaPlayer.stop()
    MainApp.showKenGame()
  }

  def handleExit(action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    mediaPlayer.stop()
    MainApp.showTitleScreen()
  }
}