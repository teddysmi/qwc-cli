package org.qwc.cli.tool.service;

import org.qwc.cli.tool.dao.RecipientEntity;

public interface RecipientService {

	// Use this to get credentials to login to mail
	Recipient getRecipient();

	Recipient createRecipient(Recipient user);

	public class Recipient {
		private Long id;
		private String email;

		public Recipient() {
			//
		};

		public Recipient(String email) {
			this.email = email;
		}

		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		public void loadIntoEntity(RecipientEntity entity) {
			entity.setId(this.id);
			entity.setEmail(this.email);

		}

		public void loadFromEntity(RecipientEntity entity) {
			this.id = entity.getId();
			this.email = entity.getEmail();
		}

	}

}
