package com.example.pmd_l2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmd_l2.AppDB;
import com.example.pmd_l2.ImageRequester;
import com.example.pmd_l2.Adapters.MyAdapter2;
import com.example.pmd_l2.R;
import com.example.pmd_l2.entity.Details;
import com.example.pmd_l2.entity.Episode;
import com.example.pmd_l2.entity.IDpersonIDepisode;
import com.example.pmd_l2.entity.Urlepisode;
import com.example.pmd_l2.fragment.SecondFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        if(savedInstanceState == null){

            String classId = getIntent().getStringExtra("id");


            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SecondFragment fragment = new SecondFragment();

            Bundle bundle = new Bundle();
            bundle.putString("id", classId);
            fragment.setArguments(bundle);

            fragmentTransaction.add(R.id.main2, fragment);
            fragmentTransaction.commit();

        }




    }



}