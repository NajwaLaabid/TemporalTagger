import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        DayTest.class,
        MonthTest.class,
        YearTest.class,
        WeekTest.class,
        UltimoProximoTest.class,
        MixedTests.class
})

public class AllTests {
}
