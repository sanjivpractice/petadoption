package com.sanjiv.exercise.petadoption.repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Sanjiv on 8/17/18.
 */
public class SetStuff {
    public static void main(String[] args) {

        // Create two sets.
        Set s1 = new HashSet();
        s1.add("Ian Darwin");
        s1.add("Bill Dooley");
        s1.add("Jesse James");

        Set s2 = new HashSet();
        s2.add("Ian Darwin");
        s2.add("Doolin' Dalton");

        Set union = new TreeSet(s1);
        union.addAll(s2);    // now contains the union

        print("union", union);

        Set intersect = new TreeSet(s1);
        intersect.retainAll(s2);

        print("intersection", intersect);

    }

    protected static void print(String label, Collection c) {

        System.out.println("--------------" + label + "--------------");

        Iterator it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
