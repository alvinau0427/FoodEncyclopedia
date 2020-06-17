package com.foodangel;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.amigold.fundapter.interfaces.ItemClickListener;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.kosalgeek.android.json.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLEncoder;
import java.util.ArrayList;

public class CouponFragment extends Fragment implements Response.Listener<String> {

    MainActivity m = new MainActivity();
    String path = m.getPath();

    final String TAG = this.getClass().getSimpleName();
    String url = null, query;
    StringRequest stringRequest = null;
    View rootView;
    ListView lvEvent;
    ActionBar actionBar;

    EventDatabase eventData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_coupon_list, container, false);
        lvEvent = (ListView) rootView.findViewById(R.id.lvEvent);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        actionBar.setTitle(getString(R.string.navigation_coupon));

        showCoupons();

//        showEventData();
        return rootView;
    }

    private void showCoupons() {
        try {
            query = URLEncoder.encode("SELECT * FROM coupon WHERE Status = 1", "UTF-8");
        }
        catch (Exception e) {
            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
        }
        url = path + "api/Selection.php?statement=" + query;

        stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jArray = new JSONArray(response);
                    String[][] images = new String[2][jArray.length()];
                    for (int i = 0; i < jArray.length(); i++) {
                        images[0][i] = jArray.getJSONObject(i).getString("CouponName");
                        images[1][i] = path + "Photo/Coupon/" + jArray.getJSONObject(i).getString("PhotoName");
                    }
                    lvEvent.setAdapter(new ImageAdapter(images, getActivity()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
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

    public void showEventData() {
        url = "https://api.meetup.com/partyideas/events";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, this, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    @Override
    public void onResponse(String response) {
        Log.d(TAG, response);
        loadData(response, -1);

    }

    public void loadData(final String response, final int target) {
        try {
            ArrayList<EventDatabase> eventList = new JsonConverter<EventDatabase>().toArrayList(response, EventDatabase.class);
            final BindDictionary<EventDatabase> dictionary = new BindDictionary<>();

            dictionary.addStringField(R.id.tvName, new StringExtractor<EventDatabase>() {
                @Override
                public String getStringValue(EventDatabase item, int position) {
                    return item.eventName;
                }
            });

            dictionary.addDynamicImageField(R.id.ivCoupon, new StringExtractor<EventDatabase>() {
                @Override
                public String getStringValue(EventDatabase item, int position) {
                    return item.link;
                }
            }, new DynamicImageLoader() {
                @Override
                public void loadImage(String url, ImageView view) {
                    if (url != null) {
                        Glide.with(getActivity())
                                .load(path + "BoardGameImage/" + url)
                                .placeholder(R.drawable.loading_page)
                                .dontAnimate()
                                .error(R.drawable.ic_eco_logo)
                                .fitCenter()
                                .into(view);
                    }
                }
            });

            FunDapter<EventDatabase> adapter = new FunDapter<>(
                    getActivity(), eventList, R.layout.fragment_coupon_item, dictionary);

            adapter.setAlternatingBackground(R.color.battle_backColor_odd, R.color.battle_backColor_even);
            lvEvent.setAdapter(adapter);
            if (target == -1) {
                lvEvent.setSelection(0);
            } else {
                lvEvent.setSelection(target);
            }
        } catch (Exception e) {
            // Clear listView
            lvEvent.setAdapter(null);
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}