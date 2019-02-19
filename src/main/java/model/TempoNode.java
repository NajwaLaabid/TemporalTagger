package model;

import util.TempoLogger;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Constants.DEBUG_STATE;
import static util.Constants.DEFAULT_COMPONENT;

/**
 *
 * modelo geral dos nós que compõem o BiaGraph. O TempoNode é feito de um regex para encontrar
 * um ou muitos componentes de data no texto do usuário e uma entidade lógica para analisar elementos
 * de data do regex correspondido.
 * @author Najwa Laabid
 */
@Getter
@Setter
public class TempoNode {
    protected ArrayList<TempoNode> connections;
    protected TempoPeriod curDate;
    protected String regex;
    protected String dateStr;

    protected int iDay;
    protected int fDay;
    protected int iMonth;
    protected int fMonth;
    protected int iYear;
    protected int fYear;
    protected int startIdx;
    protected int endIdx;
    protected ArrayList<TempoUnit> cardinals;
    protected ArrayList<TempoUnit> quantities;
    protected int cardinalNb;
    protected int quantity;
    protected TempoPeriod antecedentPeriod;

    /**
     * construtor com entrada do usuário
     * @param text entrada do usuário
     */
    public TempoNode(String text) {
        this.regex = text;

        this.connections = new ArrayList<>();
        this.curDate = new TempoPeriod();
        this.cardinals = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.antecedentPeriod = new TempoPeriod();

        this.iDay = DEFAULT_COMPONENT;
        this.fDay = DEFAULT_COMPONENT;
        this.iMonth = DEFAULT_COMPONENT;
        this.fMonth = DEFAULT_COMPONENT;
        this.iYear = DEFAULT_COMPONENT;
        this.fYear = DEFAULT_COMPONENT;
        this.startIdx = DEFAULT_COMPONENT;
        this.endIdx = DEFAULT_COMPONENT;

        this.dateStr = "";
    }

    /**
     * construtor sem entrada do usuário
     */
    public TempoNode() {
        this("");
    }

    /**
     * adiciona uma borda conectando o nó atual a outro nó
     * @param connection outro nó a ser conectado
     * @return nó atual
     */
    public TempoNode addConnection(TempoNode connection) {
        this.connections.add(connection);
        return this;
    }

    /**
     * Aloca os valores correspondentes aos componentes de data / período.
     * Geralmente é substituído em todas as implementações do nó.
     * @param matcher
     */
    public void computeDate(Matcher matcher){
        // Este código é substituído em todos os estados de análise.
        // o comportamento padrão é usar os dados da conexão antecedente.
        iDay = antecedentPeriod.getInitialDate().getDay();
        fDay = antecedentPeriod.getFinalDate().getDay();

        iMonth = antecedentPeriod.getInitialDate().getMonth();
        fMonth = antecedentPeriod.getFinalDate().getMonth();

        iYear = antecedentPeriod.getInitialDate().getYear();
        fYear = antecedentPeriod.getFinalDate().getYear();
    }

    /**
     * Função significava corresponder ao regex e chama computeDates()
     * para preencher os componentes de datas correspondentes.
     * @param text texto do usuário
     * @param antecedentPeriod período analisado no nó antecedente
     * @return retorna true se a regex foi correspondida e a data analisada e, caso contrário, false
     */
    public boolean convert(String text, TempoPeriod antecedentPeriod) {
        this.antecedentPeriod = antecedentPeriod;
        dateStr = antecedentPeriod.getDateString();
        // transmitir os valores relevantes cardeais e quantitativos,
        // para serem usados ​​na análise em seus estados finais correspondentes.
        cardinals = antecedentPeriod.getCardinals();
        cardinalNb = antecedentPeriod.getCardinalNb();
        quantity = antecedentPeriod.getQuantity();
        quantities = antecedentPeriod.getQuantities();

        if(antecedentPeriod.getEndIdx() != DEFAULT_COMPONENT
                && antecedentPeriod.getEndIdx() < text.length()) {
            text = text.substring(antecedentPeriod.getEndIdx());
        }

        // procure por regex no texto do usuário
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(text);

        int startIdx, endIdx;

        while(matcher.find()) { // encontrar todas as instâncias do regex do estado\
            // Se o antecedente estiver vazio ou o regex foi coincidente no início do texto,
            // prossiga com a conversão
            if (antecedentPeriod.getEndIdx() == DEFAULT_COMPONENT || matcher.start() == 0) {
                dateStr += matcher.group();
                TempoLogger.logText("In Convert", DEBUG_STATE);

                // atualiza startIdx e endIdx com o valor da regex atual correspondida + start pos da string de entrada
                // (substring do texto do usuário)
                startIdx = matcher.start();
                endIdx = matcher.end();

                // se o atecedent tiver uma data analisada, use o valor do startIdx do antecedente
                if (matcher.start() == matcher.end() || antecedentPeriod.getStartIdx() != DEFAULT_COMPONENT) {
                    startIdx = antecedentPeriod.getStartIdx();
                    endIdx = matcher.end() + antecedentPeriod.getEndIdx();
                }

                // código específico para a lógica de análise de nós individuais
                computeDate(matcher);
                // crie um novo objeto da data atual a partir dos valores analisados ​​em computeDate ()
                curDate = new TempoPeriod(dateStr, startIdx, endIdx,
                        new TempoDate(iDay, iMonth, iYear),
                        new TempoDate(fDay, fMonth, fYear));
                // atualizar os valores dos cardeais e quantidades
                curDate.setQuantity(quantity);
                curDate.setQuantities(quantities);
                curDate.setCardinals(cardinals);
                curDate.setCardinalNb(cardinalNb);

                return true;
            }
        }

        // se o regex não foi encontrado ou o índice não foi adjacente ao índice do atecedent,
        // defina o curDate como a data do antecedente e retorne false
        setCurDate(antecedentPeriod);

        TempoLogger.logText("No Match", DEBUG_STATE);

        return false;
    }

    /**
     * preenche os componentes de data do objeto TempoPeriod fornecido
     * @param period
     */
    public void setPeriodDates(TempoPeriod period) {
        iDay = period.getInitialDate().getDay();
        fDay = period.getFinalDate().getDay();

        iMonth = period.getInitialDate().getMonth();
        fMonth = period.getFinalDate().getMonth();

        iYear = period.getInitialDate().getYear();
        fYear = period.getFinalDate().getYear();

        curDate = new TempoPeriod(dateStr, startIdx, endIdx,
                new TempoDate(iDay, iMonth, iYear),
                new TempoDate(fDay, fMonth, fYear));
    }
}