package com.fatechnologies.domaine.paypod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartOption {
   private Month spent;
   private Month revenue;

    public ChartOption(){
        this.spent   = new Month();
        this.revenue = new Month();
    }

    public void cumulus(int i, double revenue, double spent){
                this.spent.cumulus(i, spent);
                this.revenue.cumulus(i, revenue);
    }
}
