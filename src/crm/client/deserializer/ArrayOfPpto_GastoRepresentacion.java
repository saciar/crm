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
import crm.libraries.abm.entities.Ppto_GastoRepresentacion;

public class ArrayOfPpto_GastoRepresentacion implements Serializable {
	private Ppto_GastoRepresentacion[] ppto_GastoRepresentacion;

	public ArrayOfPpto_GastoRepresentacion() {
	}

	public Ppto_GastoRepresentacion[] getPpto_GastoRepresentacion() {
		return ppto_GastoRepresentacion;
	}

	public void setPpto_GastoRepresentacion(Ppto_GastoRepresentacion[] ppto_GastoRepresentacion) {
		this.ppto_GastoRepresentacion = ppto_GastoRepresentacion;
	}

	public Ppto_GastoRepresentacion getPpto_GastoRepresentacion(int i) {
		return ppto_GastoRepresentacion[i];
	}

	public void setPpto_GastoRepresentacion(int i, Ppto_GastoRepresentacion value) {
		this.ppto_GastoRepresentacion[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfPpto_GastoRepresentacion))
			return false;
		ArrayOfPpto_GastoRepresentacion other = (ArrayOfPpto_GastoRepresentacion) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((ppto_GastoRepresentacion == null && other.getPpto_GastoRepresentacion() == null) || (ppto_GastoRepresentacion != null && java.util.Arrays
				.equals(ppto_GastoRepresentacion, other.getPpto_GastoRepresentacion())));
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
		if (getPpto_GastoRepresentacion() != null) {
			for (int i = 0; i < Array.getLength(getPpto_GastoRepresentacion()); i++) {
				java.lang.Object obj = Array.get(getPpto_GastoRepresentacion(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfPpto_GastoRepresentacion.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfPpto_GastoRepresentacion"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("ppto_GastoRepresentacion");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Ppto_GastoRepresentacion"));
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
