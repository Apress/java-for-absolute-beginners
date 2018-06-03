package com.apress.bgn.ch4.hierarchy;

/**
 * @author iuliana.cosmina
 * @date 11/04/2018
 * @since 1.0
 */
public abstract class Human {
    public static final int LIFESPAN = 100;

    protected String name;

    protected int age;

    protected float height;

    private Gender gender;

    public Human(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Human(String name, int age, float height, Gender gender) {
        this(name, age, gender);
        this.height = height;
    }

    /**
     * @return time to live
     */
    public abstract int getTimeToLive();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
