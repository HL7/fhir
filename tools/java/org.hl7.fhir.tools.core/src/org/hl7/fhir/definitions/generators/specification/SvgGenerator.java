package org.hl7.fhir.definitions.generators.specification;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLWriter;

public class SvgGenerator extends BaseGenerator {

  private enum PointKind {
    unknown, left, right, top, bottom;
  }

  private static final String NS_SVG = "http://www.w3.org/2000/svg";
  private static final String NS_XLINK = "http://www.w3.org/1999/xlink";
  private static final double LINE_HEIGHT = 14;
  private static final double HEADER_HEIGHT = 20;
  private static final double GAP_HEIGHT = 4;
  private static final double LEFT_MARGIN = 6;
  private static final double SELF_LINK_HEIGHT = 25;
  private static final double SELF_LINK_WIDTH = 60;
  private static final double DUPLICATE_GAP = 50;
  private static final double MARGIN_X = 100;
  private static final double MARGIN_Y = 10;
  private static final double WRAP_INDENT = 20;

  private class Point {
    private PointKind kind;
    public Point(double x, double y, PointKind kind) {
      this.x = x;
      this.y = y;
      this.kind = kind;
    }
    private double x;
    private double y;
    private String toPoint() {
      return Double.toString(x)+","+Double.toString(y);
    }
  }
  private class Segment {
    
    public final Point start, end;
    public final boolean isVertical; 
    public final double slope, intercept; 
     
    public Segment(Point start, Point end) {
     this.start = start;
     this.end = end;
     //set isVertical, which indicates whether this Line 
     //is vertical or not on the coordinate plane
     if (start.x == end.x)
      isVertical = true;
     else
      isVertical = false;
      
     //set slope and intercept
     if (!isVertical){
      slope = (this.start.y - this.end.y) / (this.start.x - this.end.x);
      intercept = (this.end.x * this.start.y - this.start.x * this.end.y ) /(this.start.x - this.end.x);
     }
     else {
      slope = Double.MAX_VALUE;
      intercept = - Double.MAX_VALUE;
     }
    }
   }
  private class ClassItem {
    public ClassItem(double left, double top, double width, double height) {
      this.left = left;
      this.top = top;
      this.width = width;
      this.height = height;          
    }
    private double left;
    private double top;
    private double width;
    private double height;
    public double right() {
      return left + width;
    }
    public double centerH() {
      return left + width / 2;
    }
    public double centerV() {
      return top + height / 2;
    }
    public double bottom() {
      return top + height;
    }
  }
  private class Link {
    private String path;
    private String description;
    public Link(ClassItem source, ClassItem target, String name, String cardinality, PointKind kind, String path, String description) {
      this.source = source;
      this.target = target;
      this.name = name;
      this.cardinality = cardinality;
      this.kind = kind;
      this.path = path;
      this.description = description;
    }
    private ClassItem source;
    private ClassItem target;
    private String name;
    private String cardinality;
    private PointKind kind;
    private int count;
    private int index;
  }

  private Map<ElementDefn, ClassItem> classes = new HashMap<ElementDefn, ClassItem>();
  private Map<String, ElementDefn> fakes = new HashMap<String, ElementDefn>();
  private List<Link> links = new ArrayList<SvgGenerator.Link>();  
  private double minx = 0;
  private double miny = 0;
  private boolean attributes = true;
  IniFile ini;

  public SvgGenerator(PageProcessor page) {
    this.definitions = page.getDefinitions();
    this.page = page;
  }

  public String generate(String filename) throws Exception {
    ini = new IniFile(filename);
    String[] classNames = ini.getStringProperty("diagram", "classes").split("\\,");
    if ("false".equals(ini.getStringProperty("diagram", "attributes")))
      attributes = false;
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    XMLWriter xml = new XMLWriter(bytes, "UTF-8");
    
    minx = 0;
    miny = 0;
    
    Point size = determineMetrics(classNames);
    adjustAllForMin(size);
    xml.setPretty(false);
    xml.start();
    xml.setDefaultNamespace(NS_SVG);
    xml.namespace(NS_XLINK, "xlink");
    xml.attribute("version", "1.1");
    xml.attribute("width", Double.toString(size.x));
    xml.attribute("height", Double.toString(size.y));
    xml.open("svg");
    shadowFilter(xml);
    drawElement(xml, classNames);
    countDuplicateLinks();
    for (Link l : links) {
      drawLink(xml, l);
    }
    xml.close("svg");
    xml.close();
    
    String s = new String(bytes.toByteArray());
    return s.substring(s.indexOf(">")+1);
  }

  public String generate(ResourceDefn resource) throws Exception {
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      XMLWriter xml = new XMLWriter(bytes, "UTF-8");
      generate(resource, xml);
      String s = new String(bytes.toByteArray());
      return s.substring(s.indexOf(">")+1);
  }

  public void generate(ResourceDefn resource, String filename) throws Exception {
    classes.clear();
    links.clear();
    XMLWriter xml = new XMLWriter(new FileOutputStream(filename), "UTF-8");
    generate(resource, xml);
  }

  private void generate(ResourceDefn resource, XMLWriter xml) throws Exception {
    minx = 10000;
    miny = 10000;
    
    Point size = determineMetrics(resource.getRoot(), null, resource.getRoot().getName(), true, null);
    adjustAllForMin(size);
    xml.setPretty(false);
    xml.start();
    xml.setDefaultNamespace(NS_SVG);
    xml.namespace(NS_XLINK, "xlink");
    xml.attribute("version", "1.1");
    xml.attribute("width", Double.toString(size.x));
    xml.attribute("height", Double.toString(size.y));
    xml.open("svg");
    shadowFilter(xml);
    drawClass(xml, resource.getRoot(), true, resource, false, resource.getName(), null);
    countDuplicateLinks();
    for (Link l : links) {
      drawLink(xml, l);
    }
    xml.close("svg");
    xml.close();
  }

  private void adjustAllForMin(Point size) {
    size.x = size.x - minx;
    size.y = size.y - miny;
    for (ClassItem t : classes.values()) {
      t.left = t.left - minx;
      t.top = t.top - miny;
    }
  }

  private Point determineMetrics(String[] classNames) throws Exception {
    double width = textWidth("Element") * 1.8;
    double height = HEADER_HEIGHT + GAP_HEIGHT*2;
    if ("true".equals(ini.getStringProperty("diagram", "element-attributes"))) {
      height = height + LINE_HEIGHT + GAP_HEIGHT;
      width = textWidth("extension : Extension 0..*");
    }

    Point p = new Point(0, 0, PointKind.unknown);
    ClassItem item = new ClassItem(p.x, p.y, width, height);
    classes.put(null, item);
    double x = item.right()+MARGIN_X;
    double y = item.bottom()+MARGIN_Y;
    
    for (String cn : classNames) {
      if (definitions.getPrimitives().containsKey(cn)) {
        DefinedCode cd = definitions.getPrimitives().get(cn);
        ElementDefn fake = new ElementDefn();
        fake.setName(cn);
        fakes.put(cn, fake);
        if (cd instanceof DefinedStringPattern)
          p = determineMetrics(fake, classes.get(fakes.get(((DefinedStringPattern) cd).getBase())), cn, false, cd);
        else
          p = determineMetrics(fake, item, cn, false, cd);        
      } else if (definitions.getConstraints().containsKey(cn)) {
        ProfiledType cd = definitions.getConstraints().get(cn);
        ElementDefn ed = definitions.getElementDefn(cd.getBaseType());
        ClassItem parentClss = classes.get(ed);
        ElementDefn fake = new ElementDefn();
        fake.setName(cn);
        fakes.put(cn, fake);
        p = determineMetrics(fake, parentClss, cn, false, null);
      } else {
        ElementDefn c = definitions.getElementDefn(cn);
        p = determineMetrics(c, item, c.getName(), false, null);
      }
      x = Math.max(x, p.x+MARGIN_X);
      y = Math.max(y, p.y+MARGIN_Y);
    }
    return new Point(x, y, PointKind.unknown);
  }
  
  private Point determineMetrics(ElementDefn e, ClassItem source, String path, boolean isRoot, DefinedCode primitive) {
    
    double width = textWidth(e.getName()) * 1.8 + (isRoot ? textWidth(" (Resource)") : 0);
    double height;
    if (attributes) {
      if (primitive != null) {
        if (primitive instanceof PrimitiveType) {
          height = HEADER_HEIGHT + GAP_HEIGHT*2 + LINE_HEIGHT + GAP_HEIGHT * 2;
          width = textWidth("value : "+getXsi(primitive)+" 0..1");
        }
        else
          height = HEADER_HEIGHT + GAP_HEIGHT*2;         
      } else {
        int i = 0;
        for (ElementDefn c : e.getElements()) 
          if (isAttribute(c)) {
            String[] texts = textForAttribute(c);
            i = i + texts.length;
            double w = textWidth(texts[0]);
            for (int j = 1; j < texts.length; j++)
              w = Math.max(w, textWidth(texts[j]));
            if (w > width)
              width = w;
          }
        height = HEADER_HEIGHT + GAP_HEIGHT*2 + LINE_HEIGHT * i + GAP_HEIGHT * 2;
      }
    }  else
      height = HEADER_HEIGHT + GAP_HEIGHT*2;

    if (ini != null) {
      String uml = ini.getStringProperty("directions", path);
      if (!Utilities.noString(uml) && uml.contains(";")) {
        String[] svg = uml.split("\\;");
        e.setSvgLeft(Integer.parseInt(svg[0]));
        e.setSvgTop(Integer.parseInt(svg[1]));
      } else
        e.setUmlDir(uml);
    }
    
    Point p = new Point(e.getSvgLeft(), e.getSvgTop(), PointKind.unknown);
    if (p.y == ElementDefn.MAX_NEG || p.x == ElementDefn.MAX_NEG) {
      if ("left".equals(e.getUmlDir())) {
        p.x = source.left - 120 - width;
        p.y = source.centerV() - height / 2;
        p = findEmptyPlace(p, width, height, 0, 80);
      } else if ("right".equals(e.getUmlDir())) {
        p.x = source.right() + 120;
        p.y = source.centerV() - height / 2;
        p = findEmptyPlace(p, width, height, 0, 80);
      } else if ("up".equals(e.getUmlDir())) {
        p.x = source.centerH() - width / 2;
        p.y = source.top - height - 80;
        p = findEmptyPlace(p, width, height, 80, 0);
      } else if ("down".equals(e.getUmlDir())) {
        p.x = source.centerH() - width / 2;
        p.y = source.bottom() + 80;
        p = findEmptyPlace(p, width, height, +80, 0);
      } else {
        p.y = 0;
        p.x = 0;
        p = findEmptyPlace(p, width, height, 80, 0);
      }
    }
    miny = Math.min(miny, p.y);
    minx = Math.min(minx, p.x);
    ClassItem item = new ClassItem(p.x, p.y, width, height);
    classes.put(e, item);
    double x = item.right()+MARGIN_X;
    double y = item.bottom()+MARGIN_Y;
    
    if (attributes) {
      for (ElementDefn c : e.getElements()) {  
        if (!isAttribute(c) && (Utilities.noString(c.typeCode()) || !c.typeCode().startsWith("@"))) {
          p = determineMetrics(c, item, path+"."+c.getName(), false, null);
          x = Math.max(x, p.x+MARGIN_X);
          y = Math.max(y, p.y+MARGIN_Y);
        }
      }
    }
    return new Point(x, y, PointKind.unknown);
  }

  private Point findEmptyPlace(Point p, double width, double height, double dx, double dy) {
    while (overlaps(p.x, p.y, width, height)) {
      p.x = p.x + dx;
      p.y = p.y + dy;
    }
    return p;
  }

  private boolean overlaps(double x, double y, double w, double h) {
    for (ClassItem c : classes.values()) {
      if ((inBounds(x, c.left, c.right()) || inBounds(x+w, c.left, c.right())) &&
          (inBounds(y, c.top, c.bottom()) || inBounds(y+h, c.top, c.bottom())))
        return true;
      if ((inBounds(c.left, x, x+w) || inBounds(c.right(), x, x+w)) &&
          (inBounds(c.top, y, y+h) || inBounds(c.bottom(), y, y+h)))
        return true;
    }
    return false;
  }
  
  private void countDuplicateLinks() {
    for (int i = 0; i < links.size(); i++) {
      Link l = links.get(i);
      if (l.count == 0) {
        int c = 0;
        for (int j = i+1; j < links.size(); j++) {
          Link l2 = links.get(j);
          if ((l2.source == l.source && l2.target == l.target) ||
              (l2.source == l.target && l2.target == l.source))
            c++;
        }     
        l.count = c;
        if (c > 0) {
          int k = 0;
          for (int j = i+1; j < links.size(); j++) {
            Link l2 = links.get(j);
            if ((l2.source == l.source && l2.target == l.target) ||
                (l2.source == l.target && l2.target == l.source) ) {
              k++;
              l2.count = c;
              l2.index = k;
            }
          }     
        }
      }
    }
  }

  private void drawLink(XMLWriter xml, Link l) throws Exception {
    Point start;
    Point end;
    Point p1;
    Point p2;
    if (l.source == l.target) {
      start = new Point(l.source.right(), l.source.centerV() - SELF_LINK_HEIGHT, PointKind.unknown);
      end = new Point(l.source.right(), l.source.centerV() + SELF_LINK_HEIGHT, PointKind.right);
      p1 = new Point(l.source.right() + SELF_LINK_WIDTH, l.source.centerV() - SELF_LINK_HEIGHT, PointKind.unknown);
      p2 = new Point(l.source.right() + SELF_LINK_WIDTH, l.source.centerV() + SELF_LINK_HEIGHT, PointKind.unknown);

      xml.attribute("x1", Double.toString(start.x));
      xml.attribute("y1", Double.toString(start.y));
      xml.attribute("x2", Double.toString(p1.x));
      xml.attribute("y2", Double.toString(p1.y));
      xml.attribute("style", "stroke:navy;stroke-width:1");
      xml.element("line", null);    
      xml.attribute("x1", Double.toString(p1.x));
      xml.attribute("y1", Double.toString(p1.y));
      xml.attribute("x2", Double.toString(p2.x));
      xml.attribute("y2", Double.toString(p2.y));
      xml.attribute("style", "stroke:navy;stroke-width:1");
      xml.element("line", null);    
      xml.attribute("x1", Double.toString(p2.x));
      xml.attribute("y1", Double.toString(p2.y));
      xml.attribute("x2", Double.toString(end.x));
      xml.attribute("y2", Double.toString(end.y));
      xml.attribute("style", "stroke:navy;stroke-width:1");
      xml.element("line", null);    
      

    } else {
      Point c1 = new Point(l.source.centerH(), l.source.centerV(), PointKind.unknown);
      Point c2 = new Point(l.target.centerH(), l.target.centerV(), PointKind.unknown);

      start = intersection(c1, c2, l.source);
      end = intersection(c1, c2, l.target);
      if (l.count > 0) {
        start.x = adjustForDuplicateX(start.x, start.kind, l.index);
        start.y = adjustForDuplicateY(start.y, start.kind, l.index);
        end.x = adjustForDuplicateX(end.x, end.kind, l.index);
        end.y = adjustForDuplicateY(end.y, end.kind, l.index);
      
      }
      p1 = end;
      p2 = start;
      if (start != null && end != null) {
        xml.attribute("x1", Double.toString(start.x));
        xml.attribute("y1", Double.toString(start.y));
        xml.attribute("x2", Double.toString(end.x));
        xml.attribute("y2", Double.toString(end.y));
        xml.attribute("style", "stroke:navy;stroke-width:1");
        xml.element("line", null);    
      }
    }

    if (start != null && end != null) {
      if (l.name == null){
        Point pd1 = calcGenRight(start, p1);
        Point pd2 = calcGenLeft(start, p1);
        xml.attribute("points", start.toPoint() +" " +pd1.toPoint() +" " +pd2.toPoint() +" " +start.toPoint());
        xml.attribute("style", "fill:white;stroke:navy;stroke-width:1");
        xml.attribute("transform", "rotate("+getAngle(start, p1)+" "+Double.toString(start.x)+" "+Double.toString(start.y)+")");
        xml.element("polygon", null);
      } else {
        // draw the diamond
        Point pd2 = calcDiamondEnd(start, p1);
        Point pd1 = calcDiamondRight(start, p1);
        Point pd3 = calcDiamondLeft(start, p1);
        xml.attribute("points", start.toPoint() +" " +pd1.toPoint() +" " +pd2.toPoint() +" " +pd3.toPoint()+" "+start.toPoint());
        xml.attribute("style", "fill:navy;stroke:navy;stroke-width:1");
        xml.attribute("transform", "rotate("+getAngle(start, p1)+" "+Double.toString(start.x)+" "+Double.toString(start.y)+")");
        xml.element("polygon", null);

        // draw the name half way along
        double x = (int) (p1.x + p2.x) / 2;
        double y = (int) (p1.y + p2.y) / 2 + LINE_HEIGHT / 2 + LINE_HEIGHT * l.index;
        double w = (int) (textWidth(l.name));        
        xml.attribute("x", Double.toString(x - w/2));
        xml.attribute("y", Double.toString(y - LINE_HEIGHT ));
        xml.attribute("width", Double.toString(w));
        xml.attribute("height", Double.toString(LINE_HEIGHT + GAP_HEIGHT));
        xml.attribute("style", "fill:white;stroke:black;stroke-width:0");
        xml.element("rect", null);    
        xml.attribute("x", Double.toString(x));
        xml.attribute("y", Double.toString(y - GAP_HEIGHT));
        xml.attribute("fill", "black");
        xml.attribute("class", "diagram-class-linkage");
        xml.open("text");
        xml.attribute("xlink:href", l.path);
        xml.open("a");
        xml.element("title", l.description);
        xml.text(l.name);
        xml.close("a");
        xml.close("text");

        // draw the cardinality at the terminal end
        x = end.x;
        y = end.y;
        if (end.kind == PointKind.left) {
          y = y - GAP_HEIGHT;
          x = x - 20;
        } else if (end.kind == PointKind.top)
          y = y - GAP_HEIGHT;
        else if (end.kind == PointKind.right) {
          y = y - GAP_HEIGHT;
          x = x + 15;
        } else if (end.kind == PointKind.bottom) 
          y = y + LINE_HEIGHT;
        w = 18;        
        xml.attribute("x", Double.toString(x));
        xml.attribute("y", Double.toString(y));
        xml.attribute("fill", "black");
        xml.attribute("class", "diagram-class-linkage");
        xml.element("text", l.cardinality);
      }
    }
  }

  private double adjustForDuplicateX(double x, PointKind kind, int index) {
    switch (kind) {
    case bottom: 
      return x + (DUPLICATE_GAP * (index - 0.5));
    case top:
      return x + (DUPLICATE_GAP * (index - 0.5));
    default:
      return x;        
    }
  }

  private double adjustForDuplicateY(double y, PointKind kind, int index) {
    switch (kind) {
    case left: 
      return y - (DUPLICATE_GAP * (index - 0.5));
    case right:
      return y - (DUPLICATE_GAP * (index - 0.5));
    default:
      return y;        
    }
  }

  private String getAngle(Point start, Point end) {
    double inRads = Math.atan2(end.y - start.y, end.x-start.x);
//    if (inRads < 0)
//      inRads = Math.abs(inRads);
//  else
//      inRads = 2*Math.PI - inRads;

    return Double.toString(Math.toDegrees(inRads));
  }

  private Point calcDiamondEnd(Point start, Point end) {
    return new Point(start.x+12, start.y+0, PointKind.unknown);
  }

  private Point calcDiamondRight(Point start, Point end) {
    return new Point(start.x+6, start.y+4, PointKind.unknown);
  }

  private Point calcDiamondLeft(Point start, Point end) {
    return new Point(start.x+6, start.y-4, PointKind.unknown);
  }

  private Point calcGenRight(Point start, Point end) {
    return new Point(start.x+8, start.y+6, PointKind.unknown);
  }

  private Point calcGenLeft(Point start, Point end) {
    return new Point(start.x+8, start.y-6, PointKind.unknown);
  }

  private Point intersection(Point start, Point end, ClassItem box) {
    Point p = calculateIntersect(start.x, start.y, end.x, end.y, box.left, box.top, box.left + box.width, box.top, PointKind.top);
    if (p == null)
      p = calculateIntersect(start.x, start.y, end.x, end.y, box.left, box.top+box.height, box.left+box.width, box.top+box.height, PointKind.bottom);
    if (p == null)
      p = calculateIntersect(start.x, start.y, end.x, end.y, box.left, box.top, box.left, box.top+box.height, PointKind.left);
    if (p == null)
      p = calculateIntersect(start.x, start.y, end.x, end.y, box.left+box.width, box.top, box.left+box.width, box.top+box.height, PointKind.right);
    return p;
  }

  private Point calculateIntersect(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, PointKind kind) {
    Segment s1 = new Segment(new Point(x1,y1, PointKind.unknown),  new Point(x2,y2, PointKind.unknown));
    Segment s2 = new Segment(new Point(x3,y3, PointKind.unknown),  new Point(x4,y4, PointKind.unknown));
    return hasIntersection(s1, s2, kind);
//    double slope1 = (y2-y1) / (x2-x1);
//    double slope2 = (y4-y3) / (x4-x3);
//
//    if (Math.abs(slope1 - slope2) < 0.000001)
//      return null;
//    
//    double x = ( ( (x4*y3 - y4*x3) / (x4-x3) ) - ( (x2-y1 - y2*x1) / (x2-x1) ) ) / ( slope1 - slope2 );
//    double y = slope1 * x + ( (x2*y1 - y2*x1) / (x2-x1) );
//    
//    if (inBounds(x, x1, x2) && inBounds(x, x3, x4) && inBounds(y, y1, y2) && inBounds(y, y3, y4))
//      return new Point((int) x, (int) y);
//    else
//      return null;
  }

  private boolean inBounds(double x, double x1, double x2) {
    return (x1 < x2) ? (x >= x1 && x <= x2) : (x >= x2 && x <= x1);
  }

  private void shadowFilter(XMLWriter xml) throws IOException {
    xml.escapedText(
        "  <defs>\r\n"+
            "    <filter id=\"shadow\" x=\"0\" y=\"0\" width=\"200%\" height=\"200%\">\r\n"+
            "      <feOffset result=\"offOut\" in=\"SourceGraphic\" dx=\"3\" dy=\"3\" />\r\n"+
            "      <feColorMatrix result=\"matrixOut\" in=\"offOut\" type=\"matrix\" values=\"0.2 0 0 0 0 0 0.2 0 0 0 0 0 0.2 0 0 0 0 0 1 0\" />\r\n"+
            "      <feGaussianBlur result=\"blurOut\" in=\"matrixOut\" stdDeviation=\"2\" />\r\n"+
            "      <feBlend in=\"SourceGraphic\" in2=\"blurOut\" mode=\"normal\" />\r\n"+
            "    </filter>\r\n"+
        "  </defs>");

  }

  private ClassItem drawElement(XMLWriter xml, String[] classNames) throws Exception {
    ClassItem item = classes.get(null);
    String tn = "Element";
    xml.attribute("x", Double.toString(item.left));
    xml.attribute("y", Double.toString(item.top));
    xml.attribute("rx", "4");
    xml.attribute("ry", "4");
    xml.attribute("width", Double.toString(item.width));
    xml.attribute("height", Double.toString(item.height));
    xml.attribute("filter", "url(#shadow)");
    xml.attribute("style", "fill:#f0f8ff;stroke:black;stroke-width:1");
    xml.element("rect", null);    

    xml.attribute("x", Double.toString(item.left + item.width / 2));
    xml.attribute("y", Double.toString(item.top+HEADER_HEIGHT));
    xml.attribute("fill", "black");
    xml.attribute("class", "diagram-class-title");
    xml.element("text", tn);
    
    if ("true".equals(ini.getStringProperty("diagram", "element-attributes"))) {
      xml.attribute("x1", Double.toString(item.left));
      xml.attribute("y1", Double.toString(item.top+HEADER_HEIGHT + GAP_HEIGHT*2));
      xml.attribute("x2", Double.toString(item.left+item.width));
      xml.attribute("y2", Double.toString(item.top+HEADER_HEIGHT + GAP_HEIGHT*2));
      xml.attribute("style", "stroke:dimgrey;stroke-width:1");
      xml.element("line", null);    
      addExtension(xml, item.left, item.top+HEADER_HEIGHT + GAP_HEIGHT*2 + LINE_HEIGHT);
    }

    for (String cn : classNames) {
      if (definitions.getPrimitives().containsKey(cn)) {
        DefinedCode cd = definitions.getPrimitives().get(cn);
        ElementDefn fake = fakes.get(cn);
        if (cd instanceof DefinedStringPattern)
          links.add(new Link(classes.get(fakes.get(((DefinedStringPattern) cd).getBase())), drawClass(xml, fake, false, null, true, null, cd), null, null, PointKind.unknown, null, null));        
        else
          links.add(new Link(item, drawClass(xml, fake, false, null, true, null, cd), null, null, PointKind.unknown, null, null));        
      } else if (definitions.getConstraints().containsKey(cn)) {
        ProfiledType cd = definitions.getConstraints().get(cn);
        ElementDefn fake = fakes.get(cn);
        ClassItem parent = classes.get(definitions.getElementDefn(cd.getBaseType()));
        links.add(new Link(parent, drawClass(xml, fake, false, null, true, null, null), null, null, PointKind.unknown, null, null));        
      } else 
        links.add(new Link(item, drawClass(xml, definitions.getElementDefn(cn), false, null, true, cn, null), null, null, PointKind.unknown, null, null));        
    }
    return item;
  }

  private ClassItem drawClass(XMLWriter xml, ElementDefn e, boolean isRoot, ResourceDefn resource, boolean link, String path, DefinedCode primitive) throws Exception {
    ClassItem item = classes.get(e);
    String tn = e.getName();
    if (!definitions.hasPrimitiveType(tn))
      tn = Utilities.capitalize(tn);
      
    xml.attribute("x", Double.toString(item.left));
    xml.attribute("y", Double.toString(item.top));
    xml.attribute("rx", "4");
    xml.attribute("ry", "4");
    xml.attribute("width", Double.toString(item.width));
    xml.attribute("height", Double.toString(item.height));
    xml.attribute("filter", "url(#shadow)");
    if (fakes.values().contains(e) && primitive == null)
      xml.attribute("style", "fill:#f8ddf8;stroke:black;stroke-width:1");
    else if (primitive instanceof DefinedStringPattern)
      xml.attribute("style", "fill:#f8ddf8;stroke:black;stroke-width:1");
    else
      xml.attribute("style", "fill:#f0f8ff;stroke:black;stroke-width:1");
    xml.element("rect", null);    

    xml.attribute("x1", Double.toString(item.left));
    xml.attribute("y1", Double.toString(item.top+HEADER_HEIGHT + GAP_HEIGHT*2));
    xml.attribute("x2", Double.toString(item.left+item.width));
    xml.attribute("y2", Double.toString(item.top+HEADER_HEIGHT + GAP_HEIGHT*2));
    xml.attribute("style", "stroke:dimgrey;stroke-width:1");
    xml.element("line", null);    

    xml.attribute("x", Double.toString(item.left + item.width / 2));
    xml.attribute("y", Double.toString(item.top+HEADER_HEIGHT));
    xml.attribute("fill", "black");
    if (isRoot) 
      xml.attribute("class", "diagram-class-title  diagram-class-resource");
    else 
      xml.attribute("class", "diagram-class-title");
    if (link) {
      xml.open("text");
      if (tn.equals("Extension") || tn.equals("Reference") || tn.equals("Narrative"))
        xml.attribute("xlink:href", GeneratorUtils.getSrcFile(tn, false) + ".html#"+tn.toLowerCase());
      else
        xml.attribute("xlink:href", "#"+tn.toLowerCase());
      xml.open("a");
      xml.text(tn);
      xml.close("a");
      xml.close("text");
    } else if (isRoot) {
      xml.open("text");
      xml.text(tn);
      if (Utilities.noString(e.typeCode())) {
        xml.text(" «Resource»");
      } else {
        xml.attribute("class", "diagram-class-title-link");
        xml.open("tspan");
        xml.text(" (");
        xml.attribute("xlink:href", e.typeCode().toLowerCase()+".html");
        xml.attribute("class", "diagram-class-reference");
        xml.element("a", e.typeCode());
        xml.text(")");
        xml.close("tspan");
      }
      xml.close("text");
    } else if (e.hasStatedType()) {
      xml.element("text", e.getStatedType());      
    } else
      xml.element("text", tn);

    if (attributes) {
      if (primitive != null) {
        if (primitive instanceof PrimitiveType)
          addValueAttribute(xml, item.left, item.top+HEADER_HEIGHT + GAP_HEIGHT*2 + LINE_HEIGHT, getXsi(primitive).split("\\|"));
      } else {
        int i = 0;
        for (ElementDefn c : e.getElements()) {
          if (isAttribute(c)) {
            i++;
            addAttribute(xml, item.left, item.top+HEADER_HEIGHT + GAP_HEIGHT*2 + LINE_HEIGHT * i, c, path);
            String[] texts = textForAttribute(c);
            i = i + texts.length - 1;
          }
        }
      }

      for (ElementDefn c : e.getElements()) {  
        if (!isAttribute(c)) {
          if (Utilities.noString(c.typeCode()) || !c.typeCode().startsWith("@")) {
            links.add(new Link(item, drawClass(xml, c, false, resource, false, path+"."+c.getName(), null), c.getName(), c.describeCardinality(), PointKind.unknown, baseUrl(path)+path+"."+c.getName(), c.getEnhancedDefinition()));        
          } else {
            ClassItem target = getItemForPath(resource, c.typeCode().substring(1));
            links.add(new Link(item, target, c.getName(), c.describeCardinality(), PointKind.unknown, baseUrl(path)+path+"."+c.getName(), c.getEnhancedDefinition()));                  
          }
        }
      }
    }
    return item;
  }

  private String getXsi(DefinedCode primitive) {
    String xs = ((PrimitiveType) primitive).getSchemaType();
    if (!xs.startsWith("xs:"))
      xs = "xs:"+xs;
    return xs.replace(", ", "|");
  }

  private String baseUrl(String path) throws Exception {
    String root = path.contains(".") ? path.substring(0, path.indexOf(".")) : path;
    if (definitions.hasResource(root))
      return root.toLowerCase()+"-definitions.html#";
    else if ("Narrative".equals(root))
      return "narrative-definitions.html#";
    else if ("Reference".equals(root))
      return "references-definitions.html#";
    else if ("Extension".equals(root))
      return "extensibility-definitions.html#";
    else if (definitions.hasType(root))
      return "datatypes-definitions.html#";
    if (definitions.getBaseResources().containsKey(root))
      return root.toLowerCase()+"-definitions.html#";
    else
      throw new Exception(root+" not handled yet");
  }

  private ClassItem getItemForPath(ResourceDefn resource, String path) throws Exception {
    ElementDefn e = resource.getRoot().getElementForPath(path, definitions, "SVG diagram", false);
    return classes.get(e);
  }

  private double textWidth(String text) {
    return text.length() * 4;
  }

  private String[] textForAttribute(ElementDefn e) {
    if (e.typeCode().length() > 40) {
      String s = e.typeCode();
      List<String> lines = new ArrayList<String>();
      while (s.length() > 40) {
        int i = 40;
        while (s.charAt(i) != '|')
          i--;
        lines.add((lines.size() == 0 ? e.getName()+" : " : ".....")+s.substring(0, i+1));
        s = s.substring(i+1);
      }
      lines.add(s+" : "+e.describeCardinality()+describeBinding(e) );
      return lines.toArray(new String[] {});
    }
    else 
      return new String[] { e.getName()+" : "+e.typeCode()+" : "+e.describeCardinality()+describeBinding(e) };
  }

  private String describeBinding(ElementDefn e) {
    BindingSpecification b = definitions.getBindingByName(e.getBindingName());
    if (e.hasBinding() && b.getBinding() != Binding.Unbound) {
      if (b.getBindingStrength() == BindingStrength.Example)
        return " \u00AB ("+e.getBindingName()+") \u00BB";
      else if (b.getBindingStrength() == BindingStrength.Preferred)
        return " \u00AB "+e.getBindingName()+"+ \u00BB";
      else // if (b.getBindingStrength() == BindingStrength.Required)
        return " \u00AB "+e.getBindingName()+" \u00BB";
    } else
      return "";
  }

  private boolean isAttribute(ElementDefn c) {
    return c.getElements().size() == 0 && !c.typeCode().startsWith("@");
  }

  private void addAttribute(XMLWriter xml, double left, double top, ElementDefn e, String path) throws Exception  {
    TypeCodingProgress prog = new TypeCodingProgress();
    int i = 0;
    int length = 0;
    while (!prog.done) {
      xml.attribute("x", Double.toString(left + LEFT_MARGIN + (i == 0 ? 0 : WRAP_INDENT)));
      xml.attribute("y", Double.toString(top + LINE_HEIGHT * i));
      xml.attribute("fill", "black");
      xml.attribute("class", "diagram-class-detail");
      xml.open("text");
      
      // Start the first line with the name and ':' of the attribute
      if (i == 0) 
      {
        xml.attribute("xlink:href", baseUrl(path)+path+"."+e.getName().replace("[", "_").replace("]", "_"));
        xml.open("a");
        xml.element("title", e.getEnhancedDefinition());
        xml.text(e.getName());
        xml.close("a");
        xml.text(" : ");
      } 
      
      // We're on the next line(s) of the attribute, indent first
      else 
        xml.text("     ");
      
      // Continue constructing types. If you wonder why the types don't show
      // up multiple times: 'prog' suppresses printing new stuff until we've
      // actually arrived at our position in the list of types where we 
      // left off when we wrapped to the next line. YUCK YUCK YUCK
      encodeTypes(xml, e.getTypes(), prog);
      
      // No more lines to do, add the rest of the text
      // (and pray that that fits)
      if (prog.done) {
        xml.text(" "+e.describeCardinality());
        if (e.hasBinding() && definitions.getBindingByName(e.getBindingName()).getBinding() != Binding.Unbound) {
          BindingSpecification b = definitions.getBindingByName(e.getBindingName());
          xml.text(" \u00AB ");
          if (b.getBindingStrength() == BindingStrength.Example) {
            xml.text("(");
            xml.attribute("xlink:href", getBindingLink(e));
            xml.open("a");
            xml.element("title", definitions.getBindingByName(e.getBindingName()).getDefinition());
            xml.text(e.getBindingName());
            xml.close("a");
            xml.text(")");
          } else if (b.getBindingStrength() == BindingStrength.Preferred) {
            xml.attribute("xlink:href", getBindingLink(e));
            xml.open("a");
            xml.element("title", definitions.getBindingByName(e.getBindingName()).getDefinition());
            xml.text(e.getBindingName());
            xml.close("a");
            xml.text("+");
          } else if (b.getBindingStrength() == BindingStrength.Required) {
            xml.attribute("xlink:href", getBindingLink(e));
            xml.open("a");
            xml.element("title", definitions.getBindingByName(e.getBindingName()).getDefinition());
            //xml.open("b");
            xml.text(e.getBindingName());
            //xml.close("b");
            xml.close("a");
          } else {
            xml.attribute("xlink:href", getBindingLink(e));
            xml.open("a");
            xml.element("title", definitions.getBindingByName(e.getBindingName()).getDefinition());
            xml.text(e.getBindingName());
            xml.close("a");
          }
          xml.text(" \u00BB");
        }
      }
      xml.close("text");
      i++;
    }
  }

  private void addExtension(XMLWriter xml, double left, double top) throws Exception  {
    xml.attribute("x", Double.toString(left + LEFT_MARGIN));
    xml.attribute("y", Double.toString(top));
    xml.attribute("fill", "black");
    xml.attribute("class", "diagram-class-detail");
    xml.open("text");
    xml.attribute("xlink:href", "extensibility.html");
    xml.open("a");
    xml.element("title", "Extensions - as described for all elements: additional information that is not part of the basic definition of the resource / type");
    xml.text("extension");
    xml.close("a");
    xml.text(" : ");
    xml.attribute("xlink:href", "extensibility.html");
    xml.element("a", "Extension");
    xml.text(" 0..*");
    xml.close("text");
  }

  private void addValueAttribute(XMLWriter xml, double left, double top, String[] xsiType) throws Exception  {
    xml.attribute("x", Double.toString(left + LEFT_MARGIN));
    xml.attribute("y", Double.toString(top));
    xml.attribute("fill", "black");
    xml.attribute("class", "diagram-class-detail");
    xml.open("text");
    xml.open("tspan");
    xml.element("title", "Actual value attribute of the data type");
    xml.text("value");
    xml.close("tspan");
    xml.text(" : ");
    boolean first = true;
    for (String t : xsiType) {
      if (!first)
        xml.text("|");
      xml.attribute("xlink:href", "http://www.w3.org/TR/xmlschema-2/#"+t.substring(3));
      xml.element("a", t);
      first = false;
    }
    xml.text(" 0..1");
    xml.close("text");
  }

  
  private class TypeCodingProgress {
    boolean done = false;
    int cursor = 0;
    int count = 0;
    int start = 0;
    boolean writeToOutput = true;
    
    public void startLine() {
      count = 0;    
      start = cursor;
    }
    
    public void attribute(XMLWriter xml, String name, String value) throws IOException {
      if (writeToOutput)
        xml.attribute(name, value);
    }

    public void element(XMLWriter xml, String name, String content) throws IOException {
      if (writeToOutput) {
        xml.element(name, content);
        cursor = cursor + content.length();
      }
      count = count + content.length();
    }

    public void text(XMLWriter xml, String content) throws IOException {
      if (writeToOutput) {
        xml.text(content);
        cursor = cursor + content.length();
      }
      count = count + content.length();
    }

    public boolean breaktext(XMLWriter xml, String content, String coming) throws IOException {
      text(xml, content);
      
      // Recalculate whether we need to wrap by now
      if (writeToOutput) 
      {
        if (cursor + coming.length() - start > 40)
        {
          // Line will get too long the next time around, so
          // stop writing output
          writeToOutput = false;
          return true;  // we need to stop rendering for this line
        }
      } 
      else 
      {
        // We've reached the point where we last stopped
        // writing to output (=on the previous line), so
        // start writing again
        if (count == cursor)
          writeToOutput = true;
      }
      return false;
    }

    public void close() {
      done = writeToOutput;      
    }

  }
  
  private void encodeTypes(XMLWriter xml, List<TypeRef> types, TypeCodingProgress prog)  throws Exception {
    boolean first = true;
    
    prog.startLine();
    
    for (TypeRef tr : types) 
    {
      if (!first) 
      {
        if (prog.breaktext(xml, "|", tr.getName()))
          return;
      }
      
      if (tr.getName().equals("*"))
        prog.attribute(xml, "xlink:href", "datatypes.html#open");
      else if (tr.getName().startsWith("@")) 
        prog.attribute(xml, "title", "@"+tr.getName().substring(1));
      else
        prog.attribute(xml, "xlink:href", GeneratorUtils.getSrcFile(tr.getName(), false) + ".html#" + tr.getName());
      
      prog.element(xml, "a", tr.getName());
      
      if (tr.getParams().size() > 0) {
        prog.text(xml, "(");
        boolean firstP = true;
        for (String t : tr.getParams()) {
          if (!firstP)
            if (prog.breaktext(xml, "|", t))
              return;
          prog.attribute(xml, "xlink:href", GeneratorUtils.getSrcFile(t, false) + ".html#" + t);
          prog.element(xml, "a", t);
          firstP = false;
        }
        xml.text(")");
      }
      first = false;
    }
    prog.close();

  }

  public Point hasIntersection(Segment segment1, Segment segment2, PointKind kind){
     
    if (segment1.isVertical){
      if (segment2.isVertical) // ( (segment2.start.x - segment1.start.x)*(segment2.end.x - segment1.start.x) > 0 )
        return null;
      else {
        double fx_at_segment1startx = segment2.slope * segment1.start.x - segment2.intercept;
        if (inBounds(fx_at_segment1startx, segment1.start.y, segment1.end.y) && inBounds(segment1.start.x, segment2.start.x, segment2.end.x))
          return new Point(segment1.start.x, fx_at_segment1startx, kind);
        else
          return null;
      }
    }
    else if (segment2.isVertical){
     return hasIntersection(segment2, segment1, kind);
    }
    else { //both segment1 and segment2 are not vertical 
     if (segment1.slope == segment2.slope)
      return null;
     else {
      double x1 = segment1.start.x;
      double y1 = segment1.start.y;
      double x2 = segment1.end.x;
      double y2 = segment1.end.y;
      double x3 = segment2.start.x;
      double y3 = segment2.start.y;
      double x4 = segment2.end.x;
      double y4 = segment2.end.y;
      double x = ((x4*y3-y4*x3)/(x4-x3) - (x2*y1-y2*x1)/(x2-x1)) /( (y2-y1)/(x2-x1) - (y4-y3)/(x4-x3));
      
      if (inBounds(x, x1, x2) && inBounds(x, x3, x4)) { 
        return new Point(x, (segment1.slope * x - segment1.intercept), kind);
      } else
       return null; 
     } 
    }
   }
     
}

