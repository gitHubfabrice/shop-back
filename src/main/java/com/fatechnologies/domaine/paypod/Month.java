package com.fatechnologies.domaine.paypod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Month {
    private double january;
    private double february;
    private double march;
    private double april;
    private double may;
    private double june;
    private double july;
    private double august;
    private double september;
    private double october;
    private double november;
    private double december;
    public void cumulus(int i, double amount){
        switch (i) {
            case 1  -> this.january += amount;
            case 2  -> this.february += amount;
            case 3  -> this.march += amount;
            case 4  -> this.april += amount;
            case 5  -> this.may += amount;
            case 6  -> this.june += amount;
            case 7  -> this.july += amount;
            case 8  -> this.august += amount;
            case 9  -> this.september += amount;
            case 10 -> this.october += amount;
            case 11 -> this.november += amount;
            case 12 -> this.december += amount;
        }
    }
}
