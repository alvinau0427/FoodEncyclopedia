package com.foodangel;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.amigold.fundapter.interfaces.ItemClickListener;
import com.bumptech.glide.Glide;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.jude.rollviewpager.hintview.IconHintView;
import com.kosalgeek.android.json.JsonConverter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IndexFragment extends Fragment {

    MainActivity m = new MainActivity();
    String path = m.getPath();

    View rootView;
    private RollPagerView mRollViewPager;
    private String[] imgs;
    private boolean isBattleOk = false, isGameOk = false;
    ActionBar actionBar;

    NonScrollListView lvBattleList;
    NonScrollGridView gvGameList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_index, container, false);

        lvBattleList = (NonScrollListView) rootView.findViewById(R.id.lvBattleList);
        gvGameList = (NonScrollGridView) rootView.findViewById(R.id.gvGameList);

        // ActionBar
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_name));

        getImage();
        getBattleData();
        getGameData();

        while (imgs == null || !isBattleOk || !isGameOk) {
            // Loop until the end of the second thread (if no img, it will also leave the loop)
        }

        mRollViewPager = (RollPagerView) rootView.findViewById(R.id.mRollViewPager);
        mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager));
        // mRollViewPager.setHintView(new IconHintView(rootView.getContext(), R.drawable.point_focus_pi, R.drawable.point_normal_pi));
        mRollViewPager.setHintView(new IconHintView(rootView.getContext(), R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(rootView.getContext(), getResources().getColor(R.color.colorAccent), Color.WHITE));
        // mRollViewPager.setHintView(new TextHintView(rootView.getContext()));

        return rootView;
    }

    private class TestLoopAdapter extends LoopPagerAdapter {

        public TestLoopAdapter(RollPagerView viewPager) {super(viewPager);}

        @Override
        public View getView(ViewGroup container, final int position) {
            final ImageView view = new ImageView(container.getContext());
            Glide.with(getActivity())
                    .load(path + "Photo/" + imgs[position%imgs.length])
                    .placeholder(R.drawable.loading_page)
                    .dontAnimate()
                    .error(R.drawable.ic_eco_logo)
                    .into(view);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount() {
            return imgs.length;
        }
    }

    private void getImage() {
        String query = null;
        try {
            query = URLEncoder.encode("SELECT * FROM photo WHERE Status = 1", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = path + "api/Selection.php?statement=" + query;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    JSONArray jArray = new JSONArray(response.body().string());
                    String[] items = new String[jArray.length()];
                    for (int i = 0; i < jArray.length(); i++) {
                        items[i] = jArray.getJSONObject(i).getString("PhotoName");
                    }
                    imgs = items;
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getBattleData() {
        String query = null;
        try {
            query = URLEncoder.encode("SELECT * FROM indexsetting WHERE Name = 'gatheringbattle'", "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = path + "api/Selection.php?statement=" + query;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) {
                String query2 = null;
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    int showNumber = jsonArray.getJSONObject(0).getInt("ShowItem");
                    query2 = URLEncoder.encode("SELECT * FROM gatheringbattle WHERE Status = -1 ORDER BY EventID DESC, Date DESC LIMIT " + showNumber, "UTF-8");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String url = path + "api/Selection.php?statement=" + query2;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        isBattleOk = true;
                        Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        try {
                            final ArrayList<BattleDatabase> battleList = new JsonConverter<BattleDatabase>().toArrayList(response.body().string(), BattleDatabase.class);
                            final BindDictionary<BattleDatabase> battleDictionary = new BindDictionary<>();

                            battleDictionary.addBaseField(R.id.card_view).onClick(new ItemClickListener<BattleDatabase>() {
                                @Override
                                public void onClick(BattleDatabase item, int position, View view) {
                                    BattleFragment battleFragment = new BattleFragment();
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, battleFragment);
                                    fragmentTransaction.commit();
                                }
                            });

                            battleDictionary.addDynamicImageField(R.id.ivBattlePhoto, new StringExtractor<BattleDatabase>() {
                                @Override
                                public String getStringValue(BattleDatabase item, int position) {
                                    return item.photo;
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
                                                .centerCrop()
                                                .into(view);
                                    }
                                }
                            });

                            battleDictionary.addStringField(R.id.tvBattleTitle, new StringExtractor<BattleDatabase>() {
                                @Override
                                public String getStringValue(BattleDatabase item, int position) {
                                    return item.eventName;
                                }
                            });

                            battleDictionary.addStringField(R.id.tvBattlePeople, new StringExtractor<BattleDatabase>() {
                                @Override
                                public String getStringValue(BattleDatabase item, int position) {
                                    return getNumOfJoinedParticipant(item.joinedParticipant) + " / " + item.requirement;
                                }
                            });

                            battleDictionary.addStringField(R.id.tvBattleHoster, new StringExtractor<BattleDatabase>() {
                                @Override
                                public String getStringValue(BattleDatabase item, int position) {
                                    return item.memberName;
                                }
                            });

                            battleDictionary.addStringField(R.id.tvBattleDate, new StringExtractor<BattleDatabase>() {
                                @Override
                                public String getStringValue(BattleDatabase item, int position) {
                                    return item.date;
                                }
                            });

                            new Thread(new Runnable() {
                                public void run() {
                                    lvBattleList.setAdapter(new FunDapter<>(getActivity(), battleList, R.layout.fragment_index_battle_item, battleDictionary));
                                    isBattleOk = true;
                                }
                            }).start();
                        } catch (Exception e) {
                            isBattleOk = true;
                            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private int getNumOfJoinedParticipant(String joinedParticipant) {
        if (joinedParticipant != "") {
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

    private void getGameData() {
        String query = null;
        try {
            query = URLEncoder.encode("SELECT * FROM indexsetting WHERE Name = 'boardgame'", "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = path + "api/Selection.php?statement=" + query;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) {
                String query2 = null;
                try {
                    JSONArray jsonArray = new JSONArray(response.body().string());
                    int showNumber = jsonArray.getJSONObject(0).getInt("ShowItem");
                    query2 = URLEncoder.encode("SELECT * FROM boardgame ORDER BY BoardGameID DESC LIMIT " + showNumber, "UTF-8");
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String url = path + "api/Selection.php?statement=" + query2;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        isGameOk = true;
                        Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        try {
                            final ArrayList<BoardGameDatabase> boardGameList = new JsonConverter<BoardGameDatabase>().toArrayList(response.body().string(), BoardGameDatabase.class);
                            final BindDictionary<BoardGameDatabase> boardGameDictionary = new BindDictionary<>();

                            boardGameDictionary.addBaseField(R.id.card_view).onClick(new ItemClickListener<BoardGameDatabase>() {
                                @Override
                                public void onClick(BoardGameDatabase item, int position, View view) {
                                    BoardGameFragment boardGameFragment = new BoardGameFragment();
                                    FragmentManager fragmentManager = getFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fragment_container, boardGameFragment);
                                    fragmentTransaction.commit();
                                }
                            });

                            boardGameDictionary.addDynamicImageField(R.id.ivGamePhoto, new StringExtractor<BoardGameDatabase>() {
                                @Override
                                public String getStringValue(BoardGameDatabase item, int position) {
                                    return item.photo;
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

                            boardGameDictionary.addStringField(R.id.tvGameTitle, new StringExtractor<BoardGameDatabase>() {
                                @Override
                                public String getStringValue(BoardGameDatabase item, int position) {
                                    return item.boardGameName;
                                }
                            });
                            new Thread(new Runnable() {
                                public void run() {
                                    gvGameList.setAdapter(new FunDapter<>(getActivity(), boardGameList, R.layout.fragment_index_boardgame_item, boardGameDictionary));
                                    isGameOk = true;
                                }
                            }).start();
                        } catch (Exception e) {
                            isGameOk = true;
                            Toast.makeText(getActivity(), getString(R.string.connect_failed_message), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
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

    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mRollViewPager.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mRollViewPager.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}