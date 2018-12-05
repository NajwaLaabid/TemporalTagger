import algorithm.DFS;
import algorithm.Reductor;
import model.Period;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PeriodParser {
    public ArrayList<Period> allDates;

    class SortByDate implements Comparator<Period>
    {
        public int compare(Period a, Period b)
        {
            // compare initial date of first with final date of second
            if(a.getInitialDate().getYear() == b.getFinalDate().getYear()) {

                if(a.getInitialDate().getMonth() == b.getFinalDate().getMonth())
                    return a.getInitialDate().getDay() - b.getFinalDate().getDay();

                return a.getInitialDate().getMonth() - b.getFinalDate().getMonth();

            }

            return a.getInitialDate().getYear() - b.getFinalDate().getYear();

        }
    }

    public Period parse(String text) {
        DFS dfs = new DFS();

        allDates = dfs.run(text);
//        System.out.println("first date : " + allDates.get(0).initialDateToString());
//        System.out.println("first date, start: " + allDates.get(0).getStartIdx());
//        System.out.println("first date, end: " + allDates.get(0).getEndIdx());
//
//        System.out.println("second date : " + allDates.get(1).initialDateToString());
//        System.out.println("second date, start: " + allDates.get(1).getStartIdx());
//        System.out.println("second date, end: " + allDates.get(1).getEndIdx());

        if(allDates.size() > 0 ) {
            ArrayList<Period> correctDates = new Reductor().run(allDates);

             // System.out.println("size of dates:" + correctDates.size());

            // sort in ascending order
            Collections.sort(correctDates, new SortByDate());

            if (correctDates.size() == 1) {

                return fixDates(correctDates.get(0));
            } else if (correctDates.size() == 2) {
//                System.out.println("two dates");
//                System.out.println("first date");
//                System.out.println(correctDates.get(0).initialDateToString());
//                System.out.println(correctDates.get(0).finalDateToString());
//                System.out.println("start indx: " + correctDates.get(0).getStartIdx());
//                System.out.println("end indx: " + correctDates.get(0).getEndIdx());
//
//                System.out.println("second date");
//                System.out.println(correctDates.get(1).initialDateToString());
//                System.out.println(correctDates.get(1).finalDateToString());
//                System.out.println("start indx: " + correctDates.get(1).getStartIdx());
//                System.out.println("end indx: " + correctDates.get(1).getEndIdx());


                return mergePeriods(correctDates.get(0), correctDates.get(1));
            } else {
                // throw exception

                return new Period();
            }
        } else {
            return new Period();
        }

    }

    private static Period fixDates(Period period) {
        int refMonth = LocalDate.now().getMonthValue();
        int refYear = LocalDate.now().getYear();

        // fix month
        if(period.getInitialDate().getMonth() == -1 && period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {

            if(period.getInitialDate().getDay() <= period.getFinalDate().getDay()) {

                period.getInitialDate().setMonth(refMonth);
                period.getFinalDate().setMonth(refMonth);

            } else {

                period.getInitialDate().setMonth(refMonth - 1);
                period.getFinalDate().setMonth(refMonth);

            }

        } else if( period.getInitialDate().getMonth() == -1 ) {

            refMonth = period.getFinalDate().getMonth();

            if(period.getInitialDate().getDay() <= period.getFinalDate().getDay()) {

                period.getInitialDate().setMonth(refMonth);
                period.getFinalDate().setMonth(refMonth);

            } else {

                period.getInitialDate().setMonth(refMonth - 1);
                period.getFinalDate().setMonth(refMonth);

            }

        } else if( period.getFinalDate().getMonth() == -1) {

            refMonth = period.getInitialDate().getMonth();

            if(period.getInitialDate().getDay() <= period.getFinalDate().getDay()) {

                period.getInitialDate().setMonth(refMonth);
                period.getFinalDate().setMonth(refMonth);

            } else {

                period.getInitialDate().setMonth(refMonth);
                period.getFinalDate().setMonth(refMonth + 1);

            }
        }

        if(period.getInitialDate().getYear() == -1 && period.getInitialDate().getYear() == period.getFinalDate().getYear()) {

            if(period.getInitialDate().getMonth() < period.getFinalDate().getMonth()) {

                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear);

            } else if(period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {

                if(period.getInitialDate().getDay() <= period.getInitialDate().getDay()) {

                    period.getInitialDate().setYear(refYear);
                    period.getFinalDate().setYear(refYear);

                } else {

                    period.getInitialDate().setYear(refYear - 1);
                    period.getFinalDate().setYear(refYear);

                }
            } else {

                period.getInitialDate().setYear(refYear - 1);
                period.getFinalDate().setYear(refYear);

            }
       } else if( period.getInitialDate().getYear() == -1 ) {
            refYear = period.getFinalDate().getYear();

            if(period.getInitialDate().getMonth() < period.getFinalDate().getMonth()) {

                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear);

            } else if(period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {

                if(period.getInitialDate().getDay() <= period.getInitialDate().getDay()) {

                    period.getInitialDate().setYear(refYear);
                    period.getFinalDate().setYear(refYear);

                } else {

                    period.getInitialDate().setYear(refYear - 1);
                    period.getFinalDate().setYear(refYear);

                }
            } else {

                period.getInitialDate().setYear(refYear - 1);
                period.getFinalDate().setYear(refYear);

            }

        } else if( period.getFinalDate().getYear() == -1) {

            refYear = period.getInitialDate().getYear();

            if(period.getInitialDate().getMonth() < period.getFinalDate().getMonth()) {

                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear);

            } else if(period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {

                if(period.getInitialDate().getDay() <= period.getInitialDate().getDay()) {

                    period.getInitialDate().setYear(refYear);
                    period.getFinalDate().setYear(refYear);

                } else {

                    period.getInitialDate().setYear(refYear);
                    period.getFinalDate().setYear(refYear + 1);

                }

            } else {

                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear + 1);

            }
        }

       return period;
    }

    private static Period mergePeriods(Period firstPeriod, Period secondPeriod) {
        return fixDates(new Period(firstPeriod.getInitialDate(), secondPeriod.getFinalDate()));
    }

}
