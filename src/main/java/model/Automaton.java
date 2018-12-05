package model;

import model.state.Leaf;
import model.state.Text;
import util.Util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.regex.Matcher;

import static util.Constants.DEFAULT_COMPONENT;

public class Automaton {
    private State head;

    /* Defining nodes of graph */
    State yearText;
    State yearDigits;
    State yearLast;
    State yearNext;
    State independence;

    State monthText;
    State monthLast;
    State monthNext;
    State monthDigits;
    State yearTree;
    State monthName;

    State dayText;
    State yesterday;
    State tomorrow;
    State today;
    State beforeYesterday;
    State afterTomorrow;
    State dayDigits;
    State dayDigitsAlone;

    State weekText;
    State weekLast;
    State weekNext;
    State weekRetrasada;

    State ultimoText;
    State ultimoMonth;
    State ultimoYear;

    State ultimaText;
    State ultimaWeek;

    State proximoText;
    State proximoMonth;
    State proximoYear;

    State proximaText;
    State proximaWeek;

    State leaf;

    public Automaton()  {
        initializeNodes();

        head = new Leaf();

        head.addChild(getDayTree());
        head.addChild(getMonthTree());
        head.addChild(getYearTree());
        head.addChild(getWeekTree());
        head.addChild(getUltimoTree());
        head.addChild(getUltimaTree());
        head.addChild(getProximoTree());
        head.addChild(getProximaTree());
    }

    public void initializeNodes() {
        leaf = new Leaf();
        yearText = new State("(d[eo]\\s+)?\\bano($|\\s+)"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().getYear();
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getMonth());
                fMonth = (parentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT? 12:
                        parentPeriod.getFinalDate().getMonth());

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        yearDigits = new State("([./-]|d[eo]\\s+)?\\b(\\d{4})\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = Integer.parseInt(matcher.group(2));
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getMonth());
                fMonth = (parentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT? 12:
                        parentPeriod.getFinalDate().getMonth());

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        yearLast = new State("passado\\b|que\\s+passou\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().minusYears(1).getYear();
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getMonth());
                fMonth = (parentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT? 12:
                        parentPeriod.getFinalDate().getMonth());

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        yearNext = new State("pr[óoÓ]ximo\\b|que\\s+vem\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().plusYears(1).getYear();
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getMonth());
                fMonth = (parentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT? 12:
                        parentPeriod.getFinalDate().getMonth());

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        independence = new State("da\\s+independencia\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = 1822;
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getMonth());
                fMonth = (parentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT? 12:
                        parentPeriod.getFinalDate().getMonth());

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };

        monthText = new State("(d[eo]\\s+)?\\bm[eêÊ]s($|\\s+)"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = DEFAULT_COMPONENT;
                fYear = iYear;

                iMonth = DEFAULT_COMPONENT;
                fMonth = iMonth;

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        monthLast = new State("(passado|que\\s+passou)($|\\s+)"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = Util.getYearForLastMonthName(iMonth);
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT?
                        LocalDate.now().minusMonths(1).getMonthValue(): parentPeriod.getInitialDate().getMonth());
                fMonth = iMonth;

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        monthNext = new State("(pr[óoÓ]ximo|que\\s+vem)($|\\s+)"){
            @Override
            public void computeDate(Matcher matcher) {
                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT?
                        LocalDate.now().plusMonths(1).getMonthValue(): parentPeriod.getInitialDate().getMonth());
                fMonth = iMonth;

                iYear = Util.getYearForNextMonthName(iMonth);
                fYear = iYear;

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        /*State monthDigits = new MonthDigits("([./-]|d[eo]\\s+)?\\b(1[0-2]|0?[1-9])\\b" +
                "^(?!/03/2016)($|\\s+)?");*/
        monthDigits = new State("([./-]|d[eo]\\s+)?\\b(1[0-2]|0?[1-9])\\b($|\\s+)?"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = DEFAULT_COMPONENT;
                fYear = iYear;

                iMonth = Integer.parseInt(matcher.group(2));
                fMonth = iMonth;

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT ? 1 :
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT ? 31 :
                        parentPeriod.getFinalDate().getDay());
            }
        };
        yearTree = getYearTree();
        monthName = new State("(d[eo]\\s+)?\\b(janeiro|fevereiro|mar[cçÇ]o|abril|maio|junho" +
                "|julho|agosto|setembro|outubro|novembro|dezembro|jan|fev|mar|abr|mai" +
                "|jun|jul|ago|set|out|nov|dez)($|\\s+)"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = DEFAULT_COMPONENT;
                fYear = iYear;

                iMonth = Util.getMonthInt(matcher.group(2));
                fMonth = iMonth;

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };

        dayText = new State("\\bdia($|\\s+)") {
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().getYear();
                fYear = iYear;

                iMonth = LocalDate.now().getMonthValue();
                fMonth = iMonth;

                iDay = LocalDate.now().getDayOfMonth();
                fDay = iDay;
            }
        };
        yesterday = new State("\\bontem\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().minusDays(1).getYear();
                fYear = iYear;

                iMonth = LocalDate.now().minusDays(1).getMonthValue();
                fMonth = iMonth;

                iDay = LocalDate.now().minusDays(1).getDayOfMonth();
                fDay = iDay;
            }
        };
        tomorrow = new State("\\bamanh[ãaÃ](?![a-zA-Z\\d])"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().plusDays(1).getYear();
                fYear = iYear;

                iMonth = LocalDate.now().plusDays(1).getMonthValue();
                fMonth = iMonth;

                iDay = LocalDate.now().plusDays(1).getDayOfMonth();
                fDay = iDay;
            }
        };
        today = new State("\\bhoje\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().getYear();
                fYear = iYear;

                iMonth = LocalDate.now().getMonthValue();
                fMonth = iMonth;

                iDay = LocalDate.now().getDayOfMonth();
                fDay = iDay;
            }
        };
        beforeYesterday = new State("\\bante((s\\s+de)?\\s+)?ontem\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().minusDays(2).getYear();
                fYear = iYear;

                iMonth = LocalDate.now().minusDays(2).getMonthValue();
                fMonth = iMonth;

                iDay = LocalDate.now().minusDays(2).getDayOfMonth();
                fDay = iDay;
            }
        };
        afterTomorrow = new State("\\bdepois\\s+de\\s+amanh[ãaÃ](?![a-zA-Z\\d])"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().plusDays(2).getYear();
                fYear = iYear;

                iMonth = LocalDate.now().plusDays(2).getMonthValue();
                fMonth = iMonth;

                iDay = LocalDate.now().plusDays(2).getDayOfMonth();
                fDay = iDay;
            }
        };
        dayDigits = new State("(d[eo]\\s+)?([12]\\d|3[01]|0?[1-9])($|\\s+)?"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = DEFAULT_COMPONENT;
                fYear = iYear;

                iMonth = DEFAULT_COMPONENT;
                fMonth = iMonth;

                iDay = Integer.parseInt(matcher.group(2));
                fDay = iDay;

//                System.out.println("DayDigits" + iDay + ". startIdx: " + startIdx);
//                System.out.println("DayDigits. startIdx: " + endIdx);
            }
        };
        dayDigitsAlone = new State("(d[eo]\\s+)?([12]\\d|3[01]|0?[1-9])($|\\s+)?"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = DEFAULT_COMPONENT;
                fYear = iYear;

                iMonth = DEFAULT_COMPONENT;
                fMonth = iMonth;

                iDay = Integer.parseInt(matcher.group(2));
                fDay = iDay;

//                System.out.println("DayDigits" + iDay + ". startIdx: " + startIdx);
//                System.out.println("DayDigits. startIdx: " + endIdx);
            }
        };

        weekText = new State("\\bsemana($|\\s+)"){
            @Override
            public void computeDate(Matcher matcher) {
                LocalDate startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY);
                LocalDate endOfWeek = LocalDate.now().with(DayOfWeek.SUNDAY);

                iYear = startOfWeek.getYear();
                fYear = endOfWeek.getYear();

                iMonth = startOfWeek.getMonthValue();
                fMonth = endOfWeek.getMonthValue();

                iDay = startOfWeek.getDayOfMonth();
                fDay = endOfWeek.getDayOfMonth();
            }
        };
        weekLast = new State("\\b(passada|que\\s+passou)\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                LocalDate startOfWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY);
                LocalDate endOfWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

                iYear = startOfWeek.getYear();
                fYear = endOfWeek.getYear();

                iMonth = startOfWeek.getMonthValue();
                fMonth = endOfWeek.getMonthValue();

                iDay = startOfWeek.getDayOfMonth();
                fDay = endOfWeek.getDayOfMonth();
            }
        };
        weekNext = new State("\\b(pr[óoÓ]xima|que\\s+vem)\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                LocalDate startOfWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
                LocalDate endOfWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY);

                iYear = startOfWeek.getYear();
                fYear = endOfWeek.getYear();

                iMonth = startOfWeek.getMonthValue();
                fMonth = endOfWeek.getMonthValue();

                iDay = startOfWeek.getDayOfMonth();
                fDay = endOfWeek.getDayOfMonth();
            }
        };
        weekRetrasada = new State("\\bretrasada\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                LocalDate startOfWeek = LocalDate.now().minusWeeks(2).with(DayOfWeek.MONDAY);
                LocalDate endOfWeek = LocalDate.now().minusWeeks(2).with(DayOfWeek.SUNDAY);

                iYear = startOfWeek.getYear();
                fYear = endOfWeek.getYear();

                iMonth = startOfWeek.getMonthValue();
                fMonth = endOfWeek.getMonthValue();

                iDay = startOfWeek.getDayOfMonth();
                fDay = endOfWeek.getDayOfMonth();
            }
        };

        ultimoText = new Text("\\b[úuÚ]ltimo($|\\s+)");
        ultimoMonth = new State("m[eêÊ]s\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = Util.getYearForLastMonthName(iMonth);
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT?
                        LocalDate.now().minusMonths(1).getMonthValue(): parentPeriod.getInitialDate().getMonth());
                fMonth = iMonth;

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        ultimoYear = new State("ano\\b") {
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().minusYears(1).getYear();
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getMonth());
                fMonth = (parentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT? 12:
                        parentPeriod.getFinalDate().getMonth());

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };

        ultimaText = new Text("\\b[úuÚ]ltima($|\\s+)");
        ultimaWeek = new State("semana\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                LocalDate startOfWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY);
                LocalDate endOfWeek = LocalDate.now().minusWeeks(1).with(DayOfWeek.SUNDAY);

                iYear = startOfWeek.getYear();
                fYear = endOfWeek.getYear();

                iMonth = startOfWeek.getMonthValue();
                fMonth = endOfWeek.getMonthValue();

                iDay = startOfWeek.getDayOfMonth();
                fDay = endOfWeek.getDayOfMonth();
            }
        };

        proximoText = new Text("\\bpr[óoÓ]ximo($|\\s+)");
        proximoMonth = new State("m[eêÊ]s\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT?
                        LocalDate.now().plusMonths(1).getMonthValue(): parentPeriod.getInitialDate().getMonth());
                fMonth = iMonth;

                iYear = Util.getYearForNextMonthName(iMonth);
                fYear = iYear;

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };
        proximoYear = new State("ano\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                iYear = LocalDate.now().plusYears(1).getYear();
                fYear = iYear;

                iMonth = (parentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getMonth());
                fMonth = (parentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT? 12:
                        parentPeriod.getFinalDate().getMonth());

                iDay = (parentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT? 1:
                        parentPeriod.getInitialDate().getDay());
                fDay = (parentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT? 31:
                        parentPeriod.getFinalDate().getDay());
            }
        };

        proximaText = new Text("\\bpr[óoÓ]xima($|\\s+)");
        proximaWeek = new State("semana\\b"){
            @Override
            public void computeDate(Matcher matcher) {
                LocalDate startOfWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
                LocalDate endOfWeek = LocalDate.now().plusWeeks(1).with(DayOfWeek.SUNDAY);

                iYear = startOfWeek.getYear();
                fYear = endOfWeek.getYear();

                iMonth = startOfWeek.getMonthValue();
                fMonth = endOfWeek.getMonthValue();

                iDay = startOfWeek.getDayOfMonth();
                fDay = endOfWeek.getDayOfMonth();
            }
        };
    }

    public State getYearTree() {
        yearDigits.addChild(leaf);
        yearLast.addChild(leaf);
        yearNext.addChild(leaf);
        independence.addChild(leaf);

        yearText.addChild(yearDigits);
        yearText.addChild(yearLast);
        yearText.addChild(yearNext);
        yearText.addChild(independence);
        yearText.addChild(leaf);

        // year subtree
        State yearTree = new Leaf();
        yearTree.addChild(yearText);
        yearTree.addChild(yearDigits);

        return yearTree;
    }

    public State getMonthTree() {
        monthNext.addChild(leaf);
        monthLast.addChild(leaf);

        monthName.addChild(getYearTree());
        monthName.addChild(monthNext);
        monthName.addChild(monthLast);
        monthName.addChild(leaf);

        monthDigits.addChild(getYearTree());
        monthDigits.addChild(monthNext);
        monthDigits.addChild(monthLast);
        monthDigits.addChild(leaf);

        monthText.addChild(monthDigits);
        monthText.addChild(monthName);
        monthText.addChild(monthLast);
        monthText.addChild(monthNext);
        monthText.addChild(leaf);

        State monthTree = new Leaf();

        monthTree.addChild(monthDigits);
        monthTree.addChild(monthName);
        monthTree.addChild(monthText);

        return monthTree;
    }

    public State getDayTree() {
        yesterday.addChild(leaf);
        tomorrow.addChild(leaf);
        today.addChild(leaf);
        beforeYesterday.addChild(leaf);
        afterTomorrow.addChild(leaf);

        dayDigitsAlone.addChild(getMonthTree());
        dayDigits.addChild(getMonthTree())
                .addChild(leaf);

        dayText.addChild(dayDigits);
        dayText.addChild(leaf);

        State dayTree = new Leaf();

        dayTree.addChild(dayText);
        dayTree.addChild(dayDigitsAlone);
        dayTree.addChild(yesterday);
        dayTree.addChild(tomorrow);
        dayTree.addChild(beforeYesterday);
        dayTree.addChild(afterTomorrow);
        dayTree.addChild(today);

        return dayTree;
    }

    public State getWeekTree() {
        weekLast.addChild(leaf);
        weekNext.addChild(leaf);
        weekRetrasada.addChild(leaf);

        weekText.addChild(weekLast);
        weekText.addChild(weekNext);
        weekText.addChild(leaf);
        weekText.addChild(weekRetrasada);

        State weekTree = new Leaf();

        weekTree.addChild(weekText);

        return weekTree;
    }

    public State getUltimoTree() {
        ultimoMonth.addChild(leaf);
        ultimoYear.addChild(leaf);

        ultimoText.addChild(ultimoMonth);
        ultimoText.addChild(ultimoYear);

        State ultimoTree = new Leaf();

        ultimoTree.addChild(ultimoText);

        return ultimoTree;
    }

    public State getUltimaTree() {
        ultimaWeek.addChild(leaf);

        ultimaText.addChild(ultimaWeek);

        State ultima = new Leaf();

        ultima.addChild(ultimaText);

        return ultima;
    }

    public State getProximoTree() {
        proximoMonth.addChild(leaf);
        proximoYear.addChild(leaf);

        proximoText.addChild(proximoMonth);
        proximoText.addChild(proximoYear);

        State proximo = new Leaf();

        proximo.addChild(proximoText);

        return proximo;
    }

    public State getProximaTree() {
        proximaWeek.addChild(leaf);

        proximaText.addChild(proximaWeek);

        State proxima = new Leaf();

        proxima.addChild(proximaText);

        return proxima;
    }

    public State getHead() {
        return head;
    }

    public void setHead(State head) {
        this.head = head;
    }
}