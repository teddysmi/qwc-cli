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
@Table(schema = "public", name = "tbl_feb")
public class FebEntity implements Serializable {

    @Id
    @Column(name = "MSISDN", unique = true, nullable = false)
    private Long msisdn;

    @Column(name = "BRN", nullable = true, updatable = false, length = 255)
    private String brn;

    @Column(name = "CUST_FULL_NAME", nullable = true, updatable = false, length = 255)
    private String custFullName;

    @Column(name = "ACCT_LVL_CMPNT_ID", nullable = true, updatable = false, length = 255)
    private String acctLvlCmpntId;

    @Column(name = "ACCT_LVL_CMPNT_DESC", nullable = true, updatable = false, length = 255)
    private String acctLvlCmpntDesc;

    @Column(name = "BILL_ACCT_NO", nullable = true, updatable = false, length = 255)
    private String billAcctNo;

    @Column(name = "CUST_CLASFN_DESC", nullable = true, updatable = false, length = 255)
    private String custClasfnDesc;

    @Column(name = "Type", nullable = true, updatable = false, length = 255)
    private String type;

    @Column(name = "Start_Date", nullable = true, updatable = false, length = 255)
    private String startDate;


    public Long getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(Long msisdn) {
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

    public String getAcctLvlCmpntId() {
        return acctLvlCmpntId;
    }

    public void setAcctLvlCmpntId(String acctLvlCmpntId) {
        this.acctLvlCmpntId = acctLvlCmpntId;
    }

    public String getAcctLvlCmpntDesc() {
        return acctLvlCmpntDesc;
    }

    public void setAcctLvlCmpntDesc(String acctLvlCmpntDesc) {
        this.acctLvlCmpntDesc = acctLvlCmpntDesc;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */



}
