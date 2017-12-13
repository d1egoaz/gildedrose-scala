package com.gildedrose

import org.scalatest._

class GildedRoseTest extends FunSuite {
  private def createItems(): Array[Item] = Array(
      Item("+5 Dexterity Vest",                         sellIn = 10, quality = 20),
      Item("Aged Brie",                                 sellIn = 2,  quality = 0),
      Item("Elixir of the Mongoose",                    sellIn = 5,  quality = 7),
      Item("Sulfuras, Hand of Ragnaros",                sellIn = 0,  quality = 80),
      Item("Sulfuras, Hand of Ragnaros",                sellIn = -1, quality = 80),
      Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 15, quality = 20),
      Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 10, quality = 49),
      Item("Backstage passes to a TAFKAL80ETC concert", sellIn = 5,  quality = 49),
      Item("Conjured Mana Cake",                        sellIn = 3,  quality = 6)
    )

  test("after 4 days all items included Conjured should work propertly") {
    val items = createItems()
    val app = new GildedRose(items)
    val days: Int = 4
    app.run(days)
    assert(items(0) === Item("+5 Dexterity Vest", 6, 16))
    assert(items(1) === Item("Aged Brie", -2, 6))
    assert(items(2) === Item("Elixir of the Mongoose", 1, 3))
    assert(items(3) === Item("Sulfuras, Hand of Ragnaros", 0, 80))
    assert(items(4) === Item("Sulfuras, Hand of Ragnaros", -1, 80))
    assert(items(5) === Item("Backstage passes to a TAFKAL80ETC concert", 11, 24))
    assert(items(6) === Item("Backstage passes to a TAFKAL80ETC concert", 6, 50))
    assert(items(7) === Item("Backstage passes to a TAFKAL80ETC concert",1, 50))
    assert(items(8) === Item("Conjured Mana Cake", -1, 0))
  }

  test("after 30 days all items included Conjured should work propertly") {
    val items = createItems()
    val app = new GildedRose(items)
    val days: Int = 30
    app.run(days)
    assert(items(0) === Item("+5 Dexterity Vest", -20, 0))
    assert(items(1) === Item("Aged Brie", -28, 50))
    assert(items(2) === Item("Elixir of the Mongoose", -25, 0))
    assert(items(3) === Item("Sulfuras, Hand of Ragnaros", 0, 80))
    assert(items(4) === Item("Sulfuras, Hand of Ragnaros", -1, 80))
    assert(items(5) === Item("Backstage passes to a TAFKAL80ETC concert", -15, 0))
    assert(items(6) === Item("Backstage passes to a TAFKAL80ETC concert", -20, 0))
    assert(items(7) === Item("Backstage passes to a TAFKAL80ETC concert", -25, 0))
    assert(items(8) === Item("Conjured Mana Cake", -27, 0))
  }
}
