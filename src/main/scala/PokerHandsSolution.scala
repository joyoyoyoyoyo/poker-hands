import scala.io.Codec


sealed trait Suit
case object Hearts extends Suit
case object Diamonds extends Suit
case object Spades extends Suit
case object Clubs extends Suit

case class Card(value: Value, suit: Suit)
case class Value(number: Int) extends Ordered[Value] {
  override def compare(that: Value): Int = this.number - that.number
}

case object Two extends Value(2)
case object Three extends Value(3)
case object Four extends Value(5)
case object Five extends Value(7)
case object Six extends Value(11)
case object Seven extends Value(13)
case object Eight extends Value(17)
case object Nine extends Value(19)
case object Ten extends Value(23)
case object Jack extends Value(29)
case object Queen extends Value(31)
case object King extends Value(37)
case object Ace extends Value(41)

object Deck {
  val cardSuit = Map(
    'H' -> Hearts,
    'C' -> Clubs,
    'D' -> Diamonds,
    'S' -> Spades
  )

  val cardNumber = Map(
    '2' -> Two,
    '3' -> Three,
    '4' -> Four,
    '5' -> Five,
    '6' -> Six,
    '7' -> Seven,
    '8' -> Eight,
    '9' -> Nine,
    'T' -> Ten,
    'J' -> Jack,
    'Q' -> Queen,
    'K' -> King,
    'A' -> Ace
  )
}

object Hand {
  def getHand(cards: Seq[String]) = {
    val hand = for {
      card <- cards
      suit = Deck.cardSuit(card(1))
      number = Deck.cardNumber(card(0))
    } yield Card(number, suit)
    val sortedHand = hand.sortBy(_.value)
  }

  def isRoyalFlush(hand: Seq[Card]) = ???
}
case class RoyalFlush(hand: Seq[Card]) {
  private val sortedHand = hand.sortBy(_.value)
  private val royalCardSeq = Seq(Ten, Jack, Queen, King, Ace)
  def isRoyal: Boolean = royalCardSeq.forall(royalCard => sortedHand.exists(playerCard => playerCard.value == royalCard))
  def isFlush: Boolean = sortedHand.groupBy(_.suit).size == 1
  def isStraight: Boolean = ???


}


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
