package com.example.customimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2016/10/24.
 */
public class StudentAdapter extends BaseAdapter {

    private List<Student> list;
    private LayoutInflater mInflater;

    public StudentAdapter(Context context, List<Student> list) {
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.adapter_student, null);
        }
        final ViewHolder holder = getViewHolder(convertView);
        Student student = list.get(position);
        holder.tv_name.setText(student.name +
                " 父亲-" + student.extendInfo.father +
                " 母亲-" + student.extendInfo.mother);
        if (student.sex == 0) {
            holder.iv_image.setBackgroundResource(R.drawable.man);
        } else if (student.sex == 1) {
            holder.iv_image.setBackgroundResource(R.drawable.girl);
        } else if (student.sex == 2) {
            holder.iv_image.setBackgroundResource(R.drawable.woman);
        }
        return convertView;
    }

    /**
     * 获得控件管理对象
     *
     * @param view
     * @return
     */
    private ViewHolder getViewHolder(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder == null) {
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        return holder;
    }

    /**
     * 控件管理类
     */
    private class ViewHolder {

        private TextView tv_name;
        private ImageView iv_image;

        ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv);
            iv_image = (ImageView) view.findViewById(R.id.iv);
        }
    }

}
