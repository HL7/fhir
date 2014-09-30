FHIR Delphi/Pascal Library

Contents:
1. License
2. Delphi / Lazarus Versions supported
3. Using the Library 
4. Object life-cycle management

The delphi library is maintained by Grahame Grieve (grahame@healthintersections.com.au), and is the basis for the FHIR reference server (see [todo]).

1. License

Copyright (c) 2011-2013, HL7, Inc
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

2. Delphi / Lazarus Versions supported

As released, this code supports all unicode versions of delphi.
The library depends on msxml, so won't work on anything but windows

In addition, this code (with a different supporting library) is known to
work in all pre-unicode versions of delphi (D5+). The support library for this
is not released - you can talk to me or downgrade the library yourself. 

Free Pascal / Lazarus: this code doesn't compile under FPC because I can't
figure out how to maintain dual source around the Unicode issue. If anyone
wants to help, assistance will be welcome. 

3. Using the library: 

This library provides an object model for all FHIR resources, and 
parsers and serialisers for the XML and JSON formats.

To create an object model: resources or bundles, or supporting classes
can be created directly (TFhirPatient.create), or using appropriate
methods from TFhirFactory (FhirSupport.pas). See below for notes about
managing object life cycles.

To read a stream into an object model, create a TFHIRXmlParser, or
a TFHIRJsonParser (FhirParser.pas) (you must know which in advance - 
this should be known by the mime type). Then assign it's source 
property (a stream of any type is required), call "parse", and 
then check to see whether a resource or a bundle (or a taglist, 
in some circumstances) was decoded.

To write a stream, create an object model, and then create 
either a TFHIRXmlComposer, or a TFHIRJsonComposer, and call 
the compose methods with appropriate parameters.

There's some useful example code in FHIRTest.dpr


4. Object life-cycle management

The library uses it's own approach to managing object life 
cycles: manual reference counting.

Each object keeps it's own internal reference count,
and when a new pointer to the object is created, the programmer
must increment the count. When all references to the object 
are released, the object is automatically freed.

Question: Why not use interfaces?
Answers: 
 * well, the outcome is kind of like how interfaces
   work, but without their polymorphism problems (can't
   cast an interface to a descendent interface)
 * legacy, really. This approach was implemented prior to 
   interfaces being available


The way it works is all implemented in TAdvObject

Using an object is easy: 

var
  obj : TAdvObject; { or any descendent }
begin
  obj := TAdvObject.create;
  try
    // do things with the object
  finally
    obj.free;
  end;
end;

Note: you *never* call FreeAndNil(obj) - because FreeAndNil
calls TObject(obj).free, and it can't be overridden. So 
you can't ever call FreeAndNil when working with these objects.
It's painful, but if you look at the existing code, 
you just don't ever need to do it very often - use smaller
procedures, mostly.  (or use the := nil trick below)

The technique starts to get useful when you have to add the object to
a list:

Procedure addToList(list : TAdvObjectList; params...);
var
  obj : TAdvObject; { or any descendent }
begin
  obj := TAdvObject.create;
  try
    // do things with the object and params
    list.add(obj.link);  // add to the reference count
  finally
    obj.free; // delete from the reference count, and free if no more references are left
  end;
end;

This code won't leak - if the object can't be created, an exception
will be raised, and the object will be released. If it works, it'll be 
added to the list and the list will be the only owner - the .link
routine adds to the reference count. 

So once the object is in the list, and you want to work with it:

procedure UseObject(list : TAdvObjectList; i : integer);
var
  obj : TAdvObject;
begin
  obj := list[i].link; // get a copy, and reference count it
  try
    // do stuff with the object. It's ok to keep 
    // doing stuff, even if it is removed from the list
    // while this is happening 
  finally
    obj.free; // release our reference to obj; free if this is the last reference
  end; 
end;

The list automatically owns the object, and manages it's own reference to it
correctly. You don't have to count a reference:

procedure UseObject(list : TAdvObjectList; i : integer);
var
  obj : TAdvObject;
begin
  obj := list[i]; 
  // do stuff with the object, but be quick:
  // if it gets removed from the list while you're using 
  // it (e.g. in a routine you call), then boom!
end;


If you have properties that have this type, then you code them like this:

  Property Object_ : TAdvObject read FObject write SetObject;
  
and SetObject looks like this:

Procedure TSomeObject.SetObject(value : TAdvObject);
begin
  FObject.free; // release the reference we have (or just do nothing if FObject is nil(
  FObject := value;  
end;

Then you use it like this:

Procedure doSomething(obj : TAdvObject);
var
  s : TSomeObject;
begin
  s := TSomeObject.create;
  try
    s.object_ := obj.link; // as long as s lives, it keeps a copy of s.
    // do stuff with s
  finally
    s.free;
  end;
end;

And in the TSomeObject Destructor:

Destructor TSomeObject.destroy;
begin
  // other stuff
  Object_ := nil; // equivalent to freeandNil(Object);
  inherited;
end;

Note: this pattern arose experimentally seeking a reproducible
pattern that would make it easy to keep objects alive when you
need them, and not have any leaks, in a large programming team.
Once you're used to it, it's hard to think any other way.







  
  
  