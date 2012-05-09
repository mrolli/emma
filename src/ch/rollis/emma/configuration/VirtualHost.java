//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2012.05.09 at 02:39:37 PM MESZ
//


package ch.rollis.emma.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for virtualHostType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="virtualHostType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aliases">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="documentRoot" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="logFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isDefault" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allowIndexes" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="serverName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "virtualHostType", propOrder = {
        "aliases",
        "documentRoot",
        "logFilename",
        "isDefault",
        "allowIndexes"
})
public class VirtualHost {

    @XmlElement(required = true)
    protected VirtualHost.Aliases aliases;
    @XmlElement(required = true)
    protected String documentRoot;
    @XmlElement(required = true)
    protected String logFilename;
    @XmlElement(defaultValue = "false")
    protected Boolean isDefault;
    @XmlElement(defaultValue = "false")
    protected Boolean allowIndexes;
    @XmlAttribute(required = true)
    protected String serverName;

    /**
     * Gets the value of the aliases property.
     * 
     * @return
     *     possible object is
     *     {@link VirtualHost.Aliases }
     * 
     */
    public List<String> getAliases() {
        return aliases.getAlias();
    }

    /**
     * Sets the value of the aliases property.
     * 
     * @param value
     *     allowed object is
     *     {@link VirtualHost.Aliases }
     * 
     */
    public void setAliases(VirtualHost.Aliases value) {
        this.aliases = value;
    }

    /**
     * Gets the value of the documentRoot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * 
     */
    public String getDocumentRoot() {
        return documentRoot;
    }

    /**
     * Sets the value of the documentRoot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * 
     */
    public void setDocumentRoot(String value) {
        this.documentRoot = value;
    }

    /**
     * Gets the value of the logFilename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * 
     */
    public String getLogFilename() {
        return logFilename;
    }

    /**
     * Sets the value of the logFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * 
     */
    public void setLogFilename(String value) {
        this.logFilename = value;
    }

    /**
     * Gets the value of the isDefault property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     * 
     */
    public Boolean isDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     * 
     */
    public void setIsDefault(Boolean value) {
        this.isDefault = value;
    }

    /**
     * Gets the value of the allowIndexes property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     * 
     */
    public Boolean isAllowIndexes() {
        return allowIndexes;
    }

    /**
     * Sets the value of the allowIndexes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     * 
     */
    public void setAllowIndexes(Boolean value) {
        this.allowIndexes = value;
    }

    /**
     * Gets the value of the serverName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     * 
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the value of the serverName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     * 
     */
    public void setServerName(String value) {
        this.serverName = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "alias"
    })
    public static class Aliases {

        protected List<String> alias;

        /**
         * Gets the value of the alias property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the alias property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAlias().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getAlias() {
            if (alias == null) {
                alias = new ArrayList<String>();
            }
            return this.alias;
        }

    }

}
