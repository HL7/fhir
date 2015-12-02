package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class IssueTypeEnumFactory implements EnumFactory<IssueType> {

  public IssueType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("invalid".equals(codeString))
      return IssueType.INVALID;
    if ("structure".equals(codeString))
      return IssueType.STRUCTURE;
    if ("required".equals(codeString))
      return IssueType.REQUIRED;
    if ("value".equals(codeString))
      return IssueType.VALUE;
    if ("invariant".equals(codeString))
      return IssueType.INVARIANT;
    if ("security".equals(codeString))
      return IssueType.SECURITY;
    if ("login".equals(codeString))
      return IssueType.LOGIN;
    if ("unknown".equals(codeString))
      return IssueType.UNKNOWN;
    if ("expired".equals(codeString))
      return IssueType.EXPIRED;
    if ("forbidden".equals(codeString))
      return IssueType.FORBIDDEN;
    if ("suppressed".equals(codeString))
      return IssueType.SUPPRESSED;
    if ("processing".equals(codeString))
      return IssueType.PROCESSING;
    if ("not-supported".equals(codeString))
      return IssueType.NOTSUPPORTED;
    if ("duplicate".equals(codeString))
      return IssueType.DUPLICATE;
    if ("not-found".equals(codeString))
      return IssueType.NOTFOUND;
    if ("too-long".equals(codeString))
      return IssueType.TOOLONG;
    if ("code-invalid".equals(codeString))
      return IssueType.CODEINVALID;
    if ("extension".equals(codeString))
      return IssueType.EXTENSION;
    if ("too-costly".equals(codeString))
      return IssueType.TOOCOSTLY;
    if ("business-rule".equals(codeString))
      return IssueType.BUSINESSRULE;
    if ("conflict".equals(codeString))
      return IssueType.CONFLICT;
    if ("incomplete".equals(codeString))
      return IssueType.INCOMPLETE;
    if ("transient".equals(codeString))
      return IssueType.TRANSIENT;
    if ("lock-error".equals(codeString))
      return IssueType.LOCKERROR;
    if ("no-store".equals(codeString))
      return IssueType.NOSTORE;
    if ("exception".equals(codeString))
      return IssueType.EXCEPTION;
    if ("timeout".equals(codeString))
      return IssueType.TIMEOUT;
    if ("throttled".equals(codeString))
      return IssueType.THROTTLED;
    if ("informational".equals(codeString))
      return IssueType.INFORMATIONAL;
    throw new IllegalArgumentException("Unknown IssueType code '"+codeString+"'");
  }

  public String toCode(IssueType code) {
    if (code == IssueType.INVALID)
      return "invalid";
    if (code == IssueType.STRUCTURE)
      return "structure";
    if (code == IssueType.REQUIRED)
      return "required";
    if (code == IssueType.VALUE)
      return "value";
    if (code == IssueType.INVARIANT)
      return "invariant";
    if (code == IssueType.SECURITY)
      return "security";
    if (code == IssueType.LOGIN)
      return "login";
    if (code == IssueType.UNKNOWN)
      return "unknown";
    if (code == IssueType.EXPIRED)
      return "expired";
    if (code == IssueType.FORBIDDEN)
      return "forbidden";
    if (code == IssueType.SUPPRESSED)
      return "suppressed";
    if (code == IssueType.PROCESSING)
      return "processing";
    if (code == IssueType.NOTSUPPORTED)
      return "not-supported";
    if (code == IssueType.DUPLICATE)
      return "duplicate";
    if (code == IssueType.NOTFOUND)
      return "not-found";
    if (code == IssueType.TOOLONG)
      return "too-long";
    if (code == IssueType.CODEINVALID)
      return "code-invalid";
    if (code == IssueType.EXTENSION)
      return "extension";
    if (code == IssueType.TOOCOSTLY)
      return "too-costly";
    if (code == IssueType.BUSINESSRULE)
      return "business-rule";
    if (code == IssueType.CONFLICT)
      return "conflict";
    if (code == IssueType.INCOMPLETE)
      return "incomplete";
    if (code == IssueType.TRANSIENT)
      return "transient";
    if (code == IssueType.LOCKERROR)
      return "lock-error";
    if (code == IssueType.NOSTORE)
      return "no-store";
    if (code == IssueType.EXCEPTION)
      return "exception";
    if (code == IssueType.TIMEOUT)
      return "timeout";
    if (code == IssueType.THROTTLED)
      return "throttled";
    if (code == IssueType.INFORMATIONAL)
      return "informational";
    return "?";
  }


}

