package ru.examples.models;

public class HelloMsg {

    public String text;
    public HelloMsg(){}

    public HelloMsg(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
