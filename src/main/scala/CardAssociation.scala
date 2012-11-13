package org.abstractbinary.unshuffle

import scala.collection.mutable.HashMap

case class CardInfo(clubs : Int, hearts : Int, spades : Int, diamonds : Int, red : Int, black : Int)

class CardAssociation(cards : List[Card]) {
  private val assocs = HashMap[(Card, Int), CardInfo]()
  private var i = 1
  private var reds = 0
  private var blacks = 0
  private var hearts = 0
  private var spades = 0
  private var diamonds = 0
  private var clubs = 0
  cards.foreach((card : Card) => {
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
}

object CardAssociation {
  def apply(cards : List[Card]) = new CardAssociation(cards)
}
