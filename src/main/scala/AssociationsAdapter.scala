package org.abstractbinary.unshuffle

import _root_.android.content.Context
import _root_.android.database.DataSetObserver
import _root_.android.util.Log
import _root_.android.view.{View, ViewGroup}
import _root_.android.widget.{ListAdapter, TextView}

class AssociationsAdapter(ctx : Context, id : Int, assocs : CardAssociation)
  extends ListAdapter
{
  val TAG = "AssociationsAdapter"

  private val columns = 8

  def getColumns : Int = columns

  def isEnabled(position : Int) : Boolean = true

  def areAllItemsEnabled : Boolean = true

  def getCount : Int = assocs.size * columns

  def isEmpty : Boolean = assocs.isEmpty

  def getViewTypeCount : Int = 1

  def getItemViewType(position : Int) : Int = 0

  def getView(position : Int, convertView : View, parent : ViewGroup) : View = {
    val view =
      if (convertView != null) {
        convertView.asInstanceOf[TextView]
      } else {
        new TextView(ctx)
      }
    view.setText(getStringAt(position))
    view
  }

  def hasStableIds() : Boolean = false

  def getItemId(position : Int) : Long = position

  def getItem(position : Int) : Object = getStringAt(position)

  def getStringAt(position : Int) : String = {
    Log.i(TAG, "Getting item at position %d".format(position))
    val numPos = position / columns
    val col = position % columns
    val cardAss = assocs.find((elem) => {
      val ((card, num), cardInfo) = elem
      num == (numPos + 1)
    })
    val ((card, num), cardInfo) = cardAss.get
    col match {
      case 0 => "%2d.".format(numPos + 1)
      case 1 => "%3s".format(card.toString)
      case 2 => "%2d".format(cardInfo.clubs)
      case 3 => "%2d".format(cardInfo.hearts)
      case 4 => "%2d".format(cardInfo.spades)
      case 5 => "%2d".format(cardInfo.diamonds)
      case 6 => "%2d".format(cardInfo.reds)
      case 7 => "%2d".format(cardInfo.blacks)
    }
  }

  def registerDataSetObserver(observer : DataSetObserver) : Unit = ()

  def unregisterDataSetObserver(observer : DataSetObserver) : Unit = ()
}
