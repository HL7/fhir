Sub FHIR_Layout()
'
' FHIR_Layout Macro
'
' Keyboard Shortcut: Ctrl+Shift+L
'
    Columns("A:A").ColumnWidth = 28
    Columns("B:B").ColumnWidth = 10
    Columns("C:C").ColumnWidth = 10
    Columns("D:D").ColumnWidth = 10
    Columns("E:E").ColumnWidth = 15
    Columns("F:F").ColumnWidth = 11
    Columns("G:G").ColumnWidth = 40
    Columns("H:H").ColumnWidth = 40
    Columns("I:I").ColumnWidth = 30
    Columns("J:J").ColumnWidth = 30
    Columns("K:K").ColumnWidth = 21
    Columns("L:L").ColumnWidth = 25
    Columns("M:M").ColumnWidth = 25
    Columns("A:D").Select
    With Selection.Interior
        .PatternColorIndex = xlAutomatic
        .ThemeColor = xlThemeColorDark1
        .TintAndShade = -0.149998474074526
        .PatternTintAndShade = 0
    End With
    Rows("1:1").Select
    With Selection.Interior
        .Pattern = xlSolid
        .PatternColorIndex = xlAutomatic
        .ThemeColor = xlThemeColorAccent5
        .TintAndShade = 0.799981688894314
        .PatternTintAndShade = 0
    End With
    Cells.Select
    With Selection
        .HorizontalAlignment = xlLeft
        .VerticalAlignment = xlTop
        .WrapText = True
        .Orientation = 0
        .AddIndent = False
        .IndentLevel = 0
        .ShrinkToFit = True
        .ReadingOrder = xlContext
        .MergeCells = False
    End With
    Range("G2").Select
End Sub
