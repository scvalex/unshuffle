package org.abstractbinary.unshuffle

import _root_.android.app.Fragment
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{LayoutInflater, View, ViewGroup}
import _root_.android.widget.GridView

class AssociationsFragment extends Fragment
  with ToastableFragment
  with AdapterViewOnItemClickListener
{
  val TAG = "AssociationsFragment"

  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val adapter = new AssociationsAdapter(getActivity, R.layout.grid_cell, CardAssociation.misas)
    val grid = new GridView(getActivity)
    grid.setAdapter(adapter)
    grid.setNumColumns(adapter.getColumns)
    grid
  }
}
