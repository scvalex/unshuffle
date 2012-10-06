package org.abstractbinary.unshuffle

import _root_.android.app.Fragment
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{LayoutInflater, View, ViewGroup}
import _root_.android.widget.{AdapterView, ArrayAdapter, GridView}

class CardsFragment extends Fragment
  with ToastableFragment
  with AdapterViewOnItemClickListener
{
  val TAG = "CardsFragment"

  val suites = List(Clubs, Spades, Hearts, Diamonds)

  val cards = {
    val cards = new java.util.ArrayList[Card](52)
    for (num <- 1 to 13;
         suite <- suites) {
      cards.add(Card(num, suite))
    }
    cards
  }

  def showDetailsDialog(card : Card) : Unit = {
    val ft = getFragmentManager.beginTransaction
    DetailsDialog.forCard(card) match {
      case Some(dialog) => dialog.show(ft, "card: %s".format(card.toString))
      case None => toast("No such card: %s".format(card.toString))
    }
  }

  def handleCardClicked(card : Card) : Unit = {
    Log.i(TAG, "%s clicked".format(card.toString))
    showDetailsDialog(card)
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
