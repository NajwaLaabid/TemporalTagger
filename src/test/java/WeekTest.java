import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class WeekTest {
    PeriodParser periodParser;
    LocalDate startWeek;
    LocalDate endWeek;

    @Before
    public void setUp() {
        periodParser = new PeriodParser();
    }

    @Test
    public void week1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da semana passada");

        startWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void week2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da semana QuE    Vem");

        startWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void week3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da semana prOxIma");

        startWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void week4() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas dessa semana");

        startWeek = LocalDate.now().with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void week5() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da semana");

        startWeek = LocalDate.now().with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void week6() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da semana retrasada");

        startWeek = LocalDate.now().minusWeeks(2).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().minusWeeks(2).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }
}
