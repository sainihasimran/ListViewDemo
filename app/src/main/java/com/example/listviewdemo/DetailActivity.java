package com.example.listviewdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_REPOSITORY = "KEY_REPOSITORY";

    public static Intent getCallingIntent(Context context, Repository repository) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_REPOSITORY, repository);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        TextView repositoryNameTextView = findViewById(R.id.repository_name);
        TextView repositoryOwnerTextView = findViewById(R.id.repository_owner);
        TextView repositoryDescriptionTextView = findViewById(R.id.repository_description);
        TextView isSiteAdminTextView = findViewById(R.id.repository_site_admin);
        TextView isForkedTextView = findViewById(R.id.repository_fork);

        Repository repository = getIntent().getExtras().getParcelable(KEY_REPOSITORY);
        repositoryNameTextView.setText(getString(R.string.repository_name, repository.getName()));
        repositoryOwnerTextView.setText(getString(R.string.repository_owner, repository.getOwner()));
        repositoryDescriptionTextView.setText(getString(R.string.repository_description, repository.getDescription()));
        isSiteAdminTextView.setText(getString(R.string.is_site_admin, repository.isSiteAdmin()));
        isForkedTextView.setText(getString(R.string.is_forked, repository.isFork()));
    }
}
