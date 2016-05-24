package org.hl7.fhir.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolItem;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.validation.ValidationEngine;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.dstu3.validation.Validator;
import org.hl7.fhir.utilities.TextFile;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FHIRTools {
  private static Text text;
  private static ToolItem tltmDropdownItem;
  private static ValidationEngine validator;

  /**
   * Launch the application.
   * @param args
   */
  public static void main(String[] args) {
    Display display = Display.getDefault();
    final Shell shell = new Shell();
    shell.setSize(686, 300);
    shell.setText("SWT Application");
    shell.setLayout(new BorderLayout(0, 0));
    
    TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
    
    TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
    tabItem.setText("New Item");
    
    Composite composite = new Composite(tabFolder, SWT.NONE);
    tabItem.setControl(composite);
    composite.setLayout(new BorderLayout(0, 0));
    
    ToolBar toolBar = new ToolBar(composite, SWT.BORDER | SWT.FLAT | SWT.RIGHT);
    toolBar.setLayoutData(BorderLayout.NORTH);
    
    ToolItem tltmValidate = new ToolItem(toolBar, SWT.NONE);
    tltmValidate.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        validateFile(tltmDropdownItem.getText());
      }
    });
    tltmValidate.setText("Validate");
    
    ToolItem tltmChooseFile = new ToolItem(toolBar, SWT.NONE);
    tltmChooseFile.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(SelectionEvent e) {
        FileDialog fd = new FileDialog(shell);
        fd.setFilterExtensions(new String [] {"*.xml", "*.json", "*.ttl"});
        tltmDropdownItem.setText(fd.open());
      }
    });
    tltmChooseFile.setText("Choose File");
    
    tltmDropdownItem = new ToolItem(toolBar, SWT.DROP_DOWN);
    tltmDropdownItem.setWidth(250);
    
    text = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
    text.setLayoutData(BorderLayout.CENTER);
    
    TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
    tbtmNewItem_1.setText("Publish IG");
    
    TabItem tbtmNewItem_2 = new TabItem(tabFolder, SWT.NONE);
    tbtmNewItem_2.setText("Publish Specification");
    
    TabItem tbtmNewItem_3 = new TabItem(tabFolder, SWT.NONE);
    tbtmNewItem_3.setText("Closure Table Testing");

    shell.open();
    shell.layout();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch()) {
        display.sleep();
      }
    }
  }

  protected static void validateFile(String srcFile) {
    text.setText("Working...");
    text.update();
    try {
      if (validator == null) {
        validator = new ValidationEngine();
        validator.readDefinitions("C:\\work\\org.hl7.fhir\\build\\publish\\validation.xml.zip");
        validator.connectToTSServer("http://fhir3.healthintersections.com.au/open");
      }
      validator.setSource(TextFile.fileToBytes(srcFile));
      validator.process();
    } catch (Exception e) {
      StringWriter errors = new StringWriter();
      e.printStackTrace(new PrintWriter(errors));
      text.setText("Exception: \r\n"+errors.toString());
      validator = null;
      return;
    }
    StringBuilder b = new StringBuilder();
    b.append("Validating "+srcFile+": "+Integer.toString(validator.getOutputs().size())+" messages");
    int count = 0;
    for (ValidationMessage t : validator.getOutputs()) {
      if (t.getLevel() == IssueSeverity.ERROR || t.getLevel() == IssueSeverity.FATAL)
        count++;
    }
    if (count == 0)
      b.append(" ...success\r\n\r\n");
    else
      b.append(" ...failure\r\n\r\n");
    for (ValidationMessage v : validator.getOutputs()) {
      b.append(v.summary());
      b.append("\r\n");
    }
    text.setText(b.toString());
  }
}
