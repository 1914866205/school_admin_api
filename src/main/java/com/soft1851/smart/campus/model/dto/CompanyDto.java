package com.soft1851.smart.campus.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author su
 * @className CompanyDto
 * @Description TODO
 * @Date 2020/6/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {

    private Long id;
    private  String name;
    private  String tag;
    private  String logo;
    private  Integer workers;
    private  String type;
    private  String description;
    private  String workingTime;
    private  String workingStatus;
    private Integer jobNumbers;
    private  String vacation;
    private  String address;
    private  String longitude;
    private  String latitude;
}
