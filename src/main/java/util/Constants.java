package util;

/**
 * Classe definindo as constantes da biblioteca
 * @author Najwa Laabid
 */
public class Constants {

    public static final int DEFAULT_COMPONENT = -1;

    public static final boolean DEBUG_STATE = false;
    public static final boolean DEBUG_DFS = false;
    public static final boolean DEBUG_REDUCTOR = false;
    public static final boolean DEBUG_PERIOD_PARSER = true;
    public static final boolean DEBUG_MAIN_PARSER = false;
    public static final boolean DEBUG_FINAL_STATES = false;
    public static final boolean DEBUG_STATES = false;
    public static final int BRAZIL_INDEPENDENCE_YEAR = 1822;

    public static final String digitLetter_1to9 = "um|dois|tr(e|ê)s|quatro|cinco|seis|sete|oito|nove";
    public static final String digitLetter_10to19 = "dez|onze|doze|treze|quatorze|quinze|dezesseis|dezessete" +
            "|dezoito|dezenove";
    public static final String digitLetter_20to29 = "vinte((\\s+e\\s+|\\s+)?(" + digitLetter_1to9 + "))?";
    public static final String digitLetter_30to31 = "trinta(\\s+(e\\s+)?um|(?!\\s+(e\\s+)?(" + digitLetter_1to9 + ")))";
    public static final String digitLetter_10to12 = "dez|onze|doze";

    public static final String digitLetter_1to31 = digitLetter_1to9 + "|" + digitLetter_10to19 + "|"
            + digitLetter_20to29 + "|" + digitLetter_30to31;
    public static final String digitLetter_1to12 = digitLetter_1to9 + "|" + digitLetter_10to12;
}

