
package com.seohan.erp.mat.Dto;
 
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class ItemBalanceSaveQuery {
    @Length(max = 8)
    private String      savingDate;
    @Length(max = 4)
    private String      savingTime;
    @Length(max = 8)
    private String      oldDate;
    @Length(max = 8)
    private String      bltype;
    @Length(max = 8)
    private String      timeflag;
}
