package com.example.listviewdemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RepositoryListListener {

    private AlertDialog loadingDialog;

    private View rootView;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.rootView);
        listView = findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Repository repository = (Repository) parent.getItemAtPosition(position);
                Intent intent = DetailActivity.getCallingIntent(MainActivity.this, repository);
                MainActivity.this.startActivity(intent);
            }
        });

        loadingDialog = new AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create();

        fetchRepositories();
    }

    private void fetchRepositories() {
        loadingDialog.show();
        loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        new Asycdata(this).execute("https://api.github.com/repositories");
    }

    @Override
    public void onRepositoriesFetched(List<Repository> repositories) {
        RepositoryAdapter repositoryAdapter = new RepositoryAdapter(this, repositories);
        listView.setAdapter(repositoryAdapter);
        loadingDialog.hide();
    }

    @Override
    public void onRepositoriesFetchFailed(Exception e) {
        Snackbar errorSnackbar = Snackbar.make(rootView, "Failed to fetch repository List", Snackbar.LENGTH_INDEFINITE);
        errorSnackbar.setAction("Retry", v -> fetchRepositories());
        errorSnackbar.show();
        loadingDialog.hide();
    }
}