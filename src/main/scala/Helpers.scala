package org.abstractbinary.unshuffle

import _root_.android.app.{Activity, Dialog, Fragment}
import _root_.android.view.View
import _root_.android.widget.{AdapterView, Toast}

trait ToastableFragment extends Fragment {
  def toast(text : String) = {
    getActivity.asInstanceOf[Toastable].toast(text)
  }
}

trait Toastable extends Activity {
  // Pop-up a short @Toast@ notification with the given text.
  def toast(text : String) = {
    val context = getApplicationContext
    Toast.makeText(context, text, Toast.LENGTH_LONG).show
  }
}

trait ViewOnClickListener {
  // We use functions instead of @OnClickListeners@.
  implicit def function2ViewOnClickListener(f : View => Unit) = {
    new View.OnClickListener {
      def onClick(view : View) {
        f(view)
      }
    }
  }
}

trait AdapterViewOnItemClickListener {
  // We use functions instead of @OnClickListeners@.
  implicit def function2AdapterViewOnItemClickListener(f : (AdapterView[_], View, Int, Long)  => Unit) = {
    new AdapterView.OnItemClickListener {
      def onItemClick(adapterView : AdapterView[_], view : View, position : Int, id : Long) {
        f(adapterView, view, position, id)
      }
    }
  }
}
