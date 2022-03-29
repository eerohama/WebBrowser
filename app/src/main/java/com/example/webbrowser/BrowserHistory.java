package com.example.webbrowser;

import java.util.ArrayList;

public class BrowserHistory {
    private int index;
    private static BrowserHistory instance = null;
    private ArrayList<String> list;

    public BrowserHistory(){
        this.list = new ArrayList<>();
        index = 0;
    }

    public static BrowserHistory getInstance() {
        if(instance == null){
            instance = new BrowserHistory();
        }
        return instance;
    }

    public void addAdress(String s){
        int size = list.size();
        for(int i = index+1 ; i < size; i++ ){
            list.remove(index+1);
        }
        list.add(s);
        index = list.size() - 1;
    }

    public ArrayList<String> getList(){
        return list;
    }

    public String getPrevious(){
        if(index - 1 >= 0){
            index--;
        }
        return list.get(index);
    }

    public String getNext(){
        if(index + 1 < list.size()){
            index++;
        }
        return list.get(index);
    }

    public String getCurrent(){
        return list.get(index);
    }
}
