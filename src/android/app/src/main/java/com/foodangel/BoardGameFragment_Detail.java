package com.foodangel;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;

public class BoardGameFragment_Detail extends AppCompatActivity implements View.OnClickListener {

    MainActivity m = new MainActivity();
    String path = m.getPath();

    int id, quantity;
    String itemPhoto, itemName, itemDetail, url, tel, name;
    TextView tvDetail;
    Button btnAdd;

    long today;
    SimpleDateFormat date, time;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_boardgame_detail);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        itemPhoto = intent.getStringExtra("photo");
        itemName = intent.getStringExtra("name");
        itemDetail = intent.getStringExtra("detail");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(itemName);

        tvDetail = (TextView) findViewById(R.id.tvDetail);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        tvDetail.setText(itemDetail);

        btnAdd.setOnClickListener(this);

        date = new SimpleDateFormat("yyyy-MM-dd");
        time = new SimpleDateFormat("HH:mm:ss");

        loadBackdrop();
    }

    @Override
    public void onClick(View view) {
        View promptsView = LayoutInflater.from(this).inflate(R.layout.prompt, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setView(promptsView);

        final EditText etName = (EditText) promptsView.findViewById(R.id.etName);
        final EditText etQuantity = (EditText) promptsView.findViewById(R.id.etQuantity);
        final EditText etTel = (EditText) promptsView.findViewById(R.id.etTel);
        SharedPreferences settings = getSharedPreferences("account", 0);
        if (settings.getString("nickName", null) != null) {
            etName.setText(settings.getString("nickName", null));
        }
        if (settings.getString("tel", null) != null) {
            etTel.setText(settings.getString("tel", null));
        }

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton(getString(R.string.messageActivity_yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {}
                        })
                .setNegativeButton(getString(R.string.messageActivity_no),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etName.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.boardgameActivity_nameHints), Toast.LENGTH_SHORT).show();
                } else if (etQuantity.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.boardgameActivity_quantityHints), Toast.LENGTH_SHORT).show();
                } else if (etTel.getText().toString().length() != 8) {
                    Toast.makeText(getApplicationContext(), getString(R.string.boardgameActivity_telError), Toast.LENGTH_SHORT).show();
                } else {
                    name = etName.getText().toString();
                    quantity = Integer.parseInt(etQuantity.getText().toString());
                    tel = etTel.getText().toString();
                    today = System.currentTimeMillis();
                    orderBoardGame();
                    dialog.dismiss();
                }
            }
        });
    }

    private void orderBoardGame() {
        try {
            url = URLEncoder.encode("SELECT * FROM boardgame WHERE BoardGameID = " + id, "UTF-8");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
        }
        runStatement(path + "api/Selection.php?statement=" + url, "load");
    }

    private void runStatement(String url, final String type) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (type.equals("load")) {
                        JSONArray jArray = new JSONArray(response);
                        String buyer = URLEncoder.encode(name, "UTF-8");
                        double totalPrice = jArray.getJSONObject(0).getDouble("Price") * quantity;
                        String url = path + "api/BuyBoardGame.php?boardgameID=" + id +
                                "&quantity=" + quantity +
                                "&totalprice=" + totalPrice +
                                "&membername=" + buyer +
                                "&contact=" + tel +
                                "&orderdate=" + date.format(today).toString() +
                                "&ordertime=" + time.format(today).toString();
                        runStatement(url, "buy");
                    } else if (type.equals("buy")) {
                        if (response.contains("true")) {
                            Toast.makeText(getApplicationContext(), getString(R.string.boardgameActivity_booked), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.boardgameActivity_bookFailed), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), getString(R.string.connect_failed_message), Toast.LENGTH_LONG).show();
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.ivBackdrop);
        Glide.with(this)
                .load(path + "BoardGameImage/" + itemPhoto)
                .placeholder(R.drawable.loading_page)
                .dontAnimate()
                .error(R.drawable.ic_eco_logo)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}