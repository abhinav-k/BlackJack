/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black;

import java.util.Random;

/**
 *
 * @author listeruser
 */
public class Deck {
    int cards[];
    int topCard;
    Random random;
    public Deck(){
        cards= new int[52];
        for(int i=0;i<52;++i){
            cards[i]=i;
        }
        topCard=0;
        random= new Random();
        shuffle();
    }
    public void shuffle(){
        for(int i=0;i<52;++i)
        {
           int j=randomCard();
           int k=randomCard();
           int temp=cards[j];
           cards[j]=cards[k];
           cards[k]=temp;
        }
    }
    int randomCard(){
        int r=random.nextInt();
        if(r<0)
            r=0-r;
        return r%52;
    }
    Card deal(){
        if(topCard>51){
            shuffle();
            topCard=0;
        }
        Card card= new Card(cards[topCard]);
        ++topCard;
        return card;        
    }
}
