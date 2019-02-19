import model.TempoPeriod;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;


public class SpecialDatesTest {
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
    public void anoNovo1(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ano-novo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void anoNovo2(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ano-novo de   2013");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2013", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/2013", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void anoNovo3(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ano-novo de   ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void anoNovo4(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ano-novo de   ano     do 2016");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2016", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/2016", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void anoNovo5(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ano-novo  2019");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/2019", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/2019", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void anoNovo6(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ano-novo  do ano que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void anoNovo7(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do Último    ano-novo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void anoNovo8(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do proximO    ano-novo");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("01/01/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal1(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do natal");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal2(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do natal de      2017");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/2017", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/2017", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal3(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do natal de      ano de  2013");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/2013", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/2013", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal4(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do natal de      ano passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal5(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do natal do      ano     que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal6(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do natal do      ano     que vem");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal7(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do natal     2018");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/2018", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal8(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas deste natal");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + thisYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + thisYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal9(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do ultimo natal");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal10(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do Último natal");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + lastYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + lastYear, dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void natal11(){
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do prÓximo  natal");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("25/12/" + nextYear, dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("25/12/" + nextYear, dtf), TempoPeriod.getFinalDateFormatted());
    }
}
