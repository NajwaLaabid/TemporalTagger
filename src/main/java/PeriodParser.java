import algorithm.DFS;
import model.TempoPeriod;
import util.TempoLogger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static util.Constants.DEBUG_PERIOD_PARSER;
import static util.Constants.DEFAULT_COMPONENT;

/**
 * Analisador retornando um período (data de início, data final) do texto do usuário.
 * Destina-se a ser usado em texto de linguagem natural traduzível para consultas de banco de dados
 * que requerem um intervalo de tempo. A implementação atual retorna uma data padrão (data de hoje) quando nenhuma
 * expressão de hora ou mais de 2 instâncias de data são encontradas.
 * O analisador é executado através de sua função principal "parse".
 *
 * @author Najwa Laabid
 */
public class PeriodParser {
    public ArrayList<TempoPeriod> allDates;
    public LocalDate refDate;

    /**
     * Construtor com data de referência personalizável
     * @param ref data de referência
     */
    public PeriodParser(LocalDate ref) {
        TempoLogger.logText("++++++ Period Parser inicializado com data de referência: " +
                ref.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), DEBUG_PERIOD_PARSER);
        this.refDate = ref;
    }

    /**
     * Construtor com data de referência default (data de hoje)
     */
    public PeriodParser(){
        // default data de referencia
        this(LocalDate.now());
    }

    /**
     * Função principal da classe.
     * Leva o texto do usuário em linguagem natural e retorna um período (data de início, data de término)
     * na forma de objeto TempoPeriod.
     * @param text data de referência
     * @return período encontrado
     */
    public TempoPeriod parse(String text) {
        TempoLogger.logText("========== Period Parser: parse função", DEBUG_PERIOD_PARSER);
        DFS dfs = new DFS(refDate);

        allDates = dfs.run(text);

        TempoLogger.logText("Datas retornadas por dfs", DEBUG_PERIOD_PARSER);
        TempoLogger.logArrayPeriod(allDates, DEBUG_PERIOD_PARSER);

        if(allDates.size() > 0 ) {
            ArrayList<TempoPeriod> correctDates = allDates;

            TempoLogger.logText("Datas retornadas por Reductor", DEBUG_PERIOD_PARSER);
            TempoLogger.logArrayPeriod(correctDates, DEBUG_PERIOD_PARSER);

            if (correctDates.size() == 1) {
                TempoLogger.logText("Uma data encontrada", DEBUG_PERIOD_PARSER);
                return fixDates(correctDates.get(0));
            } else if (correctDates.size() == 2) {
                TempoLogger.logText("Duas datas encontradas", DEBUG_PERIOD_PARSER);
                return mergePeriods(correctDates.get(0), correctDates.get(1));
            } else {
                // caso de mais de 3 datas => retorne erro. Por enquanto, retorne data default.
                TempoLogger.logText("Mais de duas datas encontradas => retornar a data default",
                        DEBUG_PERIOD_PARSER);
                return new TempoPeriod();
            }
        } else {
            // o texto nao tem datas => retorne data default.
            TempoLogger.logText("Nenhuma data encontrada => retornar a data default", DEBUG_PERIOD_PARSER);
            return new TempoPeriod();
        }

    }

    /**
     * Resolve datas parciais (por exemplo, de 18 a 12 deste mês => o primeiro objeto de data está faltando
     * valores de mês e ano). Resolve valores de data perdidos (ano e / ou mês) de qualquer data
     * (data inicial ou final) com referência aos valores da outra data, se presentes, caso contrário,
     * os valores da data de referência.
     * @param period período encontrado
     * @return período corrigido
     */
    private TempoPeriod fixDates(TempoPeriod period) {
        TempoLogger.logText("========== Period Parser: fixDates função", DEBUG_PERIOD_PARSER);
        int refMonth = refDate.getMonthValue();
        int refYear = refDate.getYear();

        TempoLogger.logText("Datas a serem corrigidas", DEBUG_PERIOD_PARSER);
        TempoLogger.logPeriod(period, DEBUG_PERIOD_PARSER);

        // Consertar Mês
        if(period.getInitialDate().getMonth() == DEFAULT_COMPONENT &&
                period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {
            // Caso nem data tem um valor de mês:
            // => use o valor do mês a partir da data de referência

            // Caso padrão:
            // data inicial antes da data final (initialDate.day <= finalDate.day)
            // => ambas as datas pertencem ao mesmo mês
            period.getInitialDate().setMonth(refMonth);
            period.getFinalDate().setMonth(refMonth);

            if(period.getInitialDate().getDay() > period.getFinalDate().getDay()) {
                // Caso Contrário:
                // initialDate maior que finalDate
                // => initialDate pertence ao último mês
                period.getInitialDate().setMonth(refMonth-1);
                period.getFinalDate().setMonth(refMonth);
            }
        } else if( period.getFinalDate().getMonth() == DEFAULT_COMPONENT ) {
            // Caso initialDate tem um valor de mês
            // => use o valor do mês de initialDate como um valor de mês de referência

            // use o mesmo algoritmo acima para resolver datas de referência
            refMonth = period.getInitialDate().getMonth();

            period.getInitialDate().setMonth(refMonth);
            period.getFinalDate().setMonth(refMonth);

            if(period.getInitialDate().getDay() > period.getFinalDate().getDay()) {
                period.getInitialDate().setMonth(refMonth);
                period.getFinalDate().setMonth(refMonth+1);
            }
        } else if( period.getInitialDate().getMonth() == DEFAULT_COMPONENT) {
            // Mesmo que acima, mas com o valor do mês do finalDate como referência
            refMonth = period.getFinalDate().getMonth();

            period.getInitialDate().setMonth(refMonth);
            period.getFinalDate().setMonth(refMonth);

            if(period.getInitialDate().getDay() > period.getFinalDate().getDay()) {
                period.getInitialDate().setMonth(refMonth-1);
                period.getFinalDate().setMonth(refMonth);
            }
        }

        // Consertar Ano
        // Mesma lógica do mês
        if(period.getInitialDate().getYear() == DEFAULT_COMPONENT
                && period.getInitialDate().getYear() == period.getFinalDate().getYear()) {

            period.getInitialDate().setYear(refYear);
            period.getFinalDate().setYear(refYear);

            if(period.getInitialDate().getMonth() > period.getFinalDate().getMonth()) {
                period.getInitialDate().setYear(refYear-1);
                period.getFinalDate().setYear(refYear);
            } else if(period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {

                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear);

                if(period.getInitialDate().getDay() > period.getInitialDate().getDay()) {
                    period.getInitialDate().setYear(refYear-1);
                    period.getFinalDate().setYear(refYear);
                }
            }
        } else if( period.getFinalDate().getYear() == DEFAULT_COMPONENT ) {

            refYear = period.getInitialDate().getYear();

            period.getInitialDate().setYear(refYear);
            period.getFinalDate().setYear(refYear);

            if(period.getInitialDate().getMonth() > period.getFinalDate().getMonth()) {
                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear+1);
            } else if(period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {

                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear);

                if(period.getInitialDate().getDay() > period.getInitialDate().getDay()) {
                    period.getInitialDate().setYear(refYear);
                    period.getFinalDate().setYear(refYear+1);
                }
            }
        } else if( period.getInitialDate().getYear() == DEFAULT_COMPONENT) {

            refYear = period.getFinalDate().getYear();

            period.getInitialDate().setYear(refYear);
            period.getFinalDate().setYear(refYear);

            if(period.getInitialDate().getMonth() > period.getFinalDate().getMonth()) {
                period.getInitialDate().setYear(refYear-1);
                period.getFinalDate().setYear(refYear);
            } else if(period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {

                period.getInitialDate().setYear(refYear);
                period.getFinalDate().setYear(refYear);

                if(period.getInitialDate().getDay() > period.getInitialDate().getDay()) {
                    period.getInitialDate().setYear(refYear-1);
                    period.getFinalDate().setYear(refYear);
                }
            }
        }
        return sortDates(period);
    }

    /**
     * Troca as datas inicial e final do período, se não estiverem em ordem cronológica.
     * @param period período encontrado
     * @return período com datas em ordem
     */
    private TempoPeriod sortDates(TempoPeriod period) {
        TempoLogger.logText("========== Period Parser: sortDate função", DEBUG_PERIOD_PARSER);
        boolean swap = false;

        TempoLogger.logText("Datas antes da ordenação", DEBUG_PERIOD_PARSER);
        TempoLogger.logPeriod(period, DEBUG_PERIOD_PARSER);

        // Se initialDate for maior que finalDate, troque os dois
        if(period.getInitialDate().getYear() == period.getFinalDate().getYear()) {
            if(period.getInitialDate().getMonth() == period.getFinalDate().getMonth()) {
                if(period.getInitialDate().getDay() > period.getFinalDate().getDay()) {
                    swap = true;
                }
            } else if(period.getInitialDate().getMonth() > period.getFinalDate().getMonth()) {
                swap = true;
            }
        } else if(period.getInitialDate().getYear() > period.getFinalDate().getYear()){
            swap = true;
        }

        if(swap) return new TempoPeriod(period.getFinalDate(), period.getInitialDate());

        TempoLogger.logText("Datas depois da ordenação", DEBUG_PERIOD_PARSER);
        TempoLogger.logPeriod(period, DEBUG_PERIOD_PARSER);

        return period;
    }

    /**
     * Combina dois períodos de data em um.
     * Retorna um novo período com a data inicial da primeira e a data final da segunda.
     * @param firstPeriod primeiro período
     * @param secondPeriod segundo período
     * @return merged period
     */
    private TempoPeriod mergePeriods(TempoPeriod firstPeriod, TempoPeriod secondPeriod) {
        TempoLogger.logText("========== Period Parser: mergePeriods função", DEBUG_PERIOD_PARSER);

        TempoLogger.logText("Datas mescladas", DEBUG_PERIOD_PARSER);
        TempoLogger.logPeriod(new TempoPeriod(firstPeriod.getInitialDate(), secondPeriod.getFinalDate()),
                DEBUG_PERIOD_PARSER);

        return fixDates(new TempoPeriod(firstPeriod.getInitialDate(), secondPeriod.getFinalDate()));
    }
}
