Unit AdvPersistents;

{
Copyright (c) 2001-2013, Kestral Computing Pty Ltd (http://www.kestral.com.au)
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

Interface


Uses
  AdvObjects, AdvFilers;


Type
  {$TYPEINFO ON}
  TAdvPersistent = Class(TAdvObject)
    Protected
      Function Fileable(Const sLocation : String) : Boolean; Overload; Virtual;

    Public
      Function Link : TAdvPersistent; Overload;
      Function Clone : TAdvPersistent; Overload;

      Procedure Define(oFiler : TAdvFiler); Overload; Virtual;
      Procedure Load(oFiler : TAdvFiler); Overload; Virtual;
      Procedure Save(oFiler : TAdvFiler); Overload; Virtual;

      Function Fileable : Boolean; Overload; Virtual;
  End; 

  TAdvPersistentClass = Class Of TAdvPersistent;

  EAdvExceptionClass = AdvObjects.EAdvExceptionClass;
  EAdvException = AdvObjects.EAdvException;
  TAdvObject = AdvObjects.TAdvObject;
  TAdvObjectClass = AdvObjects.TAdvObjectClass;
  TAdvFiler = AdvFilers.TAdvFiler;

  
Implementation


Function TAdvPersistent.Clone : TAdvPersistent;
Begin 
  Result := TAdvPersistent(Inherited Clone);
End;  


Function TAdvPersistent.Link : TAdvPersistent;
Begin 
  Result := TAdvPersistent(Inherited Link);
End;  


Procedure TAdvPersistent.Define(oFiler: TAdvFiler);
Begin 
  Assert(Fileable('Define'));
End;  


Procedure TAdvPersistent.Save(oFiler : TAdvFiler);
Begin 
  Assert(Invariants('Save', oFiler, TAdvFiler, 'oFiler'));

  Define(oFiler);
End;  


Procedure TAdvPersistent.Load(oFiler : TAdvFiler);
Begin 
  Assert(Invariants('Load', oFiler, TAdvFiler, 'oFiler'));
  
  Define(oFiler);
End;  


Function TAdvPersistent.Fileable : Boolean;
Begin 
  Result := True;
End;  


Function TAdvPersistent.Fileable(Const sLocation: String): Boolean;
Begin 
  Invariants(sLocation, TAdvObject);

  If Not Fileable Then
    Invariant(sLocation, 'Object is marked as unfileable.');

  Result := True;
End;


End. // AdvPersistents //
