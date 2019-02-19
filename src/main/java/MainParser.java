
import algorithm.DFS;
import model.TempoPeriod;
import util.TempoLogger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static util.Constants.DEBUG_MAIN_PARSER;
import static util.Constants.DEBUG_PERIOD_PARSER;

/**
 * Um analisador geral para recuperar todas as expressões de tempo reconhecíveis para BiaTime como elas são.
 * Não usa nenhum tipo de resolução parcial no momento.
 * @author Najwa Laabid
 */
public class MainParser {
    public ArrayList<TempoPeriod> allDates;
    public LocalDate refDate;

    /**
     * Construtor com data de referência personalizável
     * @param ref data de referência
     */
    public MainParser(LocalDate ref) {
        TempoLogger.logText("++++++ MainParser inicializado com data de referência: " +
                ref.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), DEBUG_MAIN_PARSER);
        this.refDate = ref;
    }

    /**
     * Construtor com data de referência default (data de hoje)
     */
    public MainParser(){
        // default data de referencia
        this(LocalDate.now());
    }

    /**
     * Função principal da classe.
     * LRecebe o texto do usuário em linguagem natural
     * e retorna uma matriz de TempoPeriod composta de todas as expressões de tempo encontradas no texto.
     * @param text data de referência
     * @return array de BiaDate
     */
    public ArrayList<TempoPeriod> parse(String text) {
        TempoLogger.logText("========== MainParser: parse função", DEBUG_MAIN_PARSER);
        DFS dfs = new DFS(refDate);

        allDates = dfs.run(text);

        if(allDates.size() == 0 ) {
            TempoLogger.logText("Nenhuma data encontrada => retornar a data default", DEBUG_MAIN_PARSER);
        } else {
            TempoLogger.logText("Datas retornadas por dfs", DEBUG_MAIN_PARSER);
            TempoLogger.logArrayPeriod(allDates, DEBUG_MAIN_PARSER);
        }

        // resolve partial dates

        return allDates;
    }
}
