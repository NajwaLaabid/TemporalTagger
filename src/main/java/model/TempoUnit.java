package model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe usada para definir as unidades de tempo usadas pelos cardeais e pela quantidade.
 * A classe também define uma variável para manter a quantidade de uma unidade ou o valor numérico de um cardeal.
 */
@Getter
public enum TempoUnit {
    DAY, WEEK, MONTH, TRIMESTER, SEMESTER, YEAR;
    @Setter
    private int nb = 1;
}
