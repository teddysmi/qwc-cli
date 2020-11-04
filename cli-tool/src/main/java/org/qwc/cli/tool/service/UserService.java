package org.qwc.cli.tool.service;

import org.qwc.cli.tool.dao.UserEntity;

public interface UserService {

	// Use this to get credentials to login to mail
	User getUser();

	User createUser(User user);

	public class User {
		private Long id;
		private String email;
		private String password;

		public User() {
			//
		};

		public User(String email, String password) {
			this.email = email;
			this.password = password;
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

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		public void loadIntoEntity(UserEntity entity) {
			entity.setId(this.id);
			entity.setEmail(this.email);
			entity.setPassword(this.password);
		}

		public void loadFromEntity(UserEntity entity) {
			this.id = entity.getId();
			this.email = entity.getEmail();
			this.password = entity.getPassword();
		}

	}

}
