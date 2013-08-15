package com.lutshe.doiter.views.usergoals;

import android.view.View;
import android.widget.AdapterView;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.lutshe.doiter.R;
import com.lutshe.doiter.data.model.UserGoal;
import com.lutshe.doiter.views.usergoals.details.UserGoalDetailFragment;
import com.lutshe.doiter.views.usergoals.details.UserGoalDetailFragment_;
import com.lutshe.doiter.views.util.FragmentsSwitcher;

/**
 * Created by Arsen Adzhiametov on 7/31/13.
 */
@EBean
public class UserGoalSelectedListener implements AdapterView.OnItemClickListener{
    @Bean
    FragmentsSwitcher fragmentsSwitcher;

    @Bean
    UserGoalsListAdapter userGoalsListAdapter;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
        UserGoal userGoal = userGoalsListAdapter.getItem(position);

        UserGoalDetailFragment detailFragment = UserGoalDetailFragment_.builder().goalId(userGoal.getGoalId()).build();
        fragmentsSwitcher.show(R.id.fragment_container, detailFragment);
    }
}
