import enstabretagne.base.logger.ToRecord;

import java.util.Random;

@ToRecord(name = "Chien")
public class Dog {
    public String name;
    public int age;

    public Dog() {
        Random r = new Random();
        age = r.nextInt();
        name = "Dog" + age;
    }

    @ToRecord(name = "Age")
    public int getAge() {
        return age;
    }

    @ToRecord(name = "Nom")
    public String getName() {
        return name;
    }
}
