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

case class Card(_number : Int, _suite : Suite) {
  override def toString = {
    val numberString =
      if (_number == 1)
        "A"
      else if (_number <= 10)
        _number.toString
      else if (_number == 11)
        "J"
      else if (_number == 12)
        "Q"
      else if (_number == 13)
        "K"
      else
        "Invalid card number: %d".format(_number)
    numberString + suite.toString
  }

  def isRed : Boolean =
    _suite match {
      case Hearts   => true
      case Diamonds => true
      case Clubs    => false
      case Spades   => false
    }

  def isBlack : Boolean = !isRed

  def suite = _suite
}
