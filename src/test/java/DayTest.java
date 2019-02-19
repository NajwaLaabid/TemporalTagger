import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class DayTest {
    PeriodParser periodParser;
    LocalDate yesterday;
    LocalDate beforeYesterday;
    LocalDate afterTomorrow;
    LocalDate tomorrow;
    LocalDate today;

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
    public void day1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia por favor");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(today, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 06");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.of(thisYear, thisMonth, 6 ),
                    TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(thisYear, thisMonth, 6 ),
                    TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day2_1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia 07 por favor");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.of(thisYear, thisMonth, 7 ),
                TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(thisYear, thisMonth, 7 ),
                TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 06 do mes");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.of(thisYear, thisMonth, 6 ),
                TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(thisYear, thisMonth, 6 ),
                TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day3_1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 06 do mes passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(thisMonth == 1) {
            assertEquals(LocalDate.of(lastYear, lastMonth, 6 ),
                    TempoPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.of(lastYear, lastMonth, 6 ),
                    TempoPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day3_2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 06 do mes que passou");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(thisMonth == 1) {
            assertEquals(LocalDate.of(lastYear, lastMonth, 6 ),
                    TempoPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.of(lastYear, lastMonth, 6 ),
                    TempoPeriod.getFinalDateFormatted());
        } else {
            assertEquals(LocalDate.of(thisYear, lastMonth, 6 ),
                    TempoPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.of(thisYear, lastMonth, 6 ),
                    TempoPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day3_3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 4 do mÊs que VeM");

        if(nextMonth == 1) {
            assertEquals(LocalDate.of(nextYear, nextMonth, 4 ),
                    TempoPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.of(nextYear, nextMonth, 4),
                    TempoPeriod.getFinalDateFormatted());
        } else {
            assertEquals(LocalDate.of(thisYear, nextMonth, 4 ),
                    TempoPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.of(thisYear, nextMonth, 4),
                    TempoPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day3_4() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 11 do mês próXiMo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate, TempoPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10) {
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate, TempoPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("11/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate, TempoPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day3_5() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia do 11 do mês próXiMo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate, TempoPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10){
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate, TempoPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("11/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate, TempoPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day4() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day4_1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro de 2013");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/2013", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/2013", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day4_2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro de ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day4_3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro de ano que VeM");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day5() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do dia 17 do mes Jan do ano 1965");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/01/1965", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/01/1965", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day6() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do 17 do 05");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/05/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/05/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day6_1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do 17 do 2 do 1901");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/02/1901", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/02/1901", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day7() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas de ontem");

        assertEquals(yesterday, TempoPeriod.getInitialDateFormatted());
        assertEquals(yesterday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day8() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas de amanhA por favor");

        assertEquals(tomorrow, TempoPeriod.getInitialDateFormatted());
        assertEquals(tomorrow, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day9() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas de depois de     amanhA por favor");

        assertEquals(afterTomorrow, TempoPeriod.getInitialDateFormatted());
        assertEquals(afterTomorrow, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day10() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas de anteOntem por favor");

        assertEquals(beforeYesterday, TempoPeriod.getInitialDateFormatted());
        assertEquals(beforeYesterday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day11() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas de antes de Ontem por favor");

        assertEquals(beforeYesterday, TempoPeriod.getInitialDateFormatted());
        assertEquals(beforeYesterday, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day12() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas de hojE");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.of(LocalDate.now().getYear(),
                        LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(LocalDate.now().getYear(),
                        LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day13() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do dia do 3/5");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.of(thisYear, 5, 3), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.of(thisYear, 5, 3), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day14() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do dia do 3/5/2019");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("03/05/2019", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("03/05/2019", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day15() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do dia do cinco do mês 05 do ano 2019");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("05/05/2019", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("05/05/2019", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day16() {
        TempoPeriod TempoPeriod = periodParser.parse("As vendas do dia do vinte e um do mês seis do ano 2019");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("21/06/2019", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("21/06/2019", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day17() {
        TempoPeriod TempoPeriod = periodParser.parse("As vendas do dia do vinte  do mês nove do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("20/09/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("20/09/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day18() {
        TempoPeriod TempoPeriod = periodParser.parse("As vendas do dia trinta e     um  do mês nove do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("30/09/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/09/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void day19() {
        TempoPeriod TempoPeriod = periodParser.parse("As vendas do dia vinte    seis do 11 deste ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("26/11/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("26/11/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

//    @Test
//    public void day20() {
//        TempoPeriod TempoPeriod = periodParser.parse("As vendas do dia vinteseis do mês de trinta e dois deste ano");
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        assertEquals(LocalDate.parse("26/" + thisMonth + "/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
//        assertEquals(LocalDate.parse("31/12/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
//    }

    /*@Test
    public void day21() {
        TempoPeriod TempoPeriod = periodParser.parse("As vendas do dia trinta   e dois do mês de dois deste ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/02/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("20/12/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }*/
}
