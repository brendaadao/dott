package domain

import domain.DateUtils.createRandomDate
import scala.util.Random

class Product (var name: String, var category: String, var weight: Double, var price: Double, var createdAt: String) {

  val productNames: List[String] = List("Chardonnay","Colorado","Jose Cuervo", "Jack Daniels");
  val productCategories: List[String] = List("Wine","Beer","Tequila","Whiskey");

  def this() = {
    this("", "", 0.0, 0.00, createRandomDate())

    var randomInstance = new Random();

    this.name = productNames(randomInstance.nextInt(productNames.length));
    this.category = productCategories(randomInstance.nextInt(productCategories.length));
    this.weight = randomInstance.nextDouble() * 5;
    this.price = BigDecimal(150 * randomInstance.nextDouble()).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble;
    this.createdAt = createRandomDate();
  }
}
