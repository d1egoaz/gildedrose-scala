package com.gildedrose

object TexttestFixture extends App {
  val items = Array[Item](
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

  val app = new GildedRose(items)

  val days: Int = 30

  for (i <- 0 to days) {
    println(s"-------- day $i --------")
    println("name, sellIn, quality")
    items.foreach (println)
    println()
    app.updateQuality()
  }
}
