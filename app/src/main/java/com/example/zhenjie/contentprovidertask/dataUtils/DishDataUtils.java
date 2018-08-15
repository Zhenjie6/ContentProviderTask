package com.example.zhenjie.contentprovidertask.dataUtils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.example.zhenjie.contentprovidertask.bean.Dish;
import com.example.zhenjie.contentprovidertask.bean.DishGroup;

import java.util.ArrayList;
import java.util.List;

public class DishDataUtils {




    public static List<DishGroup> getDishListFromProvider(Cursor c){
        List<DishGroup> resultList = new ArrayList<>();
        String type,dishName;
        DishGroup group;
        int groupId = 0;
        int tempGId = 0;
        int dishId;
        while(c.moveToNext()){
            type = c.getString(2);
            dishId = Integer.valueOf(c.getString(0));
            dishName = c.getString(1);
            if (resultList.size()==0){
                tempGId = -1;
            }else {
                //判断菜品所属的组是否存在列表中，如果存在，将该组的id记录到tempGId中
                for (DishGroup d : resultList) {
                    if (type.equals(d.getGroupName())) {
                        tempGId = d.getId();
                    } else tempGId = -1;
                }
            }
            if(tempGId == -1){
                //如果菜品所属的组还不存在，就在此创建
                group = new DishGroup(groupId,type);
                //然后将菜品加入该分组
                group.add(new Dish(dishId,dishName));
                group.setGroupName(type);
                resultList.add(group);//将该组加入到返回的结果列表当中
                groupId++;//让groupId自增作为下一个新组的id
                group = null;
            }else{
                //如果tempGId不等于-1，说明该菜品的组已经存在，直接把菜品加入到对应的组即可
                for (DishGroup d:resultList){
                    if (d.getId() == tempGId){
                        d.add(new Dish(dishId,dishName));
                    }
                }
            }
        }
        return resultList;
    }
}
