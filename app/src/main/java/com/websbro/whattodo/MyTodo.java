package com.websbro.whattodo;

public class MyTodo {
    String titleToDo,descriptionToDo,timeToDo;

    public MyTodo(String titleToDo, String descriptionToDo, String timeToDo) {
        this.titleToDo = titleToDo;
        this.descriptionToDo = descriptionToDo;
        this.timeToDo = timeToDo;
    }

    public String getTitleToDo() {
        return titleToDo;
    }

    public String getDescriptionToDo() {
        return descriptionToDo;
    }

    public String getTimeToDo() {
        return timeToDo;
    }
}
