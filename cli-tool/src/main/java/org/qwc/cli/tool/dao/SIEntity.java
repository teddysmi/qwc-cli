package org.qwc.cli.tool.dao;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "tbl_si")
public class SIEntity implements Serializable {

    @Id
    @Column(name = "Id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "MSISDN", unique = true, nullable = false, length = 255)
    private String msisdn;

    @Column(name = "BRN", nullable = true, updatable = false, length = 255)
    private String brn;

    @Column(name = "CUST_FULL_NAME", nullable = true, updatable = false, length = 255)
    private String custFullName;

    @Column(name = "SI_LVL_CMPNT_ID", nullable = true, updatable = false, length = 255)
    private String siLvlCmpntId;

    @Column(name = "CMPNT_DESC", nullable = true, updatable = false, length = 255)
    private String siCmpntDesc;

    @Column(name = "BILL_ACCT_NO", nullable = true, updatable = false, length = 255)
    private String billAcctNo;

    @Column(name = "CUST_CLASFN_DESC", nullable = true, updatable = false, length = 255)
    private String custClasfnDesc;

    @Column(name = "Type", nullable = true, updatable = false, length = 255)
    private String type;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getBrn() {
        return brn;
    }

    public void setBrn(String brn) {
        this.brn = brn;
    }

    public String getCustFullName() {
        return custFullName;
    }

    public void setCustFullName(String custFullName) {
        this.custFullName = custFullName;
    }

    public String getSiLvlCmpntId() {
        return siLvlCmpntId;
    }

    public void setSiLvlCmpntId(String siLvlCmpntId) {
        this.siLvlCmpntId = siLvlCmpntId;
    }

    public String getSiCmpntDesc() {
        return siCmpntDesc;
    }

    public void setSiCmpntDesc(String siCmpntDesc) {
        this.siCmpntDesc = siCmpntDesc;
    }

    public String getBillAcctNo() {
        return billAcctNo;
    }

    public void setBillAcctNo(String billAcctNo) {
        this.billAcctNo = billAcctNo;
    }

    public String getCustClasfnDesc() {
        return custClasfnDesc;
    }

    public void setCustClasfnDesc(String custClasfnDesc) {
        this.custClasfnDesc = custClasfnDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

