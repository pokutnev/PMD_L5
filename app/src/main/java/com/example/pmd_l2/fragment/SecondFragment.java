package com.example.pmd_l2.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmd_l2.Activities.MainActivity2;
import com.example.pmd_l2.Adapters.MyAdapter2;
import com.example.pmd_l2.AppDB;
import com.example.pmd_l2.R;
import com.example.pmd_l2.entity.Details;
import com.example.pmd_l2.entity.Episode;
import com.example.pmd_l2.entity.IDpersonIDepisode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SecondFragment extends Fragment {

    TextView tvView;
    ImageView imgView;
    TextView descrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_second, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView21);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




//        MyAdapter2 adapter2 = new MyAdapter2(getActivity(), new Episode[0]);
//        recyclerView.setAdapter(adapter2);

        recyclerView.setHasFixedSize(true);



        tvView = view.findViewById(R.id.tvView);
        imgView = view.findViewById(R.id.imgView);


        String id = getArguments().getString("id");

        new SecondFragment.MyAsyncTaskPerson(id).execute();
        new SecondFragment.MyAsyncTask(recyclerView, id).execute();


        return view;
    }

    public static boolean hasConnection(final Context context)
    {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected())
        {
            return true;
        }
        return false;
    }

    public class MyAsyncTaskPerson extends AsyncTask<String, Void, Details> {

        Bitmap photo;
        String id;

        public MyAsyncTaskPerson(String id) {
            this.id = id;
        }

        @Override
        protected Details doInBackground(String... strings) {
            AppDB db = Room. databaseBuilder(getContext(),
                            AppDB. class, "RickAndMortyDB")
                    .fallbackToDestructiveMigration()
                    .build();

            Log.d("TAG", "doInBackground: " + db.userDao().getAll());
            Details Person = db.userDao().getPersonID(id);


            String filename = "img-" + id + ".jpg";
            FileInputStream inputStream;

            try {
                inputStream = getActivity().openFileInput(filename);

                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                photo = BitmapFactory.decodeStream(bufferedInputStream);

                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return Person;

        }

        @Override
        protected void onPostExecute(Details Person) {

            imgView.setImageBitmap(photo);
            tvView.setText(Person.getName());

        }
    }



    private class MyAsyncTask extends AsyncTask<String[], Void, List<Episode>> {


        RecyclerView recyclerView;
        String id;

        ArrayList<Episode> episodes = new ArrayList<>();

        public MyAsyncTask(RecyclerView recyclerView, String id) {
            this.recyclerView = recyclerView;
            this.id = id;
        }


        protected List<Episode> doInBackground(String[]... urls) {

            AppDB db = Room. databaseBuilder(getContext(),
                            AppDB. class, "RickAndMortyDB")
                    .fallbackToDestructiveMigration()
                    .build();


            List<String> des = db.iDpersonIDurl().getEpisodes(id);
            Log.d("TAG", "doInBackground: " + id);
            String[] res = new String[des.size()];

            OkHttpClient client = new OkHttpClient();

            Log.d("TAG", "doInBackground: " + des);


            List<String> k = db.iDpersonIDepisodeDao().getEpisodeID(id);


            if(k == null || k.size() == 0 &&  hasConnection(getActivity())){


                for (int j = 0; j < des.size(); j++) {

                    Request request1 = null;

                    request1 = new Request.Builder()
                            .url(db.urlDao().getPersonID(des.get(j)).url)
                            .build();


                    try (Response response2 = client.newCall(request1).execute()) {

                        JSONObject jsonResponse2 = new JSONObject(response2.body().string());

                        res[j] = jsonResponse2.getString("name");
                        res[j] += " ";
                        res[j] += jsonResponse2.getString("air_date");
                        res[j] += " ";
                        res[j] += jsonResponse2.getString("episode");

                        String idEpisode = UUID.randomUUID().toString();
//                    db.episodeDao().insert(new Episode(UUID.randomUUID().toString(), res[j]));
                        db.episodeDao().insert(new Episode(idEpisode, res[j]));
                        db.iDpersonIDepisodeDao().insert(new IDpersonIDepisode(UUID.randomUUID().toString(), id, idEpisode));

                    } catch (IOException | JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

                k = db.iDpersonIDepisodeDao().getEpisodeID(id);
            }


            List<Episode> episodeList = new ArrayList<>();

            for (int i = 0; i < k.size(); i++) {
                episodeList.add(db.episodeDao().getPersonID(k.get(i)));
            }

            return episodeList;
        }


        protected void onPostExecute(List<Episode> res) {
            MyAdapter2 adapter = new MyAdapter2(getActivity(), res.toArray(new Episode[0]));
            recyclerView.setAdapter(adapter);
        }

    }


}