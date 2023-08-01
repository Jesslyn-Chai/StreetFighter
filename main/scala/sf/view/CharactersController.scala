package sf.view

import scalafx.event.ActionEvent
import scalafx.scene.media.{Media, MediaPlayer}
import scalafxml.core.macros.sfxml
import sf.MainApp

@sfxml
class CharactersController() {
  var media = new Media(getClass.getResource("../bgm/selection.mp3").toURI.toString)
  val mediaPlayer = new MediaPlayer(media = media)

  val selectMedia = new Media(getClass.getResource("../bgm/select.mp3").toURI.toString)
  val selectMediaPlayer = new MediaPlayer(media = selectMedia)

  val ryuVoice = new Media(getClass.getResource("../bgm/ryu_intro.mp3").toURI.toString)
  val ryuVoicePlayer = new MediaPlayer(media = ryuVoice)

  val kenVoice = new Media(getClass.getResource("../bgm/ken_intro.mp3").toURI.toString)
  val kenVoicePlayer = new MediaPlayer(media = kenVoice)

  val chunLiVoice = new Media(getClass.getResource("../bgm/chunli_intro.mp3").toURI.toString)
  val chunLiVoicePlayer = new MediaPlayer(media = chunLiVoice)

  mediaPlayer.setCycleCount(MediaPlayer.Indefinite)
  mediaPlayer.play()
  System.gc()

  def handleSelectRyu (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showRyuGame()
    mediaPlayer.stop()
  }

  def handleViewRyuProfile (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showProfile("view/RyuProfile.fxml")
    mediaPlayer.stop()
    ryuVoicePlayer.play()
  }

  def handleSelectKen (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showKenGame()
    mediaPlayer.stop()
  }

  def handleViewKenProfile (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showProfile("view/KenProfile.fxml")
    mediaPlayer.stop()
    kenVoicePlayer.play()
  }

  def handleSelectChunli (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showChunLiGame()
    mediaPlayer.stop()
  }

  def handleViewChunLiProfile (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showProfile("view/ChunLiProfile.fxml")
    mediaPlayer.stop()
    chunLiVoicePlayer.play()
  }

  def handleBack (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showSelectionScreen()
    mediaPlayer.stop()
  }
}