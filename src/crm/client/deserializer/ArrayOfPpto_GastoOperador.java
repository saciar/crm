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
import crm.libraries.abm.entities.Ppto_GastoOperador;

public class ArrayOfPpto_GastoOperador implements Serializable {
	private Ppto_GastoOperador[] ppto_GastoOperador;

	public ArrayOfPpto_GastoOperador() {
	}

	public Ppto_GastoOperador[] getPpto_GastoOperador() {
		return ppto_GastoOperador;
	}

	public void setPpto_GastoOperador(Ppto_GastoOperador[] ppto_GastoOperador) {
		this.ppto_GastoOperador = ppto_GastoOperador;
	}

	public Ppto_GastoOperador getPpto_GastoOperador(int i) {
		return ppto_GastoOperador[i];
	}

	public void setPpto_GastoOperador(int i, Ppto_GastoOperador value) {
		this.ppto_GastoOperador[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfPpto_GastoOperador))
			return false;
		ArrayOfPpto_GastoOperador other = (ArrayOfPpto_GastoOperador) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((ppto_GastoOperador == null && other.getPpto_GastoOperador() == null) || (ppto_GastoOperador != null && java.util.Arrays
				.equals(ppto_GastoOperador, other.getPpto_GastoOperador())));
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
		if (getPpto_GastoOperador() != null) {
			for (int i = 0; i < Array.getLength(getPpto_GastoOperador()); i++) {
				java.lang.Object obj = Array.get(getPpto_GastoOperador(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfPpto_GastoOperador.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfPpto_GastoOperador"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("ppto_GastoOperador");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Ppto_GastoOperador"));
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
