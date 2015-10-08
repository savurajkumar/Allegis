package models;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity extends Model {

	@CreatedTimestamp
	public Timestamp createdon;

	@Version
	public Timestamp lastUpdate;

}