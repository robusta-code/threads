package concurrency.lock.manager;

/**
 * User: Nicolas
 * Date: 13/10/12
 * Time: 22:41
 */
public class Task {

    String name;
    int difficulty;
    Manager manager;
    Employee employee;
    double time;

    public Task(String name, int difficulty, Manager manager, Employee employee) {
        this.name = name;
        this.difficulty = difficulty;
        this.manager = manager;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return name;
    }
}
