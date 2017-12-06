package com.andytsyupa.main;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.Vector;

public class FileTreeFrame extends JFrame {


    private JTree fileTree;
    private FileSystemModel fileSystemModel;
    private JTextArea fileDetailsTextArea = new JTextArea();

    public FileTreeFrame(String directory) {
        super("Файловий Менеджер");
        fileDetailsTextArea.setEditable(false);
        fileSystemModel = new FileSystemModel(new File(directory));
        fileTree = new JTree(fileSystemModel);
        fileTree.setEditable(true);
        fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent event) {
                File file = (File) fileTree.getLastSelectedPathComponent();
                fileDetailsTextArea.setText(getFileDetails(file));
            }
        });


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, new JScrollPane(
                fileTree), new JScrollPane(fileDetailsTextArea));

        this.setLayout(new BorderLayout());
        this.add(splitPane, BorderLayout.CENTER);

        //getContentPane().add(splitPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(840, 680);

        JPanel p = new JPanel();

        JButton buttonn = new JButton("Дата");

        JTextField textField = new JTextField("2017*12*06");
        JTextField textField2 = new JTextField("vewvdv");

        buttonn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(textField.getText());
            }
        });

        textField.setPreferredSize( new Dimension( 100, 24 ) );
        textField2.setPreferredSize( new Dimension( 100, 24 ) );

        p.add(buttonn);
        p.add(textField);
        p.add(textField2);
        this.add(p, BorderLayout.NORTH);
        //textField.setSize(30, 10);
//        getContentPane().add(textField2);
        //textField2.setSize(30, 10);

        setVisible(true);


    }


    private String getFileDetails(File file) {
        if (file == null)
            return "";
        StringBuffer buffer = new StringBuffer();
        buffer.append("Назва: " + file.getName() + "\n");
        buffer.append("Шлях: " + file.getPath() + "\n");
        buffer.append("Об'єм файлів: " + file.length() + "\n");
        return buffer.toString();
    }


    public void process(String startDate, String endDate){

    }

    public static void main(String args[]) {
        new FileTreeFrame("d:\\");
    }

    class FileSystemModel implements TreeModel {


        private File root;

        private Vector listeners = new Vector();


        public FileSystemModel(File rootDirectory) {
            root = rootDirectory;
        }

        public Object getRoot() {
            return root;
        }

        public Object getChild(Object parent, int index) {
            File directory = (File) parent;
            String[] children = directory.list();
            return new TreeFile(directory, children[index]);
        }

        public int getChildCount(Object parent) {
            File file = (File) parent;
            if (file.isDirectory()) {
                String[] fileList = file.list();
                if (fileList != null)
                    return file.list().length;
            }
            return 0;
        }

        public boolean isLeaf(Object node) {
            File file = (File) node;
            return file.isFile();
        }

        public int getIndexOfChild(Object parent, Object child) {
            File directory = (File) parent;
            File file = (File) child;
            String[] children = directory.list();
            for (int i = 0; i < children.length; i++) {
                if (file.getName().equals(children[i])) {
                    return i;
                }
            }
            return -1;

        }

        public void valueForPathChanged(TreePath path, Object value) {
            File oldFile = (File) path.getLastPathComponent();
            String fileParentPath = oldFile.getParent();
            String newFileName = (String) value;
            File targetFile = new File(fileParentPath, newFileName);
            oldFile.renameTo(targetFile);
            File parent = new File(fileParentPath);
            int[] changedChildrenIndices = {getIndexOfChild(parent, targetFile)};
            Object[] changedChildren = {targetFile};
            fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);

        }

        private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
            TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
            Iterator iterator = listeners.iterator();
            TreeModelListener listener = null;
            while (iterator.hasNext()) {
                listener = (TreeModelListener) iterator.next();
                listener.treeNodesChanged(event);
            }
        }


        public void addTreeModelListener(TreeModelListener listener) {
            listeners.add(listener);
        }

        public void removeTreeModelListener(TreeModelListener listener) {
            listeners.remove(listener);
        }

        private class TreeFile extends File {
            public TreeFile(File parent, String child) {
                super(parent, child);
            }

            public String toString() {
                return getName();
            }
        }
    }

}

