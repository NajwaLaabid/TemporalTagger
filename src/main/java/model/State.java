package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Constants.DEFAULT_COMPONENT;

@Getter
public class State {

    private ArrayList<State> children;

    protected Period curDate;

    protected String regex;

    protected int iDay;
    protected int fDay;
    protected int iMonth;
    protected int fMonth;
    protected int iYear;
    protected int fYear;
    protected int startIdx;
    protected int endIdx;
    protected Period parentPeriod;

    public State(String text) {
        this.regex = text;
        this.children = new ArrayList<State>();
        this.curDate = new Period();

        this.startIdx = DEFAULT_COMPONENT;
        this.endIdx = DEFAULT_COMPONENT;
    }
    
    public State() {
        this("");
    }

    public State addChild(State child) {
//        System.out.println("child parent regex " + child.parent.regex.getRawRegex());
//        System.out.println("child parent curDate initialDate " + child.parent.curDate.initialDateToString());
//        System.out.println("child regex " + child.regex.getRawRegex());
        this.children.add(child);

        return this;
    }

    public State copy() {
        State node = new State(regex);

        if(children != null) {
            for(int i = 0; i < children.size(); i++)
                node.addChild(children.get(i).copy());
        }

        node.setCurDate(new Period());

        return node;
    }

    public void computeDate(Matcher matcher){
        iDay = DEFAULT_COMPONENT;
        fDay = DEFAULT_COMPONENT;

        iMonth = DEFAULT_COMPONENT;
        fMonth = DEFAULT_COMPONENT;

        iYear = DEFAULT_COMPONENT;
        fYear = DEFAULT_COMPONENT;

        startIdx = DEFAULT_COMPONENT;
        endIdx = DEFAULT_COMPONENT;
    }

    public boolean convert(String text, int startPos, Period parentPeriod) {

        this.parentPeriod = parentPeriod;
        if(parentPeriod.getEndIdx() != DEFAULT_COMPONENT && parentPeriod.getEndIdx() - startPos < text.length())
            text = text.substring(parentPeriod.getEndIdx() - startPos );

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher(text);

        int startIdx, endIdx;

        while(matcher.find()) {
            if(parentPeriod.getEndIdx() == DEFAULT_COMPONENT || matcher.start() == 0) {
//                System.out.println("In convert");
                startIdx = (parentPeriod.getStartIdx() == DEFAULT_COMPONENT ? matcher.start() + startPos : parentPeriod.getStartIdx());
                endIdx = matcher.end() + (parentPeriod.getEndIdx() == DEFAULT_COMPONENT ? startPos : parentPeriod.getEndIdx());

                computeDate(matcher);

                curDate = new Period(startIdx, endIdx,
                        new Date(iDay, iMonth, iYear),
                        new Date(fDay, fMonth, fYear));

                return true;
            }
//            System.out.println("No follow-up. Start Pos: " + startPos);
//            System.out.println("parent endidx: " + parentPeriod.getEndIdx());
//            System.out.println("matcher start: " + (matcher.start() + startPos));
        }

        setCurDate(parentPeriod);
        return false;
    }

    public Period getCurDate() {
        return curDate;
    }

    public void setCurDate(Period curDate) {
        this.curDate = curDate;
    }

    public ArrayList<State> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<State> children) {
        this.children = children;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

}