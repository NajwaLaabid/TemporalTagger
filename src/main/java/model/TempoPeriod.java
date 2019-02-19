package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import static util.Constants.DEFAULT_COMPONENT;

@Getter
@Setter
public class TempoPeriod {
    private TempoDate initialDate;
    private TempoDate finalDate;
    private int startIdx;
    private int endIdx;
    private int cardinalNb;
    private ArrayList<TempoUnit> cardinals;
    private int quantity;
    private ArrayList<TempoUnit> quantities;
    private int size;
    private String dateString;

    /**
     * Construtor para criar uma instância TempoPeriod a partir de uma data inicial e final (objetos TempoDate)
     * @param initialDate data inicial
     * @param finalDate data final
     */
    public TempoPeriod(TempoDate initialDate, TempoDate finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.startIdx = -1;
        this.endIdx = -1;
        this.size = 0;
        this.cardinals = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }
    /**
     * Construtor para criar uma instância TempoPeriod a partir de uma data inicial e final (objetos LocalDate)
     * @param initialDate
     * @param finalDate
     */
    public TempoPeriod(LocalDate initialDate, LocalDate finalDate) {
        this.initialDate = new TempoDate(initialDate.getDayOfMonth(), initialDate.getMonthValue(), initialDate.getYear());
        this.finalDate = new TempoDate(finalDate.getDayOfMonth(), finalDate.getMonthValue(), finalDate.getYear());
        this.cardinals = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }
    /**
     * Construtor para criar uma instância TempoPeriod a partir de uma data de referência (objeto LocalDate)
     * @param refDate
     */
    public TempoPeriod(LocalDate refDate) {
        this.initialDate = new TempoDate(refDate.getDayOfMonth(), refDate.getMonthValue(), refDate.getYear());
        this.finalDate = new TempoDate(refDate.getDayOfMonth(), refDate.getMonthValue(), refDate.getYear());
        this.cardinals = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    /**
     * Construtor para criar uma instância TempoPeriod a partir de uma data inicial e final (objetos TempoDate)
     * e índices de início e término.
     * @param startIdx índice de início
     * @param endIdx índice de término
     * @param initialDate data de início
     * @param finalDate data de término
     * @param str string de data
     */
    public TempoPeriod(String str, int startIdx, int endIdx, TempoDate initialDate, TempoDate finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.size = this.endIdx - this.startIdx;
        this.cardinals = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.dateString = str;
    }

    /**
     * Construtor para criar uma instância TempoPeriod default
     */
    public TempoPeriod() {
        this.initialDate = new TempoDate();
        this.finalDate = new TempoDate();
        this.startIdx = -1;
        this.endIdx = -1;
        this.size = 0;
        this.cardinals = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.dateString = "";
    }

    /**
     * Função para verificar se a instância do TempoPeriod está vazia (tem um valor padrão em todos os componentes).
     * @return true se a instância estiver vazia, caso contrário, false
     */
    public boolean empty() {
        return initialDate.empty() && finalDate.empty();
    }

    /**
     * Faz uma string fora dos componentes da data inicial
     * @return data inicial formatada como uma string
     */
    public String initialDateToString() {
        return initialDate.getDay() + "/" + initialDate.getMonth() + "/" + initialDate.getYear();
    }

    /**
     * Faz uma string fora dos componentes da data final
     * @return data final formatada como uma string
     */
    public String finalDateToString() {
        return finalDate.getDay() + "/" + finalDate.getMonth() + "/" + finalDate.getYear();
    }

    /**
     * Transforma a data inicial em um objeto java localdate
     * @return data inicial em um objeto java localdate
     */
    public LocalDate getInitialDateFormatted(){
        int day = initialDate.getDay();
        int month = initialDate.getMonth();
        int year = initialDate.getYear();

        if(!initialDate.empty()) {
            if(initialDate.getDay() == 31) {
                return LocalDate.of(initialDate.getYear(), initialDate.getMonth(), 1)
                        .with(TemporalAdjusters.lastDayOfMonth());
            }

            if(initialDate.getDay() == DEFAULT_COMPONENT) {
                day = LocalDate.now().getDayOfMonth();
            }

            if(initialDate.getMonth() == DEFAULT_COMPONENT) {
                month = LocalDate.now().getMonthValue();
            }

            if(initialDate.getYear() == DEFAULT_COMPONENT) {
                year = LocalDate.now().getYear();
            }

            return LocalDate.of(year, month, day);
        }
        return LocalDate.now();
    }

    /**
     * Transforma a data final em um objeto java localdate
     * @return data final em um objeto java localdate
     */
    public LocalDate getFinalDateFormatted(){
        int day = finalDate.getDay();
        int month = finalDate.getMonth();
        int year = finalDate.getYear();

        if(!finalDate.empty()) {
            if (finalDate.getDay() == 31) {
                return LocalDate.of(finalDate.getYear(), finalDate.getMonth(), 1)
                        .with(TemporalAdjusters.lastDayOfMonth());
            }

            if(finalDate.getDay() == DEFAULT_COMPONENT) {
                day = LocalDate.now().getDayOfMonth();
            }

            if(finalDate.getMonth() == DEFAULT_COMPONENT) {
                month = LocalDate.now().getMonthValue();
            }

            if(finalDate.getYear() == DEFAULT_COMPONENT) {
                year = LocalDate.now().getYear();
            }

            return LocalDate.of(year, month, day);
        }
        return LocalDate.now();
    }
}
