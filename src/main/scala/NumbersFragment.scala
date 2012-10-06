package org.abstractbinary.unshuffle

import _root_.android.app.Fragment
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{LayoutInflater, View, ViewGroup}
import _root_.android.widget.{AdapterView, ArrayAdapter, GridView}

class NumbersFragment extends Fragment
  with ToastableFragment
  with AdapterViewOnItemClickListener
{
  val TAG = "NumbersFragment"

  def showDetailsDialog(num : Int) : Unit = {
    val ft = getFragmentManager.beginTransaction
    DetailsDialog.forNumber(num) match {
      case Some(dialog) => dialog.show(ft, "number: %d".format(num))
      case None => toast("No such number: %d".format(num.toString))
    }
  }

  def handleNumberClicked(num : Int) : Unit = {
    Log.i(TAG, "%d clicked".format(num))
    showDetailsDialog(num)
  }

  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val nums = new java.util.ArrayList[Int](52)
    (1).to(52).foreach(nums.add(_))
    val adapter = new ArrayAdapter[Int](getActivity, R.layout.grid_cell, nums)
    val grid = new GridView(getActivity)
    grid.setAdapter(adapter)
    grid.setNumColumns(4)
    grid.setOnItemClickListener((adapter : AdapterView[_], v : View, pos : Int, id : Long) => {
      handleNumberClicked(id.toInt + 1)
    })
    grid
  }
}
