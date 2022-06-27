package crm.client.deserializer;

import java.lang.reflect.Array;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

import crm.client.util.SystemConfig;
import crm.libraries.abm.helper.ServicioHelper;

public class ArrayOfServicioHelper implements java.io.Serializable {
    private ServicioHelper[] servicioHelper;

    public ArrayOfServicioHelper() {
    }

    public ServicioHelper[] getServicioHelper() {
        return servicioHelper;
    }

    public void setServicioHelper(ServicioHelper[] servicioHelper) {
        this.servicioHelper = servicioHelper;
    }

    public ServicioHelper getServicioHelper(int i) {
        return servicioHelper[i];
    }

    public void setServicioHelper(int i, ServicioHelper value) {
        this.servicioHelper[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfServicioHelper)) return false;
        ArrayOfServicioHelper other = (ArrayOfServicioHelper) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((servicioHelper==null && other.getServicioHelper()==null) || 
             (servicioHelper!=null &&
              java.util.Arrays.equals(servicioHelper, other.getServicioHelper())));
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
        if (getServicioHelper() != null) {
            for (int i=0;
                 i<Array.getLength(getServicioHelper());
                 i++) {
                java.lang.Object obj = Array.get(getServicioHelper(), i);
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
    private static TypeDesc typeDesc = new TypeDesc(ArrayOfServicioHelper.class);

    static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(), "ArrayOfServicioHelper"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("servicioHelper");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "ServicioHelper"));
		//elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
    	
    	/*
        FieldDesc field = new ElementDesc();
        field.setFieldName("servicioHelper");
        field.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "ServicioHelper"));
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

