package com.foodangel;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.emmasuzuki.easyform.EasyTextInputLayout;

public class PrivateBookingFragment extends Fragment implements View.OnClickListener {

    MainActivity m = new MainActivity();
    String path = m.getPath();

    public SharedPreferences settings;
    public SharedPreferences.Editor edit;

    View rootView;
    ActionBar actionBar;
    EasyTextInputLayout name, remark;
    Button btnSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_privatebooking, container, false);

        settings = getActivity().getSharedPreferences("account", 0);
        edit = settings.edit();

        // ActionBar
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.navigation_opinion));

        // EasyTextInputLayout
        name = (EasyTextInputLayout) rootView.findViewById(R.id.name);
        remark = (EasyTextInputLayout) rootView.findViewById(R.id.remark);

        resetData();
        // Button
        btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        resetData();
        Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
    }

    public void resetData() {
        if (!(settings.getString("email", "")).equals("")) {
            name.getEditText().setText(settings.getString("email", ""));
        }
        remark.getEditText().setText("");
    }
}
