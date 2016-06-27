package org.hl7.fhir.igtools.publisher;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class IGPublisherFrame extends javax.swing.JFrame {

  /**
   * Creates new form IGPublisherFrame
   */
  public IGPublisherFrame() {
    initComponents();
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    setTitle("FHIR Implementation Guide Publisher");
    setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\work\\org.hl7.fhir\\build\\tools\\html\\assets\\ico\\favicon.png"));
    setBounds(100, 100, 785, 449);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jToolBar1 = new javax.swing.JToolBar();
    btnExecute = new javax.swing.JButton();
    btnChoose = new javax.swing.JButton();
    txtIGName = new javax.swing.JTextField();
    jPanel1 = new javax.swing.JPanel();
    lblStatus = new javax.swing.JLabel();
    jSplitPane1 = new javax.swing.JSplitPane();
    jScrollPane1 = new javax.swing.JScrollPane();
    txtLog = new javax.swing.JTextArea();
    jScrollPane2 = new javax.swing.JScrollPane();
    jTextArea2 = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jToolBar1.setRollover(true);
    jToolBar1.setFocusable(false);

    btnExecute.setFocusable(false);
    btnExecute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnExecute.setLabel("Execute");
    btnExecute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(btnExecute);

    btnChoose.setFocusable(false);
    btnChoose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnChoose.setLabel("Choose");
    btnChoose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    jToolBar1.add(btnChoose);

    txtIGName.setText("jTextField1");
    jToolBar1.add(txtIGName);

    lblStatus.setText("Status Information");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(lblStatus)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(lblStatus)
            .addGap(0, 13, Short.MAX_VALUE))
        );

    txtLog.setColumns(20);
    txtLog.setRows(5);
    jScrollPane1.setViewportView(txtLog);

    jSplitPane1.setLeftComponent(jScrollPane1);

    jTextArea2.setColumns(20);
    jTextArea2.setRows(5);
    jScrollPane2.setViewportView(jTextArea2);

    jSplitPane1.setRightComponent(jScrollPane2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
        );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

    pack();
  }// </editor-fold>                        

  /**
   * @param args the command line arguments
   */
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
      java.util.logging.Logger.getLogger(IGPublisherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(IGPublisherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(IGPublisherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(IGPublisherFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new IGPublisherFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify                     
  private javax.swing.JButton btnExecute;
  private javax.swing.JButton btnChoose;
  private javax.swing.JLabel lblStatus;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JSplitPane jSplitPane1;
  private javax.swing.JTextArea txtLog;
  private javax.swing.JTextArea jTextArea2;
  private javax.swing.JTextField txtIGName;
  private javax.swing.JToolBar jToolBar1;
  // End of variables declaration       
}
