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
import crm.libraries.report.OrdenFacturacionSalas;

public class ArrayOfOrdenFacturacionSalas implements Serializable {
    private OrdenFacturacionSalas[] eventoSala;

    public ArrayOfOrdenFacturacionSalas() {
    }

    public OrdenFacturacionSalas[] getEventoSala() {
        return eventoSala;
    }

    public void setEventoSala(OrdenFacturacionSalas[] eventoSala) {
        this.eventoSala = eventoSala;
    }

    public OrdenFacturacionSalas getEventoSala(int i) {
        return eventoSala[i];
    }

    public void setEventoSala(int i, OrdenFacturacionSalas value) {
        this.eventoSala[i] = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ArrayOfOrdenFacturacionSalas)) return false;
        ArrayOfOrdenFacturacionSalas other = (ArrayOfOrdenFacturacionSalas) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((eventoSala==null && other.getEventoSala()==null) || 
             (eventoSala!=null &&
              java.util.Arrays.equals(eventoSala, other.getEventoSala())));
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
        if (getEventoSala() != null) {
            for (int i=0;
                 i<Array.getLength(getEventoSala());
                 i++) {
                java.lang.Object obj = Array.get(getEventoSala(), i);
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
    private static TypeDesc typeDesc = new TypeDesc(ArrayOfOrdenFacturacionSalas.class);

    static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(), "ArrayOfOrdenFacturacionSalas"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("eventoSala");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "OrdenFacturacionSalas"));
		//elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
    	
    	/*
        FieldDesc field = new ElementDesc();
        field.setFieldName("eventoSala");
        field.setXmlName(new QName(SystemConfig.getTypesNameSpace(), "EventoSala"));
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
