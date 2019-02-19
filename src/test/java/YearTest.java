import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class YearTest {
    PeriodParser periodParser;
    int thisYear;
    int lastYear;
    int nextYear;
    LocalDate today;

    @Before
    public void setUp() {
        periodParser = new PeriodParser();
        thisYear = LocalDate.now().getYear();
        lastYear = LocalDate.now().minusYears(1).getYear();
        nextYear = LocalDate.now().plusYears(1).getYear();
        today = LocalDate.now();
    }

    @Test
    public void year1() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas do 2015");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2015", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2015", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year2() {
        TempoPeriod TempoPeriod = periodParser.parse("POR FAVOR, MOSTRA VENDAS DE MARCA     DE  2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2016", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2016", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year3() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estão as vendas do -2017");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2017", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2017", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year4() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas   2020");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2020", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2020", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year5() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas     2019");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2019", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2019", dtf), TempoPeriod.getFinalDateFormatted());

    }

    @Test
    public void year6() {
        TempoPeriod TempoPeriod = periodParser.parse("Como é o LUCro  de   ANO  1996");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/1996", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/1996", dtf), TempoPeriod.getFinalDateFormatted());

    }

    @Test
    public void year7() {
        TempoPeriod TempoPeriod = periodParser.parse("Por favor, como esta  a fatura   do ano  PasSadO ");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year8() {
        TempoPeriod TempoPeriod = periodParser.parse("COMO foram as     vendas por parceiro  do ano      que     passOu");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year9() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estão as vendas  do ano      prÓximo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year10() {
        TempoPeriod TempoPeriod = periodParser.parse("Como sejam as compras  do ANO      que     vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year11() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estão as vendas  deste    ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year12() {
        TempoPeriod TempoPeriod = periodParser.parse("Como estao as vendas  de   aNo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    // Negatives

    @Test
    public void year14() {
        TempoPeriod TempoPeriod = periodParser.parse("meu mano do 12365332");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(today, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year15() {
        TempoPeriod TempoPeriod = periodParser.parse("como foi o trem que   passou");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(today, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year16() {
        TempoPeriod TempoPeriod = periodParser.parse("Vou ir ao festival    que vem");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(today, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year17() {
        TempoPeriod TempoPeriod = periodParser.parse("Vou pegar o   trem próximO");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(today, TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void year18() {
        TempoPeriod TempoPeriod = periodParser.parse("A luta da independencia");

        assertEquals(today, TempoPeriod.getInitialDateFormatted());
        assertEquals(today, TempoPeriod.getFinalDateFormatted());
    }
}
