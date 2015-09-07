{
Copyright (c) 2001-2013, Health Intersections Pty Ltd (http://www.healthintersections.com.au)
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
}

unit AltovaXMLLib_TLB;

// ************************************************************************ //
// WARNING                                                                    
// -------                                                                    
// The types declared in this file were generated from data read from a       
// Type Library. If this type library is explicitly or indirectly (via        
// another type library referring to this type library) re-imported, or the   
// 'Refresh' command of the Type Library Editor activated while editing the   
// Type Library, the contents of this file will be regenerated and all        
// manual modifications will be lost.                                         
// ************************************************************************ //

// PASTLWTR : $Revision:   1.88  $
// File generated on 10/06/2013 10:32:41 AM from Type Library described below.

// *************************************************************************//
// NOTE:                                                                      
// Items guarded by $IFDEF_LIVE_SERVER_AT_DESIGN_TIME are used by properties  
// which return objects that may need to be explicitly created via a function 
// call prior to any access via the property. These items have been disabled  
// in order to prevent accidental use from within the object inspector. You   
// may enable them by defining LIVE_SERVER_AT_DESIGN_TIME or by selectively   
// removing them from the $IFDEF blocks. However, such items must still be    
// programmatically created via a method of the appropriate CoClass before    
// they can be used.                                                          
// ************************************************************************ //
// Type Lib: C:\Program Files (x86)\Altova\AltovaXML2013\AltovaXMLLib.tlb (1)
// IID\LCID: {A546DCD7-B4A6-4BB2-A0CC-62C75A963145}\0
// Helpfile: 
// DepndLst: 
//   (1) v2.0 stdole, (C:\Windows\SysWOW64\stdole2.tlb)
//   (2) v4.0 StdVCL, (C:\Windows\SysWow64\STDVCL40.DLL)
// ************************************************************************ //
{$TYPEDADDRESS OFF} // Unit must be compiled without type-checked pointers. 
interface

uses Windows, ActiveX, Classes;

// *********************************************************************//
// GUIDS declared in the TypeLibrary. Following prefixes are used:        
//   Type Libraries     : LIBID_xxxx                                      
//   CoClasses          : CLASS_xxxx                                      
//   DISPInterfaces     : DIID_xxxx                                       
//   Non-DISP interfaces: IID_xxxx                                        
// *********************************************************************//
const
  // TypeLibrary Major and minor versions
  AltovaXMLLibMajorVersion = 1;
  AltovaXMLLibMinorVersion = 2;

  LIBID_AltovaXMLLib: TGUID = '{A546DCD7-B4A6-4BB2-A0CC-62C75A963145}';

  IID_IApplication: TGUID = '{F0158B7E-77D2-4365-A0C4-177E84F0CC1D}';
  IID_IXSLT1: TGUID = '{7654E776-D167-4B91-A3EC-DC7C41BD2473}';
  IID_IXSLT2: TGUID = '{5815D2CF-D975-4DE6-8E30-215AB3F8B2B8}';
  IID_IXQuery: TGUID = '{3787E161-FD6A-46B8-A146-3461FAA12419}';
  IID_IXMLValidator: TGUID = '{E994BCA7-9C98-4049-A633-483BBDD6CFAA}';
  DIID__IApplicationEvents: TGUID = '{20760399-8BAC-48D7-B409-351BA89EC9F0}';
  CLASS_Application: TGUID = '{533BAC07-C702-4D91-8D37-39FDC919A19C}';
  CLASS_XSLT1: TGUID = '{656F16A7-F099-48FA-8F92-2C1113DF1B74}';
  CLASS_XSLT2: TGUID = '{6BA4D22D-1838-42DF-8B69-DD35B1298F9E}';
  CLASS_XQuery: TGUID = '{2079ECCC-F450-4BEB-9AFE-7B9C46724CB6}';
  CLASS_XMLValidator: TGUID = '{4737E100-3660-4341-9D8D-A1DD3DB7B800}';
type

// *********************************************************************//
// Forward declaration of types defined in TypeLibrary                    
// *********************************************************************//
  IApplication = interface;
  IApplicationDisp = dispinterface;
  IXSLT1 = interface;
  IXSLT1Disp = dispinterface;
  IXSLT2 = interface;
  IXSLT2Disp = dispinterface;
  IXQuery = interface;
  IXQueryDisp = dispinterface;
  IXMLValidator = interface;
  IXMLValidatorDisp = dispinterface;
  _IApplicationEvents = dispinterface;

// *********************************************************************//
// Declaration of CoClasses defined in Type Library                       
// (NOTE: Here we map each CoClass to its Default Interface)              
// *********************************************************************//
  Application = IApplication;
  XSLT1 = IXSLT1;
  XSLT2 = IXSLT2;
  XQuery = IXQuery;
  XMLValidator = IXMLValidator;


// *********************************************************************//
// Interface: IApplication
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {F0158B7E-77D2-4365-A0C4-177E84F0CC1D}
// *********************************************************************//
  IApplication = interface(IDispatch)
    ['{F0158B7E-77D2-4365-A0C4-177E84F0CC1D}']
    function  Get_XSLT1: IXSLT1; safecall;
    function  Get_XSLT2: IXSLT2; safecall;
    function  Get_XQuery: IXQuery; safecall;
    function  Get_XMLValidator: IXMLValidator; safecall;
    procedure AddXMLCatalogDefault; safecall;
    procedure AddXMLCatalogFromFile(const bstrXMLCatalogFileName: WideString); safecall;
    procedure AddXMLCatalogFromText(const bstrXMLCatalogText: WideString); safecall;
    property XSLT1: IXSLT1 read Get_XSLT1;
    property XSLT2: IXSLT2 read Get_XSLT2;
    property XQuery: IXQuery read Get_XQuery;
    property XMLValidator: IXMLValidator read Get_XMLValidator;
  end;

// *********************************************************************//
// DispIntf:  IApplicationDisp
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {F0158B7E-77D2-4365-A0C4-177E84F0CC1D}
// *********************************************************************//
  IApplicationDisp = dispinterface
    ['{F0158B7E-77D2-4365-A0C4-177E84F0CC1D}']
    property XSLT1: IXSLT1 readonly dispid 1;
    property XSLT2: IXSLT2 readonly dispid 2;
    property XQuery: IXQuery readonly dispid 3;
    property XMLValidator: IXMLValidator readonly dispid 4;
    procedure AddXMLCatalogDefault; dispid 5;
    procedure AddXMLCatalogFromFile(const bstrXMLCatalogFileName: WideString); dispid 6;
    procedure AddXMLCatalogFromText(const bstrXMLCatalogText: WideString); dispid 7;
  end;

// *********************************************************************//
// Interface: IXSLT1
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {7654E776-D167-4B91-A3EC-DC7C41BD2473}
// *********************************************************************//
  IXSLT1 = interface(IDispatch)
    ['{7654E776-D167-4B91-A3EC-DC7C41BD2473}']
    procedure Execute(const bstrOutputFileName: WideString); safecall;
    function  ExecuteAndGetResultAsString: WideString; safecall;
    procedure Set_InputXMLFileName(const Param1: WideString); safecall;
    procedure Set_XSLFileName(const Param1: WideString); safecall;
    procedure Set_InputXMLFromText(const Param1: WideString); safecall;
    procedure Set_XSLFromText(const Param1: WideString); safecall;
    procedure Set_XSLStackSize(Param1: Integer); safecall;
    procedure AddExternalParameter(const bstrName: WideString; const bstrVal: WideString); safecall;
    procedure ClearExternalParameterList; safecall;
    function  Get_LastErrorMessage: WideString; safecall;
    procedure Set_DotNetExtensionsEnabled(Param1: WordBool); safecall;
    procedure Set_JavaExtensionsEnabled(Param1: WordBool); safecall;
    property InputXMLFileName: WideString write Set_InputXMLFileName;
    property XSLFileName: WideString write Set_XSLFileName;
    property InputXMLFromText: WideString write Set_InputXMLFromText;
    property XSLFromText: WideString write Set_XSLFromText;
    property XSLStackSize: Integer write Set_XSLStackSize;
    property LastErrorMessage: WideString read Get_LastErrorMessage;
    property DotNetExtensionsEnabled: WordBool write Set_DotNetExtensionsEnabled;
    property JavaExtensionsEnabled: WordBool write Set_JavaExtensionsEnabled;
  end;

// *********************************************************************//
// DispIntf:  IXSLT1Disp
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {7654E776-D167-4B91-A3EC-DC7C41BD2473}
// *********************************************************************//
  IXSLT1Disp = dispinterface
    ['{7654E776-D167-4B91-A3EC-DC7C41BD2473}']
    procedure Execute(const bstrOutputFileName: WideString); dispid 1;
    function  ExecuteAndGetResultAsString: WideString; dispid 2;
    property InputXMLFileName: WideString writeonly dispid 3;
    property XSLFileName: WideString writeonly dispid 4;
    property InputXMLFromText: WideString writeonly dispid 5;
    property XSLFromText: WideString writeonly dispid 6;
    property XSLStackSize: Integer writeonly dispid 7;
    procedure AddExternalParameter(const bstrName: WideString; const bstrVal: WideString); dispid 8;
    procedure ClearExternalParameterList; dispid 9;
    property LastErrorMessage: WideString readonly dispid 10;
    property DotNetExtensionsEnabled: WordBool writeonly dispid 11;
    property JavaExtensionsEnabled: WordBool writeonly dispid 12;
  end;

// *********************************************************************//
// Interface: IXSLT2
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {5815D2CF-D975-4DE6-8E30-215AB3F8B2B8}
// *********************************************************************//
  IXSLT2 = interface(IDispatch)
    ['{5815D2CF-D975-4DE6-8E30-215AB3F8B2B8}']
    procedure Execute(const bstrOutputFileName: WideString); safecall;
    function  ExecuteAndGetResultAsString: WideString; safecall;
    procedure Set_InputXMLFileName(const Param1: WideString); safecall;
    procedure Set_XSLFileName(const Param1: WideString); safecall;
    procedure Set_InputXMLFromText(const Param1: WideString); safecall;
    procedure Set_XSLFromText(const Param1: WideString); safecall;
    procedure Set_XSLStackSize(Param1: Integer); safecall;
    procedure AddExternalParameter(const bstrName: WideString; const bstrVal: WideString); safecall;
    procedure ClearExternalParameterList; safecall;
    function  Get_LastErrorMessage: WideString; safecall;
    procedure Set_InitialTemplateName(const Param1: WideString); safecall;
    procedure Set_InitialTemplateMode(const Param1: WideString); safecall;
    procedure Set_DotNetExtensionsEnabled(Param1: WordBool); safecall;
    procedure Set_JavaExtensionsEnabled(Param1: WordBool); safecall;
    function  ExecuteAndGetResultAsStringWithBaseOutputURI(const bstrBaseOutputURI: WideString): WideString; safecall;
    property InputXMLFileName: WideString write Set_InputXMLFileName;
    property XSLFileName: WideString write Set_XSLFileName;
    property InputXMLFromText: WideString write Set_InputXMLFromText;
    property XSLFromText: WideString write Set_XSLFromText;
    property XSLStackSize: Integer write Set_XSLStackSize;
    property LastErrorMessage: WideString read Get_LastErrorMessage;
    property InitialTemplateName: WideString write Set_InitialTemplateName;
    property InitialTemplateMode: WideString write Set_InitialTemplateMode;
    property DotNetExtensionsEnabled: WordBool write Set_DotNetExtensionsEnabled;
    property JavaExtensionsEnabled: WordBool write Set_JavaExtensionsEnabled;
  end;

// *********************************************************************//
// DispIntf:  IXSLT2Disp
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {5815D2CF-D975-4DE6-8E30-215AB3F8B2B8}
// *********************************************************************//
  IXSLT2Disp = dispinterface
    ['{5815D2CF-D975-4DE6-8E30-215AB3F8B2B8}']
    procedure Execute(const bstrOutputFileName: WideString); dispid 1;
    function  ExecuteAndGetResultAsString: WideString; dispid 2;
    property InputXMLFileName: WideString writeonly dispid 3;
    property XSLFileName: WideString writeonly dispid 4;
    property InputXMLFromText: WideString writeonly dispid 5;
    property XSLFromText: WideString writeonly dispid 6;
    property XSLStackSize: Integer writeonly dispid 7;
    procedure AddExternalParameter(const bstrName: WideString; const bstrVal: WideString); dispid 8;
    procedure ClearExternalParameterList; dispid 9;
    property LastErrorMessage: WideString readonly dispid 10;
    property InitialTemplateName: WideString writeonly dispid 11;
    property InitialTemplateMode: WideString writeonly dispid 12;
    property DotNetExtensionsEnabled: WordBool writeonly dispid 13;
    property JavaExtensionsEnabled: WordBool writeonly dispid 14;
    function  ExecuteAndGetResultAsStringWithBaseOutputURI(const bstrBaseOutputURI: WideString): WideString; dispid 15;
  end;

// *********************************************************************//
// Interface: IXQuery
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {3787E161-FD6A-46B8-A146-3461FAA12419}
// *********************************************************************//
  IXQuery = interface(IDispatch)
    ['{3787E161-FD6A-46B8-A146-3461FAA12419}']
    procedure Execute(const bstrOutputFileName: WideString); safecall;
    function  ExecuteAndGetResultAsString: WideString; safecall;
    procedure Set_XQueryFileName(const Param1: WideString); safecall;
    procedure Set_InputXMLFileName(const Param1: WideString); safecall;
    procedure Set_XQueryFromText(const Param1: WideString); safecall;
    procedure Set_InputXMLFromText(const Param1: WideString); safecall;
    function  Get_OutputEncoding: WideString; safecall;
    procedure Set_OutputEncoding(const pVal: WideString); safecall;
    function  Get_OutputIndent: WordBool; safecall;
    procedure Set_OutputIndent(pVal: WordBool); safecall;
    function  Get_OutputMethod: WideString; safecall;
    procedure Set_OutputMethod(const pVal: WideString); safecall;
    function  Get_OutputOmitXMLDeclaration: WordBool; safecall;
    procedure Set_OutputOmitXMLDeclaration(pVal: WordBool); safecall;
    procedure AddExternalVariable(const bstrName: WideString; const bstrVal: WideString); safecall;
    procedure ClearExternalVariableList; safecall;
    function  Get_LastErrorMessage: WideString; safecall;
    procedure AddExternalVariableAsXPath(const bstrName: WideString; 
                                         const bstrValueExpression: WideString); safecall;
    procedure Set_DotNetExtensionsEnabled(Param1: WordBool); safecall;
    procedure Set_JavaExtensionsEnabled(Param1: WordBool); safecall;
    property XQueryFileName: WideString write Set_XQueryFileName;
    property InputXMLFileName: WideString write Set_InputXMLFileName;
    property XQueryFromText: WideString write Set_XQueryFromText;
    property InputXMLFromText: WideString write Set_InputXMLFromText;
    property OutputEncoding: WideString read Get_OutputEncoding write Set_OutputEncoding;
    property OutputIndent: WordBool read Get_OutputIndent write Set_OutputIndent;
    property OutputMethod: WideString read Get_OutputMethod write Set_OutputMethod;
    property OutputOmitXMLDeclaration: WordBool read Get_OutputOmitXMLDeclaration write Set_OutputOmitXMLDeclaration;
    property LastErrorMessage: WideString read Get_LastErrorMessage;
    property DotNetExtensionsEnabled: WordBool write Set_DotNetExtensionsEnabled;
    property JavaExtensionsEnabled: WordBool write Set_JavaExtensionsEnabled;
  end;

// *********************************************************************//
// DispIntf:  IXQueryDisp
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {3787E161-FD6A-46B8-A146-3461FAA12419}
// *********************************************************************//
  IXQueryDisp = dispinterface
    ['{3787E161-FD6A-46B8-A146-3461FAA12419}']
    procedure Execute(const bstrOutputFileName: WideString); dispid 1;
    function  ExecuteAndGetResultAsString: WideString; dispid 2;
    property XQueryFileName: WideString writeonly dispid 3;
    property InputXMLFileName: WideString writeonly dispid 4;
    property XQueryFromText: WideString writeonly dispid 5;
    property InputXMLFromText: WideString writeonly dispid 6;
    property OutputEncoding: WideString dispid 7;
    property OutputIndent: WordBool dispid 8;
    property OutputMethod: WideString dispid 9;
    property OutputOmitXMLDeclaration: WordBool dispid 10;
    procedure AddExternalVariable(const bstrName: WideString; const bstrVal: WideString); dispid 11;
    procedure ClearExternalVariableList; dispid 12;
    property LastErrorMessage: WideString readonly dispid 13;
    procedure AddExternalVariableAsXPath(const bstrName: WideString; 
                                         const bstrValueExpression: WideString); dispid 14;
    property DotNetExtensionsEnabled: WordBool writeonly dispid 15;
    property JavaExtensionsEnabled: WordBool writeonly dispid 16;
  end;

// *********************************************************************//
// Interface: IXMLValidator
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {E994BCA7-9C98-4049-A633-483BBDD6CFAA}
// *********************************************************************//
  IXMLValidator = interface(IDispatch)
    ['{E994BCA7-9C98-4049-A633-483BBDD6CFAA}']
    function  IsValid: WordBool; safecall;
    function  IsWellFormed: WordBool; safecall;
    function  IsValidWithExternalSchemaOrDTD: WordBool; safecall;
    procedure Set_InputXMLFileName(const Param1: WideString); safecall;
    procedure Set_SchemaFileName(const Param1: WideString); safecall;
    procedure Set_DTDFileName(const Param1: WideString); safecall;
    procedure Set_InputXMLFromText(const Param1: WideString); safecall;
    procedure Set_SchemaFromText(const Param1: WideString); safecall;
    procedure Set_DTDFromText(const Param1: WideString); safecall;
    function  Get_LastErrorMessage: WideString; safecall;
    procedure Set_TreatXBRLInconsistenciesAsErrors(Param1: WordBool); safecall;
    property InputXMLFileName: WideString write Set_InputXMLFileName;
    property SchemaFileName: WideString write Set_SchemaFileName;
    property DTDFileName: WideString write Set_DTDFileName;
    property InputXMLFromText: WideString write Set_InputXMLFromText;
    property SchemaFromText: WideString write Set_SchemaFromText;
    property DTDFromText: WideString write Set_DTDFromText;
    property LastErrorMessage: WideString read Get_LastErrorMessage;
    property TreatXBRLInconsistenciesAsErrors: WordBool write Set_TreatXBRLInconsistenciesAsErrors;
  end;

// *********************************************************************//
// DispIntf:  IXMLValidatorDisp
// Flags:     (4416) Dual OleAutomation Dispatchable
// GUID:      {E994BCA7-9C98-4049-A633-483BBDD6CFAA}
// *********************************************************************//
  IXMLValidatorDisp = dispinterface
    ['{E994BCA7-9C98-4049-A633-483BBDD6CFAA}']
    function  IsValid: WordBool; dispid 1;
    function  IsWellFormed: WordBool; dispid 2;
    function  IsValidWithExternalSchemaOrDTD: WordBool; dispid 3;
    property InputXMLFileName: WideString writeonly dispid 4;
    property SchemaFileName: WideString writeonly dispid 5;
    property DTDFileName: WideString writeonly dispid 6;
    property InputXMLFromText: WideString writeonly dispid 7;
    property SchemaFromText: WideString writeonly dispid 8;
    property DTDFromText: WideString writeonly dispid 9;
    property LastErrorMessage: WideString readonly dispid 10;
    property TreatXBRLInconsistenciesAsErrors: WordBool writeonly dispid 11;
  end;

// *********************************************************************//
// DispIntf:  _IApplicationEvents
// Flags:     (4096) Dispatchable
// GUID:      {20760399-8BAC-48D7-B409-351BA89EC9F0}
// *********************************************************************//
  _IApplicationEvents = dispinterface
    ['{20760399-8BAC-48D7-B409-351BA89EC9F0}']
  end;

// *********************************************************************//
// The Class CoApplication provides a Create and CreateRemote method to          
// create instances of the default interface IApplication exposed by              
// the CoClass Application. The functions are intended to be used by             
// clients wishing to automate the CoClass objects exposed by the         
// server of this typelibrary.                                            
// *********************************************************************//
  CoApplication = class
    class function Create: IApplication;
    class function CreateRemote(const MachineName: string): IApplication;
  end;

// *********************************************************************//
// The Class CoXSLT1 provides a Create and CreateRemote method to          
// create instances of the default interface IXSLT1 exposed by              
// the CoClass XSLT1. The functions are intended to be used by             
// clients wishing to automate the CoClass objects exposed by the         
// server of this typelibrary.                                            
// *********************************************************************//
  CoXSLT1 = class
    class function Create: IXSLT1;
    class function CreateRemote(const MachineName: string): IXSLT1;
  end;

// *********************************************************************//
// The Class CoXSLT2 provides a Create and CreateRemote method to          
// create instances of the default interface IXSLT2 exposed by              
// the CoClass XSLT2. The functions are intended to be used by             
// clients wishing to automate the CoClass objects exposed by the         
// server of this typelibrary.                                            
// *********************************************************************//
  CoXSLT2 = class
    class function Create: IXSLT2;
    class function CreateRemote(const MachineName: string): IXSLT2;
  end;

// *********************************************************************//
// The Class CoXQuery provides a Create and CreateRemote method to          
// create instances of the default interface IXQuery exposed by              
// the CoClass XQuery. The functions are intended to be used by             
// clients wishing to automate the CoClass objects exposed by the         
// server of this typelibrary.                                            
// *********************************************************************//
  CoXQuery = class
    class function Create: IXQuery;
    class function CreateRemote(const MachineName: string): IXQuery;
  end;

// *********************************************************************//
// The Class CoXMLValidator provides a Create and CreateRemote method to          
// create instances of the default interface IXMLValidator exposed by              
// the CoClass XMLValidator. The functions are intended to be used by             
// clients wishing to automate the CoClass objects exposed by the         
// server of this typelibrary.                                            
// *********************************************************************//
  CoXMLValidator = class
    class function Create: IXMLValidator;
    class function CreateRemote(const MachineName: string): IXMLValidator;
  end;

implementation

uses ComObj;

class function CoApplication.Create: IApplication;
begin
  Result := CreateComObject(CLASS_Application) as IApplication;
end;

class function CoApplication.CreateRemote(const MachineName: string): IApplication;
begin
  Result := CreateRemoteComObject(MachineName, CLASS_Application) as IApplication;
end;

class function CoXSLT1.Create: IXSLT1;
begin
  Result := CreateComObject(CLASS_XSLT1) as IXSLT1;
end;

class function CoXSLT1.CreateRemote(const MachineName: string): IXSLT1;
begin
  Result := CreateRemoteComObject(MachineName, CLASS_XSLT1) as IXSLT1;
end;

class function CoXSLT2.Create: IXSLT2;
begin
  Result := CreateComObject(CLASS_XSLT2) as IXSLT2;
end;

class function CoXSLT2.CreateRemote(const MachineName: string): IXSLT2;
begin
  Result := CreateRemoteComObject(MachineName, CLASS_XSLT2) as IXSLT2;
end;

class function CoXQuery.Create: IXQuery;
begin
  Result := CreateComObject(CLASS_XQuery) as IXQuery;
end;

class function CoXQuery.CreateRemote(const MachineName: string): IXQuery;
begin
  Result := CreateRemoteComObject(MachineName, CLASS_XQuery) as IXQuery;
end;

class function CoXMLValidator.Create: IXMLValidator;
begin
  Result := CreateComObject(CLASS_XMLValidator) as IXMLValidator;
end;

class function CoXMLValidator.CreateRemote(const MachineName: string): IXMLValidator;
begin
  Result := CreateRemoteComObject(MachineName, CLASS_XMLValidator) as IXMLValidator;
end;

end.
