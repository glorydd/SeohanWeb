package com.seohan.auth.Dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor 
public class OrgUserDto implements Serializable{ 
	private String companycode	;
	private String empid	;
	private String loginid	;
	private String alias	;
	private String email	;
	private String maindeptcode	;
	private String createddt	;
	private String legacyexchangedn	;
	private String displayname	;
	private String displayyn	;
	private String roletype	;
	private String dutycode	;
	private String jobcode	;
	private String rankcode	;
	private String cellphone	;
	private String faxnumber	;
	private String extensionnumber	;
	private String locationcode	;
	private String teamchiefyn	;
	private String dateofbirth	;
	private String ifyn	;
	private String messangerifyn	;
	private String phone1	;
	private String phone2	;
	private String culture	;
	private String org_cd	;
	private String regid	;
	private Date regdt	;
	private String updid	;
	private Date upddt	;
	private String sapid	;
	private String sapmngyn	;
	private String kostl	;
	private String valid_yn	;
	private String jobdesc	;
	private String dpartner	;
	private String onsite	;
	private String sitecompany	;

}
