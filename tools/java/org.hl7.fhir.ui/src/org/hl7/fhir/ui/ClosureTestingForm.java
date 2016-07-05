package org.hl7.fhir.ui;

import java.awt.EventQueue;

import javax.swing.UIManager;


public class ClosureTestingForm {


  public ClosureTestingFrame frame;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          ClosureTestingForm window = new ClosureTestingForm();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public ClosureTestingForm() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new ClosureTestingFrame();
  }


}
