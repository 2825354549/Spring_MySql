package com.ly.mysql.domain;

//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName drilling4
 */
@TableName(value ="drilling4")
@Data
public class Drilling implements Serializable {
    private Integer id;

    private Double md;

    private Double wob;

    private Double sp;

    private Double tq;

    private Double rs;

    private Double mf;

    private Double hl;

    private Double tvd;

    private Double rop;

    private static final long serialVersionUID = 1L;
}
