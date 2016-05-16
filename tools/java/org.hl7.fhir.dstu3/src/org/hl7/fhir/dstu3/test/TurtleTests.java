package org.hl7.fhir.dstu3.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.hl7.fhir.dstu3.utils.Turtle;
import org.hl7.fhir.utilities.TextFile;
import org.junit.Test;

import junit.framework.Assert;

public class TurtleTests {

	@Test
	public void test() throws FileNotFoundException, IOException, Exception {
		new Turtle().parse(TextFile.fileToString("C:\\work\\org.hl7.fhir\\build\\publish\\specimen-example.ttl"));
	}

	private void doTest(String filename, boolean ok) throws Exception {
		try {
		  new Turtle().parse(TextFile.fileToString(filename));
		  Assert.assertTrue(ok);
		} catch (Exception e) {
		  Assert.assertTrue(e.getMessage(), !ok);
		}
	}

  @Test
  public void test_double_lower_case_e1() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\double_lower_case_e.nt", true);
  }
  @Test
  public void test_double_lower_case_e2() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\double_lower_case_e.ttl", true);
  }
  @Test
  public void test_empty_collection1() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\empty_collection.nt", true);
  }
  @Test
  public void test_empty_collection2() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\empty_collection.ttl", true);
  }
  @Test
  public void test_first1() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\first.nt", true);
  }
  @Test
  public void test_first2() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\first.ttl", true);
  }
  @Test
  public void test_HYPHEN_MINUS_in_localNameNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\HYPHEN_MINUS_in_localName.nt", true);
  }
  @Test
  public void test_HYPHEN_MINUS_in_localName() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\HYPHEN_MINUS_in_localName.ttl", true);
  }
  @Test
  public void test_IRI_spoNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRI_spo.nt", true);
  }
  @Test
  public void test_IRI_subject() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRI_subject.ttl", true);
  }
  @Test
  public void test_IRI_with_all_punctuationNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRI_with_all_punctuation.nt", true);
  }
  @Test
  public void test_IRI_with_all_punctuation() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRI_with_all_punctuation.ttl", true);
  }
  @Test
  public void test_IRI_with_eight_digit_numeric_escape() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRI_with_eight_digit_numeric_escape.ttl", true);
  }
  @Test
  public void test_IRI_with_four_digit_numeric_escape() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRI_with_four_digit_numeric_escape.ttl", true);
  }
  @Test
  public void test_IRIREF_datatypeNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRIREF_datatype.nt", true);
  }
  @Test
  public void test_IRIREF_datatype() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\IRIREF_datatype.ttl", true);
  }
  @Test
  public void test_labeled_blank_node_objectNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_object.nt", true);
  }
  @Test
  public void test_labeled_blank_node_object() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_object.ttl", true);
  }
  @Test
  public void test_labeled_blank_node_subjectNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_subject.nt", true);
  }
  @Test
  public void test_labeled_blank_node_subject() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_subject.ttl", true);
  }
  @Test
  public void test_labeled_blank_node_with_leading_digit() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_with_leading_digit.ttl", true);
  }
  @Test
  public void test_labeled_blank_node_with_leading_underscore() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_with_leading_underscore.ttl", true);
  }
  @Test
  public void test_labeled_blank_node_with_non_leading_extras() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_with_non_leading_extras.ttl", true);
  }
  @Test
  public void test_labeled_blank_node_with_PN_CHARS_BASE_character_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\labeled_blank_node_with_PN_CHARS_BASE_character_boundaries.ttl", true);
  }
  @Test
  public void test_langtagged_LONG() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\langtagged_LONG.ttl", true);
  }
  @Test
  public void test_langtagged_LONG_with_subtagNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\langtagged_LONG_with_subtag.nt", true);
  }
  @Test
  public void test_langtagged_LONG_with_subtag() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\langtagged_LONG_with_subtag.ttl", true);
  }
  @Test
  public void test_langtagged_non_LONGNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\langtagged_non_LONG.nt", true);
  }
  @Test
  public void test_langtagged_non_LONG() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\langtagged_non_LONG.ttl", true);
  }
  @Test
  public void test_lantag_with_subtagNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\lantag_with_subtag.nt", true);
  }
  @Test
  public void test_lantag_with_subtag() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\lantag_with_subtag.ttl", true);
  }
  @Test
  public void test_lastNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\last.nt", true);
  }
  @Test
  public void test_last() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\last.ttl", true);
  }
  @Test
  public void test_literal_falseNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_false.nt", true);
  }
  @Test
  public void test_literal_false() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_false.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG1() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG1_ascii_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1_ascii_boundaries.nt", true);
  }
  @Test
  public void test_LITERAL_LONG1_ascii_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1_ascii_boundaries.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG1_with_1_squoteNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1_with_1_squote.nt", true);
  }
  @Test
  public void test_LITERAL_LONG1_with_1_squote() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1_with_1_squote.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG1_with_2_squotesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1_with_2_squotes.nt", true);
  }
  @Test
  public void test_LITERAL_LONG1_with_2_squotes() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1_with_2_squotes.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG1_with_UTF8_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG1_with_UTF8_boundaries.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG2() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG2_ascii_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_ascii_boundaries.nt", true);
  }
  @Test
  public void test_LITERAL_LONG2_ascii_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_ascii_boundaries.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG2_with_1_squoteNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_with_1_squote.nt", true);
  }
  @Test
  public void test_LITERAL_LONG2_with_1_squote() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_with_1_squote.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG2_with_2_squotesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_with_2_squotes.nt", true);
  }
  @Test
  public void test_LITERAL_LONG2_with_2_squotes() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_with_2_squotes.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG2_with_REVERSE_SOLIDUSNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_with_REVERSE_SOLIDUS.nt", true);
  }
  @Test
  public void test_LITERAL_LONG2_with_REVERSE_SOLIDUS() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_with_REVERSE_SOLIDUS.ttl", true);
  }
  @Test
  public void test_LITERAL_LONG2_with_UTF8_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_LONG2_with_UTF8_boundaries.ttl", true);
  }
  @Test
  public void test_literal_trueNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_true.nt", true);
  }
  @Test
  public void test_literal_true() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_true.ttl", true);
  }
  @Test
  public void test_literal_with_BACKSPACENT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_BACKSPACE.nt", true);
  }
  @Test
  public void test_literal_with_BACKSPACE() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_BACKSPACE.ttl", true);
  }
  @Test
  public void test_literal_with_CARRIAGE_RETURNNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_CARRIAGE_RETURN.nt", true);
  }
  @Test
  public void test_literal_with_CARRIAGE_RETURN() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_CARRIAGE_RETURN.ttl", true);
  }
  @Test
  public void test_literal_with_CHARACTER_TABULATIONNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_CHARACTER_TABULATION.nt", true);
  }
  @Test
  public void test_literal_with_CHARACTER_TABULATION() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_CHARACTER_TABULATION.ttl", true);
  }
  @Test
  public void test_literal_with_escaped_BACKSPACE() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_escaped_BACKSPACE.ttl", true);
  }
  @Test
  public void test_literal_with_escaped_CARRIAGE_RETURN() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_escaped_CARRIAGE_RETURN.ttl", true);
  }
  @Test
  public void test_literal_with_escaped_CHARACTER_TABULATION() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_escaped_CHARACTER_TABULATION.ttl", true);
  }
  @Test
  public void test_literal_with_escaped_FORM_FEED() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_escaped_FORM_FEED.ttl", true);
  }
  @Test
  public void test_literal_with_escaped_LINE_FEED() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_escaped_LINE_FEED.ttl", true);
  }
  @Test
  public void test_literal_with_FORM_FEEDNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_FORM_FEED.nt", true);
  }
  @Test
  public void test_literal_with_FORM_FEED() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_FORM_FEED.ttl", true);
  }
  @Test
  public void test_literal_with_LINE_FEEDNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_LINE_FEED.nt", true);
  }
  @Test
  public void test_literal_with_LINE_FEED() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_LINE_FEED.ttl", true);
  }
  @Test
  public void test_literal_with_numeric_escape4NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_numeric_escape4.nt", true);
  }
  @Test
  public void test_literal_with_numeric_escape4() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_numeric_escape4.ttl", true);
  }
  @Test
  public void test_literal_with_numeric_escape8() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_numeric_escape8.ttl", true);
  }
  @Test
  public void test_literal_with_REVERSE_SOLIDUSNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_REVERSE_SOLIDUS.nt", true);
  }
  @Test
  public void test_literal_with_REVERSE_SOLIDUS() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\literal_with_REVERSE_SOLIDUS.ttl", true);
  }
  @Test
  public void test_LITERAL_with_UTF8_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL_with_UTF8_boundaries.nt", true);
  }
  @Test
  public void test_LITERAL1NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1.nt", true);
  }
  @Test
  public void test_LITERAL1() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1.ttl", true);
  }
  @Test
  public void test_LITERAL1_all_controlsNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1_all_controls.nt", true);
  }
  @Test
  public void test_LITERAL1_all_controls() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1_all_controls.ttl", true);
  }
  @Test
  public void test_LITERAL1_all_punctuationNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1_all_punctuation.nt", true);
  }
  @Test
  public void test_LITERAL1_all_punctuation() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1_all_punctuation.ttl", true);
  }
  @Test
  public void test_LITERAL1_ascii_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1_ascii_boundaries.nt", true);
  }
  @Test
  public void test_LITERAL1_ascii_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1_ascii_boundaries.ttl", true);
  }
  @Test
  public void test_LITERAL1_with_UTF8_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL1_with_UTF8_boundaries.ttl", true);
  }
  @Test
  public void test_LITERAL2() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL2.ttl", true);
  }
  @Test
  public void test_LITERAL2_ascii_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL2_ascii_boundaries.nt", true);
  }
  @Test
  public void test_LITERAL2_ascii_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL2_ascii_boundaries.ttl", true);
  }
  @Test
  public void test_LITERAL2_with_UTF8_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\LITERAL2_with_UTF8_boundaries.ttl", true);
  }
  @Test
  public void test_localName_with_assigned_nfc_bmp_PN_CHARS_BASE_character_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_assigned_nfc_bmp_PN_CHARS_BASE_character_boundaries.nt", true);
  }
  @Test
  public void test_localName_with_assigned_nfc_bmp_PN_CHARS_BASE_character_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_assigned_nfc_bmp_PN_CHARS_BASE_character_boundaries.ttl", true);
  }
  @Test
  public void test_localName_with_assigned_nfc_PN_CHARS_BASE_character_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_assigned_nfc_PN_CHARS_BASE_character_boundaries.nt", true);
  }
  @Test
  public void test_localName_with_assigned_nfc_PN_CHARS_BASE_character_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_assigned_nfc_PN_CHARS_BASE_character_boundaries.ttl", true);
  }
// don't need to support property names with ':'  
//  @Test
//  public void test_localname_with_COLONNT() throws Exception {
//    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localname_with_COLON.nt", true);
//  }
//  @Test
//  public void test_localname_with_COLON() throws Exception {
//    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localname_with_COLON.ttl", true);
//  }
  @Test
  public void test_localName_with_leading_digitNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_leading_digit.nt", true);
  }
  @Test
  public void test_localName_with_leading_digit() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_leading_digit.ttl", true);
  }
  @Test
  public void test_localName_with_leading_underscoreNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_leading_underscore.nt", true);
  }
  @Test
  public void test_localName_with_leading_underscore() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_leading_underscore.ttl", true);
  }
  @Test
  public void test_localName_with_nfc_PN_CHARS_BASE_character_boundariesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_nfc_PN_CHARS_BASE_character_boundaries.nt", true);
  }
  @Test
  public void test_localName_with_nfc_PN_CHARS_BASE_character_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_nfc_PN_CHARS_BASE_character_boundaries.ttl", true);
  }
  @Test
  public void test_localName_with_non_leading_extrasNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_non_leading_extras.nt", true);
  }
  @Test
  public void test_localName_with_non_leading_extras() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\localName_with_non_leading_extras.ttl", true);
  }
  @Test
  public void test_manifest() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\manifest.ttl", true);
  }
  @Test
  public void test_negative_numericNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\negative_numeric.nt", true);
  }
  @Test
  public void test_negative_numeric() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\negative_numeric.ttl", true);
  }
  @Test
  public void test_nested_blankNodePropertyListsNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\nested_blankNodePropertyLists.nt", true);
  }
  @Test
  public void test_nested_blankNodePropertyLists() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\nested_blankNodePropertyLists.ttl", true);
  }
  @Test
  public void test_nested_collectionNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\nested_collection.nt", true);
  }
  @Test
  public void test_nested_collection() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\nested_collection.ttl", true);
  }
  @Test
  public void test_number_sign_following_localNameNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\number_sign_following_localName.nt", true);
  }
  @Test
  public void test_number_sign_following_localName() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\number_sign_following_localName.ttl", true);
  }
  @Test
  public void test_number_sign_following_PNAME_NSNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\number_sign_following_PNAME_NS.nt", true);
  }
//  @Test
//  public void test_number_sign_following_PNAME_NS() throws Exception {
//    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\number_sign_following_PNAME_NS.ttl", true);
//  }
  @Test
  public void test_numeric_with_leading_0NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\numeric_with_leading_0.nt", true);
  }
  @Test
  public void test_numeric_with_leading_0() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\numeric_with_leading_0.ttl", true);
  }
  @Test
  public void test_objectList_with_two_objectsNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\objectList_with_two_objects.nt", true);
  }
  @Test
  public void test_objectList_with_two_objects() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\objectList_with_two_objects.ttl", true);
  }
  @Test
  public void test_old_style_base() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\old_style_base.ttl", true);
  }
  @Test
  public void test_old_style_prefix() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\old_style_prefix.ttl", true);
  }
  @Test
  public void test_percent_escaped_localNameNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\percent_escaped_localName.nt", true);
  }
  @Test
  public void test_percent_escaped_localName() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\percent_escaped_localName.ttl", true);
  }
  @Test
  public void test_positive_numericNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\positive_numeric.nt", true);
  }
  @Test
  public void test_positive_numeric() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\positive_numeric.ttl", true);
  }
  @Test
  public void test_predicateObjectList_with_two_objectListsNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\predicateObjectList_with_two_objectLists.nt", true);
  }
  @Test
  public void test_predicateObjectList_with_two_objectLists() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\predicateObjectList_with_two_objectLists.ttl", true);
  }
  @Test
  public void test_prefix_only_IRI() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefix_only_IRI.ttl", true);
  }
  @Test
  public void test_prefix_reassigned_and_usedNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefix_reassigned_and_used.nt", true);
  }
  @Test
  public void test_prefix_reassigned_and_used() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefix_reassigned_and_used.ttl", true);
  }
  @Test
  public void test_prefix_with_non_leading_extras() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefix_with_non_leading_extras.ttl", true);
  }
  @Test
  public void test_prefix_with_PN_CHARS_BASE_character_boundaries() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefix_with_PN_CHARS_BASE_character_boundaries.ttl", true);
  }
  @Test
  public void test_prefixed_IRI_object() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefixed_IRI_object.ttl", true);
  }
  @Test
  public void test_prefixed_IRI_predicate() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefixed_IRI_predicate.ttl", true);
  }
  @Test
  public void test_prefixed_name_datatype() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\prefixed_name_datatype.ttl", true);
  }
  @Test
  public void test_repeated_semis_at_end() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\repeated_semis_at_end.ttl", true);
  }
  @Test
  public void test_repeated_semis_not_at_endNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\repeated_semis_not_at_end.nt", true);
  }
  @Test
  public void test_repeated_semis_not_at_end() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\repeated_semis_not_at_end.ttl", true);
  }
  @Test
  public void test_reserved_escaped_localNameNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\reserved_escaped_localName.nt", true);
  }
  @Test
  public void test_reserved_escaped_localName() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\reserved_escaped_localName.ttl", true);
  }
  @Test
  public void test_sole_blankNodePropertyList() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\sole_blankNodePropertyList.ttl", true);
  }
  @Test
  public void test_SPARQL_style_base() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\SPARQL_style_base.ttl", true);
  }
  @Test
  public void test_SPARQL_style_prefix() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\SPARQL_style_prefix.ttl", true);
  }
  @Test
  public void test_turtle_eval_bad_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-bad-01.ttl", false);
  }
  @Test
  public void test_turtle_eval_bad_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-bad-02.ttl", false);
  }
  @Test
  public void test_turtle_eval_bad_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-bad-03.ttl", false);
  }
  @Test
  public void test_turtle_eval_bad_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-bad-04.ttl", false);
  }
  @Test
  public void test_turtle_eval_struct_01NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-struct-01.nt", true);
  }
  @Test
  public void test_turtle_eval_struct_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-struct-01.ttl", true);
  }
  @Test
  public void test_turtle_eval_struct_02NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-struct-02.nt", true);
  }
  @Test
  public void test_turtle_eval_struct_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-eval-struct-02.ttl", true);
  }
  @Test
  public void test_turtle_subm_01NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-01.nt", true);
  }
  @Test
  public void test_turtle_subm_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-01.ttl", true);
  }
  @Test
  public void test_turtle_subm_02NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-02.nt", true);
  }
  @Test
  public void test_turtle_subm_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-02.ttl", true);
  }
  @Test
  public void test_turtle_subm_03NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-03.nt", true);
  }
  @Test
  public void test_turtle_subm_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-03.ttl", true);
  }
  @Test
  public void test_turtle_subm_04NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-04.nt", true);
  }
  @Test
  public void test_turtle_subm_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-04.ttl", true);
  }
  @Test
  public void test_turtle_subm_05NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-05.nt", true);
  }
  @Test
  public void test_turtle_subm_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-05.ttl", true);
  }
  @Test
  public void test_turtle_subm_06NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-06.nt", true);
  }
  @Test
  public void test_turtle_subm_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-06.ttl", true);
  }
  @Test
  public void test_turtle_subm_07NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-07.nt", true);
  }
  @Test
  public void test_turtle_subm_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-07.ttl", true);
  }
  @Test
  public void test_NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-08.nt", true);
  }
  @Test
  public void test_turtle_subm_08() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-08.ttl", true);
  }
  @Test
  public void test_turtle_subm_09NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-09.nt", true);
  }
  @Test
  public void test_turtle_subm_09() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-09.ttl", true);
  }
  @Test
  public void test_turtle_subm_10NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-10.nt", true);
  }
  @Test
  public void test_turtle_subm_10() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-10.ttl", true);
  }
  @Test
  public void test_turtle_subm_11NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-11.nt", true);
  }
  @Test
  public void test_turtle_subm_11() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-11.ttl", true);
  }
  @Test
  public void test_turtle_subm_12NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-12.nt", true);
  }
  @Test
  public void test_turtle_subm_12() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-12.ttl", true);
  }
  @Test
  public void test_turtle_subm_13NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-13.nt", true);
  }
  @Test
  public void test_turtle_subm_13() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-13.ttl", true);
  }
  @Test
  public void test_turtle_subm_14NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-14.nt", true);
  }
  @Test
  public void test_turtle_subm_14() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-14.ttl", true);
  }
  @Test
  public void test_turtle_subm_15NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-15.nt", true);
  }
  @Test
  public void test_turtle_subm_15() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-15.ttl", true);
  }
  @Test
  public void test_turtle_subm_16NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-16.nt", true);
  }
  @Test
  public void test_turtle_subm_16() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-16.ttl", true);
  }
  @Test
  public void test_turtle_subm_17NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-17.nt", true);
  }
  @Test
  public void test_turtle_subm_17() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-17.ttl", true);
  }
  @Test
  public void test_turtle_subm_18NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-18.nt", true);
  }
  @Test
  public void test_turtle_subm_18() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-18.ttl", true);
  }
  @Test
  public void test_turtle_subm_19NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-19.nt", true);
  }
  @Test
  public void test_turtle_subm_19() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-19.ttl", true);
  }
  @Test
  public void test_turtle_subm_20NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-20.nt", true);
  }
  @Test
  public void test_turtle_subm_20() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-20.ttl", true);
  }
  @Test
  public void test_turtle_subm_21NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-21.nt", true);
  }
  @Test
  public void test_turtle_subm_21() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-21.ttl", true);
  }
  @Test
  public void test_turtle_subm_22NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-22.nt", true);
  }
  @Test
  public void test_turtle_subm_22() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-22.ttl", true);
  }
  @Test
  public void test_turtle_subm_23NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-23.nt", true);
  }
  @Test
  public void test_turtle_subm_23() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-23.ttl", true);
  }
  @Test
  public void test_turtle_subm_24NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-24.nt", true);
  }
  @Test
  public void test_turtle_subm_24() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-24.ttl", true);
  }
  @Test
  public void test_turtle_subm_25NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-25.nt", true);
  }
  @Test
  public void test_turtle_subm_25() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-25.ttl", true);
  }
  @Test
  public void test_turtle_subm_26NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-26.nt", true);
  }
  @Test
  public void test_turtle_subm_26() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-26.ttl", true);
  }
  @Test
  public void test_turtle_subm_27NT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-27.nt", true);
  }
  @Test
  public void test_turtle_subm_27() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-subm-27.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bad_base_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-base-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_base_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-base-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_base_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-base-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_blank_label_dot_end() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-blank-label-dot-end.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_esc_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-esc-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_esc_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-esc-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_esc_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-esc-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_esc_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-esc-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_kw_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-kw-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_kw_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-kw-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_kw_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-kw-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_kw_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-kw-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_kw_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-kw-05.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_lang_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-lang-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_LITERAL2_with_langtag_and_datatype() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-LITERAL2_with_langtag_and_datatype.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_ln_dash_start() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-ln-dash-start.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_ln_escape() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-ln-escape.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_ln_escape_start() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-ln-escape-start.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_missing_ns_dot_end() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-missing-ns-dot-end.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_missing_ns_dot_start() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-missing-ns-dot-start.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-05.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-06.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-07.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_08() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-08.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_09() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-09.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_10() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-10.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_11() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-11.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_12() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-12.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_n3_extras_13() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-n3-extras-13.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_ns_dot_end() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-ns-dot-end.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_ns_dot_start() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-ns-dot-start.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_num_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-num-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_num_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-num-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_num_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-num-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_num_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-num-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_num_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-num-05.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_number_dot_in_anon() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-number-dot-in-anon.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_pname_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-pname-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_pname_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-pname-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_pname_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-pname-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_prefix_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-prefix-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_prefix_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-prefix-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_prefix_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-prefix-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_prefix_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-prefix-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_prefix_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-prefix-05.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_string_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-string-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_string_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-string-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_string_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-string-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_string_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-string-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_string_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-string-05.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_string_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-string-06.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_string_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-string-07.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-05.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-06.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-07.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_08() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-08.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_09() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-09.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_10() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-10.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_11() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-11.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_12() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-12.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_13() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-13.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_14() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-14.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_15() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-15.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_16() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-16.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_struct_17() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-struct-17.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_uri_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-uri-01.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_uri_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-uri-02.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_uri_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-uri-03.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_uri_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-uri-04.ttl", false);
  }
  @Test
  public void test_turtle_syntax_bad_uri_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bad-uri-05.ttl", false);
  }
  @Test
  public void test_turtle_syntax_base_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-base-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_base_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-base-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_base_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-base-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_base_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-base-04.ttl", true);
  }
  @Test
  public void test_turtle_syntax_blank_label() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-blank-label.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-04.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-05.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-06.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-07.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_08() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-08.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_09() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-09.ttl", true);
  }
  @Test
  public void test_turtle_syntax_bnode_10() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-bnode-10.ttl", true);
  }
  @Test
  public void test_turtle_syntax_datatypes_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-datatypes-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_datatypes_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-datatypes-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_file_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-file-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_file_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-file-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_file_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-file-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_kw_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-kw-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_kw_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-kw-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_kw_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-kw-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_lists_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-lists-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_lists_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-lists-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_lists_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-lists-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_lists_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-lists-04.ttl", true);
  }
  @Test
  public void test_turtle_syntax_lists_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-lists-05.ttl", true);
  }
  @Test
  public void test_turtle_syntax_ln_colons() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-ln-colons.ttl", true);
  }
  @Test
  public void test_turtle_syntax_ln_dots() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-ln-dots.ttl", true);
  }
  @Test
  public void test_turtle_syntax_ns_dots() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-ns-dots.ttl", true);
  }
  @Test
  public void test_turtle_syntax_number_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_number_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_number_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_number_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-04.ttl", true);
  }
//  @Test
//  public void test_turtle_syntax_number_05() throws Exception {
//    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-05.ttl", true);
//  }
  @Test
  public void test_turtle_syntax_number_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-06.ttl", true);
  }
  @Test
  public void test_turtle_syntax_number_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-07.ttl", true);
  }
//  @Test
//  public void test_turtle_syntax_number_08() throws Exception {
//    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-08.ttl", true);
//  }
  @Test
  public void test_turtle_syntax_number_09() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-09.ttl", true);
  }
  @Test
  public void test_turtle_syntax_number_10() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-10.ttl", true);
  }
  @Test
  public void test_turtle_syntax_number_11() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-number-11.ttl", true);
  }
  @Test
  public void test_turtle_syntax_pname_esc_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-pname-esc-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_pname_esc_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-pname-esc-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_pname_esc_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-pname-esc-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-04.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-05.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-06.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-07.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_08() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-08.ttl", true);
  }
  @Test
  public void test_turtle_syntax_prefix_09() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-prefix-09.ttl", true);
  }
  @Test
  public void test_turtle_syntax_str_esc_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-str-esc-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_str_esc_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-str-esc-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_str_esc_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-str-esc-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-04.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-05.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_06() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-06.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_07() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-07.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_08() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-08.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_09() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-09.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_10() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-10.ttl", true);
  }
  @Test
  public void test_turtle_syntax_string_11() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-string-11.ttl", true);
  }
  @Test
  public void test_turtle_syntax_struct_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-struct-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_struct_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-struct-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_struct_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-struct-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_struct_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-struct-04.ttl", true);
  }
  @Test
  public void test_turtle_syntax_struct_05() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-struct-05.ttl", true);
  }
  @Test
  public void test_turtle_syntax_uri_01() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-uri-01.ttl", true);
  }
  @Test
  public void test_turtle_syntax_uri_02() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-uri-02.ttl", true);
  }
  @Test
  public void test_turtle_syntax_uri_03() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-uri-03.ttl", true);
  }
  @Test
  public void test_turtle_syntax_uri_04() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\turtle-syntax-uri-04.ttl", true);
  }
  @Test
  public void test_two_LITERAL_LONG2sNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\two_LITERAL_LONG2s.nt", true);
  }
  @Test
  public void test_two_LITERAL_LONG2s() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\two_LITERAL_LONG2s.ttl", true);
  }
  @Test
  public void test_underscore_in_localNameNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\underscore_in_localName.nt", true);
  }
  @Test
  public void test_underscore_in_localName() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\underscore_in_localName.ttl", true);
  }
  @Test
  public void test_anonymous_blank_node_object() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\anonymous_blank_node_object.ttl", true);
  }
  @Test
  public void test_anonymous_blank_node_subject() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\anonymous_blank_node_subject.ttl", true);
  }
  @Test
  public void test_bareword_a_predicateNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\bareword_a_predicate.nt", true);
  }
  @Test
  public void test_bareword_a_predicate() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\bareword_a_predicate.ttl", true);
  }
  @Test
  public void test_bareword_decimalNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\bareword_decimal.nt", true);
  }
  @Test
  public void test_bareword_decimal() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\bareword_decimal.ttl", true);
  }
  @Test
  public void test_bareword_doubleNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\bareword_double.nt", true);
  }
  @Test
  public void test_bareword_double() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\bareword_double.ttl", true);
  }
  @Test
  public void test_bareword_integer() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\bareword_integer.ttl", true);
  }
  @Test
  public void test_blankNodePropertyList_as_objectNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_as_object.nt", true);
  }
  @Test
  public void test_blankNodePropertyList_as_object() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_as_object.ttl", true);
  }
  @Test
  public void test_blankNodePropertyList_as_subjectNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_as_subject.nt", true);
  }
//  @Test
//  public void test_blankNodePropertyList_as_subject() throws Exception {
//    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_as_subject.ttl", true);
//  }
  
  @Test
  public void test_blankNodePropertyList_containing_collectionNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_containing_collection.nt", true);
  }
  @Test
  public void test_blankNodePropertyList_containing_collection() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_containing_collection.ttl", true);
  }
  @Test
  public void test_blankNodePropertyList_with_multiple_triplesNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_with_multiple_triples.nt", true);
  }
  @Test
  public void test_blankNodePropertyList_with_multiple_triples() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\blankNodePropertyList_with_multiple_triples.ttl", true);
  }
  @Test
  public void test_collection_objectNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\collection_object.nt", true);
  }
  @Test
  public void test_collection_object() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\collection_object.ttl", true);
  }
  @Test
  public void test_collection_subjectNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\collection_subject.nt", true);
  }
  @Test
  public void test_collection_subject() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\collection_subject.ttl", true);
  }
  @Test
  public void test_comment_following_localName() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\comment_following_localName.ttl", true);
  }
  @Test
  public void test_comment_following_PNAME_NSNT() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\comment_following_PNAME_NS.nt", true);
  }
  @Test
  public void test_comment_following_PNAME_NS() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\comment_following_PNAME_NS.ttl", true);
  }
  @Test
  public void test__default_namespace_IRI() throws Exception {
    doTest("C:\\work\\org.hl7.fhir\\build\\tests\\turtle\\default_namespace_IRI.ttl", true);
  }

}
