package algorithm;

import model.State;
import model.Period;
import model.Automaton;

import java.util.ArrayList;

import static java.lang.Math.max;

public class DFS {
    ArrayList<Period> dates;
    Automaton biaTree;
    int highestDateFoundIdx;

    public ArrayList<Period> run(String text) {

        dates = new ArrayList<Period>();

        int lastSize = dates.size();

        biaTree = new Automaton();
        run(biaTree.getHead(), text, 0, new Period());

        highestDateFoundIdx = 0;

        while(dates.size() > lastSize){
            lastSize = dates.size();

            dates = new Reductor().run(dates);

            updateHighestDateFoundIdx();

            if(highestDateFoundIdx >= text.length() - 1) {
//                System.out.println("+++++++++++++++++++++++++++++ HIGHEST FOUND +++++++++++++++++++++++++++++++++++++");
//                System.out.println("highestDateFoundIdx: " + highestDateFoundIdx);
                break;
            }

            text = text.substring(highestDateFoundIdx);
//            System.out.println("+++++++++++++++++++++++++++++ second round +++++++++++++++++++++++++++++++++++++");
//            System.out.println("Text: " + text);
            run(biaTree.getHead(), text, highestDateFoundIdx, new Period());
        }

        return dates;
    }

    public void updateHighestDateFoundIdx(){

        for(int i = 0; i < dates.size(); i++) {
            if ( highestDateFoundIdx < dates.get(i).getEndIdx() ) {
                highestDateFoundIdx = dates.get(i).getEndIdx();
            }
        }
    }

    public void run(State node, String text, int startPos, Period parentPeriod) {
//        System.out.println("current state: " +  node.getRegex());
//        System.out.println("initial date in parent: " + parentPeriod.initialDateToString());
//        System.out.println("initial date in state: " +  node.getCurDate().initialDateToString());
//        System.out.println("start index in parent: " +  parentPeriod.getStartIdx());
//        System.out.println("end index in parent: " +  parentPeriod.getEndIdx());

        if(node.getChildren().size() == 0) {
            node.convert(text, startPos, parentPeriod);
//            System.out.println("leaf found. Regex of leaf " +  node.getRegex());
//            System.out.println("initialDate of leaf " +  node.getCurDate().initialDateToString());
//            System.out.println("finalDate of leaf " +  node.getCurDate().finalDateToString());
//
//            System.out.println("startIndex of leaf " +  node.getCurDate().getStartIdx());
//            System.out.println("endIndex of leaf " +  node.getCurDate().getEndIdx());

            if(!node.getCurDate().empty()) {
                highestDateFoundIdx = max(highestDateFoundIdx, node.getCurDate().getEndIdx());
//                System.out.println("============ adding date: " + node.getCurDate().initialDateToString());
                dates.add(node.getCurDate());
            }
            return;
        }

        if(node.convert(text, startPos, parentPeriod)) {
//            System.out.println("Children size: " + node.getChildren().size());
            for (int i = 0; i < node.getChildren().size(); i++) {
                run(node.getChildren().get(i), text, startPos, node.getCurDate());
            }
        }
    }

}
