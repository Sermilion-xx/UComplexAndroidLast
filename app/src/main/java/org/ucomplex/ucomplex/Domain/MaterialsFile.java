package org.ucomplex.ucomplex.Domain;

/**
 * ---------------------------------------------------
 * Created by Sermilion on 30/03/2017.
 * Project: UComplex
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

public final class MaterialsFile {

    private final String id;
    private final int owner;
    private final String name;
    private final String address;
    private final String type;
    private final int size;
    private final String time;
    private final String data;

    public MaterialsFile() {
        this.id = "";
        this.owner = 0;
        this.name = "";
        this.address = "";
        this.type = "";
        this.size = 0;
        this.time = "";
        this.data = "";
    }

    public String getId() {
        return id;
    }

    public int getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getTime() {
        return time;
    }

    public String getData() {
        return data;
    }

}
