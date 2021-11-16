package domain

import scala.util.Random

class Item(var cost: Double, var amount: Int, var shipping: Double, var tax: Double, var product: Product) {

  def this() = {
    this(0.00, 0, 0.00, 0.00, new Product());

    this.amount = new Random().nextInt(5)+1;
    this.shipping = 4.99;
    this.tax = this.product.price * 0.10;
    this.cost = (this.amount * this.product.price) + this.tax;
  }
}
