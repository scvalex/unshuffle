package org.abstractbinary.unshuffle

import _root_.android.app.Activity
import _root_.android.os.Bundle
import _root_.android.util.Log

class AssociationsActivity extends Activity
  with TypedActivity
  with Toastable
{
  val TAG = "AssociationsActivity"

  override def onCreate(bundle : Bundle) : Unit = {
    super.onCreate(bundle)
    setContentView(R.layout.associations)
  }
}
