package com.example.listviewdemo;

import java.util.List;

public interface RepositoryListListener {

    void onRepositoriesFetched(List<Repository> repositories);

    void onRepositoriesFetchFailed(Exception e);
}
