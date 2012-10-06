package org.abstractbinary.unshuffle

import _root_.android.app.DialogFragment
import _root_.android.os.Bundle
import _root_.android.view.{LayoutInflater, View, ViewGroup}

import TypedResource._

/* Table:
          C  H  S  D    Red  Black
JH  1     0  1  0  0    1     0
6C  2     1  1  0  0    1     1
6H  3     1  2  0  0    2     1
4C  4     2  2  0  0    2     2
10D 5     2  2  0  1    3     2
AD  6     2  2  0  2    4     2
7C  7     3  2  0  2    4     3
4H  8     3  3  0  2    5     3
9C  9     4  3  0  2    5     4
5D  10    4  3  0  3    6     4
QH  11    4  4  0  3    7     4
AS  12    4  4  1  3    7     5
KC  13    5  4  1  3    7     6
7H  14    5  5  1  3    8     6
10S 15    5  5  2  3    8     7
4S  16    5  5  3  3    8     8
JS  17    5  5  4  3    8     9
9H  18    5  6  4  3    9     9
KD  19    5  6  4  4    10    9
5S  20    5  6  5  4    10    10
7S  21    5  6  6  4    10    11
2C  22    6  6  6  4    10    12
QC  23    7  6  6  4    10    13
AH  24    7  7  6  4    11    13
10H 25    7  8  6  4    12    13
6S  26    7  8  7  4    12    14
9S  27    7  8  8  4    12    15
7D  28    7  8  8  5    13    15
QD  29    7  8  8  6    14    15
5H  30    7  9  8  6    15    15
KH  31    7  10 8  6    16    15
4D  32    7  10 8  7    17    15
3C  33    8  10 8  7    17    16
3H  34    8  11 8  7    18    16
10C 35    9  11 8  7    18    17
9D  36    9  11 8  8    19    17
QS  37    9  11 9  8    19    18
3S  38    9  11 10 8    19    19
3D  39    9  11 10 9    20    19
2H  40    9  12 10 9    21    19
8C  41    10 12 10 9    21    20
2S  42    10 12 11 9    21    21
JC  43    11 12 11 9    21    22
2D  44    11 12 11 10   22    22
8H  45    11 13 11 10   23    22
8S  46    11 13 12 10   23    23
KS  47    11 13 13 10   23    24
AC  48    12 13 13 10   23    25
JD  49    12 13 13 11   24    25
5C  50    13 13 13 11   24    26
8D  51    13 13 13 12   25    26
6D  52    13 13 13 13   26    26
*/

// A dialog that gathers and validates the information for a new item.
class DetailsDialog extends DialogFragment
  with ViewOnClickListener
  with ToastableFragment
{
  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    inflater.inflate(R.layout.details_dialog, container, false)
  }

  override def onStart = {
    super.onStart
    // getDialog.setTitle(R.string.new_item);
  }
}

object DetailsDialog {
  def forCard(card : Card) = {
    new DetailsDialog
  }

  def forNumber(num : Int) = {
    new DetailsDialog
  }
}
