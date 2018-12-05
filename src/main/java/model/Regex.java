package model;

public class Regex {
    String rawRegex;

    public Regex() {
        rawRegex = "";
    }

    public Regex(String text) {
        this.rawRegex = text;
    }

    public String group(String text) {
        return "(" + text + ")";
    }

    public String makeCaseInsensitive(String text) {
        return "(?i)" + text;
    }

    public String addWordBreaks(String text) {
        return "(^|\\s)" + text + "($|\\s)";
    }

    public Regex addComponent(String text, boolean grouped, boolean ored) {
        String componentText = (grouped? "(" + text + ")": text);

        if(ored)
            rawRegex += "|" + componentText;
        else
            rawRegex += componentText;

        return this;
    }

    public String getFinalizedRegex(){
        return "(?i)" + rawRegex;
    }

    public String getRawRegex(){
        return rawRegex;
    }

}
