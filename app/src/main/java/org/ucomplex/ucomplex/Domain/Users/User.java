package org.ucomplex.ucomplex.Domain.Users;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Sermilion on 01/11/2016.
 */

public class User implements UserInterface{

    private int id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private boolean friend;
    private int role;
    private int person;
    private Bitmap photoBitmap;
    private String bitmapUriString;
    private int photo;
    private String code;
    private int client;
    private int type;
    private String session;
    private String name;
    private List<Role> roles;
    private int mobile;
    private long online;

    public User() {

    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public String getBitmapUriString() {
        return bitmapUriString;
    }

    public int getMobile() {
        return mobile;
    }

    public long getOnline() {
        return online;
    }

    public void setOnline(long online) {
        this.online = online;
    }

    public String getBitmapUriStringFromUri(Uri bitmapUri){
        return bitmapUri.toString();
    }

    public Uri getBitmapUriFromUriString(){
        if(bitmapUriString!=null){
            return Uri.parse(bitmapUriString);
        } else {
            return null;
        }

    }

    public void addRole(Role role){
        roles.add(role);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public Bitmap getPhotoBitmap() {
        return photoBitmap;
    }

    public void setPhotoBitmap(Bitmap photoBitmap) {
        this.photoBitmap = photoBitmap;
    }


    @Override
    public void setBitmapUriString(String bitmapUriString) {
        this.bitmapUriString = bitmapUriString;
    }

    @Override
    public int getPhoto() {
        return photo;
    }

    @Override
    public void setPhoto(int photo) {
        this.photo = photo;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

}
