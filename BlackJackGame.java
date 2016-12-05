/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package black;

import java.io.*;


/**
 *
 * @author listeruser
 */
class BlackJackGame{
    int bet, money;
    Deck deck;
    Hand playersHand; Hand dealersHand;
    BufferedReader input; 
    public BlackJackGame(){
        bet=0;
        money=1000;
        deck= new Deck();
        input= new BufferedReader(new InputStreamReader(System.in));
    }
    
    void play() throws IOException{
        System.out.println("Welcome to blackJack!\n You have $"+Integer.toString(money)+".");
        do{
            placeBet();
            if(bet>0){
                initialDeal();
                if(playersHand.blackjack())
                    playerWins();
                else{
                    while (playersHand.under(22)&&playerTakesAHit()){
                        playersHand.addCard(deck.deal());
                        playersHand.show(false,false);
                    }
                    while(dealersHand.mustHit())
                        dealersHand.addCard(deck.deal());
                    dealersHand.show(true,false);
                    showResults();
                }
            }
        }while(bet>0);
    }

    void placeBet() throws IOException,NumberFormatException{
        do{
            System.out.println("Enter bet: ");
            System.out.flush();
            bet=Integer.parseInt(input.readLine());
           } while (bet<0||bet>money);
        
        }
    void initialDeal(){
        System.out.println("New hand...");
        playersHand= new Hand();
        dealersHand= new Hand();
        for(int i=0;i<2;++i){
            playersHand.addCard(deck.deal());
            dealersHand.addCard(deck.deal());
        }
        dealersHand.show(true,true);
        playersHand.show(false,false);         
    }
    void playerWins(){
        money+=bet;
        System.out.println("Player wins $"+Integer.toString(bet)+".");
        System.out.println("Player has $"+Integer.toString(money)+".");
    }
    void dealerWins(){
        money-=bet;
        System.out.println("Player loses $"+Integer.toString(bet)+".");
        System.out.println("Player has $"+Integer.toString(money)+".");
    }
    void tie(){
        System.out.println("Tie");
        System.out.println("Player has $"+Integer.toString(money)+".");
    }
    boolean playerTakesAHit() throws IOException {
        char ch= ' ';
        do{
            System.out.println("Hit or stay: ");
            System.out.flush();
            String playersDecision = input.readLine();
            try{
                ch = playersDecision.charAt(0);
            } catch(StringIndexOutOfBoundsException exception){
                
            }
            if(ch=='H'||ch=='h')return true;
            if(ch=='S'||ch=='s')return false;
        }while(true);
        
        }
    
    void showResults(){
        if(playersHand.busted()&&dealersHand.busted())
            tie();
        else if(playersHand.busted())
            dealerWins();
        else if(dealersHand.busted())
            playerWins();
        else if(playersHand.bestScore()>dealersHand.bestScore())
            playerWins();
        else if(playersHand.bestScore()<dealersHand.bestScore())
            dealerWins();
        else
            tie();       
    }
}
