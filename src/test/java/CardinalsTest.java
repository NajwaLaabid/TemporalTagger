import model.TempoPeriod;
import util.TempoUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import static org.junit.Assert.assertEquals;

public class CardinalsTest {
    PeriodParser periodParser;

    TempoPeriod expectedPeriod;

    @Before
    public void setUp() {
        periodParser = new PeriodParser();
    }

    @Test
    public void cardinals1() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do primeiro semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(1, LocalDate.now());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals2() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do SegUndO semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(2, LocalDate.now());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals3() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do primEIro trimeSTRE");

        expectedPeriod = TempoUtil.getTrimesterCardinal(1, LocalDate.now());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals4() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do SegUndO trimeSTRE");

        expectedPeriod = TempoUtil.getTrimesterCardinal(2, LocalDate.now());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals5() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do TerCEIRO trimeSTRE");

        expectedPeriod = TempoUtil.getTrimesterCardinal(3, LocalDate.now());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals6() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do QUartO trimeSTRE");

        expectedPeriod = TempoUtil.getTrimesterCardinal(4, LocalDate.now());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals7() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do QUartO mês");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 4, 1);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(start.with(TemporalAdjusters.lastDayOfMonth()), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals8() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do primEiro mÊs");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(start.with(TemporalAdjusters.lastDayOfMonth()), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals9() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do SeGunDo mÊs");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 2, 1);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(start.with(TemporalAdjusters.lastDayOfMonth()), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals10() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do terceiro mes");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 3, 1);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(start.with(TemporalAdjusters.lastDayOfMonth()), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals11() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do terceiro mes do ano passado");

        LocalDate start = LocalDate.of(LocalDate.now().minusYears(1).getYear(), 3, 1);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(start.with(TemporalAdjusters.lastDayOfMonth()), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals12() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do TerCEIRO trimeSTRE do ano     2013");

        expectedPeriod = TempoUtil.getTrimesterCardinal(3, LocalDate.of(2013, 1, 1));

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals13() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do PrimeirO trimeSTRE de     1953");

        expectedPeriod = TempoUtil.getTrimesterCardinal(1, LocalDate.of(1953, 1, 1));

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals14() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas do seGUNdo SemeSTRE de     2000");

        expectedPeriod = TempoUtil.getSemesterCardinal(2, LocalDate.of(2000, 1, 1));

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals15() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da primeira semana");

        LocalDate start = LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals16() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da SegUNda semana");

        LocalDate start = LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals17() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da terCeira semana");

        LocalDate start = LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals18() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da QUARTA semana");

        LocalDate start = LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals19() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da QUARTA semana do mês  passado");

        LocalDate start = LocalDate.now().minusMonths(1)
                .with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals20() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da QUARTA semana do mes  05");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 5, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals21() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da terceira semana do mes  JunHO");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 6, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals22() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da SegUNdA semana do     Janeiro");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals23() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana    11");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 11, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals24() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana    115241");

        LocalDate start = LocalDate.now().with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals25() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana   do mes 1 do ano 2015");

        LocalDate start = LocalDate.of(2015, 1, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals26() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana   do mes 1 do ano 2015");

        LocalDate start = LocalDate.of(2015, 1, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals27() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana   do mes 1 do ano");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals28() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana   do mes 1 deste ano");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals29() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana   do mes 1 desse ano");

        LocalDate start = LocalDate.of(LocalDate.now().getYear(), 1, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals31() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana  do ultimo    ano ");

        LocalDate start = LocalDate.of(LocalDate.now().minusYears(1).getYear(), 1, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals32() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da Primeira semana  do mes 2 do" +
                " ultimo    ano ");

        LocalDate start = LocalDate.of(LocalDate.now().minusYears(1).getYear(), 2, 1)
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals33() {
        TempoPeriod actualPeriod = periodParser.parse("como estão as vendas da terceira semana  do   ProXimO mes");

        LocalDate start = LocalDate.now().plusMonths(1)
                .with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals34() {
        TempoPeriod actualPeriod = periodParser.parse("agenda da primeira semana do segundo semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(2, LocalDate.now());

        LocalDate start = expectedPeriod.getInitialDateFormatted()
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals35() {
        TempoPeriod actualPeriod = periodParser.parse("agenda da primeira semana do primeiro semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(1, LocalDate.now());

        LocalDate start = expectedPeriod.getInitialDateFormatted()
                .with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals36() {
        TempoPeriod actualPeriod = periodParser.parse("agenda da quarta semana do primeiro semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(1, LocalDate.now());

        LocalDate start = expectedPeriod.getInitialDateFormatted()
                .with(TemporalAdjusters.dayOfWeekInMonth(4, DayOfWeek.MONDAY));
        LocalDate end = start.with(DayOfWeek.SUNDAY);

        assertEquals(start, actualPeriod.getInitialDateFormatted());
        assertEquals(end, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals37() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do primeiro trimestre do segundo semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(2, LocalDate.now());
        expectedPeriod = TempoUtil.getTrimesterCardinal(1, expectedPeriod.getInitialDateFormatted());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }
/*
    @Test
    public void cardinals38() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do terceiro trimestre do segundo semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(2, LocalDate.now());
        TempoPeriod period = TempoUtil.getTrimesterCardinal(3, expectedPeriod.getInitialDateFormatted());

        assertEquals(period.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), actualPeriod.getFinalDateFormatted());
    }
*/
    @Test
    public void cardinals39() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do primeiro dia do segundo semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(2, LocalDate.now());

        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getInitialDateFormatted());
        assertEquals(expectedPeriod.getInitialDateFormatted(), actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals40() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do terceiro dia do primeiro semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(1, LocalDate.now());

        LocalDate date = expectedPeriod.getInitialDateFormatted().plusDays(2);

        assertEquals(date, actualPeriod.getInitialDateFormatted());
        assertEquals(date, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals41() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do quarto dia do terceiro semestre");

        expectedPeriod = TempoUtil.getSemesterCardinal(3, LocalDate.now());
        LocalDate date = expectedPeriod.getInitialDateFormatted().plusDays(3);

        assertEquals(date, actualPeriod.getInitialDateFormatted());
        assertEquals(date, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals42() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do quarto dia do terceiro semestre do ano 2013");

        expectedPeriod = TempoUtil.getSemesterCardinal(3, LocalDate.of(2013, 1, 1));

        LocalDate date = expectedPeriod.getInitialDateFormatted().plusDays(3);

        assertEquals(date, actualPeriod.getInitialDateFormatted());
        assertEquals(date, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals43() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do terceiro dia do segundo semestre do 2012");

        expectedPeriod = TempoUtil.getSemesterCardinal(2, LocalDate.of(2012, 1, 1));

        LocalDate date = expectedPeriod.getInitialDateFormatted().plusDays(2);

        assertEquals(date, actualPeriod.getInitialDateFormatted());
        assertEquals(date, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals44() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do terceiro dia da semana passada");

        LocalDate date = LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY).plusDays(2);

        assertEquals(date, actualPeriod.getInitialDateFormatted());
        assertEquals(date, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals45() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do quarto dia da semana que vem");

        LocalDate date = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY).plusDays(3);

        assertEquals(date, actualPeriod.getInitialDateFormatted());
        assertEquals(date, actualPeriod.getFinalDateFormatted());
    }

    @Test
    public void cardinals46() {
        TempoPeriod actualPeriod = periodParser.parse("agenda do PrimEiro dia da semana    reTrAsAda");

        LocalDate date = LocalDate.now().minusWeeks(2).with(DayOfWeek.MONDAY);

        assertEquals(date, actualPeriod.getInitialDateFormatted());
        assertEquals(date, actualPeriod.getFinalDateFormatted());
    }
}
