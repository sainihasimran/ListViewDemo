package com.example.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class RepositoryAdapter extends ArrayAdapter<Repository> {

    public RepositoryAdapter(@NonNull Context context, @NonNull List<Repository> repositories) {
        super(context, 0, repositories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Repository repository = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_repository, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.repositoryName = convertView.findViewById(R.id.repository_name);
            viewHolder.repositoryOwner = convertView.findViewById(R.id.repository_owner);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.repositoryName.setText(repository.getName());
        viewHolder.repositoryOwner.setText(repository.getOwner());

        return convertView;
    }

    private static final class ViewHolder {
        TextView repositoryName;
        TextView repositoryOwner;
    }
}
