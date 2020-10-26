package com.seohan.erp.it.Service;

import com.seohan.erp.it.Domain.ItDamage;
import com.seohan.erp.general.Domain.Report;
import com.seohan.erp.general.Repository.ReportRepository;
import com.seohan.erp.it.Repository.ItDamageRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import static com.seohan.SeohanWebApplication.dateFormatString;
import static com.seohan.SeohanWebApplication.timeFormatString;

@Service
public class ItDamageServiceImpl implements ItDamageService {
	@Autowired
    ReportRepository reportRepository;
    @Autowired
    ItDamageRepository itDamageRepository;

	@Override
	public List<ItDamage> itDamage() throws Exception {
		return itDamageRepository.findItDamageByStat("01");
	}

	@Override
	@Transactional
	public ItDamage update(ItDamage itDamage) throws Exception {
		String nowDate =  LocalDateTime.now().format(dateFormatString);
		String nowTime = LocalDateTime.now().format(timeFormatString);

		// ItDamage updateItDamage = itDamageRepository.findById(itDamage.getId()).get();
		
		itDamage.setCtime(nowDate + nowTime);
		itDamage.setStat("09");
		
		itDamageRepository.save(itDamage);

//		Report report = new Report();
//		String docuNo[] = itDamage.getClass3().split("-");
//		if (itDamage.getClass3().trim().length()> 0 ) {
//			report.setUdate(docuNo[0]);
//			report.setSer((docuNo[1]));
//			report.setSgub("G");
//			report.setSdate(nowDate.substring(8));
//			report.setStime(nowDate.substring(8, 14));
//			report.setEdate(nowDate.substring(8));
//			report.setEtime(nowDate.substring(8, 14));
//			report.setStat("9");
//
//
//			try{
//				reportRepository.save(report);
//			}
//			catch(Exception e){
//				e.printStackTrace(); //오류 출력(방법은 여러가지)
//			}
//		}
//		itDamageRepository.save(itDamage );

		return itDamage;
	}

	@Override
	public ItDamage save(ItDamage itDamage) throws Exception {
		String nowDate =  LocalDateTime.now().format(dateFormatString);
		String nowTime = LocalDateTime.now().format(timeFormatString);

		itDamage.setCo_gb("SEOHAN");
		itDamage.setStat("01");
		itDamage.setRtime(nowDate + nowTime);
		itDamage.setClass1(itDamage.getClass1().replace(" ",""));
		if(!itDamage.getAttach().equals("")) {
			String ext = FilenameUtils.getExtension(itDamage.getAttach());
			itDamage.setAttach(nowDate + "." + ext);
		}
//		smsModel.setContent(itDamage.getRtxt() + " - 조치 완료 / 확인 후 미조치사항 전산팀 연락 바람");
//		smsModel.setPhone(itDamage.getRtel());
//		smsModel.setSendNo("043-530-3174");
		itDamageRepository.save(itDamage );

		return itDamage;
	}
}
