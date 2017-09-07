package lemon.clown.utils.algorithm.misc.shuffle;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FastRandomShuffleTest {
    @Test
    public void getShuffleOrders() throws Exception {
        int[] targets = new FastRandomShuffle<Integer>().getShuffleOrders(10);
        System.out.println(Arrays.toString(targets));
    }

    @Test
    public void shuffle() throws Exception {
        class Person {
            String name;
            Person(String name) {
                this.name = name;
            }
        }

        Person a = new Person("a");
        Person b = new Person("b");
        Person c = new Person("c");
        Person d = new Person("d");
        Person e = new Person("e");
        Person f = new Person("f");
        Person g = new Person("g");
        Person h = new Person("h");
        Person i = new Person("i");

        Person[] persons = new Person[] { a, b, c, d, e, f, g, h, i };
        new FastRandomShuffle<Person>().shuffle(persons);

        System.out.print("[ ");
        boolean first = true;
        for(Person person: persons) {
            if( first ) first = false;
            else System.out.print(", ");
            System.out.print(person.name);
        }
        System.out.println(" ]");
    }

}