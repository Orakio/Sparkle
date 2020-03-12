package com.example.myproject.screens;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected void updateActivityTitle(String title){
        if (getActivity() instanceof BaseActivity){
            //Change activity name
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.updateActivityTitle(title);
        }
    }
}
