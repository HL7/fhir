package org.hl7.fhir.igtools.ui;

import java.awt.EventQueue;
import java.io.IOException;

public class GraphicalPublisher {

  public IGPublisherFrame frame;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          GraphicalPublisher window = new GraphicalPublisher();
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
   */
  public GraphicalPublisher() throws IOException {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   * @throws IOException 
   */
  private void initialize() throws IOException {
    frame = new IGPublisherFrame();
  }

}
