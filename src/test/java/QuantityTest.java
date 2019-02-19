import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class QuantityTest {
    PeriodParser periodParser;

    int thisMonth;
    int lastMonth;
    int nextMonth;

    int thisYear;
    int lastYear;
    int nextYear;

    LocalDate today;

    @Before
    public void setUp() {
        periodParser = new PeriodParser();

        thisMonth = LocalDate.now().getMonthValue();
        lastMonth = LocalDate.now().minusMonths(1).getMonthValue();
        nextMonth = LocalDate.now().plusMonths(1).getMonthValue();

        thisYear = LocalDate.now().getYear();
        lastYear = LocalDate.now().minusYears(1).getYear();
        nextYear = LocalDate.now().plusYears(1).getYear();

        today = LocalDate.now();
    }

    @Test
    public void quantity_day1() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 4 dias depois");

        LocalDate date = LocalDate.now().plusDays(4);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_day2() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda dois     dia  ANteS");

        LocalDate date = LocalDate.now().minusDays(2);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_month1() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 6     mês  depOis");

        LocalDate date = LocalDate.now().plusMonths(6);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_month2() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda quatro    mêses  depOis");

        LocalDate date = LocalDate.now().plusMonths(4);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_month3() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 1    mÊs  antes");

        LocalDate date = LocalDate.now().minusMonths(1);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_week1() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 5    semanas  antes");

        LocalDate date = LocalDate.now().minusWeeks(5);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_week2() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 8    semanas  depois");

        LocalDate date = LocalDate.now().plusWeeks(8);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_year1() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 23    anos  depois");

        LocalDate date = LocalDate.now().plusYears(23);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_year2() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 3    anos  antes");

        LocalDate date = LocalDate.now().minusYears(3);

        assertEquals(date, tempoPeriod.getInitialDateFormatted());
        assertEquals(date, tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_semester1() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 1    semestres  depois");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/07/2019", dtf), tempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2019", dtf), tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_semester2() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 1   semestre  antes");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/07/2018", dtf), tempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2018", dtf), tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_trimester1() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 25    trimestres  depois");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/04/2025", dtf), tempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/06/2025", dtf), tempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void quantity_trimester2() {
        TempoPeriod tempoPeriod = periodParser.parse("como esta a minha agenda 4  trimestres  antes");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2018", dtf), tempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/03/2018", dtf), tempoPeriod.getFinalDateFormatted());
    }


}
