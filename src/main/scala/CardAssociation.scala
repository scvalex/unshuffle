package org.abstractbinary.unshuffle

import scala.collection.mutable.HashMap

case class CardInfo(clubs : Int, hearts : Int, spades : Int, diamonds : Int, reds : Int, blacks : Int)

class CardAssociation(_cards : List[Card]) {
  private val assocs = HashMap[(Card, Int), CardInfo]()
  private var i = 1
  private var reds = 0
  private var blacks = 0
  private var hearts = 0
  private var spades = 0
  private var diamonds = 0
  private var clubs = 0
  _cards.foreach((card : Card) => {
    if (card.isRed)
      reds += 1
    if (card.isBlack)
      blacks += 1
    card.suite match {
      case Hearts   => hearts += 1
      case Spades   => spades += 1
      case Diamonds => diamonds += 1
      case Clubs    => clubs += 1
    }
    assocs += ((card, i) -> CardInfo(clubs, hearts, spades, diamonds, reds, blacks))
    i += 1
  })

  def getAssociations = assocs

  def find(f : Function1[((Card, Int), CardInfo), Boolean]) : Option[((Card, Int), CardInfo)] =
    assocs.find(f)

  def size = assocs.size

  def isEmpty = assocs.isEmpty
}

object CardAssociation {
  def apply(cards : List[Card]) = new CardAssociation(cards)

  val misas = new CardAssociation(List(
    Card(11, Hearts), Card(6, Clubs), Card(6, Hearts), Card(4, Clubs),
    Card(10, Diamonds), Card(1, Diamonds), Card(7, Clubs), Card(4, Hearts),
    Card(9, Clubs), Card(5, Diamonds), Card(12, Hearts), Card(1, Spades),
    Card(13, Clubs), Card(7, Hearts), Card(10, Spades), Card(4, Spades),
    Card(11, Spades), Card(9, Hearts), Card(13, Diamonds), Card(5, Spades),
    Card(7, Spades), Card(2, Clubs), Card(12, Clubs), Card(1, Hearts),
    Card(10, Hearts), Card(6, Spades), Card(9, Spades), Card(7, Diamonds),
    Card(12, Diamonds), Card(5, Hearts), Card(13, Hearts), Card(4, Diamonds),
    Card(3, Clubs), Card(3, Hearts), Card(10, Clubs), Card(9, Diamonds),
    Card(12, Spades), Card(3, Spades), Card(3, Diamonds), Card(2, Hearts),
    Card(8, Clubs), Card(2, Spades), Card(11, Clubs), Card(2, Diamonds),
    Card(8, Hearts), Card(8, Spades), Card(13, Spades), Card(1, Clubs),
    Card(11, Diamonds), Card(5, Clubs), Card(8, Diamonds), Card(6, Diamonds)
  ))
}
