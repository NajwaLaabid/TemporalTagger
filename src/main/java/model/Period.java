package model;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Period {
    private Date initialDate;
    private Date finalDate;

    private int startIdx;
    private int endIdx;

    private int size;

    public Period(Date initialDate, Date finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.startIdx = -1;
        this.endIdx = -1;
        this.size = 0;
    }

    public Period(int startIdx, int endIdx, Date initialDate, Date finalDate) {
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.startIdx = startIdx;
        this.endIdx = endIdx;
        this.size = this.endIdx - this.startIdx;
    }

    public Period() {
        this.initialDate = new Date();
        this.finalDate = new Date();
        this.startIdx = -1;
        this.endIdx = -1;
        this.size = 0;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public LocalDate getInitialDateFormatted(){
        if(!initialDate.empty()) {
            if(initialDate.getDay() == 31) {
                return LocalDate.of(initialDate.getYear(), initialDate.getMonth(), 1)
                        .with(TemporalAdjusters.lastDayOfMonth());
            }

            return LocalDate.of(initialDate.getYear(), initialDate.getMonth(), initialDate.getDay());
        }

        return LocalDate.now();
    }

    public LocalDate getFinalDateFormatted(){
        if(!finalDate.empty()) {
            if (finalDate.getDay() == 31) {
                return LocalDate.of(finalDate.getYear(), finalDate.getMonth(), 1)
                        .with(TemporalAdjusters.lastDayOfMonth());
            }

            return LocalDate.of(finalDate.getYear(), finalDate.getMonth(), finalDate.getDay());
        }

        return LocalDate.now();
    }

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public void setEndIdx(int endIdx) {
        this.endIdx = endIdx;
    }

    public boolean empty() {
        return initialDate.empty() && finalDate.empty();
    }

    public String initialDateToString() {
        return initialDate.getDay() + "/" + initialDate.getMonth() + "/" + initialDate.getYear();
    }

    public String finalDateToString() {
        return finalDate.getDay() + "/" + finalDate.getMonth() + "/" + finalDate.getYear();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
