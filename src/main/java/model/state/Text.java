package model.state;

import model.State;

import java.util.regex.Matcher;

public class Text extends State {
    public Text(String text) {
        super(text);
    }

    @Override
    public void computeDate(Matcher matcher) {
        iDay = parentPeriod.getInitialDate().getDay();
        fDay = parentPeriod.getFinalDate().getDay();

        iMonth = parentPeriod.getInitialDate().getMonth();
        fMonth = parentPeriod.getFinalDate().getMonth();

        iYear = parentPeriod.getInitialDate().getYear();
        fYear = parentPeriod.getFinalDate().getYear();

        startIdx = parentPeriod.getStartIdx();
        endIdx = parentPeriod.getEndIdx();
    }
}
