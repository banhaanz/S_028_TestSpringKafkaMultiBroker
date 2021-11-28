package com.ktb.tungngern.ycyd.tnycydlistenerpaymentresult.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MerchantInfobean {

    private String merchantId;
    private String merchantName;
    private String merchantNameEn;
    private String branchId;
    private String branchName;
    private String cbpayCompCode;
    private String cbpayRef1;
    private String cifId;
    private String companyCode;
    private String kcorpId;
    private String merchantCategoryCode;
    private String merchantSubCategoryCode;
    private String merchantCategoryDisplay;
    private String merchantSubCategoryDisplay;
    private String merchantCitizenId;
    private String merchantTaxId;
    private String ownerFirstname;
    private String ownerLastname;
    private String registerTravelTogether;
    private String merchantMobile;
    private String email;
    private String merchantLatitude;
    private String merchantLongitude;
    private String merchantAddressNo;
    private String merchantAddressMoo;
    private String merchantAddressSoi;
    private String merchantStreet;
    private String merchantBuilding;
    private String merchantSubDistrict;
    private String merchantDistrict;
    private String merchantProvince;
    private String merchantZipCode;
    private String isJuristic;
    private String contactPersonName;
    private String contactPersonPhoneNo;
    private String branchNameWithBracket;
    private String merchantProvinceCode;
    private String gwalletAmtMaxLimit;
    private String isActive;
    private String travelTour;
    private String tourCompanyName;
    private String tourPackageName;
    private String branchNo;

    @JsonProperty("accountNos")
    private List<AccountNo> accountNos;

}
