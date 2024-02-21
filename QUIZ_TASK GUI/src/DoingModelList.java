import javax.swing.*;
import java.util.ArrayList;

public class DoingModelList extends AbstractListModel {
    private ArrayList<Task> tasks;
    public DoingModelList() {
        tasks = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return tasks.size();
    }
    public void add(String task, String assigned) {
        tasks.add(new Task(task, assigned));
    }
    public void remove(int[] array) {
        for(int i = array.length; i > 0; i--) {
            tasks.remove(i-1);
        }
    }
    public void clear() {
        tasks.clear();
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    @Override
    public Object getElementAt(int index) {
        return tasks.get(index);
    }
}
