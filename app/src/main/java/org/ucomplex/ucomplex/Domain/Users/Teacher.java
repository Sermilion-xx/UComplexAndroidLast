package org.ucomplex.ucomplex.Domain.Users;

import java.util.List;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 06/12/2016.
 * Project: uComplex_v_2
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public class Teacher extends User {

    private int post;
    private String experience;
    private String dep_experience;
    private String courses;
    private int rank;
    private String academic_awards;
    private String upqualification;
    private int degree;
    private String bio;
    private String phone_work;
    private int plan;
    private int fact;
    private int fails;
    private String activity_update;
    private int selection;
    private String department;
    private int closed;
    private int agent;
    private List<Integer> departments;

    public Teacher() {
        super();
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDep_experience() {
        return dep_experience;
    }

    public void setDep_experience(String dep_experience) {
        this.dep_experience = dep_experience;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getAcademic_awards() {
        return academic_awards;
    }

    public void setAcademic_awards(String academic_awards) {
        this.academic_awards = academic_awards;
    }

    public String getUpqualification() {
        return upqualification;
    }

    public void setUpqualification(String upqualification) {
        this.upqualification = upqualification;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhone_work() {
        return phone_work;
    }

    public void setPhone_work(String phone_work) {
        this.phone_work = phone_work;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public int getFact() {
        return fact;
    }

    public void setFact(int fact) {
        this.fact = fact;
    }

    public int getFails() {
        return fails;
    }

    public void setFails(int fails) {
        this.fails = fails;
    }

    public String getActivity_update() {
        return activity_update;
    }

    public void setActivity_update(String activity_update) {
        this.activity_update = activity_update;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getAgent() {
        return agent;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public List<Integer> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Integer> departments) {
        this.departments = departments;
    }
}
