package com.example.zhenjie.contentprovidertask.bean;

import java.util.ArrayList;
import java.util.List;

public class DishGroup {
    private int id;
    private String groupName;
    private List<Dish> dishList = new ArrayList<>();

    public void add(Dish dish){
        dish.setPid(this.id);
        dishList.add(dish);
    }

    public DishGroup(int id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Dish> getDish() {
        return dishList;
    }

    public void setDish(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
