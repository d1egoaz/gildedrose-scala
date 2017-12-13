package com.gildedrose

class GildedRose(val items: Array[Item]) {

  def updateQuality(): Unit = {
    items.foreach (item => BaseItem.toCustomItem(item).update())
  }

  def run(days: Int): Unit = {
    for (_ <- 1 to days) {
      updateQuality()
    }
  }
}
