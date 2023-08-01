package sf.view

import scalafx.event.ActionEvent
import scalafx.scene.media.{Media, MediaPlayer}
import scalafxml.core.macros.sfxml
import sf.MainApp

@sfxml
class SelectionScreenController() {
  val media = new Media(getClass.getResource("../bgm/selection.mp3").toURI.toString)
  val mediaPlayer = new MediaPlayer(media = media)

  val selectMedia = new Media(getClass.getResource("../bgm/select.mp3").toURI.toString)
  val selectMediaPlayer = new MediaPlayer(media = selectMedia)

  mediaPlayer.setCycleCount(MediaPlayer.Indefinite)
  mediaPlayer.play()
  System.gc()

  def handleSelectCharacter (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showCharacters()
    mediaPlayer.stop()
  }

  def handleGameManual (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showGameManual()
    mediaPlayer.stop()
  }

  def handleBack (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showTitleScreen()
    mediaPlayer.stop()
  }
}