package com.foodangel;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ImageAdapter extends BaseAdapter {
    private Activity activity;
    private String[][] images;
    private LayoutInflater mInflat;

    public ImageAdapter(String[][] images, Activity activity) {
        this.images = images;
        this.activity = activity;
        mInflat = LayoutInflater.from(activity);
    }
    @Override
    public int getCount() {
        return images[0].length;
    }
    @Override
    public Object getItem(int position) {
        return images[1][position];
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflat.inflate(R.layout.fragment_coupon_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(images[0][position]);

        ImageView iv = (ImageView) convertView.findViewById(R.id.ivCoupon);
        Glide.with(activity)
                .load(images[1][position])
                .placeholder(R.drawable.loading_page)
                .dontAnimate()
                .thumbnail(1f)
                .into(iv);

        final View v = convertView;

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View promptsView = LayoutInflater.from(v.getContext()).inflate(R.layout.qrcode_layout_page, null);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(v.getContext());

                alertDialogBuilder.setView(promptsView);

                final ImageView ivQRCode = (ImageView) promptsView.findViewById(R.id.ivQRCode);
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(images[0][position], BarcodeFormat.QR_CODE, 800, 800);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap Bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    ivQRCode.setImageBitmap(Bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }

                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                final AlertDialog dialog = alertDialogBuilder.create();
                dialog.show();
            }
        });

        return convertView;
    }
}
