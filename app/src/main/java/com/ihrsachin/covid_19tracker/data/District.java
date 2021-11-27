package com.ihrsachin.covid_19tracker.data;

public class District {
    private String district_name;

    private int total_active;
    private int total_cured;
    private int total_death;

    private int day_active;
    private int day_cured;
    private int day_death;

    public District(String district_name, int total_active, int total_cured, int total_death, int day_active, int day_cured, int day_death) {
        this.district_name = district_name;
        this.total_active = total_active;
        this.total_cured = total_cured;
        this.total_death = total_death;
        this.day_active = day_active;
        this.day_cured = day_cured;
        this.day_death = day_death;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public int getTotal_active() {
        return total_active;
    }

    public void setTotal_active(int total_active) {
        this.total_active = total_active;
    }

    public int getTotal_cured() {
        return total_cured;
    }

    public void setTotal_cured(int total_cured) {
        this.total_cured = total_cured;
    }

    public int getTotal_death() {
        return total_death;
    }

    public void setTotal_death(int total_death) {
        this.total_death = total_death;
    }

    public int getDay_active() {
        return day_active;
    }

    public void setDay_active(int day_active) {
        this.day_active = day_active;
    }

    public int getDay_cured() {
        return day_cured;
    }

    public void setDay_cured(int day_cured) {
        this.day_cured = day_cured;
    }

    public int getDay_death() {
        return day_death;
    }

    public void setDay_death(int day_death) {
        this.day_death = day_death;
    }
}
