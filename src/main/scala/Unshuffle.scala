package org.abstractbinary.unshuffle

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.util.Log

class Unshuffle extends Activity with TypedActivity
  with Toastable
{
  val TAG = "Unshuffle"

  override def onCreate(bundle : Bundle) : Unit = {
    super.onCreate(bundle)
    setContentView(R.layout.unshuffle)
  }
}
