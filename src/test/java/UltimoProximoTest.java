import model.Period;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class UltimoProximoTest {
    PeriodParser periodParser;

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

        thisMonth = LocalDate.now().getMonthValue();
        lastMonth = LocalDate.now().minusMonths(1).getMonthValue();
        nextMonth = LocalDate.now().plusMonths(1).getMonthValue();

        thisYear = LocalDate.now().getYear();
        lastYear = LocalDate.now().minusYears(1).getYear();
        nextYear = LocalDate.now().plusYears(1).getYear();

    }

    @Test
    public void ultimoProximo1() {
        Period biaPeriod = periodParser.parse("como estão as vendas do ultimo Ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + lastYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo2() {
        Period biaPeriod = periodParser.parse("como estão as vendas do ultimo    mEs");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(lastMonth == 12) {
            LocalDate firstDate = LocalDate.parse("01/" + lastMonth + "/" + lastYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("01/" + lastMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void ultimoProximo3() {
        Period biaPeriod = periodParser.parse("como estão as vendas do ultima SemAna");

        startWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, biaPeriod.getInitialDateFormatted());
        assertEquals(endWeek, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo4() {
        Period biaPeriod = periodParser.parse("como estão as vendas do proxima SemAna");

        startWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, biaPeriod.getInitialDateFormatted());
        assertEquals(endWeek, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo5() {
        Period biaPeriod = periodParser.parse("como estão as vendas do proximo Ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + nextYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo6() {
        Period biaPeriod = periodParser.parse("como estão as vendas do proximo MEs");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("01/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10) {
            LocalDate firstDate = LocalDate.parse("01/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("01/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        }

    }

}
