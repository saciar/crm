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
import crm.libraries.abm.entities.Ppto_GastoAsistentes;

public class ArrayOfPpto_GastoAsistentes implements Serializable {
	private Ppto_GastoAsistentes[] ppto_GastoAsistentes;

	public ArrayOfPpto_GastoAsistentes() {
	}

	public Ppto_GastoAsistentes[] getPpto_GastoAsistentes() {
		return ppto_GastoAsistentes;
	}

	public void setPpto_GastoAsistentes(Ppto_GastoAsistentes[] ppto_GastoAsistentes) {
		this.ppto_GastoAsistentes = ppto_GastoAsistentes;
	}

	public Ppto_GastoAsistentes getPpto_GastoAsistentes(int i) {
		return ppto_GastoAsistentes[i];
	}

	public void setPpto_GastoAsistentes(int i, Ppto_GastoAsistentes value) {
		this.ppto_GastoAsistentes[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfPpto_GastoAsistentes))
			return false;
		ArrayOfPpto_GastoAsistentes other = (ArrayOfPpto_GastoAsistentes) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((ppto_GastoAsistentes == null && other.getPpto_GastoAsistentes() == null) || (ppto_GastoAsistentes != null && java.util.Arrays
				.equals(ppto_GastoAsistentes, other.getPpto_GastoAsistentes())));
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
		if (getPpto_GastoAsistentes() != null) {
			for (int i = 0; i < Array.getLength(getPpto_GastoAsistentes()); i++) {
				java.lang.Object obj = Array.get(getPpto_GastoAsistentes(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfPpto_GastoAsistentes.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfPpto_GastoAsistentes"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("ppto_GastoAsistentes");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Ppto_GastoAsistentes"));
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
