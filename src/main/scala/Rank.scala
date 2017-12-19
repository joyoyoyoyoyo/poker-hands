abstract class Rank {
  val rank = Map(
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

  def hash(cards: String) = ???
}

