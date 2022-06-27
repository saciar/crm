package crm.client.deserializer;

import java.lang.reflect.Array;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

import crm.client.util.SystemConfig;
import crm.libraries.abm.helper.AgregadoSalaHelper;

public class ArrayOfAgregadoSalaHelper implements java.io.Serializable {
	private AgregadoSalaHelper[] agregadoSalaHelper;

	public ArrayOfAgregadoSalaHelper() {
	}

	public AgregadoSalaHelper[] getAgregadoSalaHelper() {
		return agregadoSalaHelper;
	}

	public void setAgregadoSalaHelper(AgregadoSalaHelper[] agregHelper) {
		this.agregadoSalaHelper = agregHelper;
	}

	public AgregadoSalaHelper getAgregadoSalaHelper(int i) {
		return agregadoSalaHelper[i];
	}

	public void setAgregadoSalaHelper(int i, AgregadoSalaHelper value) {
		this.agregadoSalaHelper[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfAgregadoSalaHelper))
			return false;
		ArrayOfAgregadoSalaHelper other = (ArrayOfAgregadoSalaHelper) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((agregadoSalaHelper == null && other
				.getAgregadoSalaHelper() == null) || (agregadoSalaHelper != null && java.util.Arrays
				.equals(agregadoSalaHelper, other.getAgregadoSalaHelper())));
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
		if (getAgregadoSalaHelper() != null) {
			for (int i = 0; i < Array.getLength(getAgregadoSalaHelper()); i++) {
				java.lang.Object obj = Array.get(getAgregadoSalaHelper(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfAgregadoSalaHelper.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfAgregadoSalaHelper"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("agregadoSalaHelper");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"agregadoSalaHelper"));
		// elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
		// "string"));
		elemField.setMinOccurs(0);
		typeDesc.addFieldDesc(elemField);

		/*
		 * FieldDesc field = new ElementDesc();
		 * field.setFieldName("servicioHelper"); field.setXmlName(new
		 * QName(SystemConfig.getTypesNameSpace(), "ServicioHelper"));
		 * field.setMinOccursIs0(0); typeDesc.addFieldDesc(field);
		 */
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
