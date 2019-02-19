import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CardinalsTest.class,
        CommemorativeDatesTest.class,
        DayTest.class,
        MixedTests.class,
        MonthTest.class,
        QuantityTest.class,
        SpecialDatesTest.class,
        TriSemesterTest.class,
        UltimoProximoTest.class,
        WeekendTest.class,
        WeekTest.class,
        YearTest.class
})

public class AllTests {
}
