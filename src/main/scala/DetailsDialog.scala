package org.abstractbinary.unshuffle

import _root_.android.app.DialogFragment
import _root_.android.os.Bundle
import _root_.android.util.Log
import _root_.android.view.{LayoutInflater, View, ViewGroup}

import TypedResource._

case class CardInfo(c : Int, h : Int, s : Int, d : Int, red : Int, black : Int)

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
class DetailsDialog(card : Card, number : Int, cardInfo : CardInfo) extends DialogFragment
  with ViewOnClickListener
  with ToastableFragment
{
  override def onCreateView(inflater : LayoutInflater,
                            container : ViewGroup,
                            savedInstanceState : Bundle) : View =
  {
    val view = inflater.inflate(R.layout.details_dialog, container, false)
    view.findView(TR.red_count).setText(cardInfo.red.toString)
    view.findView(TR.black_count).setText(cardInfo.black.toString)
    view
  }

  override def onStart = {
    super.onStart
    getDialog.setTitle("%d / %s".format(number, card.toString));
  }
}

object DetailsDialog {
  val TAG = "DetailsDialog"

  // FIXME: I think only the order of the cards is relevant here.
  val assocs = Map(
    (Card(11, Hearts), 1) -> CardInfo(0, 1, 0, 0, 1, 0),
    (Card(6, Clubs), 2) -> CardInfo (1, 2, 0, 0, 1, 1),
    (Card(6, Hearts), 3) -> CardInfo(1, 2, 0, 0, 2, 1),
    (Card(4, Clubs), 4) -> CardInfo(2, 2, 0, 0, 2, 2),
    (Card(10, Diamonds), 5) -> CardInfo(2, 2, 0, 1, 3, 2),
    (Card(1, Diamonds), 6) -> CardInfo(2, 2, 0, 2, 4, 2),
    (Card(7, Clubs), 7) -> CardInfo(3, 2, 0, 2, 4, 3),
    (Card(4, Hearts), 8) -> CardInfo(3, 3, 0, 2, 5, 3),
    (Card(9, Clubs), 9) -> CardInfo(4, 3, 0, 2, 5, 4),
    (Card(5, Diamonds), 10) -> CardInfo(4, 3, 0, 3, 6, 4),
    (Card(12, Hearts), 11) -> CardInfo(4, 4, 0, 3, 7, 4),
    (Card(1, Spades), 12) -> CardInfo(4, 4, 1, 3, 7, 5),
    (Card(13, Clubs), 13) -> CardInfo(5, 4, 1, 3, 7, 6),
    (Card(7, Hearts), 14) -> CardInfo(5, 5, 1, 3, 8, 6),
    (Card(10, Spades), 15) -> CardInfo(5, 5, 2, 3, 8, 7),
    (Card(4, Spades), 16) -> CardInfo(5, 5, 3, 3, 8, 8),
    (Card(11, Spades), 17) -> CardInfo(5, 5, 4, 3, 8, 9),
    (Card(9, Hearts), 18) -> CardInfo(5, 6, 4, 3, 9, 9),
    (Card(13, Diamonds), 19) -> CardInfo(5, 6, 4, 4, 10, 9),
    (Card(5, Spades), 20) -> CardInfo(5, 6, 5, 4, 10, 10),
    (Card(7, Spades), 21) -> CardInfo(5, 6, 6, 4, 10, 11),
    (Card(2, Clubs), 22) -> CardInfo(6, 6, 6, 4, 10, 12),
    (Card(12, Clubs), 23) -> CardInfo(7, 6, 6, 4, 10, 13),
    (Card(1, Hearts), 24) -> CardInfo(7, 7, 6, 4, 11, 13),
    (Card(10, Hearts), 25) -> CardInfo(7, 8, 6, 4, 12, 13),
    (Card(6, Spades), 26) -> CardInfo(7, 8, 7, 4, 12, 14),
    (Card(9, Spades), 27) -> CardInfo(7, 8, 8, 4, 12, 15),
    (Card(7, Diamonds), 28) -> CardInfo(7, 8, 8, 5, 13, 15),
    (Card(12, Diamonds), 29) -> CardInfo(7, 8, 8, 6, 14, 15),
    (Card(5, Hearts), 30) -> CardInfo(7, 9, 8, 6, 15, 15),
    (Card(13, Hearts), 31) -> CardInfo(7, 10, 8, 6, 16, 15),
    (Card(4, Diamonds), 32) -> CardInfo(7, 10, 8, 7, 17, 15),
    (Card(3, Clubs), 33) -> CardInfo(8, 10, 8, 7, 17, 16),
    (Card(3, Hearts), 34) -> CardInfo(8, 11, 8, 7, 18, 16),
    (Card(10, Clubs), 35) -> CardInfo(9, 11, 8, 7, 18, 17),
    (Card(9, Diamonds), 36) -> CardInfo(9, 11, 8, 8, 19, 17),
    (Card(12, Spades), 37) -> CardInfo(9, 11, 9, 8, 19, 18),
    (Card(3, Spades), 38) -> CardInfo(9, 11, 10, 8, 19, 19),
    (Card(3, Diamonds), 39) -> CardInfo(9, 11, 10, 9, 20, 19),
    (Card(2, Hearts), 40) -> CardInfo(9, 12, 10, 9, 21, 19),
    (Card(8, Clubs), 41) -> CardInfo(10, 12, 10, 9, 21, 20),
    (Card(2, Spades), 42) -> CardInfo(10, 12, 11, 9, 21, 21),
    (Card(11, Clubs), 43) -> CardInfo(11, 12, 11, 9, 21, 22),
    (Card(2, Diamonds), 44) -> CardInfo(11, 12, 11, 10, 22, 22),
    (Card(8, Hearts), 45) -> CardInfo(11, 13, 11, 10, 23, 22),
    (Card(8, Spades), 46) -> CardInfo(11, 13, 12, 10, 23, 23),
    (Card(13, Spades), 47) -> CardInfo(11, 13, 13, 10, 23, 24),
    (Card(1, Spades), 48) -> CardInfo(12, 13, 13, 10, 23, 25),
    (Card(11, Diamonds), 49) -> CardInfo(12, 14, 14, 11, 24, 25),
    (Card(5, Clubs), 50) -> CardInfo(13, 13, 13, 11, 24, 26),
    (Card(8, Diamonds), 51) -> CardInfo(13, 13, 13, 12, 25, 26),
    (Card(6, Diamonds), 52) -> CardInfo(13, 13, 13, 13, 26, 26)
  )

  def forCard(card : Card) = {
    val cardInfo = assocs.find((elem) => {
      val ((card1, num), value) = elem
      card1 == card
    })
    cardInfo.map((elem) => {
      val ((card1, num), value) = elem
      new DetailsDialog(card, num, value)
    })
  }

  def forNumber(num : Int) = {
    val cardInfo = assocs.find((elem) => {
      val ((card, num1), value) = elem
      num1 == num
    })
    cardInfo.map((elem) => {
      val ((card, num1), value) = elem
      new DetailsDialog(card, num, value)
    })
  }
}
