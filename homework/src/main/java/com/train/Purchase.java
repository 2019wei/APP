package com.train;

public class Purchase {
    int ticket;
    int goback;

    public Purchase(int ticket, int goback) {
        this.ticket = ticket;
        this.goback = goback;
    }

    public void print(){
        int total = (int) ((ticket-goback) * 1000 + goback *2000*0.9) ;
        System.out.println("Total tickets: " + ticket +"\t" + "Round-trip: " + goback+"\t" +"Total:" + total);

    }
}

