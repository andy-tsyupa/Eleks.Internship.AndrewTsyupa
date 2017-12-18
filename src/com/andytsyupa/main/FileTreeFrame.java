package com.andytsyupa.main;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static Examples.Main.listFilesForFolder;
public class FileTreeFrame extends JFrame {


    private JTree fileTree;
    private JTextField directoryPath = new JTextField ();
    private JTextArea fileDetailsTextArea=new JTextArea ();
    private JTextArea fileDetailsTextArea2=new JTextArea ();
    private JSplitPane splitPane;


    private FileSystemModel fileSystemModel;
    public void reload(String directory) throws Exception {
        File dir = new File ( directory );
        boolean pathCorr = dir.exists () && dir.isDirectory ();
        if (!pathCorr){
            throw new Exception ( "Not correct path" );
        }

        fileSystemModel=new FileSystemModel (  dir);
        fileTree=new JTree ( fileSystemModel );
        splitPane.setLeftComponent ( new JScrollPane ( fileTree ));
        splitPane.setDividerLocation ( 0.5d );
        fileTree.setEditable ( true );
        fileTree.addTreeSelectionListener ( new TreeSelectionListener () {
            public void valueChanged(TreeSelectionEvent event) {
                File file=(File) fileTree.getLastSelectedPathComponent ();
                fileDetailsTextArea.setText ( getFileDetails ( file ) );

            }
        } );
        fileTree.getSelectionModel ().setSelectionMode ( TreeSelectionModel.CONTIGUOUS_TREE_SELECTION );
    }


    public FileTreeFrame(String directory) {
        super ( "Файловий Менеджер" );
        fileDetailsTextArea.setEditable ( false );
        JSplitPane splitPaneTextArea=new JSplitPane ( JSplitPane.VERTICAL_SPLIT, true, fileDetailsTextArea, fileDetailsTextArea2 );
        splitPaneTextArea.setResizeWeight(.5d);
        splitPane=new JSplitPane ( JSplitPane.HORIZONTAL_SPLIT, true, null, splitPaneTextArea );
        try {
            reload(directory);
        } catch (Exception e) {
            e.printStackTrace ();
        }


        this.setLayout ( new BorderLayout () );
        this.add ( splitPane, BorderLayout.CENTER );
        getContentPane().add(splitPane);
        setDefaultCloseOperation ( EXIT_ON_CLOSE );
        setSize ( 840, 680 );

        JPanel p=new JPanel ();
        JButton buttonn=new JButton ( "Опрацювати" );
        JButton buttonn2=new JButton ( "Видалити" );
        JTextField textField=new JTextField ( "2017-12-17" );
        JTextField textField2=new JTextField ( "2017-12-30" );

        buttonn.addActionListener ( new ActionListener () {
            public void actionPerformed(ActionEvent e) {
               // System.out.println ( textField.getText () + " - " + textField2.getText () );
                try {
                    proces ( textField.getText (), textField2.getText () );

                    reload ( directoryPath.getText () );
                } catch (Exception e1) {
                    e1.printStackTrace ();
                }
            }


        } );


        buttonn2.addActionListener ( new ActionListener () {
            public TreePath entry;
            public void actionPerformed(ActionEvent e) {
                TreePath[] list=fileTree.getSelectionPaths ();
                assert list != null;
                daniP ();
                for (TreePath entry : list) {
                    recursivDelete ( (File) entry.getLastPathComponent () );
                }
                daniK ();
            }

        } );

        textField.setPreferredSize ( new Dimension ( 70, 26 ) );
        textField2.setPreferredSize ( new Dimension ( 70, 26 ) );


        directoryPath.setText ( "D:/test" );
        directoryPath.setPreferredSize ( new Dimension ( 250,26 ) );
        p.add ( directoryPath );
        p.add ( buttonn );
        p.add ( textField );
        p.add ( textField2 );
        p.add ( buttonn2 );

        this.add ( p, BorderLayout.NORTH );
        setVisible ( true );

    }


    public static void daniP() {

        String use="<user>User</user>";
        DateFormat dateFormat=new SimpleDateFormat ( "yyyy/MM/dd HH:mm" );
        Date date=new Date ();
        try {
            String s="<del>" + "\n" + use + "\n" + "<files>" + "\n";
            Path writer=Files.write ( Paths.get ( "D:\\\\Projects\\\\pracktika\\\\src\\\\com\\\\andytsyupa\\\\main\\\\FJava2.xml" ), s.getBytes (), StandardOpenOption.APPEND );
        } catch (IOException e) {

        }
    }

    public static void recursivDelete(File file) {
        DateFormat dateFormat=new SimpleDateFormat ( "yyyy/MM/dd HH:mm" );
        Date date=new Date ();
        if ( !file.exists () ) return;

        if ( file.isDirectory () ) {
            for (File f : file.listFiles ()) {
                recursivDelete ( f );
            }
        }


        if ( file.isFile () ) {



            try {
                String sі="<file>" + "\n" + "<filename>" + file.getAbsolutePath () + "</filename>" + "\n" + "<date>" + date + "</date>" + "\n" + "</file>" + "\n";
                Path write=Files.write ( Paths.get ( "D:\\\\Projects\\\\pracktika\\\\src\\\\com\\\\andytsyupa\\\\main\\\\FJava2.xml" ), sі.getBytes (), StandardOpenOption.APPEND );
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            //* Видалення   file.delete ();
            System.out.println ( "Видалення" + file.getAbsoluteFile () );
        }
    }

    public static void daniK() {
        try {
            String sk="</files>" + "\n" + "</del>" + "\n" + " " + "\n";
            Path writers=Files.write ( Paths.get ( "D:\\\\Projects\\\\pracktika\\\\src\\\\com\\\\andytsyupa\\\\main\\\\FJava2.xml" ), sk.getBytes (), StandardOpenOption.APPEND );
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }


    private String getFileDetails(File file) {
        if ( file == null ) return "";
        StringBuffer buffer=new StringBuffer ();
        buffer.append ( "Назва: " + file.getName () + "\n" );
        buffer.append ( "Шлях: " + file.getPath () + "\n" );
        buffer.append ( "Об'єм файлів: " + (file.length () / 1024) + "KБ" + "\n" );
        return buffer.toString ();
    }


    public void proces(String start, String end) throws ParseException {

        String username=System.getProperty ( "user.name" );
        System.out.println ( username );
        DateFormat format=new SimpleDateFormat ( "yyyy-MM-dd", Locale.US );
        Date startDate=format.parse ( start );
        DateFormat format3=new SimpleDateFormat ( "yyyy-MM-dd", Locale.US );
        Date endDateFile=format3.parse ( end );
        String path="D:/test";  ///1///


        recursivDelete ( new File ( path ) );
        File folder=new File ( path );
        ArrayList<File> list=new ArrayList<File> ();
        list=listFilesForFolder ( folder, list );


        long total=0;
        for (int i=0; i < list.size (); i++) {

            File fileEntry=list.get ( i ); //fileEnteri файл
            total=total + fileEntry.length () / 1048;/*576*/
            Date date=new Date ();
            date.setTime ( fileEntry.lastModified () );

            if ( startDate.compareTo ( date ) < 0 ) {
                fileEntry.delete ();

                SimpleDateFormat format1=new SimpleDateFormat ( "d-M-Y H:m:s" );
                String DateToStr=format1.format ( date );
                System.out.println ( fileEntry.getName () + " " + DateToStr );
                fileDetailsTextArea2.append ( fileEntry.getName () + " " + DateToStr  + "\n");
            }
        }
    }

    public static void main(String args[]) throws Exception {

        new FileTreeFrame ( "d:\\test" ); //FileTreeFrame

    }


    class FileSystemModel implements TreeModel {


        private File root;
        private Vector listeners=new Vector ();
        public FileSystemModel(File rootDirectory) {
            root=rootDirectory;
        }
        public Object getRoot() {
            return root;
        }
        public Object getChild(Object parent, int index) {
            File directory=(File) parent;
            String[] children=directory.list ();
            return new TreeFile ( directory, children[index] );
        }

        public int getChildCount(Object parent) {
            File file=(File) parent;
            if ( file.isDirectory () ) {
                String[] fileList=file.list ();
                if ( fileList != null ) return file.list ().length;
            }
            return 0;
        }

        public boolean isLeaf(Object node) {
            File file=(File) node;
            return file.isFile ();
        }

        public int getIndexOfChild(Object parent, Object child) {
            File directory=(File) parent;
            File file=(File) child;
            String[] children=directory.list ();
            for (int i=0; i < children.length; i++) {
                if ( file.getName ().equals ( children[i] ) ) {
                    return i;
                }
            }
            return -1;

        }

        public void valueForPathChanged(TreePath path, Object value) {
            File oldFile=(File) path.getLastPathComponent ();
            String fileParentPath=oldFile.getParent ();
            String newFileName=(String) value;
            File targetFile=new File ( fileParentPath, newFileName );
            oldFile.renameTo ( targetFile );
            File parent=new File ( fileParentPath );
            int[] changedChildrenIndices={getIndexOfChild ( parent, targetFile )};
            Object[] changedChildren={targetFile};
            fireTreeNodesChanged ( path.getParentPath (), changedChildrenIndices, changedChildren );

        }

        private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
            TreeModelEvent event=new TreeModelEvent ( this, parentPath, indices, children );
            Iterator iterator=listeners.iterator ();
            TreeModelListener listener=null;
            while (iterator.hasNext ()) {
                listener=(TreeModelListener) iterator.next ();
                listener.treeNodesChanged ( event );
            }
        }

        public void addTreeModelListener(TreeModelListener listener) {
            listeners.add ( listener );
        }
        public void removeTreeModelListener(TreeModelListener listener) {
            listeners.remove ( listener );
        }
        public class TreeFile extends File {
            public TreeFile(File parent, String child) {
                super ( parent, child );
            }
            public String toString() {
                return getName ();
            }
        }
    }
}