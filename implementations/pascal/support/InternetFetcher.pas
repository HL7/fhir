Unit InternetFetcher;

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
  AdvBuffers,
  AdvObjects;

Type
  TInternetFetcherMethod = (imfGet, imfPost);

  TInternetFetcher = Class (TAdvObject)
  Private
    FURL: String;
    FBuffer: TAdvBuffer;
    FUsername: String;
    FPassword: String;
    FMethod: TInternetFetcherMethod;
    procedure SetBuffer(const Value: TAdvBuffer);
    procedure SetPassword(const Value: String);
    procedure SetUsername(const Value: String);
  Public
    Constructor Create; Override;

    Destructor Destroy; Override;
     
    Property URL : String read FURL write FURL;
    Property Buffer : TAdvBuffer read FBuffer write SetBuffer;

    Function CanFetch : Boolean;
    Procedure Fetch;

    Property Username : String read FUsername write SetUsername;
    Property Password : String read FPassword write SetPassword;
    Property Method : TInternetFetcherMethod read FMethod write FMethod;
  End;

Implementation

Uses
  StringSupport,

  SysUtils,
  Classes,
  
  IdURi,
  IdFTP,
  IdHTTP,
  IdSSLOpenSSL;

{ TInternetFetcher }

function TInternetFetcher.CanFetch: Boolean;
begin
  result := StringStartsWith(url, 'file:') Or
            StringStartsWith(url, 'http:') or
            StringStartsWith(url, 'https:') or
            StringStartsWith(url, 'ftp:');
end;

constructor TInternetFetcher.Create;
begin
  inherited;
  FBuffer := TAdvBuffer.create;
  FMethod := imfGet;
end;

destructor TInternetFetcher.Destroy;
begin
  FBuffer.Free;
  inherited;
end;

procedure TInternetFetcher.Fetch;
var
  oUri : TIdURI;
  oHTTP: TIdHTTP;
  oMem : TMemoryStream;
  oSSL : TIdSSLIOHandlerSocketOpenSSL;
  oFtp : TIdFTP;
begin
  if StringStartsWith(url, 'file:') Then
      FBuffer.LoadFromFileName(Copy(url, 6, $FFFF))
  else
  Begin
    oUri := TIdURI.Create(url);
    Try
      if oUri.Protocol = 'http' Then
      Begin
        oHTTP := TIdHTTP.Create(nil);
        Try
          oHTTP.HandleRedirects := true;
          oHTTP.URL.URI := url;
          oMem := TMemoryStream.Create;
          try
            if FMethod = imfPost then
              oHTTP.Post(url, oMem)
            else
            oHTTP.Get(url, oMem);
            oMem.position := 0;
            FBuffer.Capacity := oMem.Size;
            oMem.read(Fbuffer.Data^, oMem.Size);
          Finally
            oMem.Free;
          End;
        Finally
          oHTTP.Free;
        End;
      End
      Else if oUri.Protocol = 'https' Then
      Begin
        oHTTP := TIdHTTP.Create(nil);
        Try
          oSSL := TIdSSLIOHandlerSocketOpenSSL.Create(Nil);
          Try
            oHTTP.IOHandler := oSSL;
            oSSL.SSLOptions.Mode := sslmClient;
            oSSL.SSLOptions.Method := sslvTLSv1_2;
            oHTTP.URL.URI := url;
            oMem := TMemoryStream.Create;
            try
              if FMethod = imfPost then
                oHTTP.Post(url, oMem)
              else
              oHTTP.Get(url, oMem);
              oMem.position := 0;
              FBuffer.Capacity := oMem.Size;
              oMem.read(Fbuffer.Data^, oMem.Size);
            Finally
              oMem.Free;
            End;
          Finally
            oSSL.Free;
          End;
        Finally
          oHTTP.Free;
        End;
      End
      Else if oUri.Protocol = 'ftp' then
      begin
        oFtp := TIdFTP.Create(nil);
        Try
          oFTP.Host := oUri.Host;
          if username = '' then
            oFTP.Username := 'anonymous'
          else
            oFTP.Username := username;
          oFTP.Password := password;
          oFTP.Connect;
          oFTP.Passive := true;
          oFTP.ChangeDir(oURI.Path);
          oMem := TMemoryStream.Create;
          try
            oFTP.Get(oURI.Document, oMem);
            oMem.position := 0;
            FBuffer.Capacity := oMem.Size;
            oMem.read(Fbuffer.Data^, oMem.Size);
          Finally
            oMem.Free;
          End;
        Finally
          oFtp.Free;
        End;
      End
      Else
        Raise Exception.Create('Protocol '+oUri.Protocol+' not supported');
    Finally
      oUri.Free;
    End;
  End;
end;

procedure TInternetFetcher.SetBuffer(const Value: TAdvBuffer);
begin
  FBuffer.Free;
  FBuffer := Value;
end;

procedure TInternetFetcher.SetPassword(const Value: String);
begin
  FPassword := Value;
end;

procedure TInternetFetcher.SetUsername(const Value: String);
begin
  FUsername := Value;
end;

End.