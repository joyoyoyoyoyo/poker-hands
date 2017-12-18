import scala.io.Codec
import scala.util.{Success, Try}

sealed trait Player { def cards: Set[String] }
final case class Player1(cards: Set[String]) extends Player
final case class Player2(cards: Set[String]) extends Player


object PokerGame {
  def apply(game: String): (Player1, Player2) = {
    val cards = game.trim.split(" ")
    (Player1(cards.drop(5).toSet), Player2(cards.drop(5).toSet))
  }
}

object PokerHandsSolution extends App {
  val pokerGames = io.Source.fromFile("p054_poker.txt")(Codec.UTF8)
    .getLines().map(PokerGame(_))

  val results = pokerGames.map(opponents => {
    opponents match {
      case (Player1(deck1), Player2(deck2)) => {
        deck1
      }
      case _ => ???
    }
  })



}
