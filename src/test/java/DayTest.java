import model.Period;
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
        Period biaPeriod = periodParser.parse("como estão as vendas do dia por favor");

        assertEquals(today, biaPeriod.getInitialDateFormatted());
        assertEquals(today, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day2() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 06");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("06/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("06/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day21() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia 07 por favor");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("07/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("07/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day3() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 06 do mes");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("06/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("06/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day31() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 06 do mes passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("06/" + lastMonth + "/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("06/" + lastMonth + "/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day32() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 06 do mes que passou");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("06/" + lastMonth + "/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("06/" + lastMonth + "/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day33() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 4 do mÊs que VeM");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("04/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10){
            LocalDate firstDate = LocalDate.parse("04/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("04/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day34() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 11 do mês próXiMo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10) {
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("11/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day35() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia do 11 do mês próXiMo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10){
            LocalDate firstDate = LocalDate.parse("11/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("11/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate, biaPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day4() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day41() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro de 2013");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/2013", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/2013", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day42() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro de ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/" + lastYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/" + lastYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day43() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia 17 do mes do NoVembro de ano que VeM");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/11/" + nextYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/11/" + nextYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day5() {
        Period biaPeriod = periodParser.parse("como estão as vendas do dia 17 do mes Jan do ano 1965");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/01/1965", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/01/1965", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day6() {
        Period biaPeriod = periodParser.parse("como estão as vendas do 17 do 05");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/05/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/05/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day61() {
        Period biaPeriod = periodParser.parse("como estão as vendas do 17 do 2 do 1901");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("17/02/1901", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("17/02/1901", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day7() {
        Period biaPeriod = periodParser.parse("como estão as vendas de ontem");

        assertEquals(yesterday, biaPeriod.getInitialDateFormatted());
        assertEquals(yesterday, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day8() {
        Period biaPeriod = periodParser.parse("como estão as vendas de amanhA por favor");

        assertEquals(tomorrow, biaPeriod.getInitialDateFormatted());
        assertEquals(tomorrow, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day9() {
        Period biaPeriod = periodParser.parse("como estão as vendas de depois de     amanhA por favor");

        assertEquals(afterTomorrow, biaPeriod.getInitialDateFormatted());
        assertEquals(afterTomorrow, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day10() {
        Period biaPeriod = periodParser.parse("como estão as vendas de anteOntem por favor");

        assertEquals(beforeYesterday, biaPeriod.getInitialDateFormatted());
        assertEquals(beforeYesterday, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day11() {
        Period biaPeriod = periodParser.parse("como estão as vendas de antes de Ontem por favor");

        assertEquals(beforeYesterday, biaPeriod.getInitialDateFormatted());
        assertEquals(beforeYesterday, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day12() {
        Period biaPeriod = periodParser.parse("como estão as vendas de hojE");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(LocalDate.now().getDayOfMonth() < 10) {
            assertEquals(LocalDate.parse("0" + LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear(), dtf), biaPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.parse("0" +  LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear(), dtf), biaPeriod.getFinalDateFormatted());
        } else {
            assertEquals(LocalDate.parse(LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear(), dtf), biaPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.parse(LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear(), dtf), biaPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void day13() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do dia do 3/5");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("03/05/2018", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("03/05/2018", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void day14() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do dia do 3/5/2019");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("03/05/2019", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("03/05/2019", dtf), biaPeriod.getFinalDateFormatted());
    }

}
