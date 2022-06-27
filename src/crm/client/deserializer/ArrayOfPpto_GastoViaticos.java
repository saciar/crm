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
import crm.libraries.abm.entities.Ppto_GastoViaticos;

public class ArrayOfPpto_GastoViaticos implements Serializable {
	private Ppto_GastoViaticos[] ppto_GastoViaticos;

	public ArrayOfPpto_GastoViaticos() {
	}

	public Ppto_GastoViaticos[] getPpto_GastoViaticos() {
		return ppto_GastoViaticos;
	}

	public void setPpto_GastoViaticos(Ppto_GastoViaticos[] ppto_GastoViaticos) {
		this.ppto_GastoViaticos = ppto_GastoViaticos;
	}

	public Ppto_GastoViaticos getPpto_GastoViaticos(int i) {
		return ppto_GastoViaticos[i];
	}

	public void setPpto_GastoViaticos(int i, Ppto_GastoViaticos value) {
		this.ppto_GastoViaticos[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfPpto_GastoViaticos))
			return false;
		ArrayOfPpto_GastoViaticos other = (ArrayOfPpto_GastoViaticos) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((ppto_GastoViaticos == null && other.getPpto_GastoViaticos() == null) || (ppto_GastoViaticos != null && java.util.Arrays
				.equals(ppto_GastoViaticos, other.getPpto_GastoViaticos())));
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
		if (getPpto_GastoViaticos() != null) {
			for (int i = 0; i < Array.getLength(getPpto_GastoViaticos()); i++) {
				java.lang.Object obj = Array.get(getPpto_GastoViaticos(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfPpto_GastoViaticos.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfPpto_GastoViaticos"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("ppto_GastoViaticos");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Ppto_GastoViaticos"));
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
