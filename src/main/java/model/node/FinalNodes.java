package model.node;

import model.TempoDate;
import model.TempoPeriod;
import model.TempoNode;
import model.TempoUnit;
import util.TempoLogger;
import util.TempoUtil;

import java.time.LocalDate;
import java.util.ArrayList;

import static util.Constants.DEBUG_FINAL_STATES;

public class FinalNodes {
    protected LocalDate refDate;

    public FinalNodes(LocalDate refDate) {
        this.refDate = refDate;
    }

    public TempoNode quantityFinal = new TempoNode(){
        @Override
        public boolean convert(String text, TempoPeriod antecedentPeriod){
            quantities = antecedentPeriod.getQuantities();
            dateStr = antecedentPeriod.getDateString();
            if(quantities.size() == 0)
                return false;
            startIdx = antecedentPeriod.getStartIdx();
            endIdx = antecedentPeriod.getEndIdx();

            TempoLogger.logText("start idx: " + startIdx, DEBUG_FINAL_STATES);
            TempoLogger.logText("end idx: " + endIdx, DEBUG_FINAL_STATES);
            TempoLogger.logText("initial date day: " + antecedentPeriod.initialDateToString(), DEBUG_FINAL_STATES);

            if(!antecedentPeriod.empty()) {
                refDate = antecedentPeriod.getInitialDateFormatted();
            }

            TempoPeriod sum = new TempoPeriod(refDate);

            for(int i = 0; i < quantities.size(); i++) {
                sum = TempoUtil.sumUnit(sum, quantities.get(i));
            }

            setPeriodDates(sum);

            return true;
        }
    };

    public TempoNode cardinalsFinal = new TempoNode(){
        @Override
        public boolean convert(String text,  TempoPeriod antecedentPeriod){
            cardinals = antecedentPeriod.getCardinals();
            if(cardinals.size() == 0)
                return false;
            startIdx = antecedentPeriod.getStartIdx();
            endIdx = antecedentPeriod.getEndIdx();
            dateStr = antecedentPeriod.getDateString();

            TempoLogger.logText("start idx: " + startIdx, DEBUG_FINAL_STATES);
            TempoLogger.logText("end idx: " + endIdx, DEBUG_FINAL_STATES);
            TempoLogger.logText("initial date day: " + antecedentPeriod.initialDateToString(), DEBUG_FINAL_STATES);

            if(!antecedentPeriod.empty()) {
                refDate = antecedentPeriod.getInitialDateFormatted();
            }

            int size = cardinals.size();
            TempoUnit outer = cardinals.get(size - 1);
            TempoUnit inner;
            int outerNb;
            int innerNb;
            int base;
            TempoPeriod period = new TempoPeriod(refDate);

            // first
            // resolve with outer layer as bigger time (right now year)
            period = TempoUtil.getPeriod(period.getInitialDateFormatted(), outer, outer.getNb());

            for(int i = size - 2; i >= 0; i--) {
                inner = cardinals.get(i);
                base = TempoUtil.convertUnit(inner, outer);

                outerNb = (int) Math.ceil(cardinals.get(i).getNb() / base * 1.0);

                if(outerNb > 0)
                    period = TempoUtil.getPeriod(refDate, outer, outerNb);

                innerNb = inner.getNb() % base;

                if(innerNb == 0) innerNb = base;

                period = TempoUtil.getPeriod(period.getInitialDateFormatted(), inner, innerNb);

                outer = inner;
            }

            setPeriodDates(period);

            return true;
        }
    };

    public TempoNode posFinalState = new TempoNode(){
        @Override
        public boolean convert(String text, TempoPeriod antecedentPeriod){
            cardinals = antecedentPeriod.getCardinals();
            dateStr = antecedentPeriod.getDateString();
            if(cardinals.size() > 0)
                return false;

            startIdx = antecedentPeriod.getStartIdx();
            endIdx = antecedentPeriod.getEndIdx();

            iDay = antecedentPeriod.getInitialDate().getDay();
            fDay = antecedentPeriod.getFinalDate().getDay();

            iMonth = antecedentPeriod.getInitialDate().getMonth();
            fMonth = antecedentPeriod.getFinalDate().getMonth();

            iYear = antecedentPeriod.getInitialDate().getYear();
            fYear = antecedentPeriod.getFinalDate().getYear();

            curDate = new TempoPeriod(dateStr, startIdx, endIdx,
                    new TempoDate(iDay, iMonth, iYear),
                    new TempoDate(fDay, fMonth, fYear));

            curDate.setQuantity(quantity);
            curDate.setCardinals(cardinals);

            connections = new ArrayList<>();

            return true;
        }
    };
}
