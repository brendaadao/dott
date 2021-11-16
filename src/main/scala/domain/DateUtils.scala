package domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.ThreadLocalRandom

object DateUtils {
  def createRandomDate(): String = {
    val pattern: String = "yyyy-MM-dd HH:mm:ss";
    val simpleDateFormat = new SimpleDateFormat(pattern);

    val startDate = "2021-01-01 00:00:00";
    val startDateParsed = simpleDateFormat.parse(startDate).getTime();

    val endDate = "2022-01-01 00:00:00";
    val endDateParsed = simpleDateFormat.parse(endDate).getTime();

    val randomDateTime: Date = new Date(ThreadLocalRandom.current().nextLong(startDateParsed, endDateParsed));
    val result = simpleDateFormat.format(randomDateTime);
//    println("result dateUtils", result);
    return result;
  }
}
