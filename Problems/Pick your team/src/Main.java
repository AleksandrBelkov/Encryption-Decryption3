import java.util.Scanner;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        this.algorithm = algorithm; // write your code here
    }

    public Person[] selectPersons(Person[] persons) {
         return algorithm.select(persons); // write your code here
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {

    protected int step;

    public TakePersonsWithStepAlgorithm(int step) {
        this.step = step;// write your code here
    }

    @Override
    public Person[] select(Person[] persons) {
        int stepNumber = persons.length / step;
        if (persons.length % step != 0) {
            ++stepNumber;
        }
        if (step == 1) {
            return persons;
        } else {
            Person[] newList = new Person[stepNumber];
            for (int i = 0; i < stepNumber; i++) {
                newList[i] = persons[i * step];
            }
            return newList;
        }
    }
}


class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {

    protected int count;

    public TakeLastPersonsAlgorithm(int count) {
        this.count = count; // write your code here
    }

    @Override
    public Person[] select(Person[] persons) {
        if (count == 1) {
            return persons;
        } else {
            Person[] newList = new Person[count];
            for (int i = 0; i < count; i++) {
                newList[i] = persons[persons.length - count + i];
            }
            return newList;
        }
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}