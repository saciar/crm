package crm.client.deserializer;

import java.io.Serializable;
import java.lang.reflect.Array;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

import crm.client.util.SystemConfig;

public class ArrayOfObject implements Serializable {
    private Object[] object;

    public ArrayOfObject() {
    }

    public Object[] getObject() {
        return object;
    }

    public void setObject(Object[] object) {
        this.object = object;
    }

    public Object getObject(int i) {
        return object[i];
    }

    public void setObject(int i, Object value) {
        this.object[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfObject)) return false;
        ArrayOfObject other = (ArrayOfObject) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((object==null && other.getObject()==null) || 
             (object!=null &&
              java.util.Arrays.equals(object, other.getObject())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getObject() != null) {
            for (int i=0;
                 i<Array.getLength(getObject());
                 i++) {
                java.lang.Object obj = Array.get(getObject(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static TypeDesc typeDesc = new TypeDesc(ArrayOfObject.class);

    static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(), "collection"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("anyType");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "Object"));
		//elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
    	
    	/*
        FieldDesc field = new ElementDesc();
        field.setFieldName("object");
        field.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "Object"));
        field.setMinOccursIs0(0);
        typeDesc.addFieldDesc(field);*/
    };

    /**
     * Return type metadata object
     */
    public static TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType) {
        return new BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType) {
        return new BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

}

