package org.hl7.fhir.ui;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.UIManager;

import com.google.gson.JsonSyntaxException;


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
   * @throws IOException 
   * @throws FileNotFoundException 
   * @throws JsonSyntaxException 
   */
  public ClosureTestingForm() throws JsonSyntaxException, FileNotFoundException, IOException {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   * @throws IOException 
   * @throws FileNotFoundException 
   * @throws JsonSyntaxException 
   */
  private void initialize() throws JsonSyntaxException, FileNotFoundException, IOException {
    frame = new ClosureTestingFrame();
  }


}
