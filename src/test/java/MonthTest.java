import model.TempoPeriod;
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
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do mes");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate firstDate = LocalDate.parse("01/0" + thisMonth + "/" + thisYear, dtf);

        assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
        assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month2() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estão as compras deste   MÊs");

        LocalDate firstDate = LocalDate.of(thisYear, thisMonth, 1);

        assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
        assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month3() {
        TempoPeriod TempoPeriod = periodParser.parse("Como foram as vendas por marca do mêS   paSSado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate firstDate = LocalDate.of(2019,1, 1);

        assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
        assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());

    }

    @Test
    public void month4() {
        TempoPeriod TempoPeriod = periodParser.parse("Como esta o desempenho do mês     que vem");

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
    public void month5() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as compras por tipo do MÊS que passou");

        LocalDate firstDate;

        if(thisMonth == 1)
            firstDate = LocalDate.of(lastYear, lastMonth, 1);
        else
            firstDate = LocalDate.of(thisYear, lastMonth, 1);

        assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
        assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month6() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas por parceiro do mes           próXIMO");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if(nextMonth == 1) {
            LocalDate firstDate = LocalDate.parse("01/0" + nextMonth + "/" + nextYear, dtf);

            assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
            assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
        } else if(nextMonth < 10){
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
    public void month7() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas por marca do mês do        05");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/05/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month8() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mês do  07 do 2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/07/2016", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/07/2016", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month9() {
        TempoPeriod TempoPeriod = periodParser.parse("Manda o relatorio geral de  mes de 08 do ano  1997");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/08/1997", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/08/1997", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month10() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mês   do 03   do ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/03/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/03/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month11() {
        TempoPeriod TempoPeriod = periodParser.parse("Como eram as vendas do mês do 09  do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/09/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/09/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month13() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do 08");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/08/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/08/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month14() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do 09 passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if( 9 > LocalDate.now().getMonthValue()) {
            assertEquals(LocalDate.parse("01/09/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.parse("30/09/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
        } else {
            assertEquals(LocalDate.parse("01/09/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
            assertEquals(LocalDate.parse("30/09/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
        }

    }

    @Test
    public void month15() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes do 10 que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/10/2019", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/10/2019", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month16() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes do    05/2015");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/05/2015", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/2015", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month17() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes do    Jan");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/01/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month18() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes do  abril do ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/04/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/04/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month19() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes do    maio do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/05/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month20() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes do    cinco do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/05/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/05/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month21() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes  quatro do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/04/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/04/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month22() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes  quatro do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/04/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/04/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month23() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes  onze do ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/11/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("30/11/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month24() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do mes  doze do ano que passou");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/12/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month25() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do   três");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/03/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/03/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month26() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do   mês seis");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/06/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/06/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void month27() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do   mês do quatorze");

        LocalDate firstDate = LocalDate.of(thisYear, thisMonth, 1);

        assertEquals(firstDate, TempoPeriod.getInitialDateFormatted());
        assertEquals(firstDate.withDayOfMonth(firstDate.lengthOfMonth()), TempoPeriod.getFinalDateFormatted());
    }
}
