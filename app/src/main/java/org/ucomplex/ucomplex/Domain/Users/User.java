package org.ucomplex.ucomplex.Domain.Users;

import org.ucomplex.ucomplex.Domain.Users.role.Role;
import org.ucomplex.ucomplex.Domain.Users.role.RoleBase;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sermilion on 01/11/2016.
 */

public final class User implements UserInterface {

    public static final int USER_TYPE_STUDENT = 4;
    public static final int USER_TYPE_TEACHER = 3;
    public static final int USER_TYPE_STAFF = 0;

    private final int id;
    private final int person;
    private final int type;
    private final String name;
    private final String login;
    private final String password;
    private final String phone;
    private final String email;
    private final String session;
    private final int client;
    private final int role;
    private final int photo;
    private final String statuses;
    private final String code;
    private final List<? extends Role> roles;
    private final int mobile;
    private final BlackList black;
    private final FriendList friend;

    public User(UserBuilder builder) {
        this.id = builder.id;
        this.person = builder.person;
        this.type = builder.type;
        this.name = builder.name;
        this.login = builder.login;
        this.password = builder.password;
        this.phone = builder.phone;
        this.photo = builder.photo;
        this.code = builder.code;
        this.roles = builder.roles;
        this.role = builder.role;
        this.mobile = builder.mobile;
        this.email = builder.email;
        this.session = builder.session;
        this.client = builder.client;
        this.friend = builder.friend;
        this.black = builder.black;
        this.statuses = builder.statuses;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getPerson() {
        return person;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getSession() {
        return session;
    }

    public String getStatuses() {
        return statuses;
    }

    public int getClient() {
        return client;
    }

    public int getRole() {
        return role;
    }

    @Override
    public int getPhoto() {
        return photo;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public List<? extends Role> getRoles() {
        return roles;
    }

    public int getMobile() {
        return mobile;
    }

    public FriendList getIsFriend() {
        return friend;
    }

    public BlackList getBlack() {
        return black;
    }

    public static class UserBuilder {
        int id;
        int person;
        int type;
        String name;
        String login;
        String password;
        String phone;
        String email;
        String session;
        int client;
        int role;
        int photo;
        String statuses;
        String code;
        List<? extends Role> roles;
        int mobile;
        FriendList friend;
        BlackList black;

        public User build() {
            return new User(this);
        }

        public UserBuilder id(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder person(int person) {
            this.person = person;
            return this;
        }

        public UserBuilder type(int type) {
            this.type = type;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder statuses(String statuses) {
            this.statuses = statuses;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder role(int role) {
            this.role = role;
            return this;
        }

        public UserBuilder photo(int photo) {
            this.photo = photo;
            return this;
        }

        public UserBuilder code(String code) {
            this.code = code;
            return this;
        }

        public UserBuilder roles(List<? extends Role> roles) {
            this.roles = Collections.unmodifiableList(new ArrayList<>(roles));
            return this;
        }

        public UserBuilder mobile(int mobile) {
            this.mobile = mobile;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder session(String session) {
            this.session = session;
            return this;
        }

        public UserBuilder client(int client) {
            this.client = client;
            return this;
        }

        public UserBuilder friend(FriendList friend) {
            this.friend = friend;
            return this;
        }

        public UserBuilder black(BlackList friend) {
            this.black = friend;
            return this;
        }
    }
}
