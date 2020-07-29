package com.seohan.base.Domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "EXECHIS", schema = "SITLIB")
public class AccessHis implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  		id			;
	private String  	company		;
	private String  	deptcode	;
	private String  	deptname	;
	private String  	mobileno	;
	private String  	username	;
	private String  	prgname		;
	private String  	ip			;
	private String  	line		;
	private Date		exectime	;

}
