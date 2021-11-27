package com.ihrsachin.covid_19tracker.data;

import org.json.JSONObject;

public class State {
     private String state_name;

     private int total_confirmed;
     private int total_cured;
     private int total_death;

     private int day_confirm;
     private int day_cured;
     private int day_death;

     private JSONObject districts;

     public State(String name, int total_active, int total_cured, int total_death, int day_active, int day_cured, int day_death, JSONObject districts) {
          this.state_name = name;
          this.total_confirmed = total_active;
          this.total_cured = total_cured;
          this.total_death = total_death;
          this.day_confirm = day_active;
          this.day_cured = day_cured;
          this.day_death = day_death;
          this.districts = districts;
     }

     public String getState_name() {
          return state_name;
     }

     public void setState_name(String state_name) {
          this.state_name = state_name;
     }

     public int getTotal_active() {
          return total_confirmed;
     }

     public void setTotal_active(int total_active) {
          this.total_confirmed = total_active;
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
          return day_confirm;
     }

     public void setDay_active(int day_active) {
          this.day_confirm = day_active;
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

     public JSONObject getDistricts() {
          return districts;
     }

     public void setDistricts(JSONObject districts) {
          this.districts = districts;
     }
}
