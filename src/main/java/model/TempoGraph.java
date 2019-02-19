package model;

import model.node.CardinalNodes;
import model.node.FinalNodes;
import model.node.QuantityNodes;
import model.node.StandardNodes;
import lombok.Getter;

import java.time.LocalDate;

/**
 * classe construindo o gráfico geral da biblioteca.
 * @author Najwa Laabid
 */
@Getter
public class TempoGraph {
    LocalDate refDate;
    private TempoNode head;

    private FinalNodes finalStates;
    private StandardNodes states;
    private CardinalNodes cardinalStates;
    private QuantityNodes quantityStates;

    /**
     * Construtor com data de referência personalizada
     * @param refDate data de referência
     */
    public TempoGraph(LocalDate refDate){
        this.refDate = refDate;

        finalStates = new FinalNodes(refDate);
        states = new StandardNodes(refDate);
        cardinalStates = new CardinalNodes(refDate);
        quantityStates = new QuantityNodes(refDate);

        head = new TempoNode();

        head.addConnection(cardinalsGraph())
                .addConnection(yearGraph())
                .addConnection(monthGraph())
                .addConnection(weekendGraph())
                .addConnection(weekGraph())
                .addConnection(dayGraph())
                .addConnection(commemorativeGraph())
                .addConnection(semesterGraph())
                .addConnection(trimesterGraph())
                .addConnection(quantityGraph());
    }
    /**
     * Construtor com data de referência default
     */
    public TempoGraph()  {
        this(LocalDate.now());
    }
    /**
     * construir o subgrafo de datas cardinais
     * @return o nó inicial do gráfico
     */
    public TempoNode cardinalsGraph() {
        cardinalStates.cardinalsDay.addConnection(weekGraph())
                .addConnection(monthGraph())
                .addConnection(trimesterGraph())
                .addConnection(semesterGraph())
                .addConnection(yearGraph())
                .addConnection(finalStates.cardinalsFinal)
                .addConnection(cardinalStates.cardinals);

        cardinalStates.cardinalsWeek.addConnection(monthGraph())
                .addConnection(trimesterGraph())
                .addConnection(semesterGraph())
                .addConnection(yearGraph())
                .addConnection(finalStates.cardinalsFinal)
                .addConnection(cardinalStates.cardinals);

        cardinalStates.cardinalsMonth.addConnection(trimesterGraph())
                .addConnection(semesterGraph())
                .addConnection(yearGraph())
                .addConnection(finalStates.cardinalsFinal)
                .addConnection(cardinalStates.cardinals);

        cardinalStates.cardinalsTrimester.addConnection(semesterGraph())
                .addConnection(yearGraph())
                .addConnection(finalStates.cardinalsFinal)
                .addConnection(cardinalStates.cardinals);

        cardinalStates.cardinalsSemester.addConnection(yearGraph())
                .addConnection(finalStates.cardinalsFinal)
                .addConnection(cardinalStates.cardinals);

        cardinalStates.cardinals.addConnection(cardinalStates.cardinalsDay)
                .addConnection(cardinalStates.cardinalsWeek)
                .addConnection(cardinalStates.cardinalsMonth)
                .addConnection(cardinalStates.cardinalsTrimester)
                .addConnection(cardinalStates.cardinalsSemester);

        return cardinalStates.cardinals;
    }
    /**
     * construir o subgrafo de quantidades de datas
     * @return o nó inicial do gráfico
     */
    public TempoNode quantityGraph(){
        TempoNode quantityTree = new TempoNode();

        quantityStates.daysPlus.addConnection(finalStates.quantityFinal);
        quantityStates.daysMinus.addConnection(finalStates.quantityFinal);
        quantityStates.weeksPlus.addConnection(finalStates.quantityFinal);
        quantityStates.weeksMinus.addConnection(finalStates.quantityFinal);
        quantityStates.monthsPlus.addConnection(finalStates.quantityFinal);
        quantityStates.monthsMinus.addConnection(finalStates.quantityFinal);
        quantityStates.yearsPlus.addConnection(finalStates.quantityFinal);
        quantityStates.yearsMinus.addConnection(finalStates.quantityFinal);
        quantityStates.semesterPlus.addConnection(finalStates.quantityFinal);
        quantityStates.semesterMinus.addConnection(finalStates.quantityFinal);
        quantityStates.trimesterPlus.addConnection(finalStates.quantityFinal);
        quantityStates.trimesterMinus.addConnection(finalStates.quantityFinal);

        quantityStates.letters.addConnection(quantityStates.daysPlus)
                .addConnection(quantityStates.daysMinus)
                .addConnection(quantityStates.weeksPlus)
                .addConnection(quantityStates.weeksMinus)
                .addConnection(quantityStates.monthsPlus)
                .addConnection(quantityStates.monthsMinus)
                .addConnection(quantityStates.yearsPlus)
                .addConnection(quantityStates.yearsMinus)
                .addConnection(quantityStates.semesterPlus)
                .addConnection(quantityStates.semesterMinus)
                .addConnection(quantityStates.trimesterPlus)
                .addConnection(quantityStates.trimesterMinus);

        quantityStates.digits.addConnection(quantityStates.daysPlus)
                .addConnection(quantityStates.daysMinus)
                .addConnection(quantityStates.weeksPlus)
                .addConnection(quantityStates.weeksMinus)
                .addConnection(quantityStates.monthsPlus)
                .addConnection(quantityStates.monthsMinus)
                .addConnection(quantityStates.yearsPlus)
                .addConnection(quantityStates.yearsMinus)
                .addConnection(quantityStates.semesterPlus)
                .addConnection(quantityStates.semesterMinus)
                .addConnection(quantityStates.trimesterPlus)
                .addConnection(quantityStates.trimesterMinus);

        quantityTree.addConnection(quantityStates.digits)
                .addConnection(quantityStates.letters);

        return quantityTree;
    }
    /**
     * construir o subgrafo de referência temporais ao "final de semana"
     * @return o nó inicial do gráfico
     */
    public TempoNode weekendGraph(){
        states.finalSemana.addConnection(finalStates.posFinalState);

        TempoNode weekEndTree = new TempoNode();

        weekEndTree.addConnection(states.ultimoFinalSemana)
                .addConnection(states.proximoFinalSemana)
                .addConnection(states.finalSemana);

        return weekEndTree;
    }
    /**
     * construir o subgrafo do ano
     * @return o nó inicial do gráfico
     */
    public TempoNode yearGraph() {
        TempoNode yearTree = new TempoNode();

        states.yearDigits.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.yearLast.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.yearNext.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.proximoYear.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.ultimoYear.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.yearThis.addConnection(states.yearDigits)
                .addConnection(states.yearLast)
                .addConnection(states.yearNext)
                .addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        // year subtree
        yearTree.addConnection(states.yearThis)
                .addConnection(states.yearDigits)
                .addConnection(states.proximoYear)
                .addConnection(states.ultimoYear);

        return yearTree;
    }
    /**
     * construir o subgrafo de mês
     * @return o nó inicial do gráfico
     */
    public TempoNode monthGraph() {
        states.monthNext.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.monthLast.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.proximoMonth.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.ultimoMonth.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.monthName.addConnection(states.monthNext)
                .addConnection(states.monthLast)
                .addConnection(yearGraph())
                .addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.monthDigits.addConnection(states.monthNext)
                .addConnection(states.monthLast)
                .addConnection(yearGraph())
                .addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.monthDigitLetters.addConnection(states.monthNext)
                .addConnection(states.monthLast)
                .addConnection(yearGraph())
                .addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.monthThis.addConnection(states.monthDigits)
                .addConnection(states.monthDigitLetters)
                .addConnection(states.monthName)
                .addConnection(states.monthLast)
                .addConnection(states.monthNext)
                .addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        TempoNode monthTree = new TempoNode();

        monthTree.addConnection(states.monthDigits)
                .addConnection(states.monthDigitLetters)
                .addConnection(states.monthName)
                .addConnection(states.monthThis)
                .addConnection(states.ultimoMonth)
                .addConnection(states.proximoMonth);

        return monthTree;
    }
    /**
     * construir o subgrafo de dia
     * @return o nó inicial do gráfico
     */
    public TempoNode dayGraph() {
        states.yesterday.addConnection(finalStates.posFinalState);
        states.tomorrow.addConnection(finalStates.posFinalState);
        states.today.addConnection(finalStates.posFinalState);
        states.beforeYesterday.addConnection(finalStates.posFinalState);
        states.afterTomorrow.addConnection(finalStates.posFinalState);

        states.dayDigitsAlone.addConnection(monthGraph());
        states.dayDigits.addConnection(monthGraph())
                .addConnection(finalStates.posFinalState);
        states.dayDigitLetters.addConnection(monthGraph());

        states.dayThis.addConnection(states.dayDigits)
                .addConnection(states.dayDigitLetters)
                .addConnection(finalStates.posFinalState);

        TempoNode dayTree = new TempoNode();

        dayTree.addConnection(states.dayThis)
                .addConnection(states.dayDigitsAlone)
                .addConnection(states.yesterday)
                .addConnection(states.tomorrow)
                .addConnection(states.beforeYesterday)
                .addConnection(states.afterTomorrow)
                .addConnection(states.today);

        return dayTree;
    }
    /**
     * construir o subgrafo de semana
     * @return o nó inicial do gráfico
     */
    public TempoNode weekGraph() {
        states.weekLast.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.weekNext.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.weekRetrasada.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.ultimoWeek.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.proximoWeek.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.weekThis.addConnection(states.weekLast)
                .addConnection(states.weekNext)
                .addConnection(states.weekRetrasada)
                .addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        TempoNode weekTree = new TempoNode();

        weekTree.addConnection(states.ultimoWeek)
                .addConnection(states.proximoWeek)
                .addConnection(states.weekThis);

        return weekTree;
    }
    /**
     * construir o subgrafo de datas comemorativas
     * @return o nó inicial do gráfico
     */
    public TempoNode commemorativeGraph(){
        TempoNode comHead = new TempoNode();
        states.anoNovo.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.ultimoAnoNovo.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.proximoAnoNovo.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.natal.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.ultimoNatal.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.proximoNatal.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.womenDay.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.pascoa.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.womenDayNational.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.workDay.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);
        states.loversDay.addConnection(yearGraph())
                .addConnection(finalStates.posFinalState);

        comHead.addConnection(states.anoNovo)
                .addConnection(states.ultimoAnoNovo)
                .addConnection(states.proximoAnoNovo)
                .addConnection(states.natal)
                .addConnection(states.ultimoNatal)
                .addConnection(states.proximoNatal)
                .addConnection(states.womenDay)
                .addConnection(states.pascoa)
                .addConnection(states.womenDayNational)
                .addConnection(states.workDay)
                .addConnection(states.loversDay);

        return comHead;
    }
    /**
     * construir o subgrafo do semestre
     * @return o nó inicial do gráfico
     */
    public TempoNode semesterGraph() {
        TempoNode semesterTree = new TempoNode();

        states.lastSemester.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.nextSemester.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.semester.addConnection(states.lastSemester)
                .addConnection(states.nextSemester)
                .addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        states.proximoSemester.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);
        states.ultimoSemester.addConnection(finalStates.posFinalState)
                .addConnection(finalStates.cardinalsFinal);

        semesterTree.addConnection(states.semester)
                .addConnection(states.proximoSemester)
                .addConnection(states.ultimoSemester);

        return semesterTree;
    }
    /**
     * construir o subgrafo de trimestre
     * @return o nó inicial do gráfico
     */
    public TempoNode trimesterGraph() {
        TempoNode trimesterTree = new TempoNode();

        states.trimester.addConnection(states.lastTrimester)
                .addConnection(states.nextTrimester)
                .addConnection(finalStates.posFinalState);

        trimesterTree.addConnection(states.trimester)
                .addConnection(states.proximoTrimester)
                .addConnection(states.ultimoTrimester);

        return trimesterTree;
    }
}