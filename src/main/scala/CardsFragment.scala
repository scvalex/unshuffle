package org.abstractbinary.unshuffle

import _root_.android.app.Fragment
import _root_.android.os.Bundle
import _root_.android.view.{LayoutInflater, View, ViewGroup}
import _root_.android.widget.{AdapterView, ArrayAdapter, GridView}

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

class CardsFragment extends Fragment
  with ToastableFragment
  with AdapterViewOnItemClickListener
{
  val suites = List(Clubs, Spades, Hearts, Diamonds)

  val cards = {
    val cards = new java.util.ArrayList[Card](52)
    for (num <- 1 to 13;
         suite <- suites) {
      cards.add(new Card(num, suite))
    }
    cards
  }

  def handleCardClicked(card : Card) : Unit = {
    toast("%s clicked".format(card.toString))
  }

  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val adapter = new ArrayAdapter[Card](getActivity, R.layout.grid_cell, cards)
    val grid = new GridView(getActivity)
    grid.setAdapter(adapter)
    grid.setNumColumns(4)
    grid.setOnItemClickListener((adapter : AdapterView[_], v : View, pos : Int, id : Long) => {
      handleCardClicked(cards.get(pos))
    })
    grid
  }
}
