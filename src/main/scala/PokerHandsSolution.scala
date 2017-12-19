import scala.io.Codec

sealed trait Player { def cards: Set[String] }
final case class Player1(cards: Set[String]) extends Player {
  val increment = 1
  def win(points: Int)(score: (Int, Int) => Int): Int = score(points, increment)
}
final case class Player2(cards: Set[String]) extends Player {
}


object PokerGame {
  def apply(game: String): (Player1, Player2) = {
    val cards = game.trim.split(" ")
    (Player1(cards.take(5).toSet), Player2(cards.takeRight(5).toSet))
  }
}

object PokerHandsSolution extends App {
  val pokerGames = io.Source.fromResource("p054_poker.txt")(Codec.UTF8)
    .getLines().map(PokerGame(_))

  val playerOneScore = 0
  val results = pokerGames.foldLeft(playerOneScore)((isWin, opponents) => {
    opponents match {
      case (Player1(deck1), Player2(deck2)) => {
        deck1
        val outcome = Player1(deck1).win(isWin)(_ + _)
        println(deck1.mkString(" ") + " " +"Outcome: " + outcome.toString)
        outcome
      }
      case _ => println("eh?"); isWin
    }
  })



}
