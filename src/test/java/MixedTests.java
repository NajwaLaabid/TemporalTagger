import model.Period;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
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
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 12/05/2018 ate o dia 13/05/2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("12/05/2018", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("13/05/2018", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed2() {
        Period biaPeriod = periodParser.parse("como estão as vendas do mes do Janeiro ate o mes do maio");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2018", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/2018", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed3() {
        Period biaPeriod = periodParser.parse("como estão as vendas do ano do 2015 ate o 2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2015", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2018", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed4() {
        Period biaPeriod = periodParser.parse("como estão as vendas do fevereiro do ano do 2015 ate o 15 do abril 2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/02/2015", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("15/04/2018", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed5() {
        Period biaPeriod = periodParser.parse("como estão as vendas do 2 do fevereiro do ano do 2015 ate o 15 do abril 2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("02/02/2015", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("15/04/2018", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed6() {
        Period biaPeriod = periodParser.parse("como estão as vendas do 2 do marco do ano do 2015 ate o 12/03/2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("02/03/2015", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("12/03/2016", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed7() {
        Period biaPeriod = periodParser.parse("como estão as vendas do 31 do FEVEReiRo do ano do 2015 ate o dia 12/03/2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("28/02/2015", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("12/03/2016", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed8() {
        Period biaPeriod = periodParser.parse("como estão as vendas do JaneiRo passado ate a semana passada");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        endWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(LocalDate.parse("01/01/2018", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(endWeek, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void mixed9() {
        Period biaPeriod = periodParser.parse("como estão as vendas do JaneiRo passado ate o próximo mEs");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate firstDate = LocalDate.parse("01/01/" + thisYear, dtf);

        if(nextMonth == 1) {
            LocalDate finalDate = LocalDate.parse("01/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(finalDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10){
            LocalDate finalDate = LocalDate.parse("01/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(finalDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        } else {
            LocalDate finalDate = LocalDate.parse("01/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(finalDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void mixed10() {
        Period biaPeriod = periodParser.parse("como estão as vendas de hoje ate o ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(today, biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2019", dtf), biaPeriod.getFinalDateFormatted());
    }
}
