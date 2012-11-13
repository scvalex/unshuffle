package org.abstractbinary.unshuffle

import _root_.android.content.Context
import _root_.android.widget.ArrayAdapter

import scala.collection.JavaConversions._

class AssociationsAdapter(ctx : Context, id : Int, assocs : CardAssociation)
  extends ArrayAdapter[((Card, Int), CardInfo)](ctx, id, assocs.getAssociations.toList)
{
}
