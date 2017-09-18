package com.app.axcro.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.app.axcro.R;
import com.app.axcro.RedemptionsItem;
import com.app.axcro.adapters.RedemptionsAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_redemptions)
public class RedemptionsActivity extends AppCompatActivity implements RedemptionsAdapter.AdaperListener {
    private static final String TAG = "RedemptionsActivity";
    private List<RedemptionsItem> redemptionsItemList = new ArrayList<>();
    private RedemptionsAdapter redemptionsAdapter;
    @ViewById(R.id.activity_redemptions_rv)
    protected RecyclerView rvRedemptions;
    @ViewById(R.id.activity_redemptions_tv_sort_by_purchase_date)
    protected TextView tvSortByPurchaseDate;
    @ViewById(R.id.activity_redemptions_tv_sort_by_lastest_redemption)
    protected TextView tvSortByLastestRedemption;

    @AfterViews
    public void init() {
        redemptionsItemList.add(new RedemptionsItem("Kim Tan", "29 Jul 2017", "29 Jul 2017", "8"));
        redemptionsItemList.add(new RedemptionsItem("Yura", "30 Jul 2017", "30 Jul 2017", "4"));
        redemptionsItemList.add(new RedemptionsItem("Joseph G.", "30 Aug 2017", "30 Sep 2017", "2"));
        redemptionsItemList.add(new RedemptionsItem("Stephanie", "30 Sep 2017", "30 Sep 2017", "2"));
        sortByPurchaseDate();
        redemptionsAdapter = new RedemptionsAdapter(redemptionsItemList,this);
        rvRedemptions.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvRedemptions.setLayoutManager(mLayoutManager);
        rvRedemptions.setAdapter(redemptionsAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void clickItem(int position) {
        Log.i(TAG, "clickItem: "+position);
    }
    public void sort(View v){
        if(v.getId()==R.id.activity_redemptions_tv_sort_by_purchase_date){
            tvSortByPurchaseDate.setTextColor(Color.parseColor("#de000000"));
            tvSortByLastestRedemption.setTextColor(Color.parseColor("#8a000000"));
            sortByPurchaseDate();

        }
        if(v.getId()==R.id.activity_redemptions_tv_sort_by_lastest_redemption){
            tvSortByPurchaseDate.setTextColor(Color.parseColor("#8a000000"));
            tvSortByLastestRedemption.setTextColor(Color.parseColor("#de000000"));
            sortByLastestRedemption();
        }
        redemptionsAdapter.notifyDataSetChanged();
    }


    private void sortByPurchaseDate() {
        Collections.sort(redemptionsItemList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                RedemptionsItem p1 = (RedemptionsItem) o1;
                RedemptionsItem p2 = (RedemptionsItem) o2;

                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd MMM yyyy"); // I assume d-M, you may refer to M-d for month-day instead.
                Date date = null; // You will need try/catch around this
                try {
                    date = formatter.parse(p1.getPurchaseDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long millis_another = date.getTime();

                date = null; // You will need try/catch around this
                try {
                    date = formatter.parse(p2.getPurchaseDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long millis_this = date.getTime();
                return Long.signum(millis_another - millis_this);
            }
        });
    }
    private void sortByLastestRedemption() {
        Collections.sort(redemptionsItemList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                RedemptionsItem p1 = (RedemptionsItem) o1;
                RedemptionsItem p2 = (RedemptionsItem) o2;

                java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd MMM yyyy"); // I assume d-M, you may refer to M-d for month-day instead.
                Date date = null; // You will need try/catch around this
                try {
                    date = formatter.parse(p1.getLastestRedemption());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                long millis_another = date.getTime();

                date = null; // You will need try/catch around this
                try {
                    date = formatter.parse(p2.getLastestRedemption());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long millis_this = date.getTime();
                return Long.signum(millis_this - millis_another);
            }
        });
    }

}
