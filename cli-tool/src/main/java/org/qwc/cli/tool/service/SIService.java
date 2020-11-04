package org.qwc.cli.tool.service;

import org.qwc.cli.tool.dao.SIEntity;
import org.qwc.cli.tool.dao.UserEntity;

import java.util.List;

public interface SIService {
    boolean findByID(Long id);

    SI getSI();

    void createSI(List<SI> siList);

    public class SI {
        private Long msisdn;
        private String brn;
        private String custFullName;
        private String siLvlCmpntId;
        private String siCmpntDesc;
        private String billAcctNo;
        private String custClasfnDesc;
        private String type;


        public SI() {
            //
        }

        public SI(Long msisdn, String brn, String custFullName, String acctLvlCmpntId, String acctLvlCmpntDesc, String billAcctNo, String custClasfnDesc, String type, String startDate) {
            this.msisdn = msisdn;
            this.brn = brn;
            this.custFullName = custFullName;
            this.siLvlCmpntId = siLvlCmpntId;
            this.siCmpntDesc = siCmpntDesc;
            this.billAcctNo = billAcctNo;
            this.custClasfnDesc = custClasfnDesc;
            this.type = type;
        }

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

        public void loadIntoEntity(SIEntity entity) {
            entity.setMsisdn(this.msisdn);
            entity.setSiCmpntDesc(this.siCmpntDesc);
            entity.setSiLvlCmpntId(this.siLvlCmpntId);
            entity.setBillAcctNo(this.billAcctNo);
            entity.setBrn(this.brn);
            entity.setCustClasfnDesc(this.custClasfnDesc);
            entity.setCustFullName(this.custFullName);
            entity.setType(this.type);
        }

        public void loadFromEntity(SIEntity entity) {
            this.msisdn = entity.getMsisdn();
            this.siCmpntDesc = entity.getSiCmpntDesc();
            this.siLvlCmpntId = entity.getSiLvlCmpntId();
            this.billAcctNo = entity.getBillAcctNo();
            this.brn = entity.getBrn();
            this.custClasfnDesc = entity.getCustClasfnDesc();
            this.custFullName = entity.getCustFullName();
            this.type = entity.getType();
        }

    }

}
