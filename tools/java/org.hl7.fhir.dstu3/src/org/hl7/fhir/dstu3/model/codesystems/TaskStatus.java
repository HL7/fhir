package org.hl7.fhir.dstu3.model.codesystems;

/*
  Copyright (c) 2011+, HL7, Inc.
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
  
*/

// Generated on Mon, Mar 28, 2016 15:19-0600 for FHIR v1.3.0


import org.hl7.fhir.dstu3.exceptions.FHIRException;

public enum TaskStatus {

        /**
         * The task was not completed due to some process issue.  It may not have been attempted, or if attempted, it may not have failed (e.g., an issue elsewhere might have resulted in the task being cancelled).
         */
        CANCELLED, 
        /**
         * The task has been completed (more or less) as planned.
         */
        COMPLETED, 
        /**
         * The task has been created by is not yet ready to be performed.
         */
        CREATED, 
        /**
         * The task was attempted but could not be completed due to some error.
         */
        FAILED, 
        /**
         * Task has been started but is not yet complete.
         */
        INPROGRESS, 
        /**
         * Task is ready to be performed, but no action has yet been taken.
         */
        READY, 
        /**
         * added to help the parsers
         */
        NULL;
        public static TaskStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        if ("completed".equals(codeString))
          return COMPLETED;
        if ("created".equals(codeString))
          return CREATED;
        if ("failed".equals(codeString))
          return FAILED;
        if ("in-progress".equals(codeString))
          return INPROGRESS;
        if ("ready".equals(codeString))
          return READY;
        throw new FHIRException("Unknown TaskStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case CANCELLED: return "cancelled";
            case COMPLETED: return "completed";
            case CREATED: return "created";
            case FAILED: return "failed";
            case INPROGRESS: return "in-progress";
            case READY: return "ready";
            default: return "?";
          }
        }
        public String getSystem() {
          return "http://hl7.org/fhir/task-status";
        }
        public String getDefinition() {
          switch (this) {
            case CANCELLED: return "The task was not completed due to some process issue.  It may not have been attempted, or if attempted, it may not have failed (e.g., an issue elsewhere might have resulted in the task being cancelled).";
            case COMPLETED: return "The task has been completed (more or less) as planned.";
            case CREATED: return "The task has been created by is not yet ready to be performed.";
            case FAILED: return "The task was attempted but could not be completed due to some error.";
            case INPROGRESS: return "Task has been started but is not yet complete.";
            case READY: return "Task is ready to be performed, but no action has yet been taken.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case CANCELLED: return "Cancelled";
            case COMPLETED: return "Completed";
            case CREATED: return "Created";
            case FAILED: return "Failed";
            case INPROGRESS: return "In Progress";
            case READY: return "Ready";
            default: return "?";
          }
    }


}

