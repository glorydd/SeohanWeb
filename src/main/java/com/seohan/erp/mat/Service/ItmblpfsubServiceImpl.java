package com.seohan.erp.mat.Service;

import com.seohan.erp.base.Domain.Code;
import com.seohan.erp.base.Repository.CodeRepository;
import com.seohan.erp.mat.Domain.ItmblpfsubEntity;
import com.seohan.erp.mat.Dto.ItmblpfsubDto;
import com.seohan.erp.mat.Repository.ItmblpfsubRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.seohan.SeohanWebApplication.dateFormatString;
import static com.seohan.SeohanWebApplication.timeFormatString;

@Service
public class ItmblpfsubServiceImpl implements ItmblpfsubService {
    @Autowired
    ItmblpfsubRepository itmblpfsubRepository;

    @Autowired
    CodeRepository codeRepository;

    @Override
    public ItmblpfsubEntity moveLocation(ItmblpfsubDto itmblpfsubDto) throws Exception {
        LocalDateTime savingDateTime = LocalDateTime.now();
        String nowDateString = savingDateTime.format(dateFormatString);
        String nowTimeString = savingDateTime.format(timeFormatString);

        ItmblpfsubEntity originItmblpfsub =
                itmblpfsubRepository.findById(itmblpfsubDto.getId()).orElseThrow(() -> new NotFoundException("데이터가 존재하지 않습니다."));

        boolean validCheckLocation = validCheckLocationCode(itmblpfsubDto.getWarhs(), itmblpfsubDto.getChangedLocat());
        if (!validCheckLocation) throw new NotFoundException("Location이 존재하지 않습니다.");
        if (originItmblpfsub.getJqty() < itmblpfsubDto.getChangedQty()) throw new ArithmeticException("재고 수량이 부족합니다. 가능재고 : "+ originItmblpfsub.getJqty());

        // 기존 재고 차감
        if ( originItmblpfsub.getJqty() - itmblpfsubDto.getChangedQty()== 0  ){
            itmblpfsubRepository.delete(originItmblpfsub);
        }else{
            originItmblpfsub.setJqty( originItmblpfsub.getJqty() - itmblpfsubDto.getChangedQty());
            itmblpfsubRepository.save(originItmblpfsub);
        }


        // 이동한 위치에 존재하면 더하기, 없으면 신규 생성
        ItmblpfsubEntity newItmblpfsub =ItmblpfsubEntity.builder()
                .warhs(itmblpfsubDto.getWarhs())
                .itmno(itmblpfsubDto.getItmno())
                .lotno(itmblpfsubDto.getLotno())
                .qty(itmblpfsubDto.getChangedQty())
                .jqty(itmblpfsubDto.getChangedQty())
                .indate(nowDateString)
                .intime(nowTimeString)
                .locat(itmblpfsubDto.getChangedLocat())
                .sts("1")
                .build();

        ItmblpfsubEntity destItmblpfsub = itmblpfsubRepository.findByWarhsAndLocatAndItmno(itmblpfsubDto.getWarhs(), itmblpfsubDto.getChangedLocat(), itmblpfsubDto.getItmno());
        if (destItmblpfsub!=null) {
            newItmblpfsub.setId(destItmblpfsub.getId());
            newItmblpfsub.setJqty(destItmblpfsub.getJqty() + newItmblpfsub.getJqty());
        }
        newItmblpfsub = itmblpfsubRepository.save(newItmblpfsub);
        return newItmblpfsub;
    }


    public boolean validCheckLocationCode(String warehouse, String location){
        List<Code> locationLack = codeRepository.findByAdgubAndAref1AndAfill("XQ", warehouse, location.substring(0,1));

        if(!locationLack.isEmpty()){
            String[] checkLocation = locationLack.get(0).getAsdes().trim().split("\\^");

            int newLocationRow = Integer.parseInt(location.substring(1,2));
            int newLocationCol =Integer.parseInt(location.substring(3));

            if (newLocationRow <= Integer.parseInt(checkLocation[0]) &&
                    newLocationCol     <= Integer.parseInt(checkLocation[1])){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}



//	Select Case GUBN
//	Case "ADD" ''지정입고
//			SQLSTMT = CSQL("SELECT * FROM SMLIB.ITMBLPFSUB WHERE ITMNO = '$S' AND WARHS = '$S' AND LOCAT = '$S' AND LOTNO = '$S' ", _
//			IN_ITMNO, IN_WH, IN_LOCAT, IN_LOTNO)
//	TmpRs.Open SQLSTMT, CN
//	If TmpRs.EOF = False Then ''만일 같은 품번 LOT가 있다면 수량을 더해주고
//			SQLSTMT = CSQL("update SMLIB.ITMBLPFSUB set INDATE='$S',INTIME='$S',QTY = QTY + $S,JQTY = JQTY + $S  where  ITMNO='$S' AND WARHS='$S' AND LOCAT='$S' AND LOTNO='$S'  ", _
//			Left(IN_TIME, 8), Mid(IN_TIME, 9, 4), IN_QTY, IN_QTY, IN_ITMNO, IN_WH, IN_LOCAT, IN_LOTNO)
//	CN.Execute SQLSTMT
//	Else  ''만일 품번이 없다면
//
//			SQLSTMT = CSQL("insert into SMLIB.ITMBLPFSUB values('$S','$S','$S','$S','$S','$S',$S,$S,'$S')", _
//			IN_ITMNO, IN_WH, IN_LOCAT, IN_LOTNO, Left(IN_TIME, 8), Mid(IN_TIME, 9, 4), IN_QTY, IN_QTY, "1")
//	CN.Execute SQLSTMT
//	End If
//	TmpRs.Close
//	Case "DEL" ''지정취소
//			SQLSTMT = CSQL("SELECT * FROM SMLIB.ITMBLPFSUB WHERE WARHS = '$S' AND LOCAT = '$S' AND ITMNO = '$S'  AND LOTNO = '$S' ", _
//			IN_WH, IN_LOCAT, IN_ITMNO, IN_LOTNO)
//	TmpRs.Open SQLSTMT, CN
//         ''로케이션 재고 보다 신청 수량이 적으면 UPDATE''
//	If TmpRs.EOF = False Then
//	If CDbl(TmpRs.Fields("JQTY")) > IN_QTY Then
//
//	SQLSTMT = CSQL("update SMLIB.ITMBLPFSUB set INDATE= '$S',INTIME = '$S' , JQTY = JQTY - $S  where  ITMNO = '$S' AND WARHS = '$S' AND LOCAT = '$S' AND LOTNO = '$S'  ", _
//			Left(IN_TIME, 8), Mid(IN_TIME, 9, 4), IN_QTY, IN_ITMNO, IN_WH, IN_LOCAT, IN_LOTNO)
//	CN.Execute SQLSTMT
//	ElseIf CDbl(TmpRs.Fields("JQTY")) = IN_QTY Then ''같으면 데이터 삭제함
//			SQLSTMT = CSQL("DELETE FROM SMLIB.ITMBLPFSUB WHERE WARHS = '$S' AND LOCAT = '$S' AND ITMNO = '$S' AND LOTNO = '$S' ", _
//			IN_WH, IN_LOCAT, IN_ITMNO, IN_LOTNO)
//	CN.Execute SQLSTMT
//	Else
//	MsgBox "로케이션 재고 보다 처리수량이 더 많습니다.", vbOKOnly
//	TmpRs.Close
//			LOCAT_MOVE = False
//	Exit Function
//	End If
//	End If
//	TmpRs.Close
//	End Select
//
//
