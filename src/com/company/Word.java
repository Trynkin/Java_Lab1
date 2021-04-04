package com.company;

public class Word implements Comparable<Word> {
    private String word;
    private int count;

    public Word(StringBuilder input){
        word = new String(input);
        count = 1;
    }


    public void inc(){
        count++;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }


    @Override
    public int compareTo(Word w1) {
        return this.count < w1.count ? -1 : 1;
    }



}

