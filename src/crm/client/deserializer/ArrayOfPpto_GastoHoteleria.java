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
import crm.libraries.abm.entities.Ppto_GastoHoteleria;

public class ArrayOfPpto_GastoHoteleria implements Serializable {
	private Ppto_GastoHoteleria[] ppto_GastoHoteleria;

	public ArrayOfPpto_GastoHoteleria() {
	}

	public Ppto_GastoHoteleria[] getPpto_GastoHoteleria() {
		return ppto_GastoHoteleria;
	}

	public void setPpto_GastoHoteleria(Ppto_GastoHoteleria[] ppto_GastoHoteleria) {
		this.ppto_GastoHoteleria = ppto_GastoHoteleria;
	}

	public Ppto_GastoHoteleria getPpto_GastoHoteleria(int i) {
		return ppto_GastoHoteleria[i];
	}

	public void setPpto_GastoHoteleria(int i, Ppto_GastoHoteleria value) {
		this.ppto_GastoHoteleria[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfPpto_GastoHoteleria))
			return false;
		ArrayOfPpto_GastoHoteleria other = (ArrayOfPpto_GastoHoteleria) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((ppto_GastoHoteleria == null && other.getPpto_GastoHoteleria() == null) || (ppto_GastoHoteleria != null && java.util.Arrays
				.equals(ppto_GastoHoteleria, other.getPpto_GastoHoteleria())));
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
		if (getPpto_GastoHoteleria() != null) {
			for (int i = 0; i < Array.getLength(getPpto_GastoHoteleria()); i++) {
				java.lang.Object obj = Array.get(getPpto_GastoHoteleria(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfPpto_GastoHoteleria.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfPpto_GastoHoteleria"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("ppto_GastoHoteleria");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Ppto_GastoHoteleria"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);
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
	public static Serializer getSerializer(String mechType, Class _javaType,
			QName _xmlType) {
		return new BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static Deserializer getDeserializer(String mechType,
			Class _javaType, QName _xmlType) {
		return new BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
