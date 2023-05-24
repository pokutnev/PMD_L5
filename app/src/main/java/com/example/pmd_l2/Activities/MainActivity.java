package com.example.pmd_l2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;


import com.example.pmd_l2.AppDB;
import com.example.pmd_l2.entity.Details;
import com.example.pmd_l2.Adapters.MyAdapter;
import com.example.pmd_l2.R;
import com.example.pmd_l2.RecycleViewInreface;
import com.example.pmd_l2.SpacesItemDecoration;
import com.example.pmd_l2.entity.IDpersonIDurl;
import com.example.pmd_l2.entity.Urlepisode;
import com.example.pmd_l2.fragment.FirstFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Рик и Морти");


        if(savedInstanceState == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FirstFragment fragment = new FirstFragment();
            fragmentTransaction.add(R.id.main, fragment);
            fragmentTransaction.commit();


        }




    }




}