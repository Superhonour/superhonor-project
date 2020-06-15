package com.superhonor.rpc.test.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author liuweidong
 */
@Setter
@Getter
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 7008065908084481927L;

    /** id */
    private Long id;

    /** 姓名 */
    private String name;

    /** 性别 */
    private Integer sex;

    /** 创建时间 */
    private Date createTime;

}