package org.abstractbinary.unshuffle

sealed abstract class Suite
case object Clubs extends Suite {
  override def toString = "\u2663"
}
case object Spades extends Suite {
  override def toString = "\u2660"
}
case object Hearts extends Suite {
  override def toString = "\u2665"
}
case object Diamonds extends Suite {
  override def toString = "\u2666"
}

case class Card(number : Int, suite : Suite) {
  override def toString = {
    val numberString =
      if (number == 1)
        "A"
      else if (number <= 10)
        number.toString
      else if (number == 11)
        "J"
      else if (number == 12)
        "Q"
      else if (number == 13)
        "K"
      else
        "Invalid card number: %d".format(number)
    numberString + suite.toString
  }
}
