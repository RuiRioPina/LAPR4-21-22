package eapli.base.questionnaire.domain;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QuestionnaireReport {

    private final Integer universe;
    private final Integer totalAnswers;
    private final Long answersPer100;
    private final Map<String,List<List<String>>> singleChoice;
    private final Map<List<String>,List<List<String>>> multipleChoice;
    private final Map<List<String>,List<List<String>>> sortingOptions;
    private final Map<List<String>,List<List<String>>> scalingOptions;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public QuestionnaireReport (Integer universe,Integer answersPer100,Map<String, List<List<String>>> singleChoice, Map<List<String>,
            List<List<String>>> multipleChoice, Map<List<String>, List<List<String>>> sortingOptions,
            Map<List<String>, List<List<String>>> scalingOptions) {
        this.answersPer100 = Long.valueOf(answersPer100);
        this.universe = universe;
        this.singleChoice = singleChoice;
        this.multipleChoice = multipleChoice;
        this.sortingOptions = sortingOptions;
        this.scalingOptions = scalingOptions;
        this.totalAnswers = singleChoice.entrySet().size() + multipleChoice.entrySet().size()
                + sortingOptions.entrySet().size() + scalingOptions.entrySet().size();
    }

    public String reportPrint () {
        return "Universe: " + universe +
                "\nCostumers Answers: " + universe + " of " + answersPer100 + " possible"+
                "\nTotal Questions Answered: " + totalAnswers +
                "\n-----------------------\nSingle Choice\n\n" + generateSingleChoiceReport() +
                "\n-----------------------\nMultiple Choice\n\n" + generateMultipleChoiceReport() +
                "\n-----------------------\nSorting Options\n\n" + sortingOptions +
                "\n-----------------------\nScaling Options\n\n" + generateScalingOptionsReport();
    }

    public String generateSortingOptionsReport(){
        return "";
    }

    public String generateScalingOptionsReport() {
        StringBuilder aux = new StringBuilder("");
        int total = scalingOptions.entrySet().size();
        int sAgree = 0; int agree = 0; int neutral = 0;
        int disagree = 0; int sDisagree = 0;

                List <String> str = scalingOptions.keySet().iterator().next();
                for (int i = 0; i < str.size(); i++) {
                    if (str.get(i).equals("1")) {
                        sAgree++;
                    }
                    if (str.get(i).equals("2")) {
                        agree++;
                    }
                    if (str.get(i).equals("3")) {
                        neutral++;
                    }
                    if (str.get(i).equals("4")) {
                        disagree++;
                    }
                    if (str.get(i).equals("5")) {
                        sDisagree++;
                    }
                }

        aux.append("Strongly Agree").append(" - ").append( decimalFormat.format(sAgree/(float) total * 100)).append(" %\n");
        aux.append("Agree").append(" - ").append( decimalFormat.format(agree/(float) total * 100)).append(" %\n");
        aux.append("Neutral").append(" - ").append(  decimalFormat.format(neutral/(float) total * 100)).append(" %\n");
        aux.append("Disagree").append(" - ").append(  decimalFormat.format(disagree/(float) total * 100)).append(" %\n");
        aux.append("Strongly Disagree").append(" - ").append(  decimalFormat.format(sDisagree/(float) total * 100)).append(" %\n");
        return aux.toString();
    }

    public String generateMultipleChoiceReport() {
        StringBuilder aux = new StringBuilder("");
        int totalMChoice = 0;
        List <String> options = new ArrayList<>();
        for (List <String> set : multipleChoice.keySet()){
            List <List <String>> opt = multipleChoice.get(set);
            for (int i = 0; i < opt.size(); i++) {
                totalMChoice++;
                for (int j = 0; j < opt.get(i).size(); j++) {
                    if (!options.contains(opt.get(i).get(j))){
                        options.add(opt.get(i).get(j));
                    }
                }
            }
        }

        float percent1 = 0, percent2= 0, percent3= 0, percentOther= 0;
        float a = 0; float b= 0;float c= 0;
        boolean boo1 = false;
        for (List<String> set : multipleChoice.keySet()) {
            if (set.contains("1") && set.contains("2")){
                a++;
            }
            if (set.contains("1") && set.contains("3")){
                b++;
            }
            if (set.contains("2") && set.contains("3")){
                c++;
            }
            for (int j = 0; j < set.size(); j++) {
                if (set.get(j).equals("1")) {
                    percent1 = ((float) multipleChoice.get(set).size() / (float) totalMChoice) * 100;
                    aux.append("OPTION: ").append(set.get(j)).append(" - ").append(decimalFormat.format(percent1)).append(" %\n");
                    boo1 = true;
                }
            }
        }
        if (!boo1){
            aux.append("OPTION: ").append("1").append(" - ").append(0).append(" %\n");
        }

        boolean boo2 = false;
        for (List<String> set : multipleChoice.keySet()) {
            for (int j = 0; j < set.size(); j++) {
                if (set.get(j).equals("2") ) {
                    percent2 = ((float) multipleChoice.get(set).size() / (float) totalMChoice) * 100;
                    aux.append("OPTION: ").append(set.get(j)).append(" - ").append(decimalFormat.format(percent2)).append(" %\n");
                    boo2 = true;
                }
            }
        }
        if (!boo2){
            aux.append("OPTION: ").append("2").append(" - ").append(0).append(" %\n");
        }

        boolean boo3 = false;
        for (List<String> set : multipleChoice.keySet()) {
            for (int j = 0; j < set.size(); j++) {
                if (set.get(j).equals("3") ) {
                    percent3 = ((float) multipleChoice.get(set).size() / (float) totalMChoice) * 100;
                    aux.append("OPTION: ").append(set.get(j)).append(" - ").append(decimalFormat.format(percent3)).append(" %\n");
                    boo3 = true;
                }
            }
        }
        if (!boo3){
            aux.append("OPTION: ").append("3").append(" - ").append(0).append(" %\n");
        }

        int oof = 0;
        for (List<String> set : multipleChoice.keySet()) {
            for (int j = 0; j < set.size(); j++) {
                if (set.get(j).contains("4") || set.get(j).equals("5") || set.get(j).equals("6")) {
                   oof ++;
                }
            }
        }
        percentOther = ((float) oof / (float) totalMChoice )* 100;
        aux.append("OTHERS" + " - ").append(decimalFormat.format(percentOther)).append(" %\n");
        aux.append("\nCombinations\n");
        aux.append("1 + 2").append(" - ").append(decimalFormat.format( a/(float) totalMChoice * 100)).append(" %\n");
        aux.append("1 + 3").append(" - ").append(decimalFormat.format( b/(float) totalMChoice * 100)).append(" %\n");
        aux.append("2 + 3").append(" - ").append(decimalFormat.format(c/(float) totalMChoice * 100)).append(" %\n");
        return aux.toString();
    }

    public String generateSingleChoiceReport() {
        StringBuilder aux = new StringBuilder("");
        int totalSingleChoice = 0;
        List <String> options = new ArrayList<>();
        for (String set : singleChoice.keySet()){
            List <List <String>> opt = singleChoice.get(set);
            for (int i = 0; i < opt.size(); i++) {
                totalSingleChoice++;
                for (int j = 0; j < opt.get(i).size(); j++) {
                    if (!options.contains(opt.get(i).get(j))){
                        options.add(opt.get(i).get(j));
                    }
                }
            }
        }
        int other = 0;
        boolean boo1 = false; int a = 0;
        boolean boo2 = false; int b = 0;
        boolean boo3 = false; int c = 0;

        for(String set : singleChoice.keySet()) {
            if (set.equals("1")) {
                a++;
                boo1 = true;
            }
        }
        if (boo1){
            float percent = ((float) singleChoice.get("1").size() / (float) totalSingleChoice) * 100;
            aux.append("OPTION: ").append(1).append(" - ").append(decimalFormat.format(percent)).append(" %\n");
        }else {
            aux.append("OPTION: ").append(1).append(" - ").append(0).append(" %\n");
        }

        for(String set : singleChoice.keySet()) {
            if (set.equals("2")) {
                b++;
                boo2 = true;
            }
        }
        if (boo2){
            float percent = ((float) singleChoice.get("2").size() / (float) totalSingleChoice) * 100;
            aux.append("OPTION: ").append(2).append(" - ").append(decimalFormat.format(percent)).append(" %\n");
        } else {
            aux.append("OPTION: ").append(2).append(" - ").append(0).append(" %\n");
        }

        for(String set : singleChoice.keySet()) {
            if (set.equals("3")) {
                c++;
                boo3 = true;
            }
        }
        if (boo3){
            float percent = ((float) singleChoice.get("3").size() / (float) totalSingleChoice) * 100;
            aux.append("OPTION: ").append(3).append(" - ").append(decimalFormat.format(percent)).append(" %\n");
        }else {
            aux.append("OPTION: ").append(3).append(" - ").append(0).append(" %\n");
        }

        for(String set : singleChoice.keySet()) {
            if (!set.equals("1") && !set.equals("2") && !set.equals("3")){
            other = other + singleChoice.get(set).size();
        }
        }

        float percent2 = ((float) other / (float) totalSingleChoice )* 100;
        aux.append("OTHERS" + " - ").append(decimalFormat.format(percent2)).append(" %\n");
        return aux.toString();
    }

}
