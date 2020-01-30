package rolodex;

import rolodex.Enums.PhoneTypeEnum;


/**
 * A phone number record.
 * 
 * @author peter.nguyen
 */

public class PhoneNumberRecord 
{
    // Data fields.
    private PhoneTypeEnum type;
    private String number;
    
    // Constructors.
    public PhoneNumberRecord()
    {
        this.number = "";
        this.type = PhoneTypeEnum.NotSelected;
    }
    
    public PhoneNumberRecord(PhoneTypeEnum type, String number) 
    {
        this.number = number;
        this.type = type;
    }
    
    /**
     * @return the type
     */
    public PhoneTypeEnum getType()
    {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(PhoneTypeEnum type)
    {
        this.type = type;
    }

    /**
     * @return the number
     */
    public String getNumber()
    {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) 
    {
        this.number = number;
    }   
}
