package com.example.firozsaifi.mysqldbapp;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import dmax.dialog.SpotsDialog;

public class BackgroundTask extends AsyncTask<String,String,String> {

    Context ctx;
    AlertDialog alertDialog;
    AlertDialog ad;

    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information...");

        ad=new SpotsDialog(ctx,R.style.Custom);  //for this i used custom library in gradle scripts
        ad.show();

    }

    @Override
    protected String doInBackground(String... params) {
        //String reg_url="http://10.0.2.2/android/register.php";  //server url for register from locahhost
        //String login_url="http://10.0.2.2/android/login.php";   //server url for login from localhost

        String reg_url="http://hugejobs99.com/firoz/register.php";  //server url for register
        String login_url="http://hugejobs99.com/firoz/login.php";   //server url for login

        String method=params[0];
        if(method.equals("register"))
        {
            String name=params[1];
            String user_name=params[2];
            String user_pass=params[3];

            try {
                URL url=new URL(reg_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();
                IS.close();

                return "Registration success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(method.equals("login"))
        {
            String login_name=params[1];
            String login_pass=params[2];

            URL url= null;
            try {
                url = new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data= URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String response="";
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    response+=line;
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();

                return response;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registration success..."))
        {
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
        else
        {
            ad.dismiss();
            alertDialog.setMessage(result);
            alertDialog.show();
        }

    }
}
