package com.example.listviewdemo;

import android.os.AsyncTask;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Asycdata extends AsyncTask<String, String, List<Repository>> {

    private final RepositoryListListener repositoryListListener;

    public Asycdata(RepositoryListListener repositoryListListener) {
        this.repositoryListListener = repositoryListListener;
    }

    @Override
    protected List<Repository> doInBackground(String... strings) {
        String jsonurl = strings[0];
        Httphandler httphandler = new Httphandler();
        String json = httphandler.makeRequest(jsonurl);

        List<Repository> repositories = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String repositoryName = jsonObject.getString("full_name");
                JSONObject ownerObject = jsonObject.getJSONObject("owner");
                String ownerName = ownerObject.getString("login");

                if (!TextUtils.isEmpty(repositoryName) && !TextUtils.isEmpty(ownerName)) {
                    repositories.add(new Repository(repositoryName, ownerName));
                }
            }

        } catch (JSONException e) {
            repositoryListListener.onRepositoriesFetchFailed(e);
        }

        return repositories;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<Repository> repositories) {
        super.onPostExecute(repositories);
        repositoryListListener.onRepositoriesFetched(repositories);
    }
}
