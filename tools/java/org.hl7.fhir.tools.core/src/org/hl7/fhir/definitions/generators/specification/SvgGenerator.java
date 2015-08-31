package org.hl7.fhir.definitions.generators.specification;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.Enumerations.BindingStrength;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
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
  private static final int LINE_MAX = 70;

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
  private String id;
  private String prefix;

  public SvgGenerator(PageProcessor page, String prefix) {
    this.definitions = page.getDefinitions();
    this.page = page;
    this.prefix = prefix;
  }

  public String generate(String filename, String id) throws Exception {
    this.id = id;
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
    xml.attribute("width", Utilities.noString(ini.getStringProperty("size", "width")) ? Double.toString(size.x) : ini.getStringProperty("size", "width"));
    xml.attribute("height", Utilities.noString(ini.getStringProperty("size", "height")) ? Double.toString(size.y) : ini.getStringProperty("size", "height"));
    xml.enter("svg");
    shadowFilter(xml);
    drawElement(xml, classNames);
    countDuplicateLinks();
    for (Link l : links) {
      drawLink(xml, l);
    }
    xml.exit("svg");
    xml.end();
    
    String s = new String(bytes.toByteArray());
    return s.substring(s.indexOf(">")+1);
  }

  public String generate(ResourceDefn resource, String id) throws Exception {
    this.id = id;
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    XMLWriter xml = new XMLWriter(bytes, "UTF-8");
    generate(resource, xml);
    String s = new String(bytes.toByteArray());
    return s.substring(s.indexOf(">")+1);
  }

  public void generate(ResourceDefn resource, String filename, String id) throws Exception {
    this.id = id;
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
    xml.enter("svg");
    shadowFilter(xml);
    drawClass(xml, resource.getRoot(), true, resource, false, resource.getName(), null);
    countDuplicateLinks();
    for (Link l : links) {
      drawLink(xml, l);
    }
    xml.exit("svg");
    xml.end();
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
  
  private Point determineMetrics(ElementDefn e, ClassItem source, String path, boolean isRoot, DefinedCode primitive) throws Exception {
    
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
        xml.enter("text");
        xml.attribute("xlink:href", l.path);
        xml.enter("a");
        xml.element("title", l.description);
        xml.text(l.name);
        xml.exit("a");
        xml.exit("text");

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
        xml.element("text", "["+l.cardinality+"]");
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
            "    <filter id=\"shadow"+id+"\" x=\"0\" y=\"0\" width=\"200%\" height=\"200%\">\r\n"+
            "      <feOffset result=\"offOut\" in=\"SourceGraphic\" dx=\"3\" dy=\"3\" />\r\n"+
            "      <feColorMatrix result=\"matrixOut\" in=\"offOut\" type=\"matrix\" values=\"0.2 0 0 0 0 0 0.2 0 0 0 0 0 0.2 0 0 0 0 0 1 0\" />\r\n"+
            "      <feGaussianBlur result=\"blurOut\" in=\"matrixOut\" stdDeviation=\"2\" />\r\n"+
            "      <feBlend in=\"SourceGraphic\" in2=\"blurOut\" mode=\"normal\" />\r\n"+
            "    </filter>\r\n"+
        "  </defs>");

  }

  private ClassItem drawElement(XMLWriter xml, String[] classNames) throws Exception {
    boolean onlyElement = classNames.length == 1 && classNames[0].equals("Element");
    
    xml.enter("g");
    ClassItem item = classes.get(null);
    String tn = "Element";
    xml.attribute("x", Double.toString(item.left));
    xml.attribute("y", Double.toString(item.top));
    xml.attribute("rx", "4");
    xml.attribute("ry", "4");
    xml.attribute("width", Double.toString(item.width));
    xml.attribute("height", Double.toString(item.height));
    xml.attribute("filter", "url(#shadow"+id+")");
    xml.attribute("style", "fill:#f0f8ff;stroke:black;stroke-width:1");
    xml.element("rect", null);    

    xml.attribute("x", Double.toString(item.left + item.width / 2));
    xml.attribute("y", Double.toString(item.top+HEADER_HEIGHT));
    xml.attribute("fill", "black");
    xml.attribute("class", "diagram-class-title");
    xml.element("text", tn);
    
    if ("true".equals(ini.getStringProperty("diagram", "element-attributes")) || onlyElement) {
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
      } else if (!onlyElement) 
        links.add(new Link(item, drawClass(xml, definitions.getElementDefn(cn), false, null, true, cn, null), null, null, PointKind.unknown, null, null));        
    }
    xml.exit("g");
    return item;
  }

  private ClassItem drawClass(XMLWriter xml, ElementDefn e, boolean isRoot, ResourceDefn resource, boolean link, String path, DefinedCode primitive) throws Exception {
    ClassItem item = classes.get(e);
    String tn = e.getName();
    if (!definitions.hasPrimitiveType(tn))
      tn = Utilities.capitalize(tn);
      
    xml.enter("g");
    xml.attribute("x", Double.toString(item.left));
    xml.attribute("y", Double.toString(item.top));
    xml.attribute("rx", "4");
    xml.attribute("ry", "4");
    xml.attribute("width", Double.toString(item.width));
    xml.attribute("height", Double.toString(item.height));
    xml.attribute("filter", "url(#shadow"+id+")");
    if (fakes.values().contains(e) && primitive == null)
      xml.attribute("style", "fill:#f8ddf8;stroke:black;stroke-width:1");
    else if (primitive instanceof DefinedStringPattern)
      xml.attribute("style", "fill:#f8ddf8;stroke:black;stroke-width:1");
    else if (e.getName().equals("Element"))
      xml.attribute("style", "fill:#ffffff;stroke:black;stroke-width:1");
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
      xml.enter("text");
      if (tn.equals("Extension") || tn.equals("Reference") || tn.equals("Narrative"))
        xml.attribute("xlink:href", prefix+definitions.getSrcFile(tn) + ".html#"+tn.toLowerCase());
      else
        xml.attribute("xlink:href", "#"+tn.toLowerCase());
      xml.enter("a");
      xml.text(tn);
      xml.exit("a");
      xml.exit("text");
    } else if (isRoot) {
      xml.enter("text");
      xml.text(tn);
      if (Utilities.noString(e.typeCode())) {
        xml.text(" «Resource»");
      } else {
        xml.attribute("class", "diagram-class-title-link");
        xml.enter("tspan");
        xml.text(" (");
        xml.attribute("xlink:href", prefix+e.typeCode().toLowerCase()+".html");
        xml.attribute("class", "diagram-class-reference");
        xml.element("a", e.typeCode());
        xml.text(")");
        xml.exit("tspan");
      }
      xml.exit("text");
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
    xml.exit("g");
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
    if (definitions.hasResource(root) || definitions.hasLogicalModel(root))
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
    return text.length() * 4.4;
  }

  private String[] textForAttribute(ElementDefn e) throws Exception {
    LineStatus ls = new LineStatus();
    XMLWriter xml = new XMLWriter(new ByteArrayOutputStream(), "UTF-8"); // this is a dummary
    xml.start();
    addAttribute(xml, 0, 0, e, "Element.id", ls);
    ls.close();
    return ls.list.toArray(new String[] {});
  }

  private String getTypeCodeForElement(List<TypeRef> tl) {
    if (tl.isEmpty())
      return "??";
    if (tl.size() == 1 && !tl.get(0).getName().equals("Reference"))
      return tl.get(0).getName();
    String t = tl.get(0).getName();
    boolean allSame = true;
    for (int i = 1; i < tl.size(); i++) {
      allSame = t.equals(tl.get(i).getName());
    }
    if (allSame && t.equals("Reference"))
      return "Reference";
    else
      return "Type";
  }

  private String getTypeStereotypeForElement(ElementDefn e) {
    if (e.getTypes().isEmpty())
      return null;
    if (e.getTypes().size() == 1 && !e.getTypes().get(0).getName().equals("Reference"))
      return null;
    if (e.getTypes().get(0).getName().equals("Reference") && e.getTypes().size() == 1) {
      CommaSeparatedStringBuilder csv = new CommaSeparatedStringBuilder("|");
      for (String p : e.getTypes().get(0).getParams())
        csv.append(p);
      return csv.toString();
    } else 
      return e.typeCode();
  }

  private String describeBinding(ElementDefn e) {
    BindingSpecification b = e.getBinding();
    if (e.hasBinding() && b.getBinding() != BindingMethod.Unbound) {
      String name = e.getBinding().getValueSet() != null ? e.getBinding().getValueSet().getName() : e.getBinding().getName();
      if (name.toLowerCase().endsWith(" codes"))
        name = name.substring(0, name.length()-5);
      if (name.length() > 30)
        name = name.substring(0, 29)+"...";
      if (b.getStrength() == BindingStrength.EXAMPLE)
        return name+"??";
      else if (b.getStrength() == BindingStrength.PREFERRED)
        return name+"?";
      else if (b.getStrength() == BindingStrength.EXTENSIBLE)
        return name+"+";
      else // if (b.getBindingStrength() == BindingStrength.REQUIRED)
        return name+"!";
    } else
      return "";
  }

  private boolean isAttribute(ElementDefn c) {
    return c.getElements().size() == 0 && !c.typeCode().startsWith("@");
  }

  private class LineStatus {
    int line = 0;
    int length = 0;
    String current = "";
    List<String> list = new ArrayList<String>();
    
    public String see(String s) {
      length = length + s.length();
      current = current + s;
      return s;
    }
    
    public void close() {
      line++;
      list.add(current);
      length = 0;
      current = "";
    }

    public void check(XMLWriter xml, double left, double top, int l, String link) throws IOException {
      if (length + l > LINE_MAX-2) { // always leave space for one or two
        if (link != null)
          xml.exit("a");
        xml.exit("text");
        close();
        xml.attribute("x", Double.toString(left + LEFT_MARGIN + (line == 0 ? 0 : WRAP_INDENT)));
        xml.attribute("y", Double.toString(top + LINE_HEIGHT * line));
        xml.attribute("fill", "black");
        xml.attribute("class", "diagram-class-detail");
        xml.enter("text");
        xml.text(see("      "));
        if (link != null) {
          xml.attribute("xlink:href", link);
          xml.enter("a");
        }
      }
    }   
  }
  private void addAttribute(XMLWriter xml, double left, double top, ElementDefn e, String path) throws Exception  {
    LineStatus ls = new LineStatus();
    addAttribute(xml, left, top, e, path, ls);
  }
  
  private void addAttribute(XMLWriter xml, double left, double top, ElementDefn e, String path, LineStatus ls) throws Exception  {
    xml.attribute("x", Double.toString(left + LEFT_MARGIN + (ls.line == 0 ? 0 : WRAP_INDENT)));
    xml.attribute("y", Double.toString(top + LINE_HEIGHT * ls.line));
    xml.attribute("fill", "black");
    xml.attribute("class", "diagram-class-detail");
    xml.enter("text");

    xml.attribute("xlink:href", baseUrl(path)+path+"."+e.getName().replace("[", "_").replace("]", "_"));
    xml.enter("a");
    xml.element("title", e.getEnhancedDefinition());
    xml.text(ls.see(e.getName()));
    
    xml.exit("a");
    xml.text(ls.see(" : "));
    encodeType(xml, ls, getTypeCodeForElement(e.getTypes()));
    xml.text(ls.see(" ["+e.describeCardinality()+"]"));

    // now, the stereotypes
    boolean hasTS = !((e.getTypes().isEmpty()) || (e.getTypes().size() == 1 && !e.getTypes().get(0).getName().equals("Reference")));
    boolean hasBinding = (e.hasBinding() && e.getBinding().getBinding() != BindingMethod.Unbound);
    if (hasTS || hasBinding) {
      xml.text(ls.see(" \u00AB "));
      
      if (hasTS) {
        if (e.getTypes().get(0).getName().equals("Reference") && e.getTypes().size() == 1) {
          boolean first = true;
          for (String p : e.getTypes().get(0).getParams()) {
            if (first)
              first = false;
            else 
              xml.text(ls.see("|"));
            ls.check(xml, left, top, p.length(), null);
            encodeType(xml, ls, p);
          }
        } else {
          boolean firstOuter = true;
          for (TypeRef t : e.getTypes()) {
            if (firstOuter)
              firstOuter = false;
            else 
              xml.text(ls.see("|"));
            
            ls.check(xml, left, top, t.getName().length(), null);
            encodeType(xml, ls, t.getName());
            if (t.getParams().size() > 0) {
              xml.text(ls.see("("));
              boolean first = true;
              for (String p : t.getParams()) {
                if (first)
                  first = false;
                else 
                  xml.text(ls.see("|"));
                ls.check(xml, left, top, p.length(), null);
                encodeType(xml, ls, p);
              }
              xml.text(ls.see(")"));
            }
          }
        }
      }
      if (hasTS && hasBinding) {
        xml.text(ls.see("; "));
      }
      if (hasBinding) {
        BindingSpecification b = e.getBinding();
        String name = e.getBinding().getValueSet() != null ? e.getBinding().getValueSet().getName() : e.getBinding().getName();
        if (name.toLowerCase().endsWith(" codes"))
          name = name.substring(0, name.length()-5);
        if (name.length() > 30)
          name = name.substring(0, 29)+"...";
        String link = getBindingLink(prefix, e);
        if (b.getStrength() == BindingStrength.EXAMPLE) {
          xml.attribute("xlink:href", link);
          xml.enter("a");
          xml.element("title", b.getDefinition()+" (Strength=Example)");
          for (String p : parts(name)) {
            ls.check(xml, left, top, p.length(), link);
            xml.text(ls.see(p));
          }
          xml.exit("a");
          xml.text("??");
        } else if (b.getStrength() == BindingStrength.PREFERRED) {
          xml.attribute("xlink:href", link);
          xml.enter("a");
          xml.element("title", b.getDefinition()+" (Strength=Preferred)");
          for (String p : parts(name)) {
            ls.check(xml, left, top, p.length(), link);
            xml.text(ls.see(p));
          }
          xml.exit("a");
          xml.text("?");
        } else if (b.getStrength() == BindingStrength.EXTENSIBLE) {
          xml.attribute("xlink:href", link);
          xml.enter("a");
          xml.element("title", b.getDefinition()+" (Strength=Extensible)");
          for (String p : parts(name)) {
            ls.check(xml, left, top, p.length(), link);
            xml.text(ls.see(p));
          }
          xml.exit("a");
          xml.text("+");
        } else if (b.getStrength() == BindingStrength.REQUIRED) {
          xml.attribute("xlink:href", link);
          xml.enter("a");
          xml.element("title", b.getDefinition()+" (Strength=Required)");
          //xml.open("b");
          for (String p : parts(name)) {
            ls.check(xml, left, top, p.length(), link);
            xml.text(ls.see(p));
          }
          //xml.close("b");
          xml.exit("a");
          xml.text("!");
        } else {
          xml.attribute("xlink:href", link);
          xml.enter("a");
          xml.element("title", b.getDefinition()+" (??)");
          for (String p : parts(name)) {
            ls.check(xml, left, top, p.length(), link);
            xml.text(ls.see(p));
          }
          xml.exit("a");
        }
      }
      xml.text(ls.see(" \u00BB"));
    }
    
    xml.exit("text");
  }

  private List<String> parts(String s) {
    List<String> res = new ArrayList<String>();
    int i = 0;
    while (i < s.length()) {
      int j = i;
      i++;
      while (i < s.length() && !Character.isWhitespace(s.charAt(i)))
        i++;
      res.add(s.substring(j, i));
    }
    return res;
  }

  private void addExtension(XMLWriter xml, double left, double top) throws Exception  {
    xml.attribute("x", Double.toString(left + LEFT_MARGIN));
    xml.attribute("y", Double.toString(top));
    xml.attribute("fill", "black");
    xml.attribute("class", "diagram-class-detail");
    xml.enter("text");
    xml.attribute("xlink:href", prefix+"extensibility.html");
    xml.enter("a");
    xml.element("title", "Extensions - as described for all elements: additional information that is not part of the basic definition of the resource / type");
    xml.text("extension");
    xml.exit("a");
    xml.text(" : ");
    xml.attribute("xlink:href", prefix+"extensibility.html");
    xml.element("a", "Extension");
    xml.text(" 0..*");
    xml.exit("text");
  }

  private void addValueAttribute(XMLWriter xml, double left, double top, String[] xsiType) throws Exception  {
    xml.attribute("x", Double.toString(left + LEFT_MARGIN));
    xml.attribute("y", Double.toString(top));
    xml.attribute("fill", "black");
    xml.attribute("class", "diagram-class-detail");
    xml.enter("text");
    xml.enter("tspan");
    xml.element("title", "Actual value attribute of the data type");
    xml.text("value");
    xml.exit("tspan");
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
    xml.exit("text");
  }

   
  private int encodeType(XMLWriter xml, LineStatus ls, String tc)  throws Exception {
    if (tc.equals("*")) {
      xml.attribute("xlink:href", prefix+"datatypes.html#open");
      xml.element("a", ls.see(tc));
      return tc.length();
    } else if (tc.equals("Type")) {
        xml.attribute("xlink:href", prefix+"formats.html#umlchoice");
        xml.element("a", ls.see(tc));
        return tc.length();
    } else if (tc.startsWith("@")) { 
      xml.attribute("title", "@"+tc.substring(1));
      xml.element("a", ls.see(tc));
      return tc.length();
    } else if (definitions.getConstraints().containsKey(tc)) {
      ProfiledType pt = definitions.getConstraints().get(tc);
      xml.attribute("xlink:href", prefix+definitions.getSrcFile(pt.getBaseType()) + ".html#" + pt.getBaseType());
      xml.element("a", ls.see(pt.getBaseType()));
      xml.text(ls.see("("));
      xml.attribute("xlink:href", prefix+definitions.getSrcFile(tc) + ".html#" + tc);
      xml.element("a", ls.see(tc));
      xml.text(ls.see(")"));
      return tc.length()+2+pt.getBaseType().length();
    } else {
      xml.attribute("xlink:href", prefix+definitions.getSrcFile(tc) + ".html#" + tc);
      xml.element("a", ls.see(tc));
      return tc.length();
    }
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

