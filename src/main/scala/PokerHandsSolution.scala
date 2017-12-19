import scala.io.Codec


sealed trait Suit
case object Hearts extends Suit
case object Diamonds extends Suit
case object Spades extends Suit
case object Clubs extends Suit

case class Card(value: Int, suite: Suit)
sealed case class Value(number: Int) extends Ordered[Value] {
  override def compare(that: Value): Int = this.number - that.number
}

object One extends Value(1)
object Two extends Value(2)
object Three extends Value(3)
object Four extends Value(5)
object Five extends Value(7)
object Six extends Value(11)
object Seven extends Value(13)
object Eight extends Value(17)
object Nine extends Value(19)
object Ten extends Value(23)
object Jack extends Value(29)
object Queen extends Value(31)
object King extends Value(37)
object Ace extends Value(41)


sealed trait Player {
  def cards: Set[String]
  def rank: BigInt = cards.foldLeft(BigInt(1))((hash, card) => PokerGame.ranks(card.charAt(0)) * hash)

}
final case class Player1(cards: Set[String]) extends Player {
}
final case class Player2(cards: Set[String]) extends Player {
}


object PokerGame {

  def generateLUT = {

  }

  def apply(game: String): (Player1, Player2) = {
    val cards = game.trim.split(" ")
    (Player1(cards.take(5).toSet), Player2(cards.takeRight(5).toSet))
  }

  val ranks = Map(
    '2' -> 2,
    '3' -> 3,
    '4' -> 5,
    '5' -> 7,
    '6' -> 11,
    '7' -> 13,
    '8' -> 17,
    '9' -> 19,
    'T' -> 23,
    'J' -> 29,
    'Q' -> 31,
    'K' -> 37,
    'A' -> 41
  )
}

object PokerHandsSolution extends App {
  val pokerGames = io.Source.fromResource("p054_poker.txt")(Codec.UTF8)
    .getLines().map(PokerGame(_))

  val results = pokerGames.foldLeft(0)((playerOneScore, opponents) => {
    opponents match {
      case (Player1(deck1), Player2(deck2)) if Player1(deck1).rank > Player2(deck2).rank => {
        playerOneScore + 1
      }
      case _ => {
        println("eh?")
        playerOneScore
      }
    }
  })



}
