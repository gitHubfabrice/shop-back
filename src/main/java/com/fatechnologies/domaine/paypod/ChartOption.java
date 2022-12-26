package com.fatechnologies.domaine.paypod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartOption {
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
    public ChartOption(){

    }

    public void cumulus(int i, double amount){
        switch(i){

            case 1:
                this.january += amount;
                break;

            case 2:
                this.february += amount;
                break;

            case 3:
                this.april += amount;
                break;

            case 4:
                this.march += amount;
                break;

            case 5:
                this.may += amount;
                break;

            case 6:
                this.june += amount;
                break;

            case 7:
                this.july += amount;
                break;

            case 8:
                this.august += amount;
                break;

            case 9:
                this.september += amount;
                break;

            case 10:
                this.october += amount;
                break;

            case 11:
                this.november += amount;
                break;

            case 12:
                this.december += amount;
                break;

        }
    }


}
