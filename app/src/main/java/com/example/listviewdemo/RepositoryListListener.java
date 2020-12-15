package com.example.listviewdemo;

import java.util.List;

interface RepositoryListListener {

    void onRepositoriesFetched(List<Repository> repositories);

    void onRepositoriesFetchFailed(Exception e);
}
