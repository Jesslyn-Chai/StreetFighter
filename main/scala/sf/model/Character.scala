package sf.model

import CharacterMove._
import java.time.LocalDate;

abstract class Character {
  val name: String
  val dob: LocalDate
  var height: String
  var weight: String
  var size: List[String]
  val bloodType: Char
  val maxHP: Double
  var hp: Double
  var attack: Int
  var defense: Int
  var move1: Move
  var move2: Move
  var move3: Move

  def attack(selectedMove: Move, targetPlayer: Player): String = {
    // damage = ((move damage * character attack) / 5) / opponent character defense
    var damage: Double = ((selectedMove.damage * this.attack) / 5) / targetPlayer.character.defense

    if (damage < 1) {
      damage = 1
    }
    targetPlayer.character.hp -= damage

    if (targetPlayer.character.hp <= 0) {
      targetPlayer.character.hp = 0
      targetPlayer.characterHP.value = targetPlayer.character.hp
      targetPlayer.lose()
    } else {
      targetPlayer.characterHP.value = targetPlayer.character.hp
    }

    ""
  }

}

abstract class Move {
  val damage: Int
}

object CharacterMove {
  object Shoryuken extends Move {
    override val damage: Int = 25
  }

  object Hadoken extends Move {
    override val damage: Int = 15
  }

  object HurricaneKick extends Move {
    override val damage: Int = 30
  }

  object LightningKick extends Move {
    override val damage: Int = 10
  }

  object Tenshokyaku extends Move {
    override val damage: Int = 30
  }

  object SpinningBirdKick extends Move {
    override val damage: Int = 35
  }
}

class Ryu extends Character {
  val name = "Ryu"
  val dob = LocalDate.of(1964, 7, 21)
  var height = "5' 10''"
  var weight = "150lbs"
  var size = List("44''", "31''", "33''")
  val bloodType = 'O'
  val maxHP = 100
  var hp = maxHP
  var attack = 25
  var defense = 20
  var move1: Move = Hadoken
  var move2: Move = Shoryuken
  var move3: Move = HurricaneKick
}

class Ken extends Character {
  val name = "Ken"
  val dob = LocalDate.of(1965, 2, 14)
  var height = "5' 10''"
  var weight = "169lbs"
  var size = List("44''", "32''", "33''")
  val bloodType = 'B'
  val maxHP = 100
  var hp = maxHP
  var attack = 25
  var defense = 19
  var move1: Move = Shoryuken
  var move2: Move = Hadoken
  var move3: Move = HurricaneKick
}

class ChunLi extends Character {
  val name = "Chun Li"
  val dob = LocalDate.of(1968, 3, 1)
  var height = "5' 8''"
  var weight = "Unknown"
  var size = List("34''", "22''", "35''")
  val bloodType = 'A'
  val maxHP = 100
  var hp = maxHP
  var attack = 24
  var defense = 20
  var move1: Move = LightningKick
  var move2: Move = Tenshokyaku
  var move3: Move = SpinningBirdKick
}