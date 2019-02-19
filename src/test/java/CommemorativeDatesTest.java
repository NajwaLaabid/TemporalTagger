import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class CommemorativeDatesTest {
    PeriodParser periodParser;
    LocalDate yesterday;
    LocalDate beforeYesterday;
    LocalDate afterTomorrow;
    LocalDate tomorrow;
    LocalDate today;

    LocalDate startWeek;
    LocalDate endWeek;

    int thisMonth;
    int lastMonth;
    int nextMonth;

    int thisYear;
    int lastYear;
    int nextYear;

    @Before
    public void setUp() {
        periodParser = new PeriodParser();
        today = LocalDate.now();
        yesterday = LocalDate.now().minusDays(1);
        beforeYesterday = LocalDate.now().minusDays(2);
        afterTomorrow = LocalDate.now().plusDays(2);
        tomorrow = LocalDate.now().plusDays(1);

        thisMonth = LocalDate.now().getMonthValue();
        lastMonth = LocalDate.now().minusMonths(1).getMonthValue();
        nextMonth = LocalDate.now().plusMonths(1).getMonthValue();

        thisYear = LocalDate.now().getYear();
        lastYear = LocalDate.now().minusYears(1).getYear();
        nextYear = LocalDate.now().plusYears(1).getYear();
    }

    @Test
    public void com1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do pascoa até hoje");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/04/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void com2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do pascoa até hoje");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/04/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void com3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia da mulher até o dia do trabalho");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("08/03/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/05/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void com4() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia de 01/02/2018 até o dia da mulher");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/02/2018", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("08/03/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }
}
