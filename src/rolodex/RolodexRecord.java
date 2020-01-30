package rolodex;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A rolodex record.
 * 
 * @author peter.nguyen
 */
public class RolodexRecord 
{
    private String firstName;
    private String lastName;
    private String middleName;
    private LinkedList<PhoneNumberRecord> phoneNumberRecordList;

    public RolodexRecord()
    {
        this.firstName = "";
        this.lastName = "";
        this.middleName = "";
        this.phoneNumberRecordList = new LinkedList<PhoneNumberRecord>();
    }
    
    public RolodexRecord(String firstName, String lastName, String middleName)
    {
        this(firstName, lastName, middleName, null);
    }
    
    public RolodexRecord(String firstName, String lastName, String middleName, LinkedList<PhoneNumberRecord> phoneNumberRecords)
    {
        this();
        
        if (null != firstName)      this.firstName = firstName;
        if (null != lastName)       this.lastName = lastName;
        if (null != middleName)     this.middleName = middleName;
        if (null != phoneNumberRecords)   
        {
            this.phoneNumberRecordList.addAll(phoneNumberRecords);             
        }
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
    {
        if (null != firstName)
            this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
    {
        if (null != lastName)
            this.lastName = lastName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName()
    {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName)
    {
        if (null != middleName)
            this.middleName = middleName;
    }

    /**
     * @return the read-only phoneNumberRecordList
     */
    public List<PhoneNumberRecord> getPhoneNumberRecordList()
    {
        return Collections.unmodifiableList(this.phoneNumberRecordList);
    }

    /**
     * @param phoneList the phoneNumberRecordList to set
     */
    public void setPhoneNumberRecordList(List<PhoneNumberRecord> phoneNumberRecordList)
    {
        if (null != phoneNumberRecordList)
        {
            this.phoneNumberRecordList.clear();
            this.phoneNumberRecordList.addAll(phoneNumberRecordList);
        }
    }
}