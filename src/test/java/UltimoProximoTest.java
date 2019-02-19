import model.TempoPeriod;
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

    int thisSemStartMonth;

    int thisSemEndMonth;
    int thisSemEndDay;

    int lastSemStartMonth;

    int lastSemEndMonth;
    int lastSemEndDay;

    int nextSemStartMonth;

    int nextSemEndMonth;
    int nextSemEndDay;

    int thisTriStartMonth;

    int thisTriEndMonth;
    int thisTriEndDay;

    int lastTriStartMonth;

    int lastTriEndMonth;
    int lastTriEndDay;

    int nextTriStartMonth;

    int nextTriEndMonth;
    int nextTriEndDay;

    int lastTriYear;
    int nextTriYear;

    int lastSemYear;
    int nextSemYear;

    @Before
    public void setUp() {
        periodParser = new PeriodParser();

        thisMonth = LocalDate.now().getMonthValue();
        lastMonth = LocalDate.now().minusMonths(1).getMonthValue();
        nextMonth = LocalDate.now().plusMonths(1).getMonthValue();

        thisYear = LocalDate.now().getYear();
        lastYear = LocalDate.now().minusYears(1).getYear();
        nextYear = LocalDate.now().plusYears(1).getYear();

        int curMonth = LocalDate.now().getMonthValue();

        lastTriYear = thisYear;
        nextTriYear = thisYear;

        lastSemYear = thisYear;
        nextSemYear = thisYear;

        //semester
        if(curMonth <= 6) {
            thisSemStartMonth = 1;
            thisSemEndMonth = 6;
            thisSemEndDay = 30;

            lastSemStartMonth = 7;
            lastSemEndMonth = 12;
            lastSemEndDay = 31;
            lastSemYear--;

            nextSemStartMonth = 7;
            nextSemEndMonth = 12;
            nextSemEndDay = 31;
        } else if(curMonth <= 12){
            thisSemStartMonth = 7;
            thisSemEndMonth = 12;
            thisSemEndDay = 31;

            lastSemStartMonth = 1;
            lastSemEndMonth = 6;
            lastSemEndDay = 30;

            nextSemStartMonth = 1;
            nextSemEndMonth = 6;
            nextSemEndDay = 30;
            nextSemYear++;
        }

        // trimester
        if(curMonth <= 3){
            thisTriStartMonth = 1;

            thisTriEndMonth = 3;
            thisTriEndDay = 31;

            lastTriStartMonth = 10;

            lastTriEndMonth = 12;
            lastTriEndDay = 31;

            lastTriYear--;

            nextTriStartMonth = 4;

            nextTriEndMonth = 6;
            nextTriEndDay = 30;
        } else if(curMonth <= 6){
            thisTriStartMonth = 4;

            thisTriEndMonth = 6;
            thisTriEndDay = 30;

            lastTriStartMonth = 1;

            lastTriEndMonth = 3;
            lastTriEndDay = 31;

            nextTriStartMonth = 7;

            nextTriEndMonth = 9;
            nextTriEndDay = 30;
        } else if(curMonth <= 9){
            thisTriStartMonth = 7;

            thisTriEndMonth = 9;
            thisTriEndDay = 31;

            lastTriStartMonth = 4;

            lastTriEndMonth = 6;
            lastTriEndDay = 30;

            nextTriStartMonth = 10;

            nextTriEndMonth = 12;
            nextTriEndDay = 31;
        } else if(curMonth <= 12){
            thisTriStartMonth = 10;

            thisTriEndMonth = 12;
            thisTriEndDay = 31;

            lastTriStartMonth = 7;

            lastTriEndMonth = 9;
            lastTriEndDay = 30;

            nextTriStartMonth = 1;

            nextTriEndMonth = 3;
            nextTriEndDay = 31;

            nextTriYear++;
        }
    }

    @Test
    public void ultimoProximo1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ultimo Ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ultimo    mEs");

        LocalDate firstDate;

        if(thisMonth == 1)
            firstDate = LocalDate.of(lastYear, lastMonth, 1);
        else
            firstDate = LocalDate.of(thisYear, lastMonth, 1);

        assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
        assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da ultima SemAna");

        startWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo4() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do proxima SemAna");

        startWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        endWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(startWeek, TempoPeriod.getInitialDateFormatted());
        assertEquals(endWeek, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo5() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do proximo Ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo6() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do proximo MEs");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("01/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10) {
            LocalDate firstDate = LocalDate.parse("01/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("01/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
        }

    }

    @Test
    public void ultimoProximo7() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ultimo semestre");

        assertEquals(LocalDate.of(lastSemYear, lastSemStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(lastSemYear, lastSemEndMonth, lastSemEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo8() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do Último semestre");

        assertEquals(LocalDate.of(lastSemYear, lastSemStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(lastSemYear, lastSemEndMonth, lastSemEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo9() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do últImO semeSTre");

        assertEquals(LocalDate.of(lastSemYear, lastSemStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(lastSemYear, lastSemEndMonth, lastSemEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo10() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do últImO trimeSTre");

        assertEquals(LocalDate.of(lastTriYear, lastTriStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(lastTriYear, lastTriEndMonth, lastTriEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo11() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do último trimestre");

        assertEquals(LocalDate.of(lastTriYear, lastTriStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(lastTriYear, lastTriEndMonth, lastTriEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo12() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do UltimO trimestre");

        assertEquals(LocalDate.of(lastTriYear, lastTriStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(lastTriYear, lastTriEndMonth, lastTriEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo13() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do próximO semestre");

        assertEquals(LocalDate.of(nextSemYear, nextSemStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(nextSemYear, nextSemEndMonth, nextSemEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo14() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do prÓximo semestre");

        assertEquals(LocalDate.of(nextSemYear, nextSemStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(nextSemYear, nextSemEndMonth, nextSemEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo15() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do proXiMO semeSTre");

        assertEquals(LocalDate.of(nextSemYear, nextSemStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(nextSemYear, nextSemEndMonth, nextSemEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo16() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do próximO trimestre");

        assertEquals(LocalDate.of(nextTriYear, nextTriStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(nextTriYear, nextTriEndMonth, nextTriEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo17() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do prÓximo tRimestre");

        assertEquals(LocalDate.of(nextTriYear, nextTriStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(nextTriYear, nextTriEndMonth, nextTriEndDay), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void ultimoProximo18() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do proXiMO trImeSTre");

        assertEquals(LocalDate.of(nextTriYear, nextTriStartMonth, 1), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(nextTriYear, nextTriEndMonth, nextTriEndDay), TempoPeriod.getFinalDateFormatted());
    }
}
