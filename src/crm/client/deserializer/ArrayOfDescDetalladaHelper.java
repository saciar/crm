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
import crm.libraries.abm.helper.DescDetalladaServicioHelper;

public class ArrayOfDescDetalladaHelper  implements java.io.Serializable {
    private DescDetalladaServicioHelper[] descDetalladaHelper;

    public ArrayOfDescDetalladaHelper() {
    }

    public DescDetalladaServicioHelper[] getDescDetalladaHelper() {
        return descDetalladaHelper;
    }

    public void setDescDetalladaHelper(DescDetalladaServicioHelper[] descDetalladaHelper) {
        this.descDetalladaHelper = descDetalladaHelper;
    }

    public DescDetalladaServicioHelper getAccesorioHelper(int i) {
        return descDetalladaHelper[i];
    }

    public void setAccesorioHelper(int i, DescDetalladaServicioHelper value) {
        this.descDetalladaHelper[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfDescDetalladaHelper)) return false;
        ArrayOfDescDetalladaHelper other = (ArrayOfDescDetalladaHelper) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((descDetalladaHelper==null && other.getDescDetalladaHelper()==null) || 
             (descDetalladaHelper!=null &&
              java.util.Arrays.equals(descDetalladaHelper, other.getDescDetalladaHelper())));
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
        if (getDescDetalladaHelper() != null) {
            for (int i=0;
                 i<Array.getLength(getDescDetalladaHelper());
                 i++) {
                java.lang.Object obj = Array.get(getDescDetalladaHelper(), i);
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
    private static TypeDesc typeDesc = new TypeDesc(ArrayOfDescDetalladaHelper.class);

    static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(), "ArrayOfDescDetalladasHelper"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("descDetalladaHelper");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "DescDetalladaServicioHelper"));
		//elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
    	
    	/*
        FieldDesc field = new ElementDesc();
        field.setFieldName("accesorioHelper");
        field.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "AccesorioHelper"));
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
