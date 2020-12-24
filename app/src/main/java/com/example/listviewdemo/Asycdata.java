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
                JSONObject repositoryObject = jsonArray.getJSONObject(i);
                String repositoryName = repositoryObject.getString("full_name");
                String description = repositoryObject.getString("description");
                boolean fork = repositoryObject.getBoolean("fork");

                JSONObject ownerObject = repositoryObject.getJSONObject("owner");
                String ownerName = ownerObject.getString("login");
                boolean siteAdmin = ownerObject.getBoolean("site_admin");

                if (!TextUtils.isEmpty(repositoryName) && !TextUtils.isEmpty(ownerName)) {
                    repositories.add(new Repository(repositoryName, ownerName, description, fork, siteAdmin));
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
