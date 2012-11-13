package org.abstractbinary.unshuffle

import _root_.android.app.DialogFragment
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{LayoutInflater, View, ViewGroup}

import TypedResource._

// A dialog that gathers and validates the information for a new item.
class DetailsDialog(card : Card, number : Int, cardInfo : CardInfo) extends DialogFragment
  with ViewOnClickListener
  with ToastableFragment
{
  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val view = inflater.inflate(R.layout.details_dialog, container, false)
    view.findView(TR.red_count).setText(cardInfo.red.toString)
    view.findView(TR.black_count).setText(cardInfo.black.toString)
    view.findView(TR.clubs_count).setText(cardInfo.clubs.toString)
    view.findView(TR.spades_count).setText(cardInfo.spades.toString)
    view.findView(TR.hearts_count).setText(cardInfo.hearts.toString)
    view.findView(TR.diamonds_count).setText(cardInfo.diamonds.toString)
    view
  }

  override def onStart = {
    super.onStart
    getDialog.setTitle("%d / %s".format(number, card.toString));
  }
}

object DetailsDialog {
  val TAG = "DetailsDialog"

  val cardInfos = CardAssociation(List(
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
    Card(8, Hearts), Card(8, Spades), Card(13, Spades), Card(1, Spades),
    Card(11, Diamonds), Card(5, Clubs), Card(8, Diamonds), Card(6, Diamonds)
  ))

  def forCard(card : Card) = {
    val cardInfo = cardInfos.find((elem) => {
      val ((card1, num), value) = elem
      card1 == card
    })
    cardInfo.map((elem) => {
      val ((card1, num), value) = elem
      new DetailsDialog(card, num, value)
    })
  }

  def forNumber(num : Int) = {
    val cardInfo = cardInfos.find((elem) => {
      val ((card, num1), value) = elem
      num1 == num
    })
    cardInfo.map((elem) => {
      val ((card, num1), value) = elem
      new DetailsDialog(card, num, value)
    })
  }
}
