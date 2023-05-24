package com.example.pmd_l2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "episode")
public class Episode {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idepisode")
    public String id;
    @ColumnInfo(name = "episode")
    public String episode;
    @ColumnInfo(name = "air_date")
    public String air_date;
    @ColumnInfo(name = "name")
    public String name;

    public Episode(@NonNull String id, String name, String air_date, String episode) {
        this.id = id;
        this.episode = episode;
        this.air_date = air_date;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
