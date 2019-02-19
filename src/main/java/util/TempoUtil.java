package util;

import model.TempoDate;
import model.TempoPeriod;
import model.TempoUnit;
import javafx.util.Pair;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static model.TempoUnit.*;

/**
 * Classe de utilitários definindo funções auxiliares usadas em todas as classes da biblioteca.
 * @author Najwa Laabid
 */
public class TempoUtil {
    /**
     * Função para retornar o valor numérico de um mês, dado seu nome.
     * @param month nome do mês
     * @return valor numérico do mês
     */
    public static int getMonthInt(String month) {
        Hashtable<String, Integer> monthNames = new Hashtable<>();

        monthNames.put("jan", 1);
        monthNames.put("fev", 2);
        monthNames.put("mar", 3);
        monthNames.put("abr", 4);
        monthNames.put("mai", 5);
        monthNames.put("jun", 6);
        monthNames.put("jul", 7);
        monthNames.put("ago", 8);
        monthNames.put("set", 9);
        monthNames.put("out", 10);
        monthNames.put("nov", 11);
        monthNames.put("dez", 12);

        return (monthNames.get(getMonthIdx(month)) != null? monthNames.get(getMonthIdx(month)):-1);
    }

    /**
     * Função para retornar as três primeiras letras de um nome de mês em letras minúsculas.
     * @param month nome do mês
     * @return ss três primeiras letras do nome do mês
     */
    public static String getMonthIdx(String month){
        return month.substring(0, 3).toLowerCase();
    }

    /**
     * Retorna o valor do ano para a data relativa "último mês"
     * @param month valor numérico do mês
     * @return valor computado do ano
     */
    public static int getYearForLastMonth(int month) {
        if(month < LocalDate.now().getMonthValue()) {
            return LocalDate.now().getYear();
        }
        return LocalDate.now().getYear() - 1;
    }

    /**
     * Retorna o valor do ano para a data relativa "próximo mês"
     * @param month valor numérico do mês
     * @return valor computado do ano
     */
    public static int getYearForNextMonth(int month) {
        if(month > LocalDate.now().getMonthValue()) {
            return LocalDate.now().getYear();
        }
        return LocalDate.now().getYear() + 1;
    }

    /**
     * Função para converter números de letras para valores numéricos
     * @param letter número em letras
     * @return número em dígitos
     */
    public static int letterToDigit(String letter){
        Hashtable<String, Integer> letters = new Hashtable<>();
        String indx = letter.toLowerCase().replaceAll("\\s","");

        letters.put("um", 1);
        letters.put("primeiro", 1);
        letters.put("dois", 2);
        letters.put("segundo", 2);
        letters.put("tres", 3);
        letters.put("três", 3);
        letters.put("treceiro", 3);
        letters.put("quatro", 4);
        letters.put("quarto", 4);
        letters.put("cinco", 5);
        letters.put("quinto", 5);
        letters.put("seis", 6);
        letters.put("sete", 7);
        letters.put("oito", 8);
        letters.put("nove", 9);
        letters.put("deze", 10);
        letters.put("onze", 11);
        letters.put("doze", 12);
        letters.put("treze", 13);
        letters.put("quatorze", 14);
        letters.put("quinze", 15);
        letters.put("dezesseis", 16);
        letters.put("dezessete", 17);
        letters.put("dezoito", 18);
        letters.put("dezenove", 19);
        letters.put("vinte", 20);
        letters.put("vinteum", 21);
        letters.put("vinteeum", 21);
        letters.put("vintedois", 22);
        letters.put("vinteedois", 22);
        letters.put("vintetres", 23);
        letters.put("vinteetres", 23);
        letters.put("vintequatro", 24);
        letters.put("vinteequatro", 24);
        letters.put("vintecinqo", 25);
        letters.put("vinteecinqo", 25);
        letters.put("vinteseis", 26);
        letters.put("vinteeseis", 26);
        letters.put("vintesete", 27);
        letters.put("vinteesete", 27);
        letters.put("vinteoito", 28);
        letters.put("vinteeoito", 28);
        letters.put("vintenove", 29);
        letters.put("vinteenove", 29);
        letters.put("trinta", 30);
        letters.put("trintaum", 31);
        letters.put("trintaeum", 31);

        return (letters.get(indx) != null? letters.get(indx):-1);
    }

    /**
     * Função para retornar o trimestre ao qual o dado mês pertence
     * @param quantity dado mês
     * @param refDate data de referência
     * @return trimestre como período
     */
    public static TempoPeriod getTrimester(int quantity, LocalDate refDate) {
        int startTrim[] = {1, 4, 7, 10};
        int endTrim[] = {3, 6, 9, 12};

        return getTriSemesterRelative(quantity, startTrim, endTrim, refDate);
    }

    /**
     * Função para retornar o semestre ao qual o dado mês pertence
     * @param quantity dado mês
     * @param refDate data de referência
     * @return trimestre como período
     */
    public static TempoPeriod getSemester(int quantity, LocalDate refDate) {
        int startSem[] = {1, 7};
        int endSem[] = {6, 12};

        return getTriSemesterRelative(quantity, startSem, endSem, refDate);
    }

    /**
     * Função auxiliar para retornar o semestre ou trimestre de um determinado mês.
     * @param quantity dado mês
     * @param refDate data de referência
     * @param startArr array de meses iniciais de semestres / trimestres
     * @param endArr array de meses finais de semestres / trimestres
     * @return trimestre como período
     */
    public static TempoPeriod getTriSemesterRelative(int quantity, int[] startArr, int[] endArr, LocalDate refDate) {
        int startMonth = -1;
        int endMonth = -1;
        int monthsMin = 12/endArr.length;
        int month = refDate.plusMonths(quantity*monthsMin).getMonthValue();
        int year = refDate.plusMonths(quantity*monthsMin).getYear();

        for(int i = 0; i < endArr.length; i++) {
            if(month <= endArr[i]) {
                endMonth = endArr[i];
                startMonth = startArr[i];
                break;
            }
        }
        return new TempoPeriod(new TempoDate(1, startMonth, year), new TempoDate(31, endMonth, year));
    }

    /**
     * Obter valor trimestral dado o seu cardeal no ano
     * @param refDate data de referência
     * @param card cardeal no ano
     * @return
     */
    public static TempoPeriod getTrimesterCardinal(int card, LocalDate refDate) {
        int startTrim[] = {1, 4, 7, 10};
        int endTrim[] = {3, 6, 9, 12};

        int idx = card%4;

        if(idx == 0)
            idx = 4; // max value

        int yearsNb = (int) Math.ceil(card/4.0 - 1);
        int year = refDate.plusYears(yearsNb).getYear();

        return new TempoPeriod(new TempoDate(1, startTrim[idx-1], year),
                new TempoDate(31, endTrim[idx-1], year));
    }

    /**
     * Obter valor semestral dado o seu cardeal no ano
     * @param refDate data de referência
     * @param card cardeal no ano
     * @return
     */
    public static TempoPeriod getSemesterCardinal(int card, LocalDate refDate) {
        int startSem[] = {1, 7};
        int endSem[] = {6, 12};

        int idx = card%2;

        if(idx == 0)
            idx = 2; // max value

        int yearsNb = (int) Math.ceil(card/2.0 - 1);
        int year = refDate.plusYears(yearsNb).getYear();

        return new TempoPeriod(new TempoDate(1, startSem[idx-1], year),
                new TempoDate(31, endSem[idx-1], year));
    }

    /**
     * Função retornando o valor numérico de um cardeal em letras
     * @param cardString um cardeal em letras
     * @return um cardeal em numéros
     */
    public  static int convertToCardinal(String cardString) {
        Hashtable<String, Integer> cardinals = new Hashtable<>();

        String idx = cardString.replaceAll("\\s", "").toLowerCase();

        cardinals.put("primeir", 1);
        cardinals.put("segund", 2);
        cardinals.put("terceir", 3);
        cardinals.put("quart", 4);

        if(cardinals.get(idx) == null)
            return 1;

        return cardinals.get(idx);
    }

    /**
     * soma os valores de quantidade dados
     * @param sum soma em computação
     * @param unit unidade de tempo de quantidade dada
     * @return soma em computação
     */
    public static TempoPeriod sumUnit(TempoPeriod sum, TempoUnit unit) {
        LocalDate date;

        switch(unit){
            case SEMESTER:
                return getSemester( unit.getNb(), sum.getInitialDateFormatted());
            case WEEK:
                date = sum.getInitialDateFormatted().plusWeeks(unit.getNb());
                return new TempoPeriod(date);
            case TRIMESTER:
                return getTrimester( unit.getNb(), sum.getInitialDateFormatted());
            case MONTH:
                date = sum.getInitialDateFormatted().plusMonths(unit.getNb());
                return new TempoPeriod(date);
            case DAY:
                date = sum.getInitialDateFormatted().plusDays(unit.getNb());
                return new TempoPeriod(date);
            case YEAR:
                date = sum.getInitialDateFormatted().plusYears(unit.getNb());
                return new TempoPeriod(date);
            default:
                return sum;
        }
    }

    /**
     * dá número de unidade interna na unidade externa
     * @param inner cardeal interior
     * @param outer cardeal exterior
     * @return
     */
    public static int convertUnit(TempoUnit inner, TempoUnit outer){
        Map<Pair<TempoUnit, TempoUnit>, Integer> conversion = new HashMap<>();

        conversion.put(new Pair(DAY, DAY), 1);
        conversion.put(new Pair(WEEK, WEEK), 1);
        conversion.put(new Pair(MONTH, MONTH), 1);
        conversion.put(new Pair(TRIMESTER, TRIMESTER), 1);
        conversion.put(new Pair(SEMESTER, SEMESTER), 1);
        conversion.put(new Pair(YEAR, YEAR), 1);

        conversion.put(new Pair(DAY, WEEK), 7);
        conversion.put(new Pair(DAY, MONTH), 31);
        conversion.put(new Pair(DAY, TRIMESTER), 93); // 31 * 3 months in trimester
        conversion.put(new Pair(DAY, SEMESTER), 186); // 31 * 6 months in semester
        conversion.put(new Pair(DAY, YEAR), 365);

        conversion.put(new Pair(WEEK, MONTH), 5); // max nb on mondays in month
        conversion.put(new Pair(WEEK, TRIMESTER), 15); // 5 * 3 months in trimester
        conversion.put(new Pair(WEEK, SEMESTER), 30); // 5 * 6 months in semester
        conversion.put(new Pair(WEEK, YEAR), 60); // 5 * 12 months in year

        conversion.put(new Pair(MONTH, TRIMESTER), 3);
        conversion.put(new Pair(MONTH, SEMESTER), 6);
        conversion.put(new Pair(MONTH, YEAR), 12);

        conversion.put(new Pair(TRIMESTER, SEMESTER), 2);
        conversion.put(new Pair(TRIMESTER, YEAR), 4);

        conversion.put(new Pair(SEMESTER, YEAR), 2);

        if(conversion.get(new Pair(inner, outer)) == null)
            return 1;

        return conversion.get(new Pair(inner, outer));
    }

    /**
     * retorna um período para análise cardinal
     * @param refDate data de referência
     * @param inner cardeal interior
     * @param innerNb cardeal exterior
     * @return
     */
    public static TempoPeriod getPeriod(LocalDate refDate, TempoUnit inner, int innerNb) {
        LocalDate start;
        LocalDate end;

        switch(inner){
            case SEMESTER:
                return getSemesterCardinal(innerNb, refDate);
            case WEEK:
                start = refDate.with(TemporalAdjusters.dayOfWeekInMonth(innerNb, DayOfWeek.MONDAY));
                end = start.with(DayOfWeek.SUNDAY);
                return new TempoPeriod(start, end);
            case TRIMESTER:
                return getTrimesterCardinal(innerNb, refDate);
            case MONTH:
                start = LocalDate.of(refDate.getYear(), innerNb, 1);
                end = start.with(TemporalAdjusters.lastDayOfMonth());
                return new TempoPeriod(start, end);
            case DAY:
                if(innerNb > 0) {
                    start = refDate.plusDays(innerNb-1);
                    end = start;
                } else {
                    start = refDate.plusDays(innerNb);
                    end = start;
                }
                return new TempoPeriod(start, end);
            default:
                return new TempoPeriod();
        }
    }
}
