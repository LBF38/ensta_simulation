/*
 *
 */
package enstabretagne.td_introductif;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO: Auto-generated Javadoc

/**
 * The Class Dog.
 */
public class Dog {


    /**
     * The name.
     */
    public String name;

    /**
     * The age.
     */
    public int age;

    /**
     * The children.
     */
    public List<Dog> children;

    /**
     * Instantiates a new dog.
     */
    public Dog() {
        Random r = new Random();
        age = r.nextInt();
        name = "Dog" + age;
        children = new ArrayList<>();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Dog name =" + name + "[" + age + "]";
    }

}