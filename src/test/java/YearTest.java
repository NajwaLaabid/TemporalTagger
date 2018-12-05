import model.Period;
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
        Period biaPeriod = periodParser.parse("Como estao as vendas do 2015");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2015", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2015", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year2() {
        Period biaPeriod = periodParser.parse("POR FAVOR, MOSTRA VENDAS DE MARCA     DE  2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2016", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2016", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year3() {
        Period biaPeriod = periodParser.parse("Como estão as vendas do -2017");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2017", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2017", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year4() {
        Period biaPeriod = periodParser.parse("Como estao as vendas   2020");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2020", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2020", dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year5() {
        Period biaPeriod = periodParser.parse("Como estao as vendas     2019");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2019", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/2019", dtf), biaPeriod.getFinalDateFormatted());

    }

    @Test
    public void year6() {
        Period biaPeriod = periodParser.parse("Bia, como é o LUCro  de   ANO  1996");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/1996", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/1996", dtf), biaPeriod.getFinalDateFormatted());

    }

    @Test
    public void year7() {
        Period biaPeriod = periodParser.parse("Bia, por favor, como esta  a fatura   do ano  PasSadO ");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + lastYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year8() {
        Period biaPeriod = periodParser.parse("COMO foram as     vendas por parceiro  do ano      que     passOu");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + lastYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year9() {
        Period biaPeriod = periodParser.parse("Como estão as vendas  do ano      prÓximo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + nextYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year10() {
        Period biaPeriod = periodParser.parse("Como sejam as compras  do ANO      que     vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + nextYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year11() {
        Period biaPeriod = periodParser.parse("Como estão as vendas  deste    ano");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year12() {
        Period biaPeriod = periodParser.parse("Como estao as vendas  de   aNo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/" + thisYear, dtf), biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year13() {
        Period biaPeriod = periodParser.parse("O que é o ano DA indepENdencia");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/1822", dtf), biaPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("31/12/1822", dtf), biaPeriod.getFinalDateFormatted());
    }

    // Negatives

    @Test
    public void year14() {
        Period biaPeriod = periodParser.parse("meu mano do 12365332");

        assertEquals(today, biaPeriod.getInitialDateFormatted());
        assertEquals(today, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year15() {
        Period biaPeriod = periodParser.parse("como foi o trem que   passou");

        assertEquals(today, biaPeriod.getInitialDateFormatted());
        assertEquals(today, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year16() {
        Period biaPeriod = periodParser.parse("Vou ir ao festival    que vem");

        assertEquals(today, biaPeriod.getInitialDateFormatted());
        assertEquals(today, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year17() {
        Period biaPeriod = periodParser.parse("Vou pegar o   trem próximO");

        assertEquals(today, biaPeriod.getInitialDateFormatted());
        assertEquals(today, biaPeriod.getFinalDateFormatted());
    }

    @Test
    public void year18() {
        Period biaPeriod = periodParser.parse("A luta da independencia");

        assertEquals(today, biaPeriod.getInitialDateFormatted());
        assertEquals(today, biaPeriod.getFinalDateFormatted());
    }
}
