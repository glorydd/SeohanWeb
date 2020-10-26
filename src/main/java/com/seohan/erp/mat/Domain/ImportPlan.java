
package com.seohan.erp.mat.Domain;

import com.seohan.erp.mat.Dto.ImportPlanAlarm;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@SqlResultSetMapping(
        name = "ImportPlanAlarmMapping",
        classes = @ConstructorResult(
                targetClass = ImportPlanAlarm.class,
                columns = {
                        @ColumnResult(name="cstcd", type= String.class),
                        @ColumnResult(name="itmno", type= String.class),
                        @ColumnResult(name="warhs", type= String.class),
                        @ColumnResult(name="mqty", type=  Integer.class),
                        @ColumnResult(name="bsqty", type=  Integer.class),
                        @ColumnResult(name="tsqty", type=  Integer.class),
                        @ColumnResult(name="preqty", type= Integer.class),
                        @ColumnResult(name="expqty", type= Integer.class),
                        @ColumnResult(name="dscrp", type= String.class),
                        @ColumnResult(name="cusna", type= String.class)
                })
)
@NamedNativeQuery(
        name="findGetOmissionItemList",
        query ="SELECT A.CSTCD, A.ITMNO, B.WARHS, B.MQTY, C.BSQTY, C.TSQTY, D.PREQTY, " +
                "(B.MQTY - C.BSQTY + D.PREQTY -  C.TSQTY) EXPQTY, F.DSCRP, E.CUSNA  FROM ( " +
                    "SELECT CSTCD, ITMNO FROM SMLIB.CSTPLT WHERE ACTGB != 'C'  AND FILL1 = :userid " +
                ") A " +
                "Inner Join  (SELECT ITMNO, WARHS, MQTY FROM SMLIB.PUR_BALSET WHERE  GDATE = :workdate ) B ON A.ITMNO = B.ITMNO " +
                "LEFT OUTER JOIN ( " +
                    "SELECT CSTCD, WARHS, MITMNO, " +
                    "SUM(CASE WHEN PDATE != :todate THEN SSQTY ELSE 0 END) BSQTY, " +
                    "SUM(CASE WHEN PDATE = :todate THEN SSQTY ELSE 0 END) TSQTY  FROM SMLIB.PUR_PLNB " +
                    "WHERE PDATE BETWEEN :workdate AND :todate GROUP BY CSTCD,WARHS, MITMNO " +
                ") C ON A.ITMNO = C.MITMNO AND A.CSTCD =C.CSTCD AND B.WARHS = C.WARHS " +
                "LEFT OUTER JOIN ( " +
                    "SELECT  CSTCD, WARHS, ITMNO, " +
                    "(SUM(CASE WHEN LEFT(TRSCD, 1) = 'R' AND TRGAE NOT IN ('AG','SC') AND LOTNO != 'SSSSSS' THEN QUNTY ELSE 0 END) " +
                    "- SUM(CASE WHEN LEFT(TRSCD, 1) = 'I' AND TRGAE = 'AG' AND LOTNO != 'SSSSSS' THEN QUNTY ELSE 0 END)) PREQTY  FROM SMLIB.TRANSPF " +
                    "WHERE TRSDT BETWEEN  :workdate AND :fromdate GROUP BY CSTCD,WARHS, ITMNO " +
                ") D ON A.ITMNO = D.ITMNO AND A.CSTCD = D.CSTCD AND B.WARHS = D.WARHS " +
                "INNER JOIN SALIB.CUSTP E ON A.CSTCD= E.CODE1||E.CODE2 " +
                "INNER JOIN SMLIB.ITMSTPF F ON A.ITMNO = F.ITMNO " +
                "WHERE (B.MQTY - C.BSQTY + D.PREQTY -  C.TSQTY) < 0 ",
        resultSetMapping = "ImportPlanAlarmMapping" )

@Data
@Entity
@IdClass(ImportPlan.class)
@Table(name = "ITMBLPF", schema = "SMLIB")
public class ImportPlan implements Serializable {
    @Id
    private String      itmno;
    @Id
    private String      warhs;
    private String      locat;
    private int         onhnd;
    private int         safmx;
    private int         safmn;
    private String      avrgb;
    private int         avrqt;
    private int         leadt;
    private int         purut;
    private int         purtm;
    private String      purgb;
    private String      fill;
}