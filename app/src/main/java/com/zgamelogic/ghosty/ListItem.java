package com.zgamelogic.ghosty;

public class ListItem {
    //This holds the Ghost information for each ListItem
    String ghostType;
    String evidenceType1;
    String evidenceType2;
    String evidenceType3;
    String evidenceType4;

    ListItem(String ghostType,
             String evidenceType1,
             String evidenceType2,
             String evidenceType3,
             String evidenceType4)

    //This is local
    {
        //this is assigning local to global
        //Local has priority over global.
        this.ghostType = ghostType; //global = Local
        this.evidenceType1 = evidenceType1;
        this.evidenceType2 = evidenceType2;
        this.evidenceType3 = evidenceType3;
        this.evidenceType4 = evidenceType4;


    }
}


