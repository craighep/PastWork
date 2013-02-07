package entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonMethod;

/**
 *
 * @author James Bowcott
 */
@MappedSuperclass
@JsonAutoDetect(JsonMethod.NONE)
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    private Long id;
    
    @JsonIgnore
    public Long getId() {
	return id;
    }
    
    public void setId(Long id) {
	this.id = id;
    }

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	//if (!(object instanceof AbstractEntity)) {
	if (!object.getClass().isInstance(this.getClass())) {
	    return false;
	}
	AbstractEntity other = (AbstractEntity) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return this.getClass() + "[ id=" + id + " ]";
    }
    
//    public boolean isLocal() {
//	return this.id != null;
//    }
    
    public static String generateUID() {
	return UUID.randomUUID().toString();
    }
    
}
