package com.seohan.erp.it.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.seohan.SeohanWebApplication.dateFormatString;
import static com.seohan.SeohanWebApplication.timeFormatString;

@Getter @Setter
@Entity
@Table(name="IT_DAMAGE", schema="SITLIB")
public class ItDamage {
	@Id
	private Long id ;

	private String co_gb;
	private String fact;
	private String wrkct;
	private String class1;
	private String class2;
	private String class3;
	private String rtime;
	private String rsabun;
	private String rteam;
	private String rname;
	private String rtel;
	private String rtxt;
	private String ctime;
	private String csabun;
	private String cname;
	private String cteam;
	private String ctel;
	private String ctxt;
	private String attach;
	private String stat;

	protected void endItDamage(Long id){
		String nowDate =  LocalDateTime.now().format(dateFormatString);
		String nowTime = LocalDateTime.now().format(timeFormatString);
		this.ctime = nowDate + nowTime;
		this.stat = "09";
	}
}
