using System;
using System.Xml;
using System.Collections.Specialized;
using System.IO;
namespace NuiteThousandsShoes
{
    /// <summary>
    /// clsXMLOp 的摘要说明
    /// </summary>
    public class clsXMLOp
    {
        private string m_FileName = null;
        XmlDocument m_xmlDoc = null;
        //
        public clsXMLOp()
        {
            //
            // TODO: 在此处添加构造函数逻辑
            m_FileName = "";
            try
            {
                m_xmlDoc = new XmlDocument();
            }
            catch 
            {
                m_xmlDoc = null;
            }
        }

        public clsXMLOp(string xmlFilename)
        {
            //
            // TODO: 在此处添加构造函数逻辑
            m_FileName = xmlFilename;
            try
            {
                m_xmlDoc = new XmlDocument();
                m_xmlDoc.Load(m_FileName);
            }
            catch
            {
                m_xmlDoc = null;
            }
        }

        public string GetAttributeData(string xPath, string attName)
        {
            string retStr = "";
            if (m_xmlDoc != null)
            {
                XmlNode node = m_xmlDoc.SelectSingleNode(xPath);
                if (node != null)
                {
                    retStr = node.Attributes[attName].Value;
                }
            }
            return retStr;

        }

        public XmlNode CreateNode(XmlNode parent, string nodeName,string nodeText,NameValueCollection []xmlAttNVC)
        {
            XmlNode newNode = null;
            XmlText xmltext=null;
            if (m_xmlDoc != null)
            {
                try
                {
                    newNode = m_xmlDoc.CreateNode(XmlNodeType.Element, nodeName, "");
                    if (newNode != null)
                    {
                        if (parent == null)
                            m_xmlDoc.AppendChild(newNode);
                        else
                            parent.AppendChild(newNode);
                        xmltext = m_xmlDoc.CreateTextNode(nodeText);
                        if (xmltext != null)
                            newNode.AppendChild(xmltext);
                         if (xmlAttNVC != null)
                         {
                             for (int i = 0; i < xmlAttNVC.GetLength(0); i++)
                             {
                                 XmlAttribute xmlAtt = m_xmlDoc.CreateAttribute(xmlAttNVC[i].Get("key"));
                                 xmlAtt.InnerText = xmlAttNVC[i].Get("value");
                                 newNode.Attributes.Append(xmlAtt);
                             }
                         }
                    }
                }
                catch
                {
                    newNode = null;
                }
           }
           return newNode;
        }

        public bool CreateXmlDeclaration(string encoding)
        {
            bool rtVal = false;
            if (m_xmlDoc != null)
            {
                try
                {
                    //XmlNode xmlnode = m_xmlDoc.CreateNode(XmlNodeType.XmlDeclaration, "", ""); 
                    XmlDeclaration xmlDeclaration = m_xmlDoc.CreateXmlDeclaration("1.0",encoding, null);    
                    m_xmlDoc.AppendChild(xmlDeclaration);
                    //m_xmlDoc.AppendChild(xmlnode);
                    rtVal = true;
                }
                catch
                { 
                
                }
            }
            return rtVal;
        }

        public bool CreateXmlDeclaration(string version, string standalone)
        {
            bool rtVal = false;
            if (m_xmlDoc != null)
            {
                try
                {
                    //XmlNode xmlnode = m_xmlDoc.CreateNode(XmlNodeType.XmlDeclaration, "", ""); 
                    XmlDeclaration xmlDeclaration = m_xmlDoc.CreateXmlDeclaration(version, null, standalone);
                    m_xmlDoc.AppendChild(xmlDeclaration);
                    //m_xmlDoc.AppendChild(xmlnode);
                    rtVal = true;
                }
                catch
                {

                }
            }
            return rtVal;
        }

        public string GetXMLString()
        { 
            if (m_xmlDoc != null)
            {
                return m_xmlDoc.OuterXml;
            }
            return "";
        }
        public Stream GetXMLStream()
        {
            if (m_xmlDoc != null)
            {
                MemoryStream msNow = new MemoryStream();
                m_xmlDoc.Save(msNow);
                return msNow;
            }
            return null;
        }

        public Stream AddOtherXML(Stream firstXML, Stream addXML)
        {
            if (m_xmlDoc != null)
            {
                firstXML.Position = 0;
                m_xmlDoc.Load(firstXML);
                XmlDocument xldOther = new XmlDocument();
                addXML.Position = 0;
                xldOther.Load(addXML);

                XmlNode root1 = m_xmlDoc.DocumentElement;
                XmlNode root2 = m_xmlDoc.ImportNode(xldOther.DocumentElement.FirstChild, true);
                root1.AppendChild(root2);
                MemoryStream msNow = new MemoryStream();
                m_xmlDoc.Save(msNow);
                return msNow;
            }
            return null;
        }
        public Stream AddOtherXML(Stream firstXML, string addXML)
        {
            if (m_xmlDoc != null)
            {
                firstXML.Position = 0;
                m_xmlDoc.Load(firstXML);
                XmlDocument xldOther = new XmlDocument();
                xldOther.LoadXml(addXML);

                XmlNode root1 = m_xmlDoc.DocumentElement;
                XmlNode root2 = m_xmlDoc.ImportNode(xldOther.DocumentElement.FirstChild, true);
                root1.ChildNodes[0].AppendChild(root2);
                MemoryStream msNow = new MemoryStream();
                m_xmlDoc.Save(msNow);
                return msNow;
            }
            return null;
        }
        public Stream AddOtherXMLForRes(Stream firstXML, string addXML)
        {
            if (m_xmlDoc != null)
            {
                firstXML.Position = 0;
                m_xmlDoc.Load(firstXML);
                XmlDocument xldOther = new XmlDocument();
                xldOther.LoadXml(addXML);

                XmlNode root1 = m_xmlDoc.DocumentElement;
                XmlNode root2 = m_xmlDoc.ImportNode(xldOther.DocumentElement, true);
                root1.ChildNodes[0].AppendChild(root2);
                MemoryStream msNow = new MemoryStream();
                m_xmlDoc.Save(msNow);
                return msNow;
            }
            return null;
        }
        public bool SaveToFile(string fileName)
        {
            try
            {
                if (m_xmlDoc != null)
                {
                    m_xmlDoc.Save(fileName);
                    return true;
                }
            }
            catch
            {
            }
            return false;
        }
        public string GetXMLStringByNode(XmlNode xn)
        {
            CreateXmlDeclaration("GB2312");
            CreateNode(null, xn.Name, "", null);
            XmlNode root2 = m_xmlDoc.ImportNode(xn.ChildNodes[0], true);
            m_xmlDoc.DocumentElement.AppendChild(root2);
            return m_xmlDoc.OuterXml;
        }
    }
}
