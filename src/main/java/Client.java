import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Client {
    private static int idCounter = 0;

    private final int id;
    private final String name;
    private final int age;
    private final Gender sex;

    public Client(String name, int age, Gender sex) {
        this.id = idCounter++;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
