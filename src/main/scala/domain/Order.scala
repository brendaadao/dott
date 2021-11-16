package domain

import domain.DateUtils.createRandomDate
import scala.collection.mutable.ListBuffer
import scala.util.Random

class Order(var customerName: String, var customerContact: String, var address: String, var total: Double,
            var createdAt: String, var items: ListBuffer[Item] ) {

  def this() = {
    this("Brenda", "+5511123456789", "R. Henrique Sertorio", 500.00, createRandomDate(), ListBuffer());
    this.createdAt = createRandomDate();
    this.items = randomItems();
  }

  def getCustomerName(): String = {
    return this.customerName;
  }

  def randomItems(): ListBuffer[Item] = {
    var itemsList: ListBuffer[Item] = ListBuffer();

    for(count <- 1 to (new Random().nextInt(7)+1)) {
      var item = new Item();
      itemsList.append(item);
    }
    return itemsList;
  }

  def createOrders(orders: ListBuffer[Order]) = {
    for(order <- 1 to 200){
      orders.append(new Order());
    }
  }
}