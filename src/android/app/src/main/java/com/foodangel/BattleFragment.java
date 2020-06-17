package com.foodangel;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.BooleanExtractor;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.amigold.fundapter.interfaces.ItemClickListener;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kosalgeek.android.json.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BattleFragment extends Fragment implements View.OnClickListener {

    MainActivity m = new MainActivity();
    String path = m.getPath();

    SharedPreferences settings;
    SharedPreferences.Editor edit;

    final String TAG = this.getClass().getSimpleName();
    int tokenID, readyBookEventID;
    String url = null;
    String query = null;
    long today;
    int remainder;
    SimpleDateFormat date, time;

    ImageView ivClose2;
    TextView tvOK;
    EditText etNum;
    View rootView;
    ScrollView scrollDetail;
    RelativeLayout relative_num, loadingPanel;
    ListView lvBattle;
    ActionBar actionBar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    FunDapter<BattleDatabase> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_battle_list, container, false);

        settings = getActivity().getSharedPreferences("account", 0);
        edit = settings.edit();

        // ActionBar
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.navigation_battle));

        // ImageView
        ivClose2 = (ImageView) rootView.findViewById(R.id.ivClose2);
        ViewCompat.setElevation(ivClose2, 10);
        ivClose2.setOnClickListener(this);

        // TextView
        tvOK  = (TextView) rootView.findViewById(R.id.tvOK);
        tvOK.setOnClickListener(this);

        // EditText
        etNum = (EditText) rootView.findViewById(R.id.etNum);

        // ListView
        lvBattle = (ListView) rootView.findViewById(R.id.lvBattle);

        // Scroll View
        relative_num = (RelativeLayout) rootView.findViewById(R.id.relative_num);

        // Loading View
        loadingPanel = (RelativeLayout) rootView.findViewById(R.id.loadingPanel);
        loadingPanel.setVisibility(View.INVISIBLE);

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_refresh);
        // mSwipeRefreshLayout.setProgressViewOffset(true, 50, 200); // Refresh Circle Scaling, Start Location, End Location
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT); // Refresh Circle Size
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        try {
                            query = URLEncoder.encode("SELECT * , location.Place AS location, gatheringbattle.Status AS BattleStatus FROM gatheringbattle, location" +
                                    " where gatheringbattle.Place = location.LocationID" +
                                    " and (gatheringbattle.Date > '" + date.format(today).toString() +
                                    "' or (gatheringbattle.Date = '" + date.format(today).toString() +
                                    "' and gatheringbattle.Time > '" + time.format(today).toString() +
                                    "')) and gatheringbattle.Status != 2" +
                                    " and gatheringbattle.Status != 3" +
                                    " and gatheringbattle.Status != 4 ORDER BY gatheringbattle.Date, gatheringbattle.Time", "UTF-8");
                            runStatement(path + "api/Selection.php?statement=" + query, "battle");
                            showToast(getActivity(), getString(R.string.view_message_success), 1000);
                            mSwipeRefreshLayout.setRefreshing(false);
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
                        }
                    }
                }, 800);
            }
        });

        today = System.currentTimeMillis();
        date = new SimpleDateFormat("yyyy-MM-dd");
        time = new SimpleDateFormat("HH:mm:ss");

        try {
            query = URLEncoder.encode("SELECT * , location.Place AS location, gatheringbattle.Status AS BattleStatus FROM gatheringbattle, location" +
                    " where gatheringbattle.Place = location.LocationID" +
                    " and (gatheringbattle.Date > '" + date.format(today).toString() +
                    "' or (gatheringbattle.Date = '" + date.format(today).toString() +
                    "' and gatheringbattle.Time > '" + time.format(today).toString() +
                    "')) and gatheringbattle.Status != 2" +
                    " and gatheringbattle.Status != 3" +
                    " and gatheringbattle.Status != 4 ORDER BY gatheringbattle.Date, gatheringbattle.Time", "UTF-8");
            runStatement(path + "api/Selection.php?statement=" + query, "battle");
        } catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
        }
        getToken();
        return rootView;
    }

    private void runStatement(String url, final String type) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response);
                loadData(response, type);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void loadData(final String response, String type) {
        try {
            if (type.equals("battle")) {
                final ArrayList<BattleDatabase> battleList = new JsonConverter<BattleDatabase>().toArrayList(response, BattleDatabase.class);
                final BindDictionary<BattleDatabase> dictionary = new BindDictionary<>();

                dictionary.addStringField(R.id.tvBattleGameName, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        return "活動  " + (position + 1) + " - " + battle.eventName;
                    }
                });

                dictionary.addDynamicImageField(R.id.ivPhoto, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        return battle.photo;
                    }
                }, new DynamicImageLoader() {
                    @Override
                    public void loadImage(String url, ImageView view) {
                        if (url != null) {
                            Glide.with(getContext())
                                    .load(path + "Photo/" + url)
                                    .placeholder(R.drawable.loading_page)
                                    .dontAnimate()
                                    .error(R.drawable.ic_eco_logo)
                                    .fitCenter()
                                    .into(view);
                        }
                    }
                });

                dictionary.addStringField(R.id.tvHost, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        return getString(R.string.battleActivity_roomHoster) + " " + ((battle.memberName.length() > 15)? battle.memberName.substring(0, 15) + "..." : battle.memberName);
                    }
                });

                dictionary.addStringField(R.id.tvDate, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        return getString(R.string.battleActivity_date) + " " + battle.date;
                    }
                });

                dictionary.addStringField(R.id.tvTime, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        return getString(R.string.battleActivity_time) + " " + battle.time.substring(0, 5);
                    }
                });

                dictionary.addStringField(R.id.tvPlace, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        return getString(R.string.battleActivity_place) + " " + battle.location;
                    }
                });

                dictionary.addStringField(R.id.tvRequirement, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        return getString(R.string.battleActivity_requirement) + " " + getNumOfJoinedParticipant(battle.joinedParticipant) + " / " + battle.requirement;
                    }
                });

                dictionary.addStringField(R.id.tvParticipantValue, new StringExtractor<BattleDatabase>() {
                    @Override
                    public String getStringValue(BattleDatabase battle, int position) {
                        String name = "";
                        if (!battle.joinedParticipant.equals("")) {
                            try {
                                JSONArray jArray = new JSONArray(battle.joinedParticipant);
                                for(int i = 0; i < jArray.length(); i++) {
                                    String nickName = URLDecoder.decode(jArray.getJSONObject(i).getString("nickName"), "UTF-8");
                                    name += nickName.length() > 10 ? nickName.substring(0, 10) + "..." : nickName;
                                    if (jArray.getJSONObject(i).getString("extraPeople").compareTo("") != 0) {
                                        name += "+" + jArray.getJSONObject(i).getString("extraPeople") + "\n";
                                    } else {
                                        name += "\n";
                                    }
                                }
                            } catch (Exception e) {
                                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
                            }
                        }
                        return name;
                    }
                });

                dictionary.addConditionalVisibilityField(R.id.rvBook, new BooleanExtractor<BattleDatabase>() {
                    @Override
                    public boolean getBooleanValue(BattleDatabase battle, int position) {
                        return (battle.status == -1);
                    }
                }, View.INVISIBLE).onClick(new ItemClickListener<BattleDatabase>() {
                    @Override
                    public void onClick(BattleDatabase battle, int position, View view) {
                        if (!(settings.getString("email", "")).equals("")) {
                            readyBookEventID = battle.eventID;
                            remainder = battle.requirement - getNumOfJoinedParticipant(battle.joinedParticipant);
                            relative_num.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.logoutWarning), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dictionary.addConditionalVisibilityField(R.id.rvFull, new BooleanExtractor<BattleDatabase>() {
                    @Override
                    public boolean getBooleanValue(BattleDatabase battle, int position) {
                        return (battle.status == 0);
                    }
                }, View.INVISIBLE);

                dictionary.addConditionalVisibilityField(R.id.rvConfirmed, new BooleanExtractor<BattleDatabase>() {
                    @Override
                    public boolean getBooleanValue(BattleDatabase battle, int position) {
                        return (battle.status == 1);
                    }
                }, View.INVISIBLE);

                dictionary.addConditionalVisibilityField(R.id.rvCancelled, new BooleanExtractor<BattleDatabase>() {
                    @Override
                    public boolean getBooleanValue(BattleDatabase battle, int position) {
                        if (!(settings.getString("email", "")).equals("")) {
                            String account = settings.getString("email", "");
                            if (battle.account.compareTo(account) == 0) {
                                return true;
                            } else if (!battle.joinedParticipant.equals("")) {
                                try {
                                    JSONArray jArray = new JSONArray(battle.joinedParticipantToken);
                                    for(int i = 0; i < jArray.length(); i++) {
                                        if (URLDecoder.decode(jArray.getJSONObject(i).getString("token"), "UTF-8").contains(account)) {
                                            return true;
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (UnsupportedEncodingException e) {
                                    Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        return false;
                    }
                }, View.INVISIBLE).onClick(new ItemClickListener<BattleDatabase>() {
                    @Override
                    public void onClick(final BattleDatabase battle, int position, View view) {
                        if (!(settings.getString("email", "")).equals("")){
                            String account = settings.getString("email", "");
                            if (battle.account.compareTo(account) == 0) {
                                AlertDialog.Builder a = new AlertDialog.Builder(getContext());
                                a.setTitle(getString(R.string.battleActivity_warnning));
                                a.setMessage(getString(R.string.battleActivity_warnningMessage));
                                a.setCancelable(false);

                                a.setPositiveButton(getString(R.string.messageActivity_yes), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        removeBattle(battle.eventID);
                                    }
                                });

                                a.setNegativeButton(getString(R.string.messageActivity_no), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });

                                a.create();
                                a.show();
                            } else {
                                cancelBattle(battle.eventID);
                            }
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.logoutWarning), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                adapter = new FunDapter<>(getActivity(), battleList, R.layout.fragment_battle_item, dictionary);

                adapter.setAlternatingBackground(R.color.battle_backColor_odd, R.color.battle_backColor_even);
                lvBattle.setAdapter(adapter);
            } else if (type.equals("token")) {
                JSONArray jArray = new JSONArray(response);
                tokenID = jArray.getJSONObject(0).getInt("ID");
            } else if (type.equals("insert")) {
                if (response.contains("true")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_openSuccessfully), Toast.LENGTH_SHORT).show();
                    scrollDetail.setVisibility(View.INVISIBLE);
                } else if (response.contains("thatdaycreated")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_openFailed), Toast.LENGTH_SHORT).show();
                } else if (response.contains("has2room")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_openFailed2Room), Toast.LENGTH_SHORT).show();
                }
            } else if (type.equals("update")) {
                if (response.contains("true")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_successful), Toast.LENGTH_SHORT).show();
                } else if (response.contains("hoster")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_hoster), Toast.LENGTH_SHORT).show();
                } else if (response.contains("blacklisted")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_blacklisted), Toast.LENGTH_SHORT).show();
                } else if (response.contains("joined")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_failed), Toast.LENGTH_SHORT).show();
                }
            } else if (type.equals("cancel")) {
                if (response.contains("true")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_cancellSuccessfully), Toast.LENGTH_SHORT).show();
                } else if (response.contains("cancelled")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_cancellFailed), Toast.LENGTH_SHORT).show();
                }
            } else if (type.equals("remove")) {
                if (response.contains("true")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_cancellSuccessfully), Toast.LENGTH_SHORT).show();
                } else if (response.contains("false")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_cancellFailed), Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.battleActivity_noBattle_message), Toast.LENGTH_SHORT).show();
        }
    }

    private int getNumOfJoinedParticipant(String joinedParticipant) {
        if (!joinedParticipant.equals("")) {
            int num = 0;
            try {
                JSONArray jArray = new JSONArray(joinedParticipant);
                for(int i = 0; i < jArray.length(); i++) {
                    if (jArray.getJSONObject(i).getString("extraPeople").compareTo("") != 0) {
                        num += Integer.parseInt(jArray.getJSONObject(i).getString("extraPeople")) + 1;
                    } else {
                        num += 1;
                    }
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
            }
            return num;
        } else {
            return 1;
        }
    }

    private void joinBattle(int eventID, int extraPeople) {
        try {
            String account = settings.getString("email", "");
            String sExtraPeople = (extraPeople != 0) ? extraPeople + "" : "";
            String nickName = URLEncoder.encode(settings.getString("nickName", settings.getString("name", "")), "UTF-8");
            String user = URLEncoder.encode(account, "UTF-8");
            url = path + "api/JoinBattle.php?token=" + tokenID + "&nickName=" + nickName + "&extraPeople=" + sExtraPeople + "&user=" + user + "&eventID=" + eventID;
            runStatement(url, "update");
        } catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
        }
    }

    private void cancelBattle(int eventID) {
        try {
            String account = settings.getString("email", "");
            String user = URLEncoder.encode(account, "UTF-8");
            url = path + "api/CancelBattle.php?user=" + user + "&eventID=" + eventID;
            runStatement(url, "cancel");
        } catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
        }
    }

    private void removeBattle(int eventID) {
        try {
            url = path + "api/RemoveBattle.php?eventID=" + eventID;
            runStatement(url, "remove");
        } catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvOK:
                if (etNum.getText().toString().length() == 0 ||  (etNum.getText().toString()).equals("0")) {
                    Toast.makeText(getActivity(), getString(R.string.battleActivity_confirmMessage), Toast.LENGTH_SHORT).show();
                } else {
                    int extraPeople = Integer.parseInt(etNum.getText().toString()) - 1;
                    if (extraPeople + 1 > remainder)
                        Toast.makeText(getActivity(), getString(R.string.battleActivity_moreThanRemainderMessage), Toast.LENGTH_SHORT).show();
                    else {
                        joinBattle(readyBookEventID, extraPeople);
                        etNum.setText("");
                        relative_num.setVisibility(View.INVISIBLE);
                    }
                }
                break;
            case R.id.ivClose2:
                etNum.setText("");
                relative_num.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void getToken() {
        try {
            query = URLEncoder.encode("SELECT * FROM users WHERE Token = '" + FirebaseInstanceId.getInstance().getToken() + "'", "UTF-8");
            runStatement(path + "api/Selection.php?statement=" + query, "token");
        } catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
        }
    }

    public void showToast(final Activity activity, final String word, final long time) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }
}