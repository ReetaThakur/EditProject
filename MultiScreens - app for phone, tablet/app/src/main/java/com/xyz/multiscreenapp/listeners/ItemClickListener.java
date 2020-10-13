package com.xyz.multiscreenapp.listeners;

import com.xyz.multiscreenapp.model.VersionModel;

public interface ItemClickListener {

    void onRecyclerViewItemClicked(VersionModel versionModel, int position);
}
