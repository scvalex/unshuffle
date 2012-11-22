package org.abstractbinary.unshuffle

import _root_.android.app.Activity
import _root_.android.content.Intent
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{Menu, MenuItem}

class Unshuffle extends Activity
  with TypedActivity
  with Toastable
{
  val TAG = "Unshuffle"

  override def onCreate(bundle : Bundle) : Unit = {
    super.onCreate(bundle)
    setContentView(R.layout.unshuffle)
  }

  override def onCreateOptionsMenu(menu : Menu) = {
    val inflater = getMenuInflater
    inflater.inflate(R.menu.unshuffle, menu)
    true
  }

  override def onOptionsItemSelected(item : MenuItem) = {
    item.getItemId match {
      case R.id.associations => {
        startActivity(new Intent(this, classOf[AssociationsActivity]))
        true
      }
      case _ => {
        super.onOptionsItemSelected(item)
      }
    }
  }
}
