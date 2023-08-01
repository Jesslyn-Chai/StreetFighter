package sf.view

import scalafx.event.ActionEvent
import scalafx.scene.media.{Media, MediaPlayer}
import scalafxml.core.macros.sfxml
import sf.MainApp

@sfxml
class TitleScreenController() {
  val media = new Media(getClass.getResource("../bgm/title.mp3").toURI.toString)
  val mediaPlayer = new MediaPlayer(media = media)

  val selectMedia = new Media(getClass.getResource("../bgm/select.mp3").toURI.toString)
  val selectMediaPlayer = new MediaPlayer(media = selectMedia)

  mediaPlayer.setCycleCount(MediaPlayer.Indefinite)
  mediaPlayer.play()
  System.gc()

  def handlePlay (action: ActionEvent): Unit = {
    selectMediaPlayer.play()
    MainApp.showSelectionScreen()
    mediaPlayer.stop()
  }
}