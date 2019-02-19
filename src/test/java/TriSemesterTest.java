import model.TempoPeriod;
import util.TempoUtil;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class TriSemesterTest {
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
    public void semester1() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas deste semestre");

        TempoPeriod period = TempoUtil.getSemester(0, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester2() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do semestre passado");

        TempoPeriod period = TempoUtil.getSemester(-1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester3() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do semestre  que     passou");

        TempoPeriod period = TempoUtil.getSemester(-1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester4() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do semestre  que     vem");

        TempoPeriod period = TempoUtil.getSemester(1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester5() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do semestre      próximo");

        TempoPeriod period = TempoUtil.getSemester(1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester6() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do trimestre");

        TempoPeriod period = TempoUtil.getTrimester(0, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester7() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do trimestre      quE vEm");

        TempoPeriod period = TempoUtil.getTrimester(1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester8() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do trimestre      próxiMo");

        TempoPeriod period = TempoUtil.getTrimester(1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester9() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do trimestre      que    pasSoU");

        TempoPeriod period = TempoUtil.getTrimester(-1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester10() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas do trimestre      passado");

        TempoPeriod period = TempoUtil.getTrimester(-1, LocalDate.now());

        assertEquals(period.getInitialDateFormatted(), TempoPeriod.getInitialDateFormatted());
        assertEquals(period.getFinalDateFormatted(), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester11() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da primeira semana do semestre passado");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("02/07/2018", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("08/07/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester12() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da QuaRtA semana do SeMestrE     QuE PassOu");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("23/07/2018", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("29/07/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }

    @Test
    public void semester13() {
        TempoPeriod TempoPeriod = periodParser.parse("como estão as vendas da terceira semana do SeMestrE     QuE PassOu");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(LocalDate.parse("16/07/2018", dtf), TempoPeriod.getInitialDateFormatted());
        assertEquals(LocalDate.parse("22/07/2018", dtf), TempoPeriod.getFinalDateFormatted());
    }
}
