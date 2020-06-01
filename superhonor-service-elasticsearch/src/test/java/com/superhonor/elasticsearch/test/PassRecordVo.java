package com.superhonor.elasticsearch.test;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuweidong
 */
@Data
@ToString
public class PassRecordVo implements Serializable {

    private Long id;
    private String cardNo;
    private String plateNo;
    private Long passRecordOrigId;
    private String uniqueIdentifier;
    private Long productId;
    private Long customerId;
    private String customerName;
    private Date passTime;
    private Date chargeTime;
    private BigDecimal fee;
    private String entrance;
    private String exit;
    private Boolean delFlag;
    private Date createTime;
    private Date modifiedTime;

    public static PassRecordVo init() {
        PassRecordVo passRecordVo = new PassRecordVo();
        passRecordVo.setId(1L);
        passRecordVo.setCardNo("12345678");
        passRecordVo.setChargeTime(new Date());
        passRecordVo.setCreateTime(new Date());
        passRecordVo.setCustomerId(2L);
        passRecordVo.setCustomerName("芳华");
        passRecordVo.setDelFlag(false);
        passRecordVo.setEntrance("九江");
        passRecordVo.setExit("上饶");
        passRecordVo.setFee(new BigDecimal("100.05"));
        passRecordVo.setModifiedTime(new Date());
        passRecordVo.setPassRecordOrigId(789L);
        passRecordVo.setPassTime(new Date());
        passRecordVo.setPlateNo("鲁A123456");
        passRecordVo.setProductId(16L);
        passRecordVo.setUniqueIdentifier("unique12345678");
        return passRecordVo;
    }

}
