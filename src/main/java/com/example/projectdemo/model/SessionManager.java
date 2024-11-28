package com.example.projectdemo.model;

import java.time.LocalDate;

public class SessionManager {
    private static User currentUser;
    private static LocalDate startDate;
    private static LocalDate endDate;
    private static Location location;

    public static void login(User user) {
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setStartDate(LocalDate startDate){
        SessionManager.startDate = startDate;
    }
    public static void setEndDate(LocalDate endDate){
        SessionManager.endDate = endDate;
    }
    public static void setLocation(Location location){SessionManager.location = location;}

    public static LocalDate getStartDate(){
        return startDate;
    }
    public static LocalDate getEndDate(){
        return endDate;
    }
    public static Location getLocation(){return location;}
}
