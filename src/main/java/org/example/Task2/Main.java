package org.example.Task2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //System.out.println(persons);

        long youngPeople = persons.stream()
                .filter(age -> age.getAge() < 18).count();
        System.out.println(youngPeople);

        List<String> personsArmy = persons.stream()
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() > 18 && age.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(personsArmy);

        //первый долгий путь нахождения списка работяг
        List<String> jobPeopleMan = persons.stream()
                .filter(edu -> edu.getEducation() == Education.HIGHER)
                .filter(sex -> sex.getSex() == Sex.MAN)
                .filter(age -> age.getAge() > 18 && age.getAge() < 65)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .toList();

        List<String> jobPeopleWoman = persons.stream()
                .filter(edu -> edu.getEducation() == Education.HIGHER)
                .filter(sex -> sex.getSex() == Sex.WOMAN)
                .filter(age -> age.getAge() > 18 && age.getAge() < 60)
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .toList();

        List<String> allJobPeople = new ArrayList<>(jobPeopleMan);
        allJobPeople.addAll(jobPeopleWoman);
        Collections.sort(allJobPeople);
        System.out.println(allJobPeople);

        //второй способ нахождения работяг
        List<String> jobPeople = persons.stream()
                .filter(edu -> edu.getEducation() == Education.HIGHER)
                .filter(value -> {
                    if (value.getSex() == Sex.MAN)
                        return value.getAge() > 18 && value.getAge() < 65;
                    else
                        return value.getAge() > 18 && value.getAge() < 60;
                })
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(jobPeople);

    }
}