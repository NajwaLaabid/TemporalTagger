package algorithm;

import model.Period;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Reductor {
    class SortBySize implements Comparator<Period>
    {
        public int compare(Period a, Period b)
        {
            return (b.getEndIdx() - b.getStartIdx()) - (a.getEndIdx() - a.getStartIdx());
        }
    }

    public ArrayList<Period> run(ArrayList<Period> dates) {

        Collections.sort(dates, new SortBySize());
//        System.out.println("======== Initial ===========");
//        for(int i = 0; i < dates.size(); i++) {
//            System.out.println("date " + i + " " +  dates.get(i).initialDateToString());
//            System.out.println("start index " + " " + dates.get(i).getStartIdx());
//            System.out.println("end index " + " " + dates.get(i).getEndIdx());
//            System.out.println("size " + " " + (dates.get(i).getEndIdx() - dates.get(i).getStartIdx()));
//        }

        dates = removeDuplicates(dates);
//        System.out.println("======== No Replicates ===========");
//        for(int i = 0; i < dates.size(); i++) {
//            System.out.println("date " + i + " " +  dates.get(i).initialDateToString());
//            System.out.println("start index " + " " + dates.get(i).getStartIdx());
//            System.out.println("end index " + " " + dates.get(i).getEndIdx());
//            System.out.println("size " + " " + dates.get(i).getSize());
//        }

        int totalIterations = dates.size();
        int count = 0;

        while(dates.size() > 1 && count < totalIterations){
            dates = delete(dates);
            count++;
        }
//        System.out.println("======== after deletions ===========");
//        for(int i = 0; i < dates.size(); i++) {
//            System.out.println("date " + i + " " +  dates.get(i).initialDateToString());
//            System.out.println("start index " + " " + dates.get(i).getStartIdx());
//            System.out.println("end index " + " " + dates.get(i).getEndIdx());
//            System.out.println("size " + " " + dates.get(i).getSize());
//        }

        return dates;
    }

    public ArrayList<Period> removeDuplicates(ArrayList<Period> dates){
        Period cmp = dates.get(0);

        for(int i = 0; i < dates.size() - 1; i++) {
            if (dates.get(i).initialDateToString().equals(dates.get(i+1).initialDateToString())
                    && dates.get(i).finalDateToString().equals(dates.get(i+1).finalDateToString())) {
                    dates.remove(i+1);
                    i--;
            }
        }

        return dates;
    }

    public static ArrayList<Period> delete(ArrayList<Period> dates) {
        boolean intersection;
        boolean withinBounds;

        for(int i = 0; i < dates.size(); i++) {
            for(int j = 0; j < dates.size(); j++) {
                withinBounds = false;
                intersection = true;
                if(i != j && j < dates.size() && i < dates.size())
                    withinBounds = true;

                if (withinBounds && (dates.get(i).getEndIdx() < dates.get(j).getStartIdx()
                        || dates.get(j).getEndIdx() < dates.get(i).getStartIdx()))
                    intersection = false;

                if (!withinBounds || !intersection) {
                    continue;
                }

                if (dates.get(i).getSize() >= dates.get(j).getSize()) {
                    dates.remove(j);
                } else {
                    dates.remove(i);
                }
            }
        }

        return dates;
    }
}
