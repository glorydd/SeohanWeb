package com.seohan.erp.mat.Domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "PUR_PLNA", schema = "SMLIB", catalog = "" )
public class PurPlna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jdate;
    private int serno;
    private String sabun;
    @Column(name = "IN_TIME")
    private String inTime;
    private String docuno;
    private String stat;

}
