package com.gildedrose

abstract class BaseItem(item: Item) {
  private val maxQuality = 50
  protected val decreaseQualityAmount: Int = 1

  def update(): Unit = {
    // - At the end of each day our system lowers both values for every item
    item.sellIn -= 1
    updateQuality()

    // Once the sell by date has passed, Quality degrades twice as fast
    if (item.sellIn < 0) {
      updateQuality()
    }
  }

  // default behaviour is to decrease quality
  protected def updateQuality(): Unit = decreaseQuality()

  protected def decreaseQuality(): Unit =
    if (item.quality - decreaseQualityAmount < 0) {
      item.quality = 0
    } else if (item.quality >= decreaseQualityAmount) {
      item.quality -= decreaseQualityAmount
    }

  protected def increaseQuality(): Unit =
    // - The Quality of an item is never more than 50
    if (item.quality < maxQuality) {
      item.quality += 1
    }

  protected def increaseQuality(by: Int): Unit =
    for (_ <- 1 to by) increaseQuality()
}

object BaseItem {
  def toCustomItem(item: Item): BaseItem =
    item.name match {
      case "Aged Brie"                                 => AgedBrie(item)
      case "Backstage passes to a TAFKAL80ETC concert" => BackstagePasses(item)
      case "Sulfuras, Hand of Ragnaros"                => Sulfuras(item)
      case name if name.startsWith("Conjured")         => Conjured(item)
      case _                                           => NormalItem(item)
    }
}

case class NormalItem(item: Item) extends BaseItem(item)

case class AgedBrie(item: Item) extends BaseItem(item) {
  // - "Aged Brie" actually increases in Quality the older it gets
  override def updateQuality(): Unit = increaseQuality()
}

case class Sulfuras(item: Item) extends BaseItem(item) {
  // - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
  override def update(): Unit = {}
}

case class BackstagePasses(item: Item) extends BaseItem(item) {
  def increaseBackstageQuality(): Unit =
    if (item.sellIn <= 5) {
      // and by 3 when there are 5 days or less but
      increaseQuality(by = 3)
    } else if (item.sellIn <= 10) {
      // Quality increases by 2 when there are 10 days or less
      increaseQuality(by = 2)
    } else {
      increaseQuality()
    }

  override def updateQuality(): Unit =
    // - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    // but Quality drops to 0 after the concert
    if (item.sellIn < 0) {
      item.quality = 0
    } else {
      increaseBackstageQuality()
    }
}

case class Conjured(item: Item) extends BaseItem(item) {
  override val decreaseQualityAmount: Int = 2
}
