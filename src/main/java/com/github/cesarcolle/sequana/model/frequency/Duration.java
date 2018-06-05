package com.github.cesarcolle.sequana.model.frequency;

public class Duration {
    private Integer time;


    public Duration(Integer time) {
        this.time = time;
    }

    public Integer getTime() {
        return time;
    }

    public static Duration stringToDuration(String duration){
        Integer coef = 60;
        if (duration.contains("h")){
            coef = 3600;
        }

        String d = duration.replaceAll("[m|h]", "");
        return new Duration(Integer.parseInt(d) * coef);
    }
}
