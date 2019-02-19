package algorithm;

import model.TempoGraph;
import model.TempoNode;
import model.TempoPeriod;
import util.TempoLogger;

import java.time.LocalDate;
import java.util.ArrayList;

import static util.Constants.*;


/**
 * Classe para executar DFS (pesquisa em profundidade) no autômato da biblioteca.
 * A classe tenta analisar todas as expressões de tempo em sua ordem de aparecimento em relação a um conjunto
 * de expressões de tempo predefinidas no TempoGraph. O algoritmo DFS continua em execução até que nenhuma outra
 * data seja encontrada ou que o texto inteiro seja verificado. O algoritmo é executado através da função
 * principal da classe "run" e é invocado em todos os Parsers da biblioteca. "Run" retorna uma lista de todas
 * as expressões de tempo encontradas e analisadas, a serem avaliadas e filtradas pela classe Reductor
 * nos analisadores.
 * @author Najwa Laabid
 */
public class DFS {
    ArrayList<TempoPeriod> dates;
    ArrayList<TempoPeriod> finalDates;
    TempoGraph tempoGraph;
    int highestIndexFound;
    LocalDate refDate;
    Reductor reductor;

    /**
     * Construtor com data de referência personalizável
     * @param refDate
     */
    public DFS(LocalDate refDate) {
        this.refDate = refDate;
    }

    /**
     * Construtor com data de referência default (data de hoje)
     */
    public DFS() {
        this(LocalDate.now());
    }

    /**
     * Atravessa o autômato da biblioteca no modo DFS com o texto do usuário como "input" para localizar e analisar
     * todas as expressões de tempo reconhecíveis para a biblioteca.
     * @param text texto do usuário em linguagem natural
     * @return lista das datas encontradas e analisadas
     */
    public ArrayList<TempoPeriod> run(String text) {
        TempoLogger.logText("========== DFS: run função", DEBUG_DFS);
        dates = new ArrayList<>();
        finalDates = new ArrayList<>();
        reductor = new Reductor();

        int lastSize = 0;
        int round = 0;

        // inicialize o autômato do algoritmo
        tempoGraph = new TempoGraph(refDate);

        // primeira chamada para função de execução dfs recursiva
        run(tempoGraph.getHead(), text, 0, new TempoPeriod());

        highestIndexFound = 0;

        while(dates.size() > 0){ // enquanto estamos encontrando novas datas
            round++;

            // atualize a variável de datas após remover duplicatas e a análises incompletas de data.
            finalDates.addAll(reductor.run(dates));
            dates = reductor.run(dates);

            // continuar a análise do texto a partir do índice do highestIndexFound
            text = updateText(text);
            dates.clear();

            TempoLogger.logText("Round " + round + " with text: " + text, DEBUG_DFS);
            run(tempoGraph.getHead(), text, 0, new TempoPeriod());
        }

        return finalDates;
    }

    public String updateText(String text){
        for(int i = 0; i < dates.size(); i++) {
            text = text.replace(dates.get(i).getDateString(), "");
        }

        return text;
    }

    /**
     * Run recursiva: implementação recursiva de uma passagem DFS do autômato da biblioteca.
     * A travessia para quando um estado final é encontrado.
     * Caso contrário, a função de conversão de cada estado é executada antes de passar para suas conexões.
     * @param node
     * @param text
     * @param startPos
     * @param parentPeriod
     */
    public void run(TempoNode node, String text, int startPos, TempoPeriod parentPeriod) {
        TempoLogger.logText("No node (estado): ", DEBUG_DFS);

        TempoLogger.logText("Do estado precedente: ", DEBUG_DFS);
        TempoLogger.logPeriod(parentPeriod, DEBUG_DFS);

        // se nenhuma conexão for encontrada, o estado é um estado final
        if(node.getConnections().size() == 0) {
            node.convert(text,  parentPeriod);

            TempoLogger.logText("Final node encontrado. ", DEBUG_DFS);
            // adicione a data encontrada no estado final à variável de datas se não estiver vazia
            if(!node.getCurDate().empty()) {
                TempoLogger.logText("Adicionado a data... ", DEBUG_DFS);
                dates.add(node.getCurDate());
            }
            return;
        }
        // se o estado atual não for final, execute a função do estado atual e verifique suas conexões
        if(node.convert(text, parentPeriod)) {
            TempoLogger.logText("Número de estados conectados: ", DEBUG_DFS);
            for (int i = 0; i < node.getConnections().size(); i++) {
                run(node.getConnections().get(i), text, 0, node.getCurDate());
            }
        }
    }
}
