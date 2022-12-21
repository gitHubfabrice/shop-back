package com.fatechnologies.domaine.dto;

import lombok.Getter;

@Getter
public enum TypeOperation {
     ADD(0),
     OUT(1);
     private final int type;
     TypeOperation(int type){
          this.type = type;
     }
     public int getValue(){
          return type;
     }
}
