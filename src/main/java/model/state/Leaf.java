package model.state;

import model.State;
import model.Period;

public class Leaf extends State {
    public Leaf() {
        super("");
    }

    @Override
    public boolean convert(String text, int startPos, Period parentPeriod) {
        setCurDate(parentPeriod);
        return true;
    }

}
