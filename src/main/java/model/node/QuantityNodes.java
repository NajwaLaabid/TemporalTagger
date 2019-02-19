package model.node;

import model.TempoNode;
import model.TempoUnit;
import util.TempoLogger;
import util.TempoUtil;

import java.time.LocalDate;
import java.util.regex.Matcher;

import static util.Constants.DEBUG_STATES;
import static util.Constants.digitLetter_1to31;

public class QuantityNodes {
    protected LocalDate refDate;

    public QuantityNodes(LocalDate refDate) {
        this.refDate = refDate;
    }

    public TempoNode digits = new TempoNode("\\b([12]\\d|3[01]|0?[1-9])\\s+") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Dígitos de quantidade' encontrado. Analisando as datas...", DEBUG_STATES);
            quantity = Integer.parseInt(matcher.group(1));
        }
    };
    public TempoNode letters = new TempoNode("\\b(" + digitLetter_1to31 + ")\\s+") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Dígitos em letras de quantidade' encontrado. Analisando as datas...", DEBUG_STATES);
            quantity =  TempoUtil.letterToDigit(matcher.group(1));
        }
    };
    public TempoNode daysPlus = new TempoNode("dia(s)?\\s+depois($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Dia depois quantidade' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.DAY;

            unit.setNb(quantity);

            quantities.add(unit);
        }
    };
    public TempoNode daysMinus = new TempoNode("dia(s)?\\s+antes($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Dia antes de quantidade' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.DAY;

            unit.setNb(-quantity);

            quantities.add(unit);
        }
    };
    public TempoNode weeksPlus = new TempoNode("\\bsemana(s)?\\s+depois($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Semana depois' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.WEEK;

            unit.setNb(quantity);

            quantities.add(unit);
        }
    };
    public TempoNode weeksMinus = new TempoNode("\\bsemana(s)?\\s+antes($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Semana antes' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.WEEK;

            unit.setNb(-quantity);

            quantities.add(unit);
        }
    };
    public TempoNode monthsPlus = new TempoNode("\\bm[eêÊ]s(es)?\\s+depois($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Mês depois' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.MONTH;

            unit.setNb(quantity);

            quantities.add(unit);
        }
    };
    public TempoNode monthsMinus = new TempoNode("\\bm[eêÊ]s(es)?\\s+antes($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- Mês antes' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.MONTH;

            unit.setNb(-quantity);

            quantities.add(unit);
        }
    };
    public TempoNode yearsPlus = new TempoNode("\\bano(s)?\\s+depois($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- ano depois' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.YEAR;

            unit.setNb(quantity);

            quantities.add(unit);
        }
    };
    public TempoNode yearsMinus = new TempoNode("\\bano(s)?\\s+antes($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- ano antes' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.YEAR;

            unit.setNb(-quantity);

            quantities.add(unit);
        }
    };
    public TempoNode semesterPlus = new TempoNode("\\bsemestre(s)?\\s+depois($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- semestre depois' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.SEMESTER;

            unit.setNb(quantity);

            quantities.add(unit);
        }
    };
    public TempoNode semesterMinus = new TempoNode("\\bsemestre(s)?\\s+antes($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- semestre antes' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.SEMESTER;

            unit.setNb(-quantity);

            quantities.add(unit);
        }
    };
    public TempoNode trimesterPlus = new TempoNode("\\btrimestre(s)?\\s+depois($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- trimester depois' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.TRIMESTER;

            unit.setNb(quantity);

            quantities.add(unit);
        }
    };
    public TempoNode trimesterMinus = new TempoNode("\\btrimestre(s)?\\s+antes($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoLogger.logText("'Quantidade- trimester antes' encontrado. Analisando as datas...", DEBUG_STATES);
            TempoUnit unit = TempoUnit.TRIMESTER;

            unit.setNb(-quantity);

            quantities.add(unit);
        }
    };
}
