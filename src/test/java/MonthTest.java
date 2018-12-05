import model.Period;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class MonthTest {
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
    public void month1() {
        Period biaPeriod = periodParser.parse("como estão as vendas do mes");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month2() {
        Period biaPeriod = periodParser.parse("Como estão as compras deste   MÊs");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/" + thisMonth + "/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month3() {
        Period biaPeriod = periodParser.parse("Como foram as vendas por marca do mêS   paSSado");

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
    public void month4() {
        Period biaPeriod = periodParser.parse("Como esta o desempenho do mês     que vem");

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

    @Test
    public void month5() {
        Period biaPeriod = periodParser.parse("Como estao as compras por tipo do MÊS que passou");

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
    public void month6() {
        Period biaPeriod = periodParser.parse("Como estao as vendas por parceiro do mes           próXIMO");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("01/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10){
            LocalDate firstDate = LocalDate.parse("01/0" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        } else {
            LocalDate firstDate = LocalDate.parse("01/" + nextMonth + "/" + thisYear, dtf);

            assertEquals(firstDate, biaPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), biaPeriod.getFinalDateFormatted());
        }
    }

    @Test
    public void month7() {
        Period biaPeriod = periodParser.parse("Como estao as vendas por marca do mês do        05");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/05/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month8() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mês do  07 do 2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/07/2016", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/07/2016", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month9() {
        Period biaPeriod = periodParser.parse("Bia manda o relatorio geral de  mes de 08 do ano  1997");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/08/1997", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/08/1997", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month10() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mês   do 03   do ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/03/" + lastYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/03/" + lastYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month11() {
        Period biaPeriod = periodParser.parse("Como eram as vendas do mês do 09  do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/09/" + nextYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/09/" + nextYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month12() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mÊS 04 do ano da independencia");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/04/1822", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/04/1822", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month13() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do 08");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/08/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/08/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month14() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do 09 passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if( 9 > LocalDate.now().getMonthValue()) {
            assertEquals(LocalDate.parse("01/09/" + lastYear, dtf), biaPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.parse("30/09/" + lastYear, dtf), biaPeriod.getFinalDateFormatted());
        } else {
            assertEquals(LocalDate.parse("01/09/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.parse("30/09/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
        }

    }

    @Test
    public void month15() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mes do 10 que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/10/" + nextYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/10/" + nextYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month16() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mes do    05/2015");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/05/2015", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/2015", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month17() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mes do    Jan");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/01/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month18() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mes do  abril do ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/04/" + lastYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/04/" + lastYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void month19() {
        Period biaPeriod = periodParser.parse("Como estao as vendas do mes do    maio do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/05/" + nextYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/" + nextYear, dtf), biaPeriod.getFinalDateFormatted());
    }
}
