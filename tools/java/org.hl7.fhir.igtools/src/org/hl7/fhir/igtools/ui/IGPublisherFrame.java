package org.hl7.fhir.igtools.ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.SwingWorker;

import org.hl7.fhir.dstu3.model.Constants;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.igtools.publisher.IGKnowledgeProvider;
import org.hl7.fhir.igtools.publisher.IGLogger;
import org.hl7.fhir.igtools.publisher.Publisher;
import org.hl7.fhir.igtools.publisher.Publisher.GenerationTool;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonObject;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class IGPublisherFrame extends javax.swing.JFrame {

  private static final String LOG_PREFIX = "--$%^^---";
  
  private javax.swing.JButton btnExecute;
  private javax.swing.JButton btnChoose;
  private javax.swing.JButton btnGetHelp;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JSplitPane jSplitPane1;
  private javax.swing.JTextArea txtLog;
  private javafx.embed.swing.JFXPanel txtValidation;
  private javax.swing.JComboBox<String> cbxIGName;
  private javax.swing.JToolBar jToolBar1;
  private IniFile ini;

  private BackgroundPublisherTask task;
  private StringBuilder fullLog = new StringBuilder();
  private String qa;
  
  /**
   * Creates new form IGPublisherFrame
   */
  public IGPublisherFrame() {
    ini = new IniFile(Utilities.path(System.getProperty("user.home"), "fhir-ig.ini"));
    initComponents();
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    setTitle("FHIR Implementation Guide Publisher");
    setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\work\\org.hl7.fhir\\build\\tools\\html\\assets\\ico\\favicon.png"));
    setBounds(100, 100, 785, 449);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        frameClose();
      }
    });

    jToolBar1 = new javax.swing.JToolBar();
    btnExecute = new javax.swing.JButton();
    btnChoose = new javax.swing.JButton();
    cbxIGName = new javax.swing.JComboBox<String>();
    jPanel1 = new javax.swing.JPanel();
    btnGetHelp = new javax.swing.JButton();
    jSplitPane1 = new javax.swing.JSplitPane();
    jScrollPane1 = new javax.swing.JScrollPane();
    txtLog = new javax.swing.JTextArea();
    jScrollPane2 = new javax.swing.JScrollPane();
    txtValidation = new javafx.embed.swing.JFXPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jToolBar1.setRollover(true);
    jToolBar1.setFocusable(false);

    btnExecute.setFocusable(false);
    btnExecute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnExecute.setLabel("Execute");
    btnExecute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    btnExecute.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnExecuteClick(evt);
      }
    });
    jToolBar1.add(btnExecute);

    btnChoose.setFocusable(false);
    btnChoose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    btnChoose.setLabel("Choose");
    btnChoose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    btnChoose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnChooseClick(evt);
      }
    });
    jToolBar1.add(btnChoose);

    if (ini.getProperties("igs") != null && ini.getProperties("igs").containsKey("selected")) {
      for (int i = 0; i < ini.getIntegerProperty("igs", "count"); i++) 
        cbxIGName.addItem(ini.getStringProperty("igs", "file"+Integer.toString(i)));
      cbxIGName.setSelectedIndex(ini.getIntegerProperty("igs", "selected"));
    }
    cbxIGName.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbxIGNameChange(evt);
      }
    });
    jToolBar1.add(cbxIGName);

    btnGetHelp.setText("Debug Summary");
    btnGetHelp.setEnabled(false);
    btnGetHelp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnGetHelpClick(evt);
      }
    });
    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btnGetHelp)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(btnGetHelp)
            .addGap(0, 13, Short.MAX_VALUE))
        );

    txtLog.setColumns(20);
    txtLog.setRows(5);
    txtLog.setEditable(false);
    txtLog.getCaret().setVisible(false);
    jScrollPane1.setViewportView(txtLog);

    jSplitPane1.setLeftComponent(jScrollPane1);

    jScrollPane2.setViewportView(txtValidation);

    jSplitPane1.setRightComponent(jScrollPane2);
    if (ini.getProperties("layout") != null && ini.getProperties("layout").containsKey("split")) 
      jSplitPane1.setDividerLocation(ini.getIntegerProperty("layout", "split"));

    jSplitPane1.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, 
        new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        splitResize(evt);              
      }
    });

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
    if (ini.getProperties("layout") != null && ini.getProperties("layout").containsKey("X")) {
      setLocation(ini.getIntegerProperty("layout", "X"), ini.getIntegerProperty("layout", "Y")); 
      setSize(ini.getIntegerProperty("layout", "W"), ini.getIntegerProperty("layout", "H")); 
    }

  }                        


  
  private void btnChooseClick(java.awt.event.ActionEvent evt) {                                         
    JFileChooser jfc = new JFileChooser();
    if (cbxIGName.getSelectedItem() != null)
      jfc.setCurrentDirectory(new File(Utilities.getDirectoryForFile((String) cbxIGName.getSelectedItem())));
    if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      int index = -1;
      String s = jfc.getSelectedFile().getAbsolutePath();
      for (int i = 0; i < cbxIGName.getItemCount(); i++) {
        if (s.equals(cbxIGName.getItemAt(i)))
          index = i;;
      }
      if (index == -1) {
        index = ini.getProperties("igs") == null ? 0 : ini.getIntegerProperty("igs", "count");
        ini.setStringProperty("igs", "file"+Integer.toString(index), s, null);
        ini.setIntegerProperty("igs", "count", index+1, null);
        cbxIGName.addItem(ini.getStringProperty("igs", "file"+Integer.toString(index)));
      }
      ini.setIntegerProperty("igs", "selected", index, null);
      cbxIGName.setSelectedIndex(ini.getIntegerProperty("igs", "selected"));
    }
  } 

  private void cbxIGNameChange(java.awt.event.ActionEvent evt) {         
    int index = cbxIGName.getSelectedIndex();
    ini.setIntegerProperty("igs", "selected", index, null);
  }                                          

  protected void splitResize(PropertyChangeEvent evt) {
    ini.setIntegerProperty("layout", "split", jSplitPane1.getDividerLocation(), null); 
  }

  protected void frameClose() {
    ini.setIntegerProperty("layout", "X", getX(), null); 
    ini.setIntegerProperty("layout", "Y", getY(), null); 
    ini.setIntegerProperty("layout", "W", getWidth(), null); 
    ini.setIntegerProperty("layout", "H", getHeight(), null); 
    ini.save();    
  }

  // ------ Execcution ------------------------------------------------------------------------------------------

  public class BackgroundPublisherTask extends SwingWorker<String, String> implements IGLogger  {

    
    @Override
    public String doInBackground() {
      Publisher pu = new Publisher();
      pu.setConfigFile((String) cbxIGName.getSelectedItem());
      pu.setLogger(this);
      try {
        pu.execute(false);
        qa = pu.getQAFile();
      } catch (Exception e) {
        logMessage("Error : "+e.getMessage());
        for (StackTraceElement m : e.getStackTrace()) 
          logMessage("   "+m.toString());
      } 
      return "Finished";
    }

    @Override
    public void logMessage(String msg) {
      publish(msg);
    }

    @Override
    public void logDebugMessage(String msg) {
      publish(LOG_PREFIX+msg);
      
    }    
    @Override
    protected void process(List<String> msgs) {
      for (String msg : msgs) {
        if (msg.startsWith(LOG_PREFIX)) {
          fullLog.append(msg.substring(LOG_PREFIX.length())+"\r\n");
        } else {
          txtLog.append(msg+"\r\n");
          fullLog.append(msg+"\r\n");
        }
      }
      txtLog.setCaretPosition(txtLog.getText().length() - 1);
    }

    @Override
    protected void done() {
      btnExecute.setEnabled(true);
      btnChoose.setEnabled(true);
      cbxIGName.setEnabled(true);
      btnGetHelp.setEnabled(true);      
      btnExecute.setLabel("Execute");
      Platform.runLater( () -> { // FX components need to be managed by JavaFX
        
        WebView webView = new WebView();
        webView.getEngine().load("file:"+qa);
        txtValidation.setScene( new Scene( webView ) );
     });
    }


  }

  private void btnExecuteClick(java.awt.event.ActionEvent evt) {
    btnExecute.setEnabled(false);
    btnChoose.setEnabled(false);
    cbxIGName.setEnabled(false);
    btnGetHelp.setEnabled(false);
    btnExecute.setLabel("Running");
    txtLog.setText("");
    fullLog.setLength(0);
    Platform.runLater( () -> { // FX components need to be managed by JavaFX
      WebView webView = new WebView();
      webView.getEngine().loadContent( "<html> Publication in Process!" );
      txtValidation.setScene( new Scene( webView ) );
   });
    task = new BackgroundPublisherTask();
    task.execute();
  }

  protected void btnGetHelpClick(ActionEvent evt) {
    try {
      StringBuilder b = new StringBuilder();
      b.append("= Log =\r\n");
      b.append(fullLog);
      b.append("\r\n\r\n");
      b.append("= System =\r\n");

      b.append("ig: ");
      b.append((String) cbxIGName.getSelectedItem());
      b.append("\r\n");

      b.append("current.dir: ");
      b.append(getCurentDirectory());
      b.append("\r\n");

      b.append("user.dir: ");
      b.append(System.getProperty("user.home"));
      b.append("\r\n");

      b.append("tx.server: ");
      b.append("http://fhir3.healthintersections.com.au/open");
      b.append("\r\n");

      b.append("tx.cache: ");
      b.append(Utilities.path(System.getProperty("user.home"), "fhircache"));
      b.append("\r\n");

      b.append("\r\n");

      b.append("= Validation =\r\n");
      b.append(TextFile.fileToString(Utilities.changeFileExt(qa, ".txt")));

      b.append("\r\n");
      b.append("\r\n");
      
      b.append("= IG =\r\n");
      b.append(TextFile.fileToString((String) cbxIGName.getSelectedItem()));

      b.append("\r\n");
      b.append("\r\n");
      b.append("= VS.cache =\r\n");
      for (String s : new File(Utilities.path(System.getProperty("user.home"), "fhircache")).list()) {
        b.append(s);
        b.append("\r\n");
      }
      StringSelection stringSelection = new StringSelection(b.toString());
      Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
      clpbrd.setContents(stringSelection, null);
      JOptionPane.showMessageDialog(this, "Report copied to clipboard. Now paste it into an email to grahame@hl7.org");
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, e.getMessage());
      e.printStackTrace();
    }
  }

  private String getCurentDirectory() {
    String currentDirectory;
    File file = new File(".");
    currentDirectory = file.getAbsolutePath();
    return currentDirectory;
  }

}
