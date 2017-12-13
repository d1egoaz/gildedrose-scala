package com.gildedrose

// ಠ_ಠ var!! ¯\_(ツ)_/¯
case class Item(val name: String, var sellIn: Int, var quality: Int) {
  override def toString(): String = s"$name, $sellIn, $quality"
}
