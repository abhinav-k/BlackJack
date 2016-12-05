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
public class Hand {
    int numCards;
    Card cards[];
    static int MaxCards=12;
    
    public Hand(){
        numCards=0;
        cards=new Card[MaxCards];
    }
    void addCard(Card c){
        cards[numCards]=c;
        ++numCards;
    }
    void show(boolean isDealer, boolean hideFirstCard){
        if(isDealer)
            System.out.println("Dealer: ");
        else
            System.out.println("Player: ");
        for(int i=0;i<numCards;++i){
            if(i==0&&hideFirstCard)
                System.out.println(" Hidden");
            else
                System.out.println(" "+cards[i].value+" of "+cards[i].suit);
        }  
       }
    boolean blackjack(){
        if(numCards==2){
            if(cards[0].iValue==1 &&cards[1].iValue==10)
                return true;
            if(cards[1].iValue==1 &&cards[0].iValue==10)
                return true;
        }
        return false;
    }
    boolean under(int n){
        int points=0;
        for(int i=0;i<numCards;i++)
            points+=cards[i].iValue;
        return points<n;
    }
    int bestScore(){
        int points=0;
        boolean haveAce=false;
        for(int i=0;i<numCards;++i){
            points+=cards[i].iValue;
            if(cards[i].iValue==1)
            haveAce=true;
        }
        if(haveAce){
            if(points+10<22)
                points+=10;
        }
            return points;
    }
    boolean mustHit(){
        return bestScore()<17;
    }
    boolean busted(){
        return !under(22);
    }
}
