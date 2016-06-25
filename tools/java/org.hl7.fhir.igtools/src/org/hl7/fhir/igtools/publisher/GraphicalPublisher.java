package org.hl7.fhir.igtools.publisher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;

public class GraphicalPublisher {

  public JFrame frmFhirImplementationGuide;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GraphicalPublisher window = new GraphicalPublisher();
          window.frmFhirImplementationGuide.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public GraphicalPublisher() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frmFhirImplementationGuide = new JFrame();
    frmFhirImplementationGuide.setTitle("FHIR Implementation Guide Publisher");
    frmFhirImplementationGuide.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\work\\org.hl7.fhir\\build\\tools\\html\\assets\\ico\\favicon.png"));
    frmFhirImplementationGuide.setBounds(100, 100, 785, 449);
    frmFhirImplementationGuide.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmFhirImplementationGuide.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    
    JToolBar toolBar = new JToolBar();
    frmFhirImplementationGuide.getContentPane().add(toolBar);
    
    JButton button = new JButton("Execute");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(frmFhirImplementationGuide, "Eggs are not supposed to be green.");
      }
    });
    toolBar.add(button);
  }

}
