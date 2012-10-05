package org.abstractbinary.unshuffle

import _root_.android.app.{Activity, Dialog}
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{Menu, MenuItem, View}
import _root_.android.widget.{EditText, Toast}

import java.util.Calendar

class Unshuffle extends Activity with TypedActivity
  with Toastable
{
  val TAG = "Unshuffle"

  override def onCreate(bundle : Bundle) = {
    super.onCreate(bundle)
    setContentView(R.layout.unshuffle)
  }
}
