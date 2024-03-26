package com.example.readjsonfilefromweb.models;

public class Animal {
    private int id,longevity;
    private String name, env, ate;

    public Animal(int id, String name,int longevity, String env, String ate) {
        this.id = id;
        this.longevity = longevity;
        this.name = name;
        this.env = env;
        this.ate = ate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLongevity() {
        return longevity;
    }

    public void setLongevity(int longevity) {
        this.longevity = longevity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getAte() {
        return ate;
    }

    public void setAte(String ate) {
        this.ate = ate;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", longevity=" + longevity +
                ", name='" + name + '\'' +
                ", env='" + env + '\'' +
                ", ate='" + ate + '\'' +
                '}';
    }
}
