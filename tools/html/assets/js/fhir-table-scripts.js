let fhirTableLoading = false;

function getCollapsed(store, row) {
  return sessionStorage.getItem("ft-"+store+row);
}

function setCollapsed(store, row, value) {
  if (!fhirTableLoading) {
    if (value == 'collapsed') {
      sessionStorage.setItem("ft-"+store+row, value);
    } else {
      sessionStorage.removeItem("ft-"+store+row);
    }
  }
}
  
function fhirTableRowExpand(table, id) {
  var rows = table.getElementsByTagName('tr');
  var row, i;
  var noex = null;
  for (i = 0; i < rows.length; i++) {
    row = rows[i];
    if (row.id.startsWith(id)) {
      if (noex && row.id.startsWith(noex)) {
        // do nothing
      } else {
        noex = null;
        if (row.id != id) {
          row.style.display = "";
          if (row.className == 'closed') {
            noex = row.id;
          }
        }
      }
    }
  }
}

function fhirTableRowCollapse(table, id) {
  var rows = table.getElementsByTagName('tr');
  var row, i;
  for (i = 0; i < rows.length; i++) {
    row = rows[i];
    if (row.id.startsWith(id) && row.id != id) {
      row.style.display = "none";
    }
  }
}

function findElementFromFocus(src, name) {
  e = src;
  while (e && e.tagName != name) {
    e = e.parentNode;
  }
  return e;
}

// src - a handle to an element in a row in the table 
function tableRowAction(src) {
  let table = findElementFromFocus(src, "TABLE");
  let row = findElementFromFocus(src, "TR");
  let td = row.firstElementChild;
  let state = row.className;
  if (state == "closed") {
    fhirTableRowExpand(table, row.id);
    row.className = "open";
    src.src  = src.src.replace("-closed", "-open");
    td.style.backgroundImage = td.style.backgroundImage.replace('0.png', '1.png');
    setCollapsed(table.id, row.id, 'expanded');
  } else {
    fhirTableRowCollapse(table, row.id);
    row.className = "closed";
    src.src  = src.src.replace("-open", "-closed");
    td.style.backgroundImage = td.style.backgroundImage.replace('1.png', '0.png');
    setCollapsed(table.id, row.id, 'collapsed');
  }
}

// src - a handle to an element in a row in the table 
function fhirTableInit(src) {
  let table = findElementFromFocus(src, "TABLE");
  var rows = table.getElementsByTagName('tr');
  var row, i;
  fhirTableLoading = true;
  for (i = 0; i < rows.length; i++) {
    row = rows[i];
    var id = row.id;
    if (getCollapsed(table.id, id) == 'collapsed') {
      let td = row.firstElementChild;
      let e = td.firstElementChild;
      while (e.tagName != "IMG" || !(e.src.includes("join"))) {
        e = e.nextSibling;
      }
      tableRowAction(e);
    }
  }
  fhirTableLoading = false;
}

