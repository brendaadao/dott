package handler

import domain.Order

import java.text.SimpleDateFormat
import java.util.Date
import scala.collection.mutable.ListBuffer

object SalesService {
  private val dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private val orders: ListBuffer[Order] = ListBuffer();
  private var from: Date = new Date();
  private var to: Date = new Date();

  def main(args: Array[String]) = {
    this.from = dateFormat.parse(args(0));
    this.to = dateFormat.parse(args(1));

    val orderInstance = new Order();
    orderInstance.createOrders(orders);

    var firstRange: Int = 0;
    var secondRange: Int = 0;
    var thirdRange: Int = 0;
    var fourthRange: Int = 0;

    var range = 0;
    var interval = orders.length / 4;
    if (args.length == 3) {
      range = args(2).toInt;
    } else {
      range = 0;
    }
    val firstInterval: checkInterval = new checkInterval(orders.take(interval), from, to, range);
    val secondInterval: checkInterval = new checkInterval(orders.drop(interval).take(interval), from, to, range);
    val thirdInterval: checkInterval = new checkInterval(orders.drop(2 * interval).take(interval), from, to, range);
    val fourthInterval: checkInterval = new checkInterval(orders.drop(3 * interval), from, to, range);

    for (set <- 0 to 3) {
      set match {
        case 0 => firstRange = firstInterval.getCount()(0) + secondInterval.getCount()(0) +
          thirdInterval.getCount()(0) + fourthInterval.getCount()(0);
        case 1 => secondRange = firstInterval.getCount()(1) + secondInterval.getCount()(1) +
          thirdInterval.getCount()(1) + fourthInterval.getCount()(1);
        case 2 => thirdRange = firstInterval.getCount()(2) + secondInterval.getCount()(2) +
          thirdInterval.getCount()(2) + fourthInterval.getCount()(2);
        case 3 => fourthRange = firstInterval.getCount()(3) + secondInterval.getCount()(3) +
          thirdInterval.getCount()(3) + fourthInterval.getCount()(3);
      }
    }

    if (args.length == 3) {
      val range = args.length;
      println("\n\n1-" + range + " months: " + firstRange + " orders")
      println(range + 1 + "-" + range * 2 + " months: " + secondRange + " orders")
      println(range * 2 + 1 + "-" + range * 3 + " months: " + thirdRange + " orders")
      println(">" + (range * 3 + 1) + " months: " + fourthRange + " orders")
    } else {
      println("\n\n1-3  months: " + firstRange + " orders")
      println("4-6  months: " + secondRange + " orders")
      println("7-12 months: " + thirdRange + " orders")
      println(">12  months: " + fourthRange + " orders")
    }

  }

  class checkInterval(orders: ListBuffer[Order], from: Date, to: Date, range: Int) {
    var firstRange: Int = 0;
    var secondRange: Int = 0;
    var thirdRange: Int = 0;
    var fourthRange: Int = 0;

    orders.foreach(order => {
      val orderDate = dateFormat.parse(order.createdAt);

      if (this.to.getTime() > orderDate.getTime() && orderDate.getTime() > this.from.getTime()) {
        order.items.foreach(item => {
          val itemsDate = dateFormat.parse(item.product.createdAt);

          val nMonths = ((new Date().getYear() - itemsDate.getYear) * 12) +
            ((12 - (itemsDate.getMonth() - 1) + (new Date().getMonth() - 12)));

          if (range != 0) {
            if (nMonths < range) {
              firstRange += 1;
            } else if (nMonths < range * 2) {
              secondRange += 1;
            } else if (nMonths < range * 3) {
              thirdRange += 1;
            } else {
              fourthRange += 1;
            }
          } else {
            if (nMonths < 3) {
              firstRange += 1;
            } else if (nMonths < 6) {
              secondRange += 1;
            } else if (nMonths < 12) {
              thirdRange += 1;
            } else {
              fourthRange += 1;
            }
          }

        })
      }
    })
    def getCount(): List[Int] = {
      return List(firstRange, secondRange, thirdRange, fourthRange);
    }
  }
}

