Unit OIDSupport;

Interface

uses
  RegExpr;

Const
  OID_LOINC = '2.16.840.1.113883.6.1';
  OID_SNOMED = '2.16.840.1.113883.6.96';
  OID_REGEX = '[0-2](\.(0|[1-9][0-9]*))*';

Function isOid(oid : String) : Boolean;

Implementation

Uses
  IdHttp,
  SysUtils,
  StringSupport;

Function isOid(oid : String) : Boolean;
var
  regex : TRegExpr;
Begin
  if (pos('.', oid) = 0) or (length(oid) > 64) then
    result := false
  else
  begin
    regex := TRegExpr.Create;
    try
      regex.Expression := OID_REGEX;
      regex.Compile;
      result := regex.Exec(oid);
    finally
      regex.Free;
    end;
  end;
End;

End.