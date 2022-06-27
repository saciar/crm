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
import crm.libraries.abm.entities.Ppto_GastoSC;

public class ArrayOfPpto_GastoSC implements Serializable {
	private Ppto_GastoSC[] ppto_GastoSC;

	public ArrayOfPpto_GastoSC() {
	}

	public Ppto_GastoSC[] getPpto_GastoSC() {
		return ppto_GastoSC;
	}

	public void setPpto_GastoSC(Ppto_GastoSC[] ppto_GastoSC) {
		this.ppto_GastoSC = ppto_GastoSC;
	}

	public Ppto_GastoSC getPpto_GastoSC(int i) {
		return ppto_GastoSC[i];
	}

	public void setPpto_GastoSC(int i, Ppto_GastoSC value) {
		this.ppto_GastoSC[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfPpto_GastoSC))
			return false;
		ArrayOfPpto_GastoSC other = (ArrayOfPpto_GastoSC) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((ppto_GastoSC == null && other.getPpto_GastoSC() == null) || (ppto_GastoSC != null && java.util.Arrays
				.equals(ppto_GastoSC, other.getPpto_GastoSC())));
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
		if (getPpto_GastoSC() != null) {
			for (int i = 0; i < Array.getLength(getPpto_GastoSC()); i++) {
				java.lang.Object obj = Array.get(getPpto_GastoSC(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfPpto_GastoSC.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfPpto_GastoSC"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("ppto_GastoSC");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Ppto_GastoSC"));
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
