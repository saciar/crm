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
import crm.libraries.abm.entities.Ppto_GastoVarios;

public class ArrayOfPpto_GastoVarios implements Serializable {
	private Ppto_GastoVarios[] ppto_GastoVarios;

	public ArrayOfPpto_GastoVarios() {
	}

	public Ppto_GastoVarios[] getPpto_GastoVarios() {
		return ppto_GastoVarios;
	}

	public void setPpto_GastoVarios(Ppto_GastoVarios[] ppto_GastoVarios) {
		this.ppto_GastoVarios = ppto_GastoVarios;
	}

	public Ppto_GastoVarios getPpto_GastoVarios(int i) {
		return ppto_GastoVarios[i];
	}

	public void setPpto_GastoVarios(int i, Ppto_GastoVarios value) {
		this.ppto_GastoVarios[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfPpto_GastoVarios))
			return false;
		ArrayOfPpto_GastoVarios other = (ArrayOfPpto_GastoVarios) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((ppto_GastoVarios == null && other.getPpto_GastoVarios() == null) || (ppto_GastoVarios != null && java.util.Arrays
				.equals(ppto_GastoVarios, other.getPpto_GastoVarios())));
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
		if (getPpto_GastoVarios() != null) {
			for (int i = 0; i < Array.getLength(getPpto_GastoVarios()); i++) {
				java.lang.Object obj = Array.get(getPpto_GastoVarios(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfPpto_GastoVarios.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfPpto_GastoVarios"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("ppto_GastoVarios");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Ppto_GastoVarios"));
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
