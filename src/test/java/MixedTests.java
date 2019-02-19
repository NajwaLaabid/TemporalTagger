import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class MixedTests {
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
    public void mixed1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 12/05/2018 ate o dia 13/05/2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("12/05/2018", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("13/05/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do mes do Janeiro ate o mes do maio");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ano do 2015 ate o 2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2015", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed4() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do fevereiro do ano do 2015 ate o 15 do abril 2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/02/2015", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("15/04/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed5() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do 2 do fevereiro do ano do 2015 ate o 15 do abril 2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("02/02/2015", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("15/04/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed6() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do 2 do marco do ano do 2015 ate o 12/03/2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("02/03/2015", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("12/03/2016", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed7() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do 31 do FEVEReiRo do ano do 2015 ate o dia 12/03/2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("28/02/2015", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("12/03/2016", dtf), TempoPeriod.getFinalDateFormatted());
    }

//    @Test
//    public void mixed11() {
//        TempoPeriod TempoPeriod = periodParser.parse("Como foram as vendas do dia 02/02/2019 até o dia 02/05/2019. " +
//                "Amanhã, me manda o relatorio por favor.");
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        assertEquals(tomorrow, TempoPeriod.getInitialDateFormatted());
//        assertEquals(LocalDate.parse("02/02/2019", dtf), TempoPeriod.getFinalDateFormatted());
//    }
}
