/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.entities;

import java.util.Objects;

/**
 *
 * @author khair
 */
public class Address {
    private int id;
    private int userId;
    private String name;
    private String address;
    private String postal;
    private String phoneLiv;

    public Address(int id, int userId, String name, String address, String postal, String phoneLiv) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.postal = postal;
        this.phoneLiv = phoneLiv;
    }

    public Address(int userId, String name, String address, String postal, String phoneLiv) {
		super();
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.postal = postal;
		this.phoneLiv = phoneLiv;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPhoneLiv() {
        return phoneLiv;
    }

    public void setPhoneLiv(String phoneLiv) {
        this.phoneLiv = phoneLiv;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postal='" + postal + '\'' +
                ", phoneLiv='" + phoneLiv + '\'' +
                '}';
    }
}

