package model;


import lombok.Getter;
import lombok.Setter;

import static util.Constants.*;

@Getter
@Setter
/**
 * classe modelando componentes básicos de data (dia, mês e ano)
 * @author Najwa Laabid
 */
public class TempoDate {
    // componentes básicos de data
    private int day;
    private int month;
    private int year;

    /**
     * construtor com valores de componentes específicos
     * @param day valor do dia
     * @param month valor do mês
     * @param year valor do ano
     */
    public TempoDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * construtor com valores de componentes default
     */
    public TempoDate() {
        this.day = DEFAULT_COMPONENT;
        this.month = DEFAULT_COMPONENT;
        this.year = DEFAULT_COMPONENT;
    }

    /**
     * Função para verificar se os componentes da data estão vazios (isto é, configurados para seus valores padrão)
     * @return true se a data estiver vazia, caso contrário, false
     */
    public boolean empty() {
        return day == DEFAULT_COMPONENT && month == DEFAULT_COMPONENT && year == DEFAULT_COMPONENT;
    }
}
