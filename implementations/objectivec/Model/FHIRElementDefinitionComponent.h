/*
  Copyright (c) 2011-2013, HL7, Inc.
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
  

 * Generated on Thu, Jan 30, 2014 05:26+1100 for FHIR v0.12
 */
/*
 * null
 *
 * [FhirComposite("ElementDefinitionComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRProfile.h"

@class FHIRString;
@class FHIRInteger;
@class FHIRTypeRefComponent;
@class FHIRElement;
@class FHIRId;
@class FHIRElementDefinitionConstraintComponent;
@class FHIRBoolean;
@class FHIRElementDefinitionBindingComponent;
@class FHIRElementDefinitionMappingComponent;

@interface FHIRElementDefinitionComponent : FHIRElement

/*
 * Concise definition for xml presentation
 */
@property (nonatomic, strong) FHIRString *short_Element;

@property (nonatomic, strong) NSString *short_;

/*
 * Full formal definition in human language
 */
@property (nonatomic, strong) FHIRString *formalElement;

@property (nonatomic, strong) NSString *formal;

/*
 * Comments about the use of this element
 */
@property (nonatomic, strong) FHIRString *commentsElement;

@property (nonatomic, strong) NSString *comments;

/*
 * Why is this needed?
 */
@property (nonatomic, strong) FHIRString *requirementsElement;

@property (nonatomic, strong) NSString *requirements;

/*
 * Other names
 */
@property (nonatomic, strong) NSArray/*<string>*/ *synonymElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *synonym;

/*
 * Minimum Cardinality
 */
@property (nonatomic, strong) FHIRInteger *minElement;

@property (nonatomic, strong) NSNumber *min;

/*
 * Maximum Cardinality (a number or *)
 */
@property (nonatomic, strong) FHIRString *maxElement;

@property (nonatomic, strong) NSString *max;

/*
 * Data type and Profile for this element
 */
@property (nonatomic, strong) NSArray/*<TypeRefComponent>*/ *type;

/*
 * To another element constraint (by element.name)
 */
@property (nonatomic, strong) FHIRString *nameReferenceElement;

@property (nonatomic, strong) NSString *nameReference;

/*
 * Fixed value: [as defined for a primitive type]
 */
@property (nonatomic, strong) FHIRElement *value;

/*
 * Example value: [as defined for type]
 */
@property (nonatomic, strong) FHIRElement *example;

/*
 * Length for strings
 */
@property (nonatomic, strong) FHIRInteger *maxLengthElement;

@property (nonatomic, strong) NSNumber *maxLength;

/*
 * Reference to invariant about presence
 */
@property (nonatomic, strong) NSArray/*<id>*/ *conditionElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *condition;

/*
 * Condition that must evaluate to true
 */
@property (nonatomic, strong) NSArray/*<ElementDefinitionConstraintComponent>*/ *constraint;

/*
 * If the element must supported
 */
@property (nonatomic, strong) FHIRBoolean *mustSupportElement;

@property (nonatomic, strong) NSNumber *mustSupport;

/*
 * If this modifies the meaning of other elements
 */
@property (nonatomic, strong) FHIRBoolean *isModifierElement;

@property (nonatomic, strong) NSNumber *isModifier;

/*
 * ValueSet details if this is coded
 */
@property (nonatomic, strong) FHIRElementDefinitionBindingComponent *binding;

/*
 * Map element to another set of definitions
 */
@property (nonatomic, strong) NSArray/*<ElementDefinitionMappingComponent>*/ *mapping;

- (FHIRErrorList *)validate;

@end
