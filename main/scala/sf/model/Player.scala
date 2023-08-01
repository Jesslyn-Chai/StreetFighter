package sf.model

import scalafx.beans.property.{DoubleProperty}

class Player(val name: String, val character: Character) {
  val characterHP: DoubleProperty = DoubleProperty(character.hp)
  var lost: Boolean = false

  def lose(): Unit = {
    this.lost = true
  }
}



