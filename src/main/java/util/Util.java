package util;
import java.time.LocalDate;
import java.util.Hashtable;

public class Util {

    public static int getMonthInt(String month) {
        Hashtable<String, Integer> monthNames = new Hashtable<>();

        monthNames.put("jan", 1);
        monthNames.put("fev", 2);
        monthNames.put("mar", 3);
        monthNames.put("abr", 4);
        monthNames.put("mai", 5);
        monthNames.put("jun", 6);
        monthNames.put("jul", 7);
        monthNames.put("ago", 8);
        monthNames.put("set", 9);
        monthNames.put("out", 10);
        monthNames.put("nov", 11);
        monthNames.put("dez", 12);

        return (monthNames.get(getMonthIdx(month)) != null? monthNames.get(getMonthIdx(month)):-1);
    }

    public static String getMonthIdx(String month){
        return month.substring(0, 3).toLowerCase();
    }

    public static int getYearForLastMonthName(int lastMonth) {
        if(lastMonth > LocalDate.now().getMonthValue()) {
            return LocalDate.now().getYear() - 1;
        }

        return LocalDate.now().getYear();
    }

    public static int getYearForNextMonthName(int nextMonth) {
        if(nextMonth < LocalDate.now().getMonthValue()) {
            return LocalDate.now().getYear() + 1;
        }

        return LocalDate.now().getYear();
    }

}
