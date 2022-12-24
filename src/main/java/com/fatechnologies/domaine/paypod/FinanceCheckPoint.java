package com.fatechnologies.domaine.paypod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinanceCheckPoint {
    private double amountMonth;
    private double amountYear;
    private double amountDay;

    public FinanceCheckPoint(){

    }

    public FinanceCheckPoint(double amountMonth, double amountYear, double amountDay) {
        this.amountMonth = amountMonth;
        this.amountYear = amountYear;
        this.amountDay = amountDay;
    }

    public void moreMonth(double amount){
        this.amountMonth += amount;
    }

    public void moreYear(double amount){
        this.amountYear += amount;
    }
}
