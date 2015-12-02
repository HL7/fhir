package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3DataTypeEnumFactory implements EnumFactory<V3DataType> {

  public V3DataType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("ANY".equals(codeString))
      return V3DataType.ANY;
    if ("ANT<ANY>".equals(codeString))
      return V3DataType.ANT_ANY_;
    if ("ANT<CD>".equals(codeString))
      return V3DataType.ANT_CD_;
    if ("ANT<PQ>".equals(codeString))
      return V3DataType.ANT_PQ_;
    if ("BAG<ANY>".equals(codeString))
      return V3DataType.BAG_ANY_;
    if ("BAG<CD>".equals(codeString))
      return V3DataType.BAG_CD_;
    if ("BAG<PQ>".equals(codeString))
      return V3DataType.BAG_PQ_;
    if ("BL".equals(codeString))
      return V3DataType.BL;
    if ("CD".equals(codeString))
      return V3DataType.CD;
    if ("CE".equals(codeString))
      return V3DataType.CE;
    if ("CS".equals(codeString))
      return V3DataType.CS;
    if ("CV".equals(codeString))
      return V3DataType.CV;
    if ("CR".equals(codeString))
      return V3DataType.CR;
    if ("HXIT<ANY>".equals(codeString))
      return V3DataType.HXIT_ANY_;
    if ("HXIT<AD>".equals(codeString))
      return V3DataType.HXIT_AD_;
    if ("II".equals(codeString))
      return V3DataType.II;
    if ("IVL<QTY>".equals(codeString))
      return V3DataType.IVL_QTY_;
    if ("IVL<INT>".equals(codeString))
      return V3DataType.IVL_INT_;
    if ("IVL<PQ>".equals(codeString))
      return V3DataType.IVL_PQ_;
    if ("UVP<IVL<PQ>>".equals(codeString))
      return V3DataType.UVP_IVL_PQ__;
    if ("IVL<REAL>".equals(codeString))
      return V3DataType.IVL_REAL_;
    if ("IVL<TS>".equals(codeString))
      return V3DataType.IVL_TS_;
    if ("SET<TS>".equals(codeString))
      return V3DataType.SET_TS_;
    if ("EIVL<TS>".equals(codeString))
      return V3DataType.EIVL_TS_;
    if ("GTS".equals(codeString))
      return V3DataType.GTS;
    if ("PIVL<TS>".equals(codeString))
      return V3DataType.PIVL_TS_;
    if ("LIST<ANY>".equals(codeString))
      return V3DataType.LIST_ANY_;
    if ("LIST<BL>".equals(codeString))
      return V3DataType.LIST_BL_;
    if ("BIN".equals(codeString))
      return V3DataType.BIN;
    if ("ED".equals(codeString))
      return V3DataType.ED;
    if ("ST".equals(codeString))
      return V3DataType.ST;
    if ("ADXP".equals(codeString))
      return V3DataType.ADXP;
    if ("ON".equals(codeString))
      return V3DataType.ON;
    if ("PNXP".equals(codeString))
      return V3DataType.PNXP;
    if ("LIST<LIST<ANY>>".equals(codeString))
      return V3DataType.LIST_LIST_ANY__;
    if ("LIST<LIST<BL>>".equals(codeString))
      return V3DataType.LIST_LIST_BL__;
    if ("LIST<BIN>".equals(codeString))
      return V3DataType.LIST_BIN_;
    if ("LIST<ED>".equals(codeString))
      return V3DataType.LIST_ED_;
    if ("LIST<ST>".equals(codeString))
      return V3DataType.LIST_ST_;
    if ("LIST<ADXP>".equals(codeString))
      return V3DataType.LIST_ADXP_;
    if ("AD".equals(codeString))
      return V3DataType.AD;
    if ("LIST<PNXP>".equals(codeString))
      return V3DataType.LIST_PNXP_;
    if ("PN".equals(codeString))
      return V3DataType.PN;
    if ("OID".equals(codeString))
      return V3DataType.OID;
    if ("QTY".equals(codeString))
      return V3DataType.QTY;
    if ("INT".equals(codeString))
      return V3DataType.INT;
    if ("MO".equals(codeString))
      return V3DataType.MO;
    if ("PPD<QTY>".equals(codeString))
      return V3DataType.PPD_QTY_;
    if ("PQ".equals(codeString))
      return V3DataType.PQ;
    if ("PPD<PQ>".equals(codeString))
      return V3DataType.PPD_PQ_;
    if ("REAL".equals(codeString))
      return V3DataType.REAL;
    if ("PPD<REAL>".equals(codeString))
      return V3DataType.PPD_REAL_;
    if ("RTO".equals(codeString))
      return V3DataType.RTO;
    if ("TS".equals(codeString))
      return V3DataType.TS;
    if ("SET<ANY>".equals(codeString))
      return V3DataType.SET_ANY_;
    if ("SET<CD>".equals(codeString))
      return V3DataType.SET_CD_;
    if ("SET<UVP<CD>>".equals(codeString))
      return V3DataType.SET_UVP_CD__;
    if ("NPPD<CD>".equals(codeString))
      return V3DataType.NPPD_CD_;
    if ("SET<CE>".equals(codeString))
      return V3DataType.SET_CE_;
    if ("SET<CS>".equals(codeString))
      return V3DataType.SET_CS_;
    if ("SET<CV>".equals(codeString))
      return V3DataType.SET_CV_;
    if ("SET<IVL<PQ>>".equals(codeString))
      return V3DataType.SET_IVL_PQ__;
    if ("SET<UVP<IVL<PQ>>>".equals(codeString))
      return V3DataType.SET_UVP_IVL_PQ___;
    if ("NPPD<IVL<PQ>>".equals(codeString))
      return V3DataType.NPPD_IVL_PQ__;
    if ("SET<LIST<ST>>".equals(codeString))
      return V3DataType.SET_LIST_ST__;
    if ("SET<AD>".equals(codeString))
      return V3DataType.SET_AD_;
    if ("SET<HXIT<AD>>".equals(codeString))
      return V3DataType.SET_HXIT_AD__;
    if ("HIST<AD>".equals(codeString))
      return V3DataType.HIST_AD_;
    if ("SET<PQ>".equals(codeString))
      return V3DataType.SET_PQ_;
    if ("SET<ST>".equals(codeString))
      return V3DataType.SET_ST_;
    if ("SET<UVP<ANY>>".equals(codeString))
      return V3DataType.SET_UVP_ANY__;
    if ("NPPD<ANY>".equals(codeString))
      return V3DataType.NPPD_ANY_;
    if ("URL".equals(codeString))
      return V3DataType.URL;
    if ("TEL".equals(codeString))
      return V3DataType.TEL;
    if ("UVN<ANY>".equals(codeString))
      return V3DataType.UVN_ANY_;
    if ("UVN<CD>".equals(codeString))
      return V3DataType.UVN_CD_;
    if ("UVP<ANY>".equals(codeString))
      return V3DataType.UVP_ANY_;
    if ("UVP<CD>".equals(codeString))
      return V3DataType.UVP_CD_;
    if ("SET<INT>".equals(codeString))
      return V3DataType.SET_INT_;
    if ("SET<REAL>".equals(codeString))
      return V3DataType.SET_REAL_;
    throw new IllegalArgumentException("Unknown V3DataType code '"+codeString+"'");
  }

  public String toCode(V3DataType code) {
    if (code == V3DataType.ANY)
      return "ANY";
    if (code == V3DataType.ANT_ANY_)
      return "ANT<ANY>";
    if (code == V3DataType.ANT_CD_)
      return "ANT<CD>";
    if (code == V3DataType.ANT_PQ_)
      return "ANT<PQ>";
    if (code == V3DataType.BAG_ANY_)
      return "BAG<ANY>";
    if (code == V3DataType.BAG_CD_)
      return "BAG<CD>";
    if (code == V3DataType.BAG_PQ_)
      return "BAG<PQ>";
    if (code == V3DataType.BL)
      return "BL";
    if (code == V3DataType.CD)
      return "CD";
    if (code == V3DataType.CE)
      return "CE";
    if (code == V3DataType.CS)
      return "CS";
    if (code == V3DataType.CV)
      return "CV";
    if (code == V3DataType.CR)
      return "CR";
    if (code == V3DataType.HXIT_ANY_)
      return "HXIT<ANY>";
    if (code == V3DataType.HXIT_AD_)
      return "HXIT<AD>";
    if (code == V3DataType.II)
      return "II";
    if (code == V3DataType.IVL_QTY_)
      return "IVL<QTY>";
    if (code == V3DataType.IVL_INT_)
      return "IVL<INT>";
    if (code == V3DataType.IVL_PQ_)
      return "IVL<PQ>";
    if (code == V3DataType.UVP_IVL_PQ__)
      return "UVP<IVL<PQ>>";
    if (code == V3DataType.IVL_REAL_)
      return "IVL<REAL>";
    if (code == V3DataType.IVL_TS_)
      return "IVL<TS>";
    if (code == V3DataType.SET_TS_)
      return "SET<TS>";
    if (code == V3DataType.EIVL_TS_)
      return "EIVL<TS>";
    if (code == V3DataType.GTS)
      return "GTS";
    if (code == V3DataType.PIVL_TS_)
      return "PIVL<TS>";
    if (code == V3DataType.LIST_ANY_)
      return "LIST<ANY>";
    if (code == V3DataType.LIST_BL_)
      return "LIST<BL>";
    if (code == V3DataType.BIN)
      return "BIN";
    if (code == V3DataType.ED)
      return "ED";
    if (code == V3DataType.ST)
      return "ST";
    if (code == V3DataType.ADXP)
      return "ADXP";
    if (code == V3DataType.ON)
      return "ON";
    if (code == V3DataType.PNXP)
      return "PNXP";
    if (code == V3DataType.LIST_LIST_ANY__)
      return "LIST<LIST<ANY>>";
    if (code == V3DataType.LIST_LIST_BL__)
      return "LIST<LIST<BL>>";
    if (code == V3DataType.LIST_BIN_)
      return "LIST<BIN>";
    if (code == V3DataType.LIST_ED_)
      return "LIST<ED>";
    if (code == V3DataType.LIST_ST_)
      return "LIST<ST>";
    if (code == V3DataType.LIST_ADXP_)
      return "LIST<ADXP>";
    if (code == V3DataType.AD)
      return "AD";
    if (code == V3DataType.LIST_PNXP_)
      return "LIST<PNXP>";
    if (code == V3DataType.PN)
      return "PN";
    if (code == V3DataType.OID)
      return "OID";
    if (code == V3DataType.QTY)
      return "QTY";
    if (code == V3DataType.INT)
      return "INT";
    if (code == V3DataType.MO)
      return "MO";
    if (code == V3DataType.PPD_QTY_)
      return "PPD<QTY>";
    if (code == V3DataType.PQ)
      return "PQ";
    if (code == V3DataType.PPD_PQ_)
      return "PPD<PQ>";
    if (code == V3DataType.REAL)
      return "REAL";
    if (code == V3DataType.PPD_REAL_)
      return "PPD<REAL>";
    if (code == V3DataType.RTO)
      return "RTO";
    if (code == V3DataType.TS)
      return "TS";
    if (code == V3DataType.SET_ANY_)
      return "SET<ANY>";
    if (code == V3DataType.SET_CD_)
      return "SET<CD>";
    if (code == V3DataType.SET_UVP_CD__)
      return "SET<UVP<CD>>";
    if (code == V3DataType.NPPD_CD_)
      return "NPPD<CD>";
    if (code == V3DataType.SET_CE_)
      return "SET<CE>";
    if (code == V3DataType.SET_CS_)
      return "SET<CS>";
    if (code == V3DataType.SET_CV_)
      return "SET<CV>";
    if (code == V3DataType.SET_IVL_PQ__)
      return "SET<IVL<PQ>>";
    if (code == V3DataType.SET_UVP_IVL_PQ___)
      return "SET<UVP<IVL<PQ>>>";
    if (code == V3DataType.NPPD_IVL_PQ__)
      return "NPPD<IVL<PQ>>";
    if (code == V3DataType.SET_LIST_ST__)
      return "SET<LIST<ST>>";
    if (code == V3DataType.SET_AD_)
      return "SET<AD>";
    if (code == V3DataType.SET_HXIT_AD__)
      return "SET<HXIT<AD>>";
    if (code == V3DataType.HIST_AD_)
      return "HIST<AD>";
    if (code == V3DataType.SET_PQ_)
      return "SET<PQ>";
    if (code == V3DataType.SET_ST_)
      return "SET<ST>";
    if (code == V3DataType.SET_UVP_ANY__)
      return "SET<UVP<ANY>>";
    if (code == V3DataType.NPPD_ANY_)
      return "NPPD<ANY>";
    if (code == V3DataType.URL)
      return "URL";
    if (code == V3DataType.TEL)
      return "TEL";
    if (code == V3DataType.UVN_ANY_)
      return "UVN<ANY>";
    if (code == V3DataType.UVN_CD_)
      return "UVN<CD>";
    if (code == V3DataType.UVP_ANY_)
      return "UVP<ANY>";
    if (code == V3DataType.UVP_CD_)
      return "UVP<CD>";
    if (code == V3DataType.SET_INT_)
      return "SET<INT>";
    if (code == V3DataType.SET_REAL_)
      return "SET<REAL>";
    return "?";
  }


}

