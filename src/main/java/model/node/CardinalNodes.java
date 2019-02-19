package model.node;

import model.TempoNode;
import model.TempoUnit;

import java.time.LocalDate;
import java.util.regex.Matcher;

import static util.TempoUtil.convertToCardinal;

public class CardinalNodes {
    protected LocalDate refDate;

    public CardinalNodes(LocalDate refDate) {
        this.refDate = refDate;
    }

    public TempoNode cardinals = new TempoNode("\\b([dn][eoa]\\s+)?(primeir|segund|terceir|quart)[oa]($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            cardinalNb = convertToCardinal(matcher.group(2));
        }
    };
    public TempoNode cardinalsDay = new TempoNode("\\bdia($|\\s+)"){
        @Override
        public void computeDate(Matcher matcher) {
            TempoUnit unit = TempoUnit.DAY;

            unit.setNb(cardinalNb);

            cardinals.add(unit);
        }
    };
    public TempoNode cardinalsWeek = new TempoNode("\\bsemana($|\\s+)"){
        @Override
        public void computeDate(Matcher matcher) {
            TempoUnit unit = TempoUnit.WEEK;

            unit.setNb(cardinalNb);

            cardinals.add(unit);
        }
    };
    public TempoNode cardinalsMonth = new TempoNode("\\bm[eêÊ]s($|\\s+)"){
        @Override
        public void computeDate(Matcher matcher) {
            TempoUnit unit = TempoUnit.MONTH;

            unit.setNb(cardinalNb);

            cardinals.add(unit);
        }
    };
    public TempoNode cardinalsSemester = new TempoNode("\\bsemestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoUnit unit = TempoUnit.SEMESTER;

            unit.setNb(cardinalNb);

            cardinals.add(unit);
        }
    };
    public TempoNode cardinalsTrimester = new TempoNode("\\btrimestre($|\\s+)") {
        @Override
        public void computeDate(Matcher matcher) {
            TempoUnit unit = TempoUnit.TRIMESTER;

            unit.setNb(cardinalNb);

            cardinals.add(unit);
        }
    };
}
