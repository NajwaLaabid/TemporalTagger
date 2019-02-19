package util;

import model.TempoPeriod;

import java.util.ArrayList;

/**
 * Classe usada para imprimir as instruções de depuração de várias classes da biblioteca, mediante solicitação.
 */
public class TempoLogger {
    /**
     * Função usada para imprimir um texto.
     * @param text texto para imprimir
     * @param permission variável de debug
     */
    public static void logText(String text, boolean permission) {
        if(!permission) return;

        System.out.println(text);
    }

    /**
     * Função usada para imprimir um período.
     * @param period período para imprimir
     * @param permission variável de debug
     */
    public static void logPeriod(TempoPeriod period, boolean permission) {
        if(!permission) return;

        System.out.println("************ Periodo **************");
        System.out.println("Data inicial: " + period.initialDateToString());
        System.out.println("Data final: " +  period.finalDateToString());
        System.out.println("Start (inicio) index: " + period.getStartIdx());
        System.out.println("End (fim) index: " + period.getEndIdx());
        System.out.println("String: " + period.getDateString());
        System.out.println("**********************************");
    }

    /**
     * Função usada para imprimir um array de períodos.
     * @param periods array de períodos para imprimir
     * @param permission variável de debug
     */
    public static void logArrayPeriod(ArrayList<TempoPeriod> periods, boolean permission) {
        if(!permission) return;

        System.out.println("************ Array Periodo **************");
        for(int i = 0; i < periods.size(); i++) {
            logPeriod(periods.get(i), true);
        }
    }

}
