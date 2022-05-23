package com.client.demo;

public class Friendship {
    private int id;
    private int idFriend1;
    private int idFriend2;

    public Friendship(int idFriend1, int idFriend2)
    {
        this.idFriend1 = idFriend1;
        this.idFriend2 = idFriend2;
    }

    public int getIdFriend1() {
        return idFriend1;
    }

    public void setIdFriend1(int idFriend1) {
        this.idFriend1 = idFriend1;
    }

    public int getIdFriend2() {
        return idFriend2;
    }

    public void setIdFriend2(int idFriend2) {
        this.idFriend2 = idFriend2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
