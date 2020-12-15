package org.qwc.cli.tool.service;

import org.qwc.cli.tool.dao.FebEntity;
import org.qwc.cli.tool.dao.UserEntity;

import java.util.List;

//
public interface FebService {

	boolean findByMsisdn(String msisdn);

	Feb getFeb();

	void createFeb(List<Feb> feb);

	public class Feb {

		private Long id;
		private String msisdn;
		private String brn;
		private String custFullName;
		private String acctLvlCmpntId;
		private String acctLvlCmpntDesc;
		private String billAcctNo;
		private String custClasfnDesc;
		private String type;
		private String startDate;

		public Feb() {
			//
		}

		public Feb(Long id, String msisdn, String brn, String custFullName, String acctLvlCmpntId, String acctLvlCmpntDesc,
				String billAcctNo, String custClasfnDesc, String type, String startDate) {
			this.id = id;
			this.msisdn = msisdn;
			this.brn = brn;
			this.custFullName = custFullName;
			this.acctLvlCmpntId = acctLvlCmpntId;
			this.acctLvlCmpntDesc = acctLvlCmpntDesc;
			this.billAcctNo = billAcctNo;
			this.custClasfnDesc = custClasfnDesc;
			this.type = type;
			this.startDate = startDate;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
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

		public void loadIntoEntity(FebEntity entity) {
			entity.setId(this.id);
			entity.setMsisdn(this.msisdn);
			entity.setAcctLvlCmpntDesc(this.acctLvlCmpntDesc);
			entity.setAcctLvlCmpntId(this.acctLvlCmpntId);
			entity.setBillAcctNo(this.billAcctNo);
			entity.setBrn(this.brn);
			entity.setCustClasfnDesc(this.custClasfnDesc);
			entity.setCustFullName(this.custFullName);
			entity.setStartDate(this.startDate);
			entity.setType(this.type);
		}

		public void loadFromEntity(FebEntity entity) {
			this.id = entity.getId();
			this.msisdn = entity.getMsisdn();
			this.acctLvlCmpntDesc = entity.getAcctLvlCmpntDesc();
			this.acctLvlCmpntId = entity.getAcctLvlCmpntId();
			this.billAcctNo = entity.getBillAcctNo();
			this.brn = entity.getBrn();
			this.custClasfnDesc = entity.getCustClasfnDesc();
			this.custFullName = entity.getCustFullName();
			this.startDate = entity.getStartDate();
			this.type = entity.getType();
		}

	}

}
