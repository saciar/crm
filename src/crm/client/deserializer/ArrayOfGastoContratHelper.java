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
import crm.libraries.abm.helper.GastoContratHelper;

public class ArrayOfGastoContratHelper implements Serializable {
	private GastoContratHelper[] gastoContratHelper;

	public ArrayOfGastoContratHelper() {
	}

	public GastoContratHelper[] getGastoContratHelper() {
		return gastoContratHelper;
	}

	public void setGastoContratHelper(GastoContratHelper[] gastoContratHelper) {
		this.gastoContratHelper = gastoContratHelper;
	}

	public GastoContratHelper getGastoContratHelper(int i) {
		return gastoContratHelper[i];
	}

	public void setGastoContratHelper(int i, GastoContratHelper value) {
		this.gastoContratHelper[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfGastoContratHelper))
			return false;
		ArrayOfGastoContratHelper other = (ArrayOfGastoContratHelper) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((gastoContratHelper == null && other.getGastoContratHelper() == null) || (gastoContratHelper != null && java.util.Arrays
				.equals(gastoContratHelper, other.getGastoContratHelper())));
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
		if (getGastoContratHelper() != null) {
			for (int i = 0; i < Array.getLength(getGastoContratHelper()); i++) {
				java.lang.Object obj = Array.get(getGastoContratHelper(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfGastoContratHelper.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfGastoContratHelper"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("gastoContratHelper");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"GastoContratHelper"));
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
