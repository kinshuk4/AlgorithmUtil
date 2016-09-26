package com.vaani.algo.compete.cc150.chap3;

import java.util.LinkedList;

/**
 * An animal shelter holds only dogs and cats, and operates on a strictly
 * "first in, first out" basis. People must adopt either the "oldest" (based on
 * arrival time) of all animals at the shelter, or they can select whether they
 * would prefer a dog or a cat (and will receive the oldest animal of that
 * type). They cannot select which specific animal they would like. Create the
 * data structures to maintain this system and implement operations such as
 * enqueue, dequeueAny, dequeueDog and dequeueCat. You may use the built-in
 * LinkedList data structure.
 */
// space: all O(1)
// time: enqueue O(1), dequeue O(1), dequeueCat O(n), dequeueDog O(n)
public class Question7 {

    private LinkedList<Animal> animals = new LinkedList<Animal>();

    public void enqueue(Animal animal) {
        // write implementation here
        animals.add(animal);
    }

    public Animal dequeueDog() {
        // write implementation here
        if (animals.size() == 0) {
            return null;
        }
        Animal selected = null;
        int index = -1;
        for (int i = 0; i < animals.size(); ++i) {
            if (animals.get(i) instanceof Dog) {
                index = i;
                selected = animals.get(i);
                break;
            }
        }
        animals.remove(index);
        return selected;
    }

    public Animal dequeueCat() {
        // write implementation here
        if (animals.size() == 0) {
            return null;
        }
        Animal selected = null;
        int index = -1;
        for (int i = 0; i < animals.size(); ++i) {
            if (animals.get(i) instanceof Cat) {
                index = i;
                selected = animals.get(i);
                break;
            }
        }
        animals.remove(index);
        return selected;
    }

    public Animal dequeueAny() {
        // write implementation here
        if (animals.size() != 0) {
            Animal selected = animals.get(0);
            animals.remove(0);
            return selected;
        }
        return null;
    }

    public static abstract class Animal {
        public int id;

        public Animal(int id) {
            this.id = id;
        }
    }

    public static class Dog extends Animal {
        public Dog(int id) {
            super(id);
        }
    }

    public static class Cat extends Animal {
        public Cat(int id) {
            super(id);
        }
    }

}

