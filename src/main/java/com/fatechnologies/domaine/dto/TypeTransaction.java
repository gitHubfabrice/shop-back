package com.fatechnologies.domaine.dto;

import lombok.Getter;

@Getter
public enum TypeTransaction {
     CREDIT(0),
     DEBIT(1);
     private final int type;
     TypeTransaction(int type){
          this.type = type;
     }
     public int getValue(){
          return type;
     }
}
