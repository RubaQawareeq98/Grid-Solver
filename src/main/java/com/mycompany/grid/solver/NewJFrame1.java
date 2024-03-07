/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.grid.solver;

import com.mycompany.grid.solver.aStarAlgo.Node;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Administrator
 */

class aStarAlgo {

    private ArrayList <Node> testedPath = new ArrayList<>();
    private final int[][] grid;
    private final ArrayList<Node> openList;
    private final ArrayList<Node> closedList;
    private final Node start;
    private final Node goal;

    public aStarAlgo(int[][] grid, Node start, Node goal) {
        this.grid = grid;
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        this.start = start;
        this.goal = goal;
    }

    public ArrayList<Node> solve() {
        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = getLowestCostNode();

            openList.remove(current);

            if (current.equals(goal)) {
                return pathFromStartToGoal(current);
            }

            testedPath.add(current);
            for (Node border : getBoundaries(current)) {
                if(! openList.contains(border) && ! closedList.contains(border)){
                    border.parent = current;
                    border.heuristic = calcHeuristic(border);
                    openList.add(border);
                }
                else if(openList.contains(border)){
                        if(current.pathCost + calcHeuristic(border) < border.pathCost + calcHeuristic(border)){
                            openList.add(border);
                            border.parent =current;
                        }
                }

            }

            openList.remove(current);
            closedList.add(current);
        }

        return null; // No path found
    }

    private Node getLowestCostNode() {
        Node current = openList.get(0);
        for (Node node : openList) {
            if (node.pathCost + calcHeuristic(node) < current.pathCost + calcHeuristic(current)) {
                current = node;
            }
        }
        return current;
    }

    private ArrayList<Node> getBoundaries(Node node) {
        ArrayList<Node> boundaries = new ArrayList<>();
        // bottom node
        if(isValidLocation(node.i,node.j-1)){
            boundaries.add(new Node (node.i,node.j-1));
        }
        // right node
        if(isValidLocation(node.i+1,node.j)){
            boundaries.add(new Node (node.i+1,node.j));
        }

        // left node
        if(isValidLocation(node.i-1,node.j)){
            boundaries.add(new Node (node.i-1,node.j));
        }


        // up node
        if(isValidLocation(node.i,node.j+1)){
            boundaries.add(new Node (node.i,node.j+1));
        }

        return boundaries;
    }

    private boolean isValidLocation(int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] != 1;
    }
    private int calcHeuristic(Node n){
        return  Math.abs(n.i - goal.i) + Math.abs(n.j - goal.j);
    }

    private ArrayList<Node> pathFromStartToGoal(Node current) {
        ArrayList<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    static class Node {
        int i;
        int j;
        int pathCost;
        int heuristic;
        Node parent;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
            this.pathCost = 0;
            this.heuristic = 0;
            this.parent = null;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return i == node.i && j == node.j;
        }

    }
}

public class NewJFrame1 extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame1
     */
    public NewJFrame1(int [][]values, Node start, Node target1, Node target2) {
        
        initComponents();
        this.gridValues = values;
        this.start = start;
        this.target1 = target1;
        this.target2 = target2;

        aStarAlgo solver = new aStarAlgo(gridValues, start, target1);
        ArrayList<Node> path = solver.solve();
        test(path);
    }
    public NewJFrame1() {
      
        initComponents();
    }

  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        num = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pathLabel = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(242, 233, 228));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));

        jLabel2.setFont(new java.awt.Font("Sitka Display", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Tested path:");

        jLabel3.setFont(new java.awt.Font("Sitka Display", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Your Grid Solution");

        num.setFont(new java.awt.Font("Sitka Display", 1, 36)); // NOI18N
        num.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        grid.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout gridLayout = new javax.swing.GroupLayout(grid);
        grid.setLayout(gridLayout);
        gridLayout.setHorizontalGroup(
            gridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        gridLayout.setVerticalGroup(
            gridLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(grid);

        jLabel4.setFont(new java.awt.Font("Sitka Display", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Number Of Steps Neded: ");

        pathLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(290, 290, 290))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(186, 186, 186))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(530, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public void test(ArrayList<Node> path){
        System.out.println("Hello");
//         grid.removeAll();
         int rows =gridValues.length, columns =gridValues[0].length;
         grid.removeAll();

        // Set fixed size for each button
        int buttonSize = 50; // Adjust this value as needed
        Dimension buttonDimension = new Dimension(buttonSize, buttonSize);

        // Set the layout manager for the grid panel
        GridLayout gridLayout = new GridLayout(rows, columns);
        grid.setLayout(gridLayout);
        
        // Generate the grid of buttons
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                // Create a button for each cell
                JButton button = new JButton();
                button.setBackground(white);
                button.setPreferredSize(buttonDimension); // Set preferred button size
                // Check if the button corresponds to a node in the list
                
               if(gridValues[row][col] == 1 )
                   button.setBackground(black);
               else if(row == start.i && col == start.j){
                   button.setBackground(green);
               }
              else if(row == target1.i && col == target1.j){
                   button.setBackground(red);
               }
               
              else if (path.contains(new Node(row, col))) {
                    button.setBackground(Color.blue); // Set button background color to blue
                }
               num.setText(path.size() - 1+"");
                // Add the button to the panel
                grid.add(button);
            }
        }
        
        
        // Refresh the layout
        grid.revalidate();
        grid.repaint();
        
      
    }
    
    
    public void start(){
         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame1(gridValues, start,target1, target2).setVisible(true);
            }
        });
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            int a [][] = {{1}};
            public void run() {
                new NewJFrame1().setVisible(true);
            }
        });
    }
    
    
    
    
Color red = new Color(215,11,0); // Red
    Color green = new Color(86,153,89); // Green
    Color white = new Color(255,255, 255); // Blue
    Color black = new Color(0, 0, 0); // Yellow
    private Color[] colors = { green,red, white, black};
    private int [][] gridValues;
private Node start, target1, target2;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel grid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel num;
    private javax.swing.JLabel pathLabel;
    // End of variables declaration//GEN-END:variables
}
