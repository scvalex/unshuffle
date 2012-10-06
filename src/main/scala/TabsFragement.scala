package org.abstractbinary.unshuffle

import _root_.android.app.{Activity, Fragment}
import _root_.android.content.Context
import _root_.android.util.Log
import _root_.android.os.Bundle
import _root_.android.support.v13.app.FragmentPagerAdapter
import _root_.android.support.v4.view.ViewPager
import _root_.android.view.{LayoutInflater, View, ViewGroup}
import _root_.android.widget.{TabHost, TabWidget}

case class TabInfo(tag : String, klass : Class[_], args : Bundle)

class DummyTabFactory(context : Context) extends TabHost.TabContentFactory {
  override def createTabContent(tag : String) : View = {
    val v = new View(context)
    v.setMinimumWidth(0)
    v.setMinimumHeight(0)
    v
  }
}

class TabsAdapter(activity : Activity, tabHost : TabHost, pager : ViewPager)
  extends FragmentPagerAdapter(activity.getFragmentManager)
  with TabHost.OnTabChangeListener
  with ViewPager.OnPageChangeListener
{
  var tabs = List[TabInfo]()

  tabHost.setOnTabChangedListener(this)
  pager.setAdapter(this)
  pager.setOnPageChangeListener(this)

  def addTab(tabSpec : TabHost#TabSpec, klass : Class[_], args : Bundle) : Unit = {
    tabSpec.setContent(new DummyTabFactory(activity))

    val info = TabInfo(tabSpec.getTag, klass, args)
    tabs = info :: tabs
    tabHost.addTab(tabSpec)
    notifyDataSetChanged
  }

  override def getCount() : Int = {
    tabs.size
  }

  override def getItem(position : Int) : Fragment = {
    val info = tabs(position)
    Fragment.instantiate(activity, info.klass.getName, info.args)
  }

  override def onTabChanged(tabId : String) : Unit = {
    pager.setCurrentItem(tabHost.getCurrentTab);
  }

  override def onPageScrolled(position : Int,
                              postitionOffset : Float,
                              positionOffsetPixels : Int) : Unit =
  {
  }

  override def onPageSelected(position : Int) : Unit = {
    val widget = tabHost.getTabWidget
    val oldFocusability = widget.getDescendantFocusability
    widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS)
    tabHost.setCurrentTab(position)
    widget.setDescendantFocusability(oldFocusability)
  }

  override def onPageScrollStateChanged(state : Int) : Unit = {
  }
}

class TabsFragment extends Fragment {
    override def onCreateView(inflater : LayoutInflater,
                              container : ViewGroup,
                              savedInstanceState : Bundle) : View =
    {
      val view = inflater.inflate(R.layout.fragment_tabs_pager, container, false)
      val tabHost = view.findViewById(android.R.id.tabhost).asInstanceOf[TabHost]
      tabHost.setup

      val viewPager = view.findViewById(R.id.pager).asInstanceOf[ViewPager]

      val tabsAdapter = new TabsAdapter(getActivity, tabHost, viewPager)

      tabsAdapter.addTab(tabHost.newTabSpec("numbers").setIndicator("Numbers"),
                         classOf[NumbersFragment], null);
      tabsAdapter.addTab(tabHost.newTabSpec("cards").setIndicator("Cards"),
                         classOf[CardsFragment], null);

      view
    }
}
