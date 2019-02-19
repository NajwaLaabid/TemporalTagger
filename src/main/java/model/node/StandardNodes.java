package model.node;

import model.TempoPeriod;
import model.TempoNode;
import util.TempoLogger;
import util.TempoUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.temporal.TemporalAdjusters;
import java.util.regex.Matcher;

import static util.Constants.*;

public class StandardNodes {
    protected LocalDate refDate;

    public StandardNodes(LocalDate refDate) {
        this.refDate = refDate;
    }

    // year
    public TempoNode yearLast = new TempoNode("passado\\b|que\\s+passou\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Ano passado' encontrado. Analisando as datas...", DEBUG_STATES);

            // obter valor do ano passado a partir da data de referência
            iYear = refDate.minusYears(1).getYear();
            fYear = iYear;

            // defina o valor de mês e dia para os atecedentes
            // caso os valores foram analisados ​​anteriormente
            iMonth = antecedentPeriod.getInitialDate().getMonth();
            fMonth = antecedentPeriod.getFinalDate().getMonth();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            // Se os valores de dia e mês não foram encontrados antes,
            // forneça os limites de data do intervalo de tempo "ano"
            // (primeiro dia do ano e último dia do ano)
            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
            if (iMonth == DEFAULT_COMPONENT) iMonth = 1;
            if (fMonth == DEFAULT_COMPONENT) fMonth = 12;
        }
    };
    public TempoNode yearNext = new TempoNode("pr[óoÓ]ximo\\b|que\\s+vem\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Ano próximo' encontrado. Analisando as datas...", DEBUG_STATES);
            // mesma lógica do "ano passado"
            iYear = refDate.plusYears(1).getYear();
            fYear = iYear;

            iMonth = antecedentPeriod.getInitialDate().getMonth();
            fMonth = antecedentPeriod.getFinalDate().getMonth();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
            if (iMonth == DEFAULT_COMPONENT) iMonth = 1;
            if (fMonth == DEFAULT_COMPONENT) fMonth = 12;
        }
    };
    public TempoNode yearThis = new TempoNode("(d(e|o|este|esse)\\s+)?\\bano($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Este ano' encontrado. Analisando as datas...", DEBUG_STATES);
            // mesma lógica do "ano passado"
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = antecedentPeriod.getInitialDate().getMonth();
            fMonth = antecedentPeriod.getFinalDate().getMonth();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
            if (iMonth == DEFAULT_COMPONENT) iMonth = 1;
            if (fMonth == DEFAULT_COMPONENT) fMonth = 12;
        }
    };
    public TempoNode yearDigits = new TempoNode("([./-]|d[eo]\\s+)?\\b(\\d{4})\\b") {
        @Override
        public void computeDate(Matcher matcher) {

            TempoLogger.logText("'Ano em dígitos' encontrado. Analisando as datas...", DEBUG_STATES);
            // mesma lógica do "ano passado"
            iYear = Integer.parseInt(matcher.group(2));
            fYear = Integer.parseInt(matcher.group(2));

            iMonth = antecedentPeriod.getInitialDate().getMonth();
            fMonth = antecedentPeriod.getFinalDate().getMonth();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
            if (iMonth == DEFAULT_COMPONENT) iMonth = 1;
            if (fMonth == DEFAULT_COMPONENT) fMonth = 12;
        }
    };
    public TempoNode independence = new TempoNode("da\\s+independencia(\\s+do\\s+brasil)?\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Ano de independencia' encontrado. Analisando as datas...", DEBUG_STATES);
            // mesma lógica do "ano passado"
            iYear = BRAZIL_INDEPENDENCE_YEAR;
            fYear = iYear;

            iMonth = antecedentPeriod.getInitialDate().getMonth();
            fMonth = antecedentPeriod.getFinalDate().getMonth();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
            if (iMonth == DEFAULT_COMPONENT) iMonth = 1;
            if (fMonth == DEFAULT_COMPONENT) fMonth = 12;
        }
    };
    public TempoNode proximoYear = new TempoNode("\\b(d[oe]\\s+)?pr[óoÓ]ximo\\s+ano($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            iYear = refDate.plusYears(1).getYear();
            fYear = iYear;

            iMonth = (antecedentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT ?
                    1 : antecedentPeriod.getInitialDate().getMonth());
            fMonth = (antecedentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT ?
                    12 : antecedentPeriod.getFinalDate().getMonth());

            iDay = (antecedentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT ?
                    1 : antecedentPeriod.getInitialDate().getDay());
            fDay = (antecedentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT ? 31 :
                    antecedentPeriod.getFinalDate().getDay());
        }
    };
    public TempoNode ultimoYear = new TempoNode("\\b(d[eo]\\s+)?[úuÚ]ltimo\\s+ano($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            iYear = refDate.minusYears(1).getYear();
            fYear = iYear;

            iMonth = (antecedentPeriod.getInitialDate().getMonth() == DEFAULT_COMPONENT ?
                    1 : antecedentPeriod.getInitialDate().getMonth());
            fMonth = (antecedentPeriod.getFinalDate().getMonth() == DEFAULT_COMPONENT ?
                    12 : antecedentPeriod.getFinalDate().getMonth());

            iDay = (antecedentPeriod.getInitialDate().getDay() == DEFAULT_COMPONENT ?
                    1 : antecedentPeriod.getInitialDate().getDay());
            fDay = (antecedentPeriod.getFinalDate().getDay() == DEFAULT_COMPONENT ? 31 :
                    antecedentPeriod.getFinalDate().getDay());
        }
    };

    // month
    public TempoNode monthDigits = new TempoNode("([./-]|d(e|o|este|esse)\\s+)?\\b(1[0-2]|0?[1-9])\\b($|\\s+)?") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Mês em dígitos' encontrado. Analisando as datas...", DEBUG_STATES);
            // obter valor de ano padrão a partir da data de referência
            iYear = refDate.getYear();
            fYear = iYear;

            // obter valor de mês do regex
            iMonth = Integer.parseInt(matcher.group(3));
            fMonth = Integer.parseInt(matcher.group(3));

            // obter valor de dia a partir de datas analisadas anteriormente
            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            // Se os valores de dia não foram encontrados antes,
            // forneça os limites de data do intervalo de tempo "mês"
            // (primeiro dia do mês e último dia do mês)
            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };
    public TempoNode monthDigitLetters = new TempoNode("(d(e|o|este|esse)\\s+)?\\b(" + digitLetter_1to12 + ")\\b($|\\s+)?") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Mês em dígitos de letras' encontrado. Analisando as datas...", DEBUG_STATES);
            // mesma logica que 'mês em dígitos'
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = TempoUtil.letterToDigit(matcher.group(3));
            fMonth = TempoUtil.letterToDigit(matcher.group(3));

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };
    public TempoNode monthLast = new TempoNode("(passado|que\\s+passou)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            // caso de um mês antes do mês de referência
            LocalDate start = refDate.minusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
            LocalDate end = refDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

            // caso do mês específico do ano passado
            int parentMonth = antecedentPeriod.getInitialDate().getMonth();
            if(parentMonth != DEFAULT_COMPONENT && parentMonth != refDate.getMonthValue()){
                start = LocalDate.of(TempoUtil.getYearForLastMonth(parentMonth), parentMonth, 1);
                end = start.with(TemporalAdjusters.lastDayOfMonth());
            }

            iYear = start.getYear();
            fYear = end.getYear();

            iMonth = start.getMonthValue();
            fMonth = end.getMonthValue();

            // caso do valor do dia dado
            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            // caso de valor padrão para o dia
            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };
    public TempoNode monthName = new TempoNode("(d(e|o|este|esse)\\s+)?\\b(janeiro|fevereiro|mar[cçÇ]o|abril|maio|junho" +
            "|julho|agosto|setembro|outubro|novembro|dezembro|jan|fev|mar|abr|mai" +
            "|jun|jul|ago|set|out|nov|dez)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Mês nome' encontrado. Analisando as datas...", DEBUG_STATES);
            // mesma logica que 'mês em dígitos'
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = TempoUtil.getMonthInt(matcher.group(3));
            fMonth = TempoUtil.getMonthInt(matcher.group(3));

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };
    public TempoNode monthNext = new TempoNode("(pr[óoÓ]ximo|que\\s+vem)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            // caso de: mes passado
            LocalDate start = refDate.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
            LocalDate end = refDate.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

            // caso de: mes 9 passado
            int parentMonth = antecedentPeriod.getInitialDate().getMonth();
            if(parentMonth != DEFAULT_COMPONENT && parentMonth != refDate.getMonthValue()){
                start = LocalDate.of(TempoUtil.getYearForNextMonth(parentMonth), parentMonth, 1);
                end = start.with(TemporalAdjusters.lastDayOfMonth());
            }

            iMonth = start.getMonthValue();
            fMonth = end.getMonthValue();

            iYear = start.getYear();
            fYear = end.getYear();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };
    public TempoNode monthThis = new TempoNode("(d(e|o|este|esse)\\s+)?\\bm[eêÊ]s($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Este mês' encontrado. Analisando as datas...", DEBUG_STATES);
            // mesma logica que 'mês em dígitos'
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = refDate.getMonthValue();
            fMonth = iMonth;

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };
    public TempoNode proximoMonth = new TempoNode("\\b(d[oe]\\s+)?pr[óoÓ]ximo\\s+m[eêÊ]s($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Próximo mês' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate start = refDate.plusMonths(1);
            LocalDate end = refDate.plusMonths(1);

            iYear = start.getYear();
            fYear = end.getYear();

            iMonth = start.getMonthValue();
            fMonth = end.getMonthValue();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };
    public TempoNode ultimoMonth = new TempoNode("\\b(d[eo]\\s+)?[úuÚ]ltimo\\s+m[eêÊ]s($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Último mês' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate start = refDate.minusMonths(1);
            LocalDate end = refDate.minusMonths(1);

            iYear = start.getYear();
            fYear = end.getYear();

            iMonth = start.getMonthValue();
            fMonth = end.getMonthValue();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = 1;
            if (fDay == DEFAULT_COMPONENT) fDay = 31;
        }
    };

    // week
    public TempoNode weekThis = new TempoNode("\\b(d[eoa]\\s+)?semana($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Semana' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = refDate.with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode weekLast = new TempoNode("\\b(passada|que\\s+passou)\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Semana passada' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.minusWeeks(1).with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = refDate.minusWeeks(1).with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode weekNext = new TempoNode("\\b(pr[óoÓ]xima|que\\s+vem)\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Semana próxima' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.plusWeeks(1).with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = refDate.plusWeeks(1).with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode weekRetrasada = new TempoNode("\\bretrasada\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Semana retrasada' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.minusWeeks(2).with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = refDate.minusWeeks(2).with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode ultimoWeek = new TempoNode("\\b[úuÚ]ltima\\s+semana\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Última semana' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.minusWeeks(1).with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = refDate.minusWeeks(1).with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode proximoWeek = new TempoNode("\\bpr[óoÓ]xima\\s+semana\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Próxima semana' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.plusWeeks(1).with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = refDate.plusWeeks(1).with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };

    // day
    public TempoNode beforeYesterday = new TempoNode("\\bante((s\\s+de)?\\s+)?ontem\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Anteontem' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.minusDays(2).getYear();
            fYear = iYear;

            iMonth = refDate.minusDays(2).getMonthValue();
            fMonth = iMonth;

            iDay = refDate.minusDays(2).getDayOfMonth();
            fDay = iDay;
        }
    };
    public TempoNode afterTomorrow = new TempoNode("\\bdepois\\s+de\\s+amanh[ãaÃ](?![a-zA-Z\\d])") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Depois de amanhã' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.plusDays(2).getYear();
            fYear = iYear;

            iMonth = refDate.plusDays(2).getMonthValue();
            fMonth = iMonth;

            iDay = refDate.plusDays(2).getDayOfMonth();
            fDay = iDay;
        }
    };
    public TempoNode yesterday = new TempoNode("\\bontem\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Ontem' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.minusDays(1).getYear();
            fYear = iYear;

            iMonth = refDate.minusDays(1).getMonthValue();
            fMonth = iMonth;

            iDay = refDate.minusDays(1).getDayOfMonth();
            fDay = iDay;
        }
    };
    public TempoNode tomorrow = new TempoNode("\\bamanh[ãaÃ](?![a-zA-Z\\d])") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Amanhã' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.plusDays(1).getYear();
            fYear = iYear;

            iMonth = refDate.plusDays(1).getMonthValue();
            fMonth = iMonth;

            iDay = refDate.plusDays(1).getDayOfMonth();
            fDay = iDay;
        }
    };
    public TempoNode today = new TempoNode("\\bhoje\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Hoje' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = refDate.getMonthValue();
            fMonth = iMonth;

            iDay = refDate.getDayOfMonth();
            fDay = iDay;
        }
    };
    public TempoNode dayDigits = new TempoNode("(d(e|o|este|esse)\\s+)?([12]\\d|3[01]|0?[1-9])($|\\s+)?") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia em dígitos' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = DEFAULT_COMPONENT;
            fYear = iYear;

            iMonth = DEFAULT_COMPONENT;
            fMonth = iMonth;

            iDay = Integer.parseInt(matcher.group(3));
            fDay = iDay;
        }
    };
    public TempoNode dayDigitLetters = new TempoNode("(d(e|o|este|esse)\\s+)?\\b(" + digitLetter_1to31 + ")\\b($|\\s+)?") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia em dígitos de letras' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = DEFAULT_COMPONENT;
            fYear = iYear;

            iMonth = DEFAULT_COMPONENT;
            fMonth = iMonth;

            iDay = TempoUtil.letterToDigit(matcher.group(3));
            fDay = iDay;
        }
    };
    public TempoNode dayDigitsAlone = new TempoNode("(d(e|o|este|esse)\\s+)?([12]\\d|3[01]|0?[1-9])($|\\s+)?") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia em dígitos (alone)' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = DEFAULT_COMPONENT;
            fYear = iYear;

            iMonth = DEFAULT_COMPONENT;
            fMonth = iMonth;

            iDay = Integer.parseInt(matcher.group(3));
            fDay = iDay;
        }
    };
    public TempoNode dayThis = new TempoNode("\\bdia($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = refDate.getMonthValue();
            fMonth = iMonth;

            iDay = refDate.getDayOfMonth();
            fDay = iDay;
        }
    };

    // unidades de tempo adicionais
    public TempoNode finalSemana = new TempoNode("\\b(final|fim)\\s+d[ea]\\s+semana\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Final de semana' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.with(DayOfWeek.SATURDAY);
            LocalDate endOfWeek = refDate.with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode ultimoFinalSemana = new TempoNode("\\b(d[eo]\\s+)?[úuÚ]ltimo\\s+" +
            "(final|fim)\\s+d[ea]\\s+semana\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Último final de semana' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.minusWeeks(1).with(DayOfWeek.SATURDAY);
            LocalDate endOfWeek = refDate.minusWeeks(1).with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode proximoFinalSemana = new TempoNode("\\b(d[oe]\\s+)?pr[óoÓ]ximo\\s+" +
            "(final|fim)\\s+d[ea]\\s+semana\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Próximo final de semana' encontrado. Analisando as datas...", DEBUG_STATES);
            LocalDate startOfWeek = refDate.with(DayOfWeek.SATURDAY);
            LocalDate endOfWeek = refDate.with(DayOfWeek.SUNDAY);

            iYear = startOfWeek.getYear();
            fYear = endOfWeek.getYear();

            iMonth = startOfWeek.getMonthValue();
            fMonth = endOfWeek.getMonthValue();

            iDay = startOfWeek.getDayOfMonth();
            fDay = endOfWeek.getDayOfMonth();
        }
    };
    public TempoNode semester = new TempoNode("\\b(d[eo]\\s+)?semestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Semestre' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getSemester(0, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = antecedentPeriod.getInitialDate().getMonth();
            fMonth = antecedentPeriod.getFinalDate().getMonth();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            if (iDay == DEFAULT_COMPONENT) iDay = period.getInitialDate().getDay();
            if (fDay == DEFAULT_COMPONENT) fDay = period.getFinalDate().getDay();

            if (iMonth == DEFAULT_COMPONENT) iMonth = period.getInitialDate().getMonth();
            if (fMonth == DEFAULT_COMPONENT) fMonth = period.getFinalDate().getMonth();
        }
    };
    public TempoNode lastSemester = new TempoNode("passado\\b|que\\s+passou\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Semestre passado' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getSemester(-1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode nextSemester = new TempoNode("(pr[óoÓ]ximo|que\\s+vem)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Semestre próximo' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getSemester(1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode proximoSemester = new TempoNode("\\b(d[oe]\\s+)?pr[óoÓ]ximo\\s+semestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Próximo semestre' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getSemester(1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode ultimoSemester = new TempoNode("\\b(d[eo]\\s+)?[úuÚ]ltimo\\s+semestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Último semestre' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getSemester(-1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode trimester = new TempoNode("\\b(d[eo]\\s+)?trimestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Trimestre' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getTrimester(0, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode lastTrimester = new TempoNode("passado\\b|que\\s+passou\\b") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Trimestre passado' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getTrimester(-1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode nextTrimester = new TempoNode("(pr[óoÓ]ximo|que\\s+vem)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Trimestre próximo' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getTrimester(1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode proximoTrimester = new TempoNode("\\b(d[oe]\\s+)?pr[óoÓ]ximo\\s+trimestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Próximo trimestre' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getTrimester(1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };
    public TempoNode ultimoTrimester = new TempoNode("\\b(d[eo]\\s+)?[úuÚ]ltimo\\s+trimestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Último trimestre' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoPeriod period = TempoUtil.getTrimester(-1, refDate);

            iYear = period.getInitialDate().getYear();
            fYear = period.getFinalDate().getYear();

            iMonth = period.getInitialDate().getMonth();
            fMonth = period.getFinalDate().getMonth();

            iDay = period.getInitialDate().getDay();
            fDay = period.getFinalDate().getDay();
        }
    };

    // commemorative dates
    public TempoNode anoNovo = new TempoNode("\\b((dia\\s+(de)?)?ano)?(-|\\s+)?(novo|bom)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Ano-novo' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = 1;
            fMonth = iMonth;

            iDay = 1;
            fDay = iDay;
        }
    };
    public TempoNode natal = new TempoNode("\\bnatal($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Natal' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = 12;
            fMonth = iMonth;

            iDay = 25;
            fDay = iDay;
        }
    };
    public TempoNode womenDay = new TempoNode("\\bdia\\s+(internacional\\s+)?d[ae]\\s+mulher($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia internacional da mulher' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = 3;
            fMonth = iMonth;

            iDay = 8;
            fDay = iDay;
        }
    };
    public TempoNode pascoa = new TempoNode("\\bpascoa($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Pascoa' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = 4;
            fMonth = iMonth;

            iDay = 1;
            fDay = iDay;
        }
    };
    public TempoNode womenDayNational = new TempoNode("\\bdia\\s+nacional\\s+d[ae]\\s+mulher($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia nacional da mulher' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = 4;
            fMonth = iMonth;

            iDay = 30;
            fDay = iDay;
        }
    };
    public TempoNode workDay = new TempoNode("\\bdia\\s+d[oe]\\s+trabalho($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia do trabalho' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = 5;
            fMonth = iMonth;

            iDay = 1;
            fDay = iDay;
        }
    };
    public TempoNode loversDay = new TempoNode("\\bdia\\s+dos\\s+namorados($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Dia dos namoradores' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.getYear();
            fYear = iYear;

            iMonth = 6;
            fMonth = iMonth;

            iDay = 12;
            fDay = iDay;
        }
    };
    public TempoNode ultimoAnoNovo = new TempoNode("\\b(d[eo]\\s+)?[úuÚ]ltimo\\s+((dia\\s+(de)?)?ano)?(-|\\s+)?" +
            "(novo|bom)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Último ano-novo' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.minusYears(1).getYear();
            fYear = iYear;

            iMonth = 1;
            fMonth = iMonth;

            iDay = 1;
            fDay = iDay;
        }
    };
    public TempoNode ultimoNatal = new TempoNode("\\b(d[eo]\\s+)?[úuÚ]ltimo\\s+natal($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Último natal' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.minusYears(1).getYear();
            fYear = iYear;

            iMonth = 12;
            fMonth = iMonth;

            iDay = 25;
            fDay = iDay;
        }
    };
    public TempoNode proximoAnoNovo = new TempoNode("\\b(d[oe]\\s+)?pr[óoÓ]ximo\\s+((dia\\s+(de)?)?ano)?(-|\\s+)?" +
            "(novo|bom)($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Próximo ano-novo' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.plusYears(1).getYear();
            fYear = iYear;

            iMonth = 1;
            fMonth = iMonth;

            iDay = 1;
            fDay = iDay;
        }
    };
    public TempoNode proximoNatal = new TempoNode("\\b(d[oe]\\s+)?pr[óoÓ]ximo\\s+natal($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Próximo natal' encontrado. Analisando as datas...", DEBUG_STATES);
            iYear = refDate.plusYears(1).getYear();
            fYear = iYear;

            iMonth = 12;
            fMonth = iMonth;

            iDay = 25;
            fDay = iDay;
        }
    };
}


