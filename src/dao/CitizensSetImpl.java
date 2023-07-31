package dao;

import model.Person;

import java.util.*;

public class CitizensSetImpl implements Citizens {
    private static Comparator<Person> lastNameComparator = Comparator.comparing(Person::getLastName).thenComparingInt(Person::getId);
    private static Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge).thenComparingInt(Person::getId);
    private Set<Person> idList;


    public CitizensSetImpl() {
        idList = new TreeSet<>();
    }

    public CitizensSetImpl(List<Person> citizens) {
        this();
        for (Person person : citizens) {
            add(person);
        }
    }

    //O(1)
    @Override
    public boolean add(Person person) {
        if (person != null) {
            return idList.add(person);

        }
        return false;
    }

    //O(1)
    @Override
    public boolean remove(int id) {
        Person person = find(id);
        if (person != null) {
            idList.remove(person);
            return true;
        }
        return false;
    }

    //O(n)
    @Override
    public Person find(int id) {
        for (Person p : idList) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }

    //O(n)
    @Override
    public Iterable<Person> find(int minAge, int maxAge) {
        List<Person> result = new ArrayList<>();
        for (Person person : idList) {
            if (person.getAge() >= minAge && person.getAge() <= maxAge) {
                result.add(person);
            }
        }
        return result;
    }

    //O(n)
    @Override
    public Iterable<Person> find(String lastName) {
        List<Person> result = new ArrayList<>();
        for (Person person : idList) {
            if (person.getLastName().equals(lastName)) {
                result.add(person);
            }
        }
        return result;
    }

    //O(n)
    @Override
    public Iterable<Person> getAllPersonsSortedById() {
        return new ArrayList<>(idList);
    }

    // O(n(log(n)))
    @Override
    public Iterable<Person> getAllPersonsSortedByLastName() {
        List<Person> sortedPersons = new ArrayList<>(idList);
        sortedPersons.sort(lastNameComparator);
        return sortedPersons;
    }

    // O(n(log(n)))
    @Override
    public Iterable<Person> getAllPersonsSortedByAge() {
        List<Person> sortedPersons = new ArrayList<>(idList);
        sortedPersons.sort(ageComparator);
        return sortedPersons;
    }

    //O(n)
    @Override
    public int size() {
        return idList.size();
    }
}
