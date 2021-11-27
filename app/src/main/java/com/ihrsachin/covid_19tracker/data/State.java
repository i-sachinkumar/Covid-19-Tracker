package com.ihrsachin.covid_19tracker.data;

import org.json.JSONObject;

import java.io.Serializable;

public class State implements Serializable{
     private String state_name;

     private int total_confirmed;
     private int total_cured;
     private int total_death;
     private int total_population;
     private int total_vaccinated1;
     private int total_vaccinated2;

     private int day_confirm;
     private int day_cured;
     private int day_death;
     private int day_vaccinated1;
     private int day_vaccinated2;

     public State(String state_name, int total_confirmed, int total_cured, int total_death, int total_population, int total_vaccinated1, int total_vaccinated2, int day_confirm, int day_cured, int day_death, int day_vaccinated1, int day_vaccinated2) {
          this.state_name = state_name;
          this.total_confirmed = total_confirmed;
          this.total_cured = total_cured;
          this.total_death = total_death;
          this.total_population = total_population;
          this.total_vaccinated1 = total_vaccinated1;
          this.total_vaccinated2 = total_vaccinated2;
          this.day_confirm = day_confirm;
          this.day_cured = day_cured;
          this.day_death = day_death;
          this.day_vaccinated1 = day_vaccinated1;
          this.day_vaccinated2 = day_vaccinated2;
     }

     public String getState_name() {
          return state_name;
     }

     public void setState_name(String state_name) {
          this.state_name = state_name;
     }

     public int getTotal_confirmed() {
          return total_confirmed;
     }

     public void setTotal_confirmed(int total_confirmed) {
          this.total_confirmed = total_confirmed;
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

     public int getTotal_population() {
          return total_population;
     }

     public void setTotal_population(int total_population) {
          this.total_population = total_population;
     }

     public int getTotal_vaccinated1() {
          return total_vaccinated1;
     }

     public void setTotal_vaccinated1(int total_vaccinated1) {
          this.total_vaccinated1 = total_vaccinated1;
     }

     public int getTotal_vaccinated2() {
          return total_vaccinated2;
     }

     public void setTotal_vaccinated2(int total_vaccinated2) {
          this.total_vaccinated2 = total_vaccinated2;
     }

     public int getDay_confirm() {
          return day_confirm;
     }

     public void setDay_confirm(int day_confirm) {
          this.day_confirm = day_confirm;
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

     public int getDay_vaccinated1() {
          return day_vaccinated1;
     }

     public void setDay_vaccinated1(int day_vaccinated1) {
          this.day_vaccinated1 = day_vaccinated1;
     }

     public int getDay_vaccinated2() {
          return day_vaccinated2;
     }

     public void setDay_vaccinated2(int day_vaccinated2) {
          this.day_vaccinated2 = day_vaccinated2;
     }
}
