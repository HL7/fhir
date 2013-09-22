rm tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/formats/*
rm tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/model/*

cp -r implementations/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/formats/* tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/formats/
cp -r implementations/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/model/* tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/model/
cp -r implementations/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/utils/* tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/utils/
cp -r implementations/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/validation/* tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/validation/

cp -r implementations/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/test/* tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/test/
cp -r implementations/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/client/* tools/java/org.hl7.fhir.instance/src/org/hl7/fhir/instance/client/
