import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class formPanel extends JPanel {
    public formPanel() {
        init();
    }
    public void init() {
        this.setLayout(new BorderLayout());

        JPanel upperPanel = new JPanel();


        upperPanel.setLayout(new GridBagLayout());
        JLabel taskName = new JLabel("Task Name:");
        JLabel assignedTo = new JLabel("Assigned To:");
        JTextField taskField = new JTextField(20);
        JTextField assignedField = new JTextField(20);
        JButton addBtn = new JButton("Add");

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);
        c.gridx = 0;
        c.gridy = 0;
        upperPanel.add(taskName, c);
        c.gridx = 1;
        c.gridwidth = 2;
        upperPanel.add(taskField, c);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        upperPanel.add(assignedTo, c);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 2;
        upperPanel.add(assignedField, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        upperPanel.add(addBtn, c);

        this.add(upperPanel, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();

        d.fill = GridBagConstraints.BOTH;
        d.gridx = 0;
        d.gridy = 0;
        listPanel.add(new JLabel("To Do"), d);
        d.gridx = 2;
        listPanel.add(new JLabel("Doing"), d);
        d.gridx = 4;
        listPanel.add(new JLabel("Done"), d);

        ToDoModelList toDoModelList = new ToDoModelList();
        DoingModelList doingModelList = new DoingModelList();
        DoneModelList doneModelList = new DoneModelList();

        JList todoList = new JList(toDoModelList);
        JList doingList = new JList(doingModelList);
        JList doneList = new JList(doneModelList);


        d.gridx = 0;
        d.gridy = 1;
        d.gridheight = 4;
        listPanel.add(new JScrollPane(todoList), d);
        d.gridx = 2;
        d.gridy = 1;
        d.gridheight = 4;
        listPanel.add(new JScrollPane(doingList), d);
        d.gridx = 4;
        d.gridy = 1;
        d.gridheight = 4;
        listPanel.add(new JScrollPane(doneList), d);


        JButton clr1 = new JButton("Clear");
        JButton clr2 = new JButton("Clear");
        JButton clr3 = new JButton("Clear");

        d.gridx = 0;
        d.gridy = 5;
        d.gridheight = 1;
        listPanel.add(clr1, d);
        d.gridx = 2;
        d.gridy = 5;
        d.gridheight = 1;
        listPanel.add(clr2, d);
        d.gridx = 4;
        d.gridy = 5;
        d.gridheight = 1;
        listPanel.add(clr3, d);

        JButton moveRight1 = new JButton(">");
        JButton moveLeft1 = new JButton("<");
        JButton moveRight2 = new JButton(">");
        JButton moveLeft2 = new JButton("<");

        d.gridx = 1;
        d.gridy = 1;
        listPanel.add(moveRight1, d);
        d.gridx = 1;
        d.gridy = 2;
        listPanel.add(moveLeft1, d);
        d.gridx = 3;
        d.gridy = 1;
        listPanel.add(moveRight2, d);
        d.gridx = 3;
        d.gridy = 2;
        listPanel.add(moveLeft2, d);

        this.add(listPanel, BorderLayout.CENTER);

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taskField.getText().isBlank() || assignedField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Please enter the full details to add.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    String taskName = taskField.getText();
                    String assignedName = assignedField.getText();
                    toDoModelList.add(taskName, assignedName);
                    taskField.setText("");
                    assignedField.setText("");

                    taskField.requestFocus();
                    assignedField.requestFocus();
                    todoList.updateUI();
                }
            }
        });
        moveRight1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> taskList= todoList.getSelectedValuesList();
                if(todoList.getSelectedValuesList().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a task to move.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    for(int i = taskList.size(); i > 0; i--) {
                        Task newTask = taskList.get(i -1);
                        doingModelList.add(newTask.getTaskName(), newTask.getAssignedTo());
                    }
                    int[] indexArray = todoList.getSelectedIndices();
                    toDoModelList.remove(indexArray);

                    todoList.clearSelection();
                    todoList.updateUI();
                    doingList.updateUI();
                }
            }
        });
        moveLeft1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> taskList= doingList.getSelectedValuesList();
                if(doingList.getSelectedValuesList().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a task to move.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    for(int i = taskList.size(); i > 0; i--) {
                        Task newTask = taskList.get(i -1);
                        toDoModelList.add(newTask.getTaskName(), newTask.getAssignedTo());
                    }
                    int[] indexArray = doingList.getSelectedIndices();
                    doingModelList.remove(indexArray);

                    todoList.updateUI();
                    doingList.clearSelection();
                    doingList.updateUI();
                }
            }
        });
        moveRight2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> taskList= doingList.getSelectedValuesList();
                if(doingList.getSelectedValuesList().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a task to move.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    for(int i = taskList.size(); i > 0; i--) {
                        Task newTask = taskList.get(i -1);
                        doneModelList.add(newTask.getTaskName(), newTask.getAssignedTo());
                    }
                    int[] indexArray = doingList.getSelectedIndices();
                    doingModelList.remove(indexArray);

                    doingList.clearSelection();
                    doingList.updateUI();
                    doneList.updateUI();
                }
            }
        });
        moveLeft2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Task> taskList= doneList.getSelectedValuesList();
                if(doneList.getSelectedValuesList().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select a task to move.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    for(int i = taskList.size(); i > 0; i--) {
                        Task newTask = taskList.get(i -1);
                        doingModelList.add(newTask.getTaskName(), newTask.getAssignedTo());
                    }
                    int[] indexArray = doneList.getSelectedIndices();
                    doneModelList.remove(indexArray);

                    doneList.clearSelection();
                    doneList.updateUI();
                    doingList.updateUI();
                }
            }
        });

        clr1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toDoModelList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The section you are trying to clear is already empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    toDoModelList.clear();
                }

                todoList.updateUI();
            }
        });
        clr2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(doingModelList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The section you are trying to clear is already empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    doingModelList.clear();
                }
                doingList.updateUI();
            }
        });
        clr3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(doneModelList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The section you are trying to clear is already empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    doneModelList.clear();
                }
                doneList.updateUI();
            }
        });
    }
}