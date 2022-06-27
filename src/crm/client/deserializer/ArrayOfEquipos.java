/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.deserializer;

import crm.client.util.SystemConfig;
import crm.libraries.abm.entities.Equipos;
import java.lang.reflect.Array;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;
import javax.xml.namespace.QName;


/**
 *
 * @author saciar
 */
public class ArrayOfEquipos implements java.io.Serializable {
	private Equipos[] equipos;

	public ArrayOfEquipos() {
	}

	public Equipos[] getEquipos() {
		return equipos;
	}

	public void setEquipos(Equipos[] agregHelper) {
		this.equipos = agregHelper;
	}

	public Equipos getEquipos(int i) {
		return equipos[i];
	}

	public void setEquipos(int i, Equipos value) {
		this.equipos[i] = value;
	}

	private java.lang.Object __equalsCalc = null;

    @Override
	public synchronized boolean equals(java.lang.Object obj) {
		if (!(obj instanceof ArrayOfEquipos))
			return false;
		ArrayOfEquipos other = (ArrayOfEquipos) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && ((equipos == null && other
				.getEquipos() == null) || (equipos != null && java.util.Arrays
				.equals(equipos, other.getEquipos())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

    @Override
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getEquipos() != null) {
			for (int i = 0; i < Array.getLength(getEquipos()); i++) {
				java.lang.Object obj = Array.get(getEquipos(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(ArrayOfEquipos.class);

	static {
		typeDesc.setXmlType(new QName(SystemConfig.getTypesNameSpace(),
				"ArrayOfEquipos"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("Equipos");
		elemField.setXmlName(new QName(SystemConfig.getTypesNameSpace(),
				"Equipos"));
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
