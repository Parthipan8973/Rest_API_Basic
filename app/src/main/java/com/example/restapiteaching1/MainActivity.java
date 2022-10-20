package com.example.restapiteaching1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.restapiteaching1.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding actBind;
   String ApI = "https://reqres.in/api/users?page=2";
   String id,mail,firstname,lastname, alldatas,Url;

   String edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       actBind = DataBindingUtil.setContentView(this,R.layout.activity_main);



       actBind.button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Asytask asytask = new Asytask();
               asytask.execute();


           }
       });

    }

    private class Asytask extends AsyncTask {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            HttpsClass httpClass = new HttpsClass();
         String apidata =    httpClass.makeServiceCall(ApI);

//            try {
//                JSONObject jsonObject = new JSONObject(apidata);
//                JSONArray jsonArray =jsonObject.getJSONArray("data");
//                JSONObject jsonObject1 =jsonArray.getJSONObject(0);
//                alldatas =   jsonObject1.getString("last_name");
//
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }


            edittext= actBind.editTextTextPersonName.getText().toString();
            int val = Integer.valueOf(edittext);


            try {
                JSONObject jsonObject = new JSONObject(apidata);
               JSONArray jsonArray = jsonObject .getJSONArray("data");
                JSONObject jsonObject1 =jsonArray.getJSONObject(val);
                id =  jsonObject1.getString("id");
                mail= jsonObject1.getString("email");
                firstname =  jsonObject1.getString("first_name");
                lastname =jsonObject1.getString("last_name");
                Url = jsonObject1.getString("avatar");





            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            alldatas = "ID :"+id+"\n"+"\n"+"Mail ID :"+mail+"\n"+"\n"
                    +"First name :"+firstname+"\n"+"\n"+"Last name :"+lastname+"\n"+"\n"+"Your photo";
            actBind.textView2.setText(alldatas);

            Glide.with(MainActivity.this).load(Url).into(actBind.imageView);


//            actBind.textView2.setText(alldatas);
        }
    }
}