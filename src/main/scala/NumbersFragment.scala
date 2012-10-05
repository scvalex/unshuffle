package org.abstractbinary.unshuffle

import _root_.android.app.Fragment
import _root_.android.os.Bundle
import _root_.android.view.{LayoutInflater, View, ViewGroup}
import _root_.android.widget.{ArrayAdapter, GridView}

class NumbersFragment extends Fragment {
  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val nums = new java.util.ArrayList[Int](52)
    (1).to(52).foreach(nums.add(_))
    val adapter = new ArrayAdapter[Int](getActivity, android.R.layout.simple_list_item_1, nums)
    val grid = new GridView(getActivity)
    grid.setAdapter(adapter)
    grid
  }
}
