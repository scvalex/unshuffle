package org.abstractbinary.unshuffle

import _root_.android.app.DialogFragment
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{LayoutInflater, View, ViewGroup}

import TypedResource._

// A dialog that gathers and validates the information for a new item.
class DetailsDialog(_card : Card, _number : Int, _cardInfo : CardInfo) extends DialogFragment
  with ViewOnClickListener
  with ToastableFragment
{
  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val view = inflater.inflate(R.layout.details_dialog, container, false)
    view.findView(TR.red_count).setText(_cardInfo.red.toString)
    view.findView(TR.black_count).setText(_cardInfo.black.toString)
    view.findView(TR.clubs_count).setText(_cardInfo.clubs.toString)
    view.findView(TR.spades_count).setText(_cardInfo.spades.toString)
    view.findView(TR.hearts_count).setText(_cardInfo.hearts.toString)
    view.findView(TR.diamonds_count).setText(_cardInfo.diamonds.toString)
    view
  }

  override def onStart = {
    super.onStart
    getDialog.setTitle("%d / %s".format(_number, _card.toString));
  }
}

object DetailsDialog {
  val TAG = "DetailsDialog"

  val cardInfos = CardAssociation.misas

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
