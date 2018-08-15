package com.example.zhenjie.contentprovidertask.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhenjie.contentprovidertask.R;
import com.example.zhenjie.contentprovidertask.bean.DishGroup;

import java.util.List;

public class MExpandableListViewAdapter extends BaseExpandableListAdapter{

    static List<DishGroup> groups;
    private Context context;
    private LayoutInflater inflater;

    public MExpandableListViewAdapter(List<DishGroup> groups,Context context){
        this.groups = groups;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getDish().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getDish().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groups.get(groupPosition).getId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getDish().get(childPosition).getId();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.group_item_expandable_list_view,parent,false);
            GroupViewHolder viewHolder = new GroupViewHolder();
            viewHolder.iv = convertView.findViewById(R.id.iv_indicator);
            viewHolder.tv = convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(viewHolder);
            viewHolder.iv.setImageResource(R.mipmap.ic_launcher);
            viewHolder.tv.setText(groups.get(groupPosition).getGroupName());
        }else {
            GroupViewHolder viewHolder= (GroupViewHolder) convertView.getTag();
            viewHolder.iv.setImageResource(R.mipmap.ic_launcher);
            viewHolder.tv = convertView.findViewById(R.id.tv_group_name);
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.child_item_expandable_list_view,parent,false);
            ChildViewHolder viewHolder = new ChildViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.tv_child_name);
            convertView.setTag(viewHolder);
            viewHolder.tv.setText(groups.get(groupPosition).getDish().get(childPosition).getName());
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                   makeDialog(v,groupPosition,childPosition);
                   return true;
                }
            });
        }else {
            ChildViewHolder viewHolder = (ChildViewHolder) convertView.getTag();
            viewHolder.tv.setText(groups.get(groupPosition).getDish().get(childPosition).getName());
        }
        return convertView;
    }

    private void makeDialog(View v, int gId, final int cId) {
        final String name = groups.get(gId).getDish().get(cId).getName();
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("WARNING")
                .setMessage("是否要删除["+"]"+name)
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.getContentResolver().delete(Uri.parse("content://com.imooc.menuprovider"),"where dish_id =",
                                new String[]{cId+""});
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder{
        ImageView iv;
        TextView tv;
    }

    class ChildViewHolder{
        TextView tv;
    }
}
