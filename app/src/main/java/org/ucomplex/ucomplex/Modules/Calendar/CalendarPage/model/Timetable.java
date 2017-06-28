package org.ucomplex.ucomplex.Modules.Calendar.CalendarPage.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sermilion on 20/12/2015.
 */
public final class Timetable implements Serializable{

    private final Map<Integer, String> teachers;
    private final Map<Integer, String> hours;
    private final Map<Integer, String> rooms;
    private final Map<Integer, String> groups;
    private final Map<Integer, String> subjects;
    private final Map<Integer, List<Map<String, String>>> entries;

    public Timetable() {
        this.teachers = null;
        this.groups =  null;
        this.hours = null;
        this.rooms = null;
        this.subjects = null;
        this.entries = null;
    }

    public Map<Integer, String> getTeachers() {
        return teachers;
    }

    public Map<Integer, String> getGroups() {
        return groups;
    }

    public Map<Integer, String> getHours() {
        return hours;
    }

    public Map<Integer, String> getRooms() {
        return rooms;
    }

    public Map<Integer, String> getSubjects() {
        return subjects;
    }

    public Map<Integer, List<Map<String, String>>> getEntries() {
        if (entries == null) {
            return new HashMap<>();
        }
        return entries;
    }
}
