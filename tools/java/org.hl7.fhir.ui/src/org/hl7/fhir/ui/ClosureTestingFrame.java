package org.hl7.fhir.ui;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.synth.Region;
import javax.swing.text.JTextComponent;

import org.eclipse.swt.internal.ole.win32.GUID;
import org.hl7.fhir.dstu2016may.model.Parameters;
import org.hl7.fhir.dstu2016may.model.Parameters.ParametersParameterComponent;
import org.hl7.fhir.dstu2016may.model.PrimitiveType;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.ConceptMap.ConceptMapGroupComponent;
import org.hl7.fhir.dstu3.model.ConceptMap.SourceElementComponent;
import org.hl7.fhir.dstu3.model.ConceptMap.TargetElementComponent;
import org.hl7.fhir.dstu3.model.Enumerations.ConceptMapEquivalence;
import org.hl7.fhir.dstu3.utils.client.FHIRToolingClient;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ClosureTestingFrame extends javax.swing.JFrame {

  private javax.swing.JButton btnChoose;
  private javax.swing.JButton btnGUID;
  private javax.swing.JButton btnAdd;
  private javax.swing.JComboBox<String> cbxServer;
  private javax.swing.JTextField txtName;
  private javax.swing.JTextField txtSystem;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javafx.embed.swing.JFXPanel txtValidation;
  private javax.swing.JTextField txtCode;
  private javax.swing.JPanel pnlOutput;

  private IniFile ini;
  private WebView webView;
  private String cache;
  private String source;
  private org.hl7.fhir.dstu2016may.utils.client.FHIRToolingClient client2;
  private org.hl7.fhir.dstu3.utils.client.FHIRToolingClient client3;
  private JsonObject displayCache;

  public ClosureTestingFrame() throws JsonSyntaxException, FileNotFoundException, IOException {
    ini = new IniFile(Utilities.path(System.getProperty("user.home"), "fhir-test.ini"));
    cache = Utilities.path(System.getProperty("user.home"), "fhircache");
    if (new File(Utilities.path(cache, "display.cache")).exists())
      displayCache = (JsonObject) new JsonParser().parse(TextFile.fileToString(Utilities.path(cache, "display.cache")));
    else
      displayCache = new JsonObject();
    initComponents();
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    setTitle("Closure Test Client");
    setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\work\\org.hl7.fhir\\build\\tools\\html\\assets\\ico\\favicon.png"));
    setBounds(100, 100, 785, 449);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        frameClose();
      }
    });


    jPanel1 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    btnChoose = new javax.swing.JButton();
    btnGUID = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    cbxServer = new javax.swing.JComboBox<>();
    jPanel3 = new javax.swing.JPanel();
    jPanel4 = new javax.swing.JPanel();
    txtName = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    btnAdd = new javax.swing.JButton();
    txtCode = new javax.swing.JTextField();
    txtSystem = new javax.swing.JTextField();
    pnlOutput = new javax.swing.JPanel();
    txtValidation = new javafx.embed.swing.JFXPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    btnChoose.setText("Choose");
    btnChoose.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnChooseClick(evt);
      }
    });

    btnGUID.setText("GUID");
    btnGUID.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnGUIDClick(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnChoose)
            .addContainerGap())
        );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addComponent(btnChoose)
            .addGap(0, 13, Short.MAX_VALUE))
        );

    jLabel1.setText("Server");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addComponent(cbxServer, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(cbxServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap())
        );

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnGUID)
            .addContainerGap())
        );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addComponent(btnGUID)
            .addGap(0, 13, Short.MAX_VALUE))
        );


    jLabel2.setText("Closure Table");

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel2)
            .addGap(18, 18, 18)
            .addComponent(txtName, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(3, 3, 3)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel2)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

    jLabel3.setText("Status Message");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel3)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addGap(0, 14, Short.MAX_VALUE)
            .addComponent(jLabel3))
        );

    btnAdd.setText("Add Code");
    btnAdd.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnAddClick(evt);
      }
    });

    txtCode.setText("Code");


    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(btnAdd)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(txtSystem, 0, 478, Short.MAX_VALUE)
            .addContainerGap())
        );
    jPanel6Layout.setVerticalGroup(
        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel6Layout.createSequentialGroup()
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAdd)
                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtSystem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(0, 13, Short.MAX_VALUE))
        );


    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(pnlOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(pnlOutput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

    pnlOutput.setBackground(new java.awt.Color(255, 255, 255));
    pnlOutput.setForeground(new java.awt.Color(255, 255, 255));

    pnlOutput.add(txtValidation);
    txtValidation.setLocation(30, 30);
    if (ini.getProperties("servers") == null) {
      ini.setStringProperty("servers", "selected", "0", null);
      ini.setStringProperty("servers", "count", "5", null);
      ini.setStringProperty("servers", "file0", "http://local.healthintersections.com.au:960/open", null); 
      ini.setStringProperty("servers", "file1", "http://fhir3.healthintersections.com.au/open", null); 
      ini.setStringProperty("servers", "file2", "http://terminology.hl7.org.au/open", null);
      ini.setStringProperty("servers", "file3", "http://fhir.ext.apelon.com/dtsserverws/fhir", null);
      ini.setStringProperty("servers", "file4", "http://ontoserver.csiro.au/stu3", null);
    }
    for (int i = 0; i < ini.getIntegerProperty("servers", "count"); i++) 
      cbxServer.addItem(ini.getStringProperty("servers", "file"+Integer.toString(i)));
    cbxServer.setSelectedIndex(ini.getIntegerProperty("servers", "selected"));
    cbxServer.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cbxServerChange(evt);
      }
    });

    txtName.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void changedUpdate(DocumentEvent arg0) {
        txtNameEdit();
      }

      @Override
      public void insertUpdate(DocumentEvent arg0) {
        txtNameEdit();
      }

      @Override
      public void removeUpdate(DocumentEvent arg0) {
        txtNameEdit();
      }
    });
    txtCode.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void changedUpdate(DocumentEvent arg0) {
        txtCodeEdit();
      }

      @Override
      public void insertUpdate(DocumentEvent arg0) {
        txtCodeEdit();
      }

      @Override
      public void removeUpdate(DocumentEvent arg0) {
        txtCodeEdit();
      }
    });
    if (ini.getProperties("closure") != null) { 
      txtCode.setText(ini.getStringProperty("closure", "code")); 
      txtSystem.setText(ini.getStringProperty("closure", "system"));
      txtName.setText(ini.getStringProperty("closure", "name"));
    }
    pack();

    if (ini.getProperties("layout") != null && ini.getProperties("layout").containsKey("X")) {
      setLocation(ini.getIntegerProperty("layout", "X"), ini.getIntegerProperty("layout", "Y")); 
      setSize(ini.getIntegerProperty("layout", "W"), ini.getIntegerProperty("layout", "H")); 
    }

    updateButton();
    updateView();
  }


  protected void frameClose() {
    ini.setIntegerProperty("layout", "X", getX(), null); 
    ini.setIntegerProperty("layout", "Y", getY(), null); 
    ini.setIntegerProperty("layout", "W", getWidth(), null); 
    ini.setIntegerProperty("layout", "H", getHeight(), null); 
    ini.setStringProperty("closure", "code", txtCode.getText(), null); 
    ini.setStringProperty("closure", "system", txtSystem.getText(), null); 
    ini.setStringProperty("closure", "name", txtName.getText(), null); 
    ini.save();    
    try {
      TextFile.stringToFile(new Gson().toJson(displayCache), Utilities.path(cache, "display.cache"));
    } catch (IOException e) {
      e.printStackTrace();
    }    

    if (ini.getProperties("layout") != null && ini.getProperties("layout").containsKey("X")) {
      setLocation(ini.getIntegerProperty("layout", "X"), ini.getIntegerProperty("layout", "Y")); 
      setSize(ini.getIntegerProperty("layout", "W"), ini.getIntegerProperty("layout", "H")); 
    }
  }

  private void cbxServerChange(java.awt.event.ActionEvent evt) {         
    int index = cbxServer.getSelectedIndex();
    ini.setIntegerProperty("server", "selected", index, null);
    updateButton();
    updateView();
  }                                          

  private void txtNameEdit() {
    updateButton();
    updateView();
  }

  private void txtCodeEdit() {
    updateButton();
    updateView();
  }

  private void btnChooseClick(java.awt.event.ActionEvent evt) {  
    String s = (String) JOptionPane.showInputDialog(
        this,
        "Choose a server:\n"+ "\"FHIR Endpoint URL\"",
        "Choose a server", JOptionPane.PLAIN_MESSAGE, null, null, null);
    if (s != null) {
      int index = -1;
      for (int i = 0; i < cbxServer.getItemCount(); i++) {
        if (s.equals(cbxServer.getItemAt(i)))
          index = i;
      }
      if (index == -1) {
        index = ini.getProperties("servers") == null ? 0 : ini.getIntegerProperty("servers", "count");
        ini.setStringProperty("servers", "file"+Integer.toString(index), s, null);
        ini.setIntegerProperty("servers", "count", index+1, null);
        cbxServer.addItem(ini.getStringProperty("servers", "file"+Integer.toString(index)));
      }
      ini.setIntegerProperty("servers", "selected", index, null);
      cbxServer.setSelectedIndex(ini.getIntegerProperty("servers", "selected"));
      updateView();
    }
  } 

  private void btnGUIDClick(java.awt.event.ActionEvent evt) {  
    String s = UUID.randomUUID().toString().toLowerCase();
    txtName.setText(s);
    updateButton();
    updateView();
  } 

  private void btnAddClick(java.awt.event.ActionEvent evt) {
    try {
      if (btnAdd.getText().equals("Initialize"))
        initialise(getServer(), txtName.getText());
      else 
        for (String c : txtCode.getText().split("\\,"))
          add(getServer(), txtName.getText(), txtSystem.getText(), c);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(this, "Error: "+e.getMessage());
      e.printStackTrace();
    }
  }

  private String getServer() {
    return ini.getStringProperty("servers", "file"+Integer.toString(cbxServer.getSelectedIndex()));
  } 

  private void initialise(String url, String name) throws URISyntaxException, IOException {
    connect(url);
    if (client2 != null)
      initialise2016May(url, name);
    else
      initialise3(url, name);
  }
  
  private boolean serverIs2016May(String url) {
    return Utilities.existsInList(url, "http://terminology.hl7.org.au/open", "http://fhir.ext.apelon.com/dtsserverws/fhir", "http://ontoserver.csiro.au/stu3");
  }

  private void connect(String url) throws URISyntaxException {
    if (serverIs2016May(url)) {
      if (client2 == null || url.equals(client2.getAddress())) 
        client2 = new org.hl7.fhir.dstu2016may.utils.client.FHIRToolingClient(url);
      client3 = null;
    } else {
      if (client3 == null || url.equals(client3.getAddress())) 
        client3 = new org.hl7.fhir.dstu3.utils.client.FHIRToolingClient(url);
      client2 = null;
    }
  }

  private String getDisplay(String url, String sys, String code) throws URISyntaxException {
    String key = sys+"::"+code;
    if (displayCache.has(key))
      return readStr(displayCache, key);
    connect(url);
    String res = null;
    Map<String, String> pIn = new HashMap<String, String>();
    pIn.put("system", sys);
    pIn.put("code", code);
    if (client2 != null) {
      Parameters pOut = client2.lookupCode(pIn);
      for (ParametersParameterComponent param : pOut.getParameter()) {
        if (param.getName().equals("display"))
          res = ((PrimitiveType) param.getValue()).asStringValue();
      }
    } else {
      org.hl7.fhir.dstu3.model.Parameters pOut = client3.lookupCode(pIn);
      for (org.hl7.fhir.dstu3.model.Parameters.ParametersParameterComponent param : pOut.getParameter()) {
        if (param.getName().equals("display"))
          res = ((org.hl7.fhir.dstu3.model.PrimitiveType) param.getValue()).asStringValue();
      }
    }
    if (res != null)
      writeStr(displayCache, key, res);
    return res;
  }
  
  private void initialise2016May(String url, String name) throws URISyntaxException, IOException {
    org.hl7.fhir.dstu2016may.model.ConceptMap cm = client2.initializeClosure(name);
    JsonObject json = new JsonObject();
    json.addProperty("version", cm.getVersion());
    json.addProperty("name", cm.getName());
    saveCurrentTable(json);
    updateButton();
    updateView();
  }

  private void initialise3(String url, String name) throws URISyntaxException, IOException {
    ConceptMap cm = client3.initializeClosure(name);
    JsonObject json = new JsonObject();
    json.addProperty("version", cm.getVersion());
    json.addProperty("name", cm.getName());
    saveCurrentTable(json);
    updateButton();
    updateView();
  }

  private void add(String url, String name, String system, String code) throws URISyntaxException, IOException {
    connect(url);
    if (client2 != null)
      add2016May(url, name, system, code);
    else
      add3(url, name, system, code);
    updateButton();
    updateView();    
  }
  
  private void add2016May(String url, String name, String system, String code) throws URISyntaxException, IOException {
    org.hl7.fhir.dstu2016may.model.ConceptMap cm = client2.updateClosure(name, new org.hl7.fhir.dstu2016may.model.Coding().setSystem(system).setCode(code));
    JsonObject json = getCurrentTable();
    writeStr(json, "version", cm.getVersion());
    writeStr(json, "name", cm.getName());
      for (org.hl7.fhir.dstu2016may.model.ConceptMap.SourceElementComponent el : cm.getElement()) {
        for (org.hl7.fhir.dstu2016may.model.ConceptMap.TargetElementComponent tgt : el.getTarget()) {
          if (tgt.getEquivalence() == org.hl7.fhir.dstu2016may.model.Enumerations.ConceptMapEquivalence.SUBSUMES) 
            addToJson(json, el.getSystem(), el.getCode(), tgt.getSystem(), tgt.getCode());
          else
            addToJson(json, tgt.getSystem(), tgt.getCode(), el.getSystem(), el.getCode());
        }
      }      
    saveCurrentTable(json);
  }

  private void add3(String url, String name, String system, String code) throws URISyntaxException, IOException {
    ConceptMap cm = client3.updateClosure(name, new Coding(system, code, null));
    JsonObject json = getCurrentTable();
    writeStr(json, "version", cm.getVersion());
    writeStr(json, "name", cm.getName());
    for (ConceptMapGroupComponent mg : cm.getGroup()) {
      for (SourceElementComponent el : mg.getElement()) {
        for (TargetElementComponent tgt : el.getTarget()) {
          if (tgt.getEquivalence() == ConceptMapEquivalence.SUBSUMES) 
            addToJson(json, mg.getSource(), el.getCode(), mg.getTarget(), tgt.getCode());
          else
            addToJson(json, mg.getTarget(), tgt.getCode(), mg.getSource(), el.getCode());
        }
      }      
    }
    saveCurrentTable(json);
  }

  private void addToJson(JsonObject json, String ss, String sc, String ts, String tc) {
    JsonObject oss = getChild(json, ss);
    JsonObject osc = getChild(oss, sc);
    JsonArray ots = getArray(osc, ts);
    writeArray(ots, tc);
  }

  private void writeArray(JsonArray arr, String value) {
    for (JsonElement e : arr) {
      if (e instanceof JsonPrimitive && ((JsonPrimitive) e).getAsString().equals(value)) {
        return;
      }
    }
    arr.add(new JsonPrimitive(value));
  }

  private JsonObject getChild(JsonObject obj, String name) {
    JsonObject oss = obj.getAsJsonObject(name);
    if (oss == null) {
      oss = new JsonObject();
      obj.add(name, oss);
    }
    return oss;
  }

  private JsonArray getArray(JsonObject obj, String name) {
    JsonArray oss = obj.getAsJsonArray(name);
    if (oss == null) {
      oss = new JsonArray();
      obj.add(name, oss);
    }
    return oss;
  }

  private void writeStr(JsonObject json, String name, String value) {
    JsonElement e = json.get(name);
    if (e != null)
      json.remove(name);
    json.addProperty(name, value);
  }

  private void updateButton() {
    try {
      if (getCurrentTable() == null) {
        btnAdd.setText("Initialize");
        txtCode.setEnabled(false);
        txtSystem.setEnabled(false);
      } else {
        if (Utilities.noString(txtCode.getText()))
          btnAdd.setText("Initialize");
        else
          btnAdd.setText("Add");
        txtCode.setEnabled(true);
        txtSystem.setEnabled(true);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void updateView() {
    try {
      JsonObject table = getCurrentTable();
      if (table == null)
        source = saveNoState();
      else
        source = buildSummary(table, getServer());
    } catch (Exception e) {
      source = "<html><body>"+Utilities.escapeXml(e.getMessage())+"</body></html>";
    }
    Platform.runLater( () -> { // FX components need to be managed by JavaFX
      webView = new WebView();

      webView.getEngine().loadContent(source);
      txtValidation.setScene( new Scene( webView ) );
    });
  }

  private String buildSummary(JsonObject json, String srvr) throws URISyntaxException {
    StringBuilder b = new StringBuilder();
    b.append("<html><body style=\"font-family: sans-serif\">");
    b.append("<p>"+readStr(json, "name")+" version "+readStr(json, "version")+"</p>\r\n");
    b.append("<table border=\"0\" cellspacing=\"0\">\r\n");
    b.append("<tr><td>Child</td><td>&nbsp;</td><td>Parent</td></tr>\r\n");
    
    for (String p : propertyNames(json)) {
      if (p.contains(":")) {
        processSources(b, p, json.getAsJsonObject(p), srvr);
      }
    }
    b.append("</table>\r\n");
    b.append("</body></html>");
    return b.toString();
  }

  private List<String> propertyNames(JsonObject json) {
    List<String> res = new ArrayList<String>();
    for (Entry<String, JsonElement> jp : json.entrySet())
      res.add(jp.getKey());
    Collections.sort(res);
    return res;
  }

  private void processSources(StringBuilder b, String ss, JsonObject obj, String srvr) throws URISyntaxException {
    for (String p : propertyNames(obj)) {
      JsonObject osc = obj.getAsJsonObject(p);
      for (String pt : propertyNames(osc)) {
        processTargets(b, srvr, ss, p, pt, osc.getAsJsonArray(pt));
      }
    }    
  }

  private void processTargets(StringBuilder b, String srvr, String ss, String sc, String ts, JsonArray otc) throws URISyntaxException {
    for (JsonElement tc : otc) {
      b.append("<tr><td>");
      b.append(sys(ss));
      b.append(" ");
      b.append(sc);
      b.append(" ");
      b.append("<span style=\"color: grey\">");
      b.append(getDisplay(srvr, ss, sc));
      b.append("</span></td><td>&nbsp;&nbsp;</td><td>");
      b.append(sys(ts));
      b.append(" ");
      b.append(((JsonPrimitive) tc).getAsString());
      b.append(" ");
      b.append("<span style=\"color: grey\">");
      b.append(getDisplay(srvr, ts, ((JsonPrimitive) tc).getAsString()));
      b.append("</span></tr>\r\n");
    }
  }

  private Object sys(String system) {
    if ("http://snomed.info/sct".equals(system))
      return "SCT";
    return system;
  }

  private String readStr(JsonObject json, String name) {
    JsonElement e = json.get(name);
    if (e == null)
      return "";
    if (!(e instanceof JsonPrimitive))
      return "";
    return e.getAsString();
  }

  private String saveNoState() {
    if (cbxServer.getSelectedIndex() == -1)
      return "<html><body>No Server selected</body></html>";
    else if (Utilities.noString(txtName.getText()))
      return "<html><body>No Closure Name Provided</body></html>";
    else
      return "<html><body>Not initialised<br/></body></html>";    
  }

  private JsonObject getCurrentTable() throws IOException {
    File file = new File(Utilities.path(cache, "closure-"+Integer.toString(cbxServer.getSelectedIndex())+"-"+txtName.getText()+".json"));
    if (!file.exists())
      return null;
    try {
      return (JsonObject) new JsonParser().parse(TextFile.fileToString(file.getAbsolutePath()));
    } catch (Exception e) {
      return null;
    }
  }

  private void saveCurrentTable(JsonObject json) throws IOException {
    File file = new File(Utilities.path(cache, "closure-"+Integer.toString(cbxServer.getSelectedIndex())+"-"+txtName.getText()+".json"));
    TextFile.stringToFile(new Gson().toJson(json), file.getAbsolutePath());    
  }



}
