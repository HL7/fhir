package org.hl7.fhir.definitions.uml;

import java.util.ArrayList;
import java.util.List;

public class UMLDiagram extends UMLEntity {

  public class Point {
    private int x;
    private int y;
    
    public Point() {
      super();
      x = 0;
      y = 0;
    }
    public Point(int x, int y) {
      super();
      this.x = x;
      this.y = y;
    }
    public int getX() {
      return x;
    }
    public void setX(int x) {
      this.x = x;
    }
    public int getY() {
      return y;
    }
    public void setY(int y) {
      this.y = y;
    }
    
  }
  
  public class UMLDiagramAssociation {
    private UMLAssociation details;
    private Point start = new Point();
    private Point end = new Point();
    public UMLAssociation getDetails() {
      return details;
    }
    public void setDetails(UMLAssociation details) {
      this.details = details;
    }
    public Point getStart() {
      return start;
    }
    public Point getEnd() {
      return end;
    }
    
    
  }

  public class UMLDiagramGeneralization {
    private UMLClass general;
    private UMLClass special;
    private Point start = new Point();
    private Point end = new Point();
    private boolean showAttributes;
    public UMLClass getGeneral() {
      return general;
    }
    public void setGeneral(UMLClass general) {
      this.general = general;
    }
    public UMLClass getSpecial() {
      return special;
    }
    public void setSpecial(UMLClass special) {
      this.special = special;
    }
    public boolean isShowAttributes() {
      return showAttributes;
    }
    public void setShowAttributes(boolean showAttributes) {
      this.showAttributes = showAttributes;
    }
    public Point getStart() {
      return start;
    }
    public Point getEnd() {
      return end;
    }
    
    
  }

  public class UMLDiagramClass {
    private UMLClass details;
    private Point topLeft = new Point();
    private Point bottomRight = new Point();
    private boolean showAttributes;
    public UMLClass getDetails() {
      return details;
    }
    public void setDetails(UMLClass details) {
      this.details = details;
    }
    public boolean isShowAttributes() {
      return showAttributes;
    }
    public void setShowAttributes(boolean showAttributes) {
      this.showAttributes = showAttributes;
    }
    public Point getTopLeft() {
      return topLeft;
    }
    public Point getBottomRight() {
      return bottomRight;
    }
    
  }

  private Point size = new Point(100, 100);
  private List<UMLDiagramClass> classes =  new ArrayList<>();
  private List<UMLDiagramAssociation> assocations =  new ArrayList<>();
  private List<UMLDiagramGeneralization> generalizations =  new ArrayList<>();
  
  public UMLDiagram(String name) {
    super(name);
  }

  public List<UMLDiagramClass> getClasses() {
    return classes;
  }

  public List<UMLDiagramAssociation> getAssocations() {
    return assocations;
  }

  public List<UMLDiagramGeneralization> getGeneralizations() {
    return generalizations;
  }

  public Point getSize() {
    return size;
  }

  public void setSize(Point size) {
    this.size = size;
  }

  
}
