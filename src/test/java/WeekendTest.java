import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class WeekendTest {
    PeriodParser periodParser;
    LocalDate saturday;
    LocalDate sunday;

    @Before
    public void setUp() {
        periodParser = new PeriodParser();
    }

    @Test
    public void weekend1(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do FinAl dA SemaNa");

        saturday = LocalDate.now().with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void weekend2(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ultimo FinAl dA SemaNa   passada");

        saturday = LocalDate.now().minusWeeks(1).with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void weekend3(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do Último    FinAl dA  SemaNa passadO");

        saturday = LocalDate.now().minusWeeks(1).with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void weekend4(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do próximo   FinAl     dA  SemaNa  ");

        saturday = LocalDate.now().with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void weekend5(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do PROXIMO FinAl     dA  SemaNa");

        saturday = LocalDate.now().with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void weekend6(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do próximo Fim     dE  SemaNa");

        saturday = LocalDate.now().with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void weekend7(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do último Fim     dE  SemaNa");

        saturday = LocalDate.now().minusWeeks(1).with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void weekend8(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do Fim     dE  SemaNa");

        saturday = LocalDate.now().with(DayOfWeek.SATURDAY);
        sunday = LocalDate.now().with(DayOfWeek.SUNDAY);

        assertEquals(saturday, TempoPeriod.getInitialDateFormatted());
        assertEquals(sunday, TempoPeriod.getFinalDateFormatted());
    }
}
