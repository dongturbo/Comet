package com.comet;

public class Message {
    private long id = System.currentTimeMillis();
    private String text;

    public Message(){

    }

    public Message(String newText){
        text = newText;
        System.out.println(toString());
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
