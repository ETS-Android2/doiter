package com.lutshe.doiter.views.usergoals.list;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.RootContext;
import com.lutshe.doiter.data.database.dao.GoalsDao;
import com.lutshe.doiter.data.model.Goal;
import com.lutshe.doiter.data.provider.ImagesProvider;
import com.lutshe.doiter.data.provider.stub.ImagesProviderStub;
import com.lutshe.doiter.views.goals.list.GoalItemView;
import com.lutshe.doiter.views.goals.list.GoalItemView_;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class UserGoalsListAdapter extends BaseAdapter{

    private Goal[] userGoals;

    @Bean(ImagesProviderStub.class)
    ImagesProvider imagesProvider;

    @Bean
    GoalsDao goalsDao;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return goals().length;
    }

    @Override
    public Goal getItem(int position) {
        return goals()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @ItemClick
    public void listItemClicked(Goal goal) {
        Toast toast = new Toast(context);
        toast.setText(goal.getName());
        toast.show();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        GoalItemView goalView = (GoalItemView) convertView;

        if (goalView == null) {
            goalView = GoalItemView_.build(context);
        }

        Goal goal = getItem(position);
        Bitmap bitmap = imagesProvider.getImage(goal.getId());
        return goalView.bind(goal, bitmap);
    }

    @Override
    public void notifyDataSetChanged() {
        loadGoals();
        super.notifyDataSetChanged();
    }

    private Goal[] goals() {
        if (userGoals == null) {
            loadGoals();
        }
        return userGoals;
    }

    private void loadGoals() {
        userGoals = goalsDao.getAllUserGoals();
    }
}
