package com.example.ExpandableListView;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ExpandableListAdapter adapter =new BaseExpandableListAdapter()
        {
            int[] logos=new int[]
                    {
                            R.drawable.p,
                            R.drawable.z,
                            R.drawable.t
                    };
            private String[] armTypes=new String[]
                    { "神族兵种", "虫族兵种", "人族兵种"};
            private String[][] arms=new String[][]
                    {
                            { "狂战士", "龙骑士", "黑暗圣堂", "电兵" },
                            { "小狗", "刺蛇", "飞龙", "自爆飞机" },
                            { "机枪兵", "护士MM" , "幽灵" }
                    };
            @Override
            public  Object getChild(int groupPosition,int childPosition)
            {
                return arms[groupPosition][childPosition];
            }
            @Override
            public long getChildId(int groupPosition,int childPosition)
            {
                return childPosition;
            }
            @Override
            public int getChildrenCount(int groupPosition)
            {
                return arms[groupPosition].length;
            }
            private TextView getTextView()
            {
                AbsListView.LayoutParams lp=new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,64);
                TextView textView=new TextView(MyActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                textView.setPadding(36,0,0,0);
                textView.setTextSize(20);
                return textView;

            }
            @Override
            public View getChildView(int groupPosition,int childPosition,
                                     boolean isLastChild,View convertView,ViewGroup parent)
            {
                TextView textView=getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());
                return textView;
            }
            @Override
            public Object getGroup(int groupPosition)
            {
                return armTypes[groupPosition];
            }
            @Override
            public  int getGroupCount()
            {
                return armTypes.length;
            }
            @Override
            public long getGroupId(int groupPosition)
            {
                return groupPosition;
            }
            @Override
            public View getGroupView(int groupPosition,boolean isExpanded,
                                     View convertView,ViewGroup parent)
            {
                LinearLayout ll=new LinearLayout(MyActivity.this);
                ll.setOrientation(0);
                ImageView logo=new ImageView(MyActivity.this);
                logo.setImageResource(logos[groupPosition]);
                ll.addView(logo);
                TextView textView=getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);
                return ll;
            }
            @Override
            public boolean isChildSelectable(int groupPosition,
                                             int childPosition)
            {
                return true;
            }
            @Override
            public  boolean hasStableIds()
            {
                return true;
            }
        };
        ExpandableListView expandListView=(ExpandableListView)findViewById(R.id.list);
        expandListView.setAdapter(adapter);
    }
}
