package org.abstractbinary.unshuffle

import _root_.android.app.Fragment
import _root_.android.os.Bundle
import _root_.android.view.{LayoutInflater, View, ViewGroup}
import _root_.android.widget.{AdapterView, ArrayAdapter, GridView}

class CardsFragment extends Fragment
  with ToastableFragment
  with AdapterViewOnItemClickListener
{
  def handleCardClicked(card : String) : Unit = {
    toast("%s clicked".format(card))
  }

  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val cards = new java.util.ArrayList[String](52)
    for (suite <- List("C", "S", "H", "D")) {
      cards.add("A" + suite)
    }
    for (num <- 2 to 10;
         suite <- List("C", "S", "H", "D")) {
      cards.add(num.toString + suite)
    }
    for (num <- List("J", "Q", "K");
         suite <- List("C", "S", "H", "D")) {
      cards.add(num + suite)
    }
    val adapter = new ArrayAdapter[String](getActivity, R.layout.grid_cell, cards)
    val grid = new GridView(getActivity)
    grid.setAdapter(adapter)
    grid.setNumColumns(4)
    grid.setOnItemClickListener((adapter : AdapterView[_], v : View, pos : Int, id : Long) => {
      handleCardClicked(cards.get(pos))
    })
    grid
  }
}
