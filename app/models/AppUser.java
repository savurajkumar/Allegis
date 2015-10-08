package models;

import java.security.MessageDigest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.codec.binary.Base64;

import play.Logger;

import com.avaje.ebean.Model.Finder;

@Entity
public class AppUser extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	public String firstName;

	public String middleName;

	public String lastName;

	public String email;

	@Column(columnDefinition = "TEXT")
	public String password;

	@Column(columnDefinition = "TEXT")
	public String salt;

	public static Finder<Long, AppUser> find = new Finder<Long, AppUser>(
			AppUser.class);

	public Boolean matchPassword(final String aPassword) {
		try {
			final String passwordWithSalt = aPassword + salt;
			final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			final byte[] passBytes = passwordWithSalt.getBytes();
			final String hashedPassword = Base64.encodeBase64String(sha256
					.digest(passBytes));
			if (hashedPassword.compareTo(password) == 0) {
				return true;
			} else {
				return false;
			}
		} catch (final Exception e) {
			Logger.debug("error occured while comparing the password", e);
			return false;
		}

	}

}
