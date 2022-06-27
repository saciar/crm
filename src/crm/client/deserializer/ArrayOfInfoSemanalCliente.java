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
import crm.libraries.report.InfoSemanalCliente;;

public class ArrayOfInfoSemanalCliente implements Serializable {
    private InfoSemanalCliente[] info;

    public ArrayOfInfoSemanalCliente() {
    }

    public InfoSemanalCliente[] getInfo() {
        return info;
    }

    public InfoSemanalCliente getInfo(int i) {
        return info[i];
    }
    
    public void setInfo(InfoSemanalCliente[] info) {
		this.info = info;
	}

	public void setInfo(int i, InfoSemanalCliente value) {
        this.info[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfInfoSemanalCliente)) return false;
        ArrayOfInfoSemanalCliente other = (ArrayOfInfoSemanalCliente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((info==null && other.getInfo()==null) || 
             (info!=null &&
              java.util.Arrays.equals(info, other.getInfo())));
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
        if (getInfo() != null) {
            for (int i=0;
                 i<Array.getLength(getInfo());
                 i++) {
                java.lang.Object obj = Array.get(getInfo(), i);
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
    private static TypeDesc typeDesc = new TypeDesc(ArrayOfInfoSemanalCliente.class);

    static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(), "ArrayOfInfoSemanalCliente"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("infoSemanalCliente");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "InfoSemanalCliente"));
		//elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
    	
    	/*
        FieldDesc field = new ElementDesc();
        field.setFieldName("evento");
        field.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "Evento"));
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
