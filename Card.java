/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black;

/**
 *
 * @author listeruser
 */
public class Card {
    int iValue;
    String value,suit;
    public Card(int n){
        int iSuit=n/13;
        iValue=n%13+1;
        switch(iSuit){
            case 0: suit="Spades";
                break;
            case 1: suit="Hearts";
                break;
            case 2: suit="Clubs";
                break;
            default: suit="Diamonds";
                break;
        }
        if(iValue==1)
            value="Ace";
        else if(iValue==10)
            value="Ten";
        else if(iValue==11)
            value="Jack";
        else if(iValue==12)
            value="Queen";
        else if(iValue==13)
            value="King";
        else
            value= Integer.toString(iValue);
        if(iValue>10)
            iValue=10;
    }
    int getValue(){
        return iValue;
    }
    
}
