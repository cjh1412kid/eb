using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml;
using System.Data;
using System.IO;
using Pacia.Configuration.EnDe;

namespace NuiteThousandsShoes
{
    public class XmlConfig
    {
        private string url;//xml文件路径

        public string Url
        {
            get { return url; }
            set { url = value; }
        }
        public static string RootNameALL = "contents";//根节点


        private static string encodeAttr = "encryption";
        private static string encodeValue = "1";
        private static string noencodeValue = "0";
        private   EnDeAll eda = new EnDeAll();
        public XmlConfig(string url)
        {
            this.url = url;
            eda.GetEnDeObject(EnDeFactory.Algorithm.DES);
            if (File.Exists(url) == false)
            {
                System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
                XmlDeclaration xmlDeclaration = xmlDocument.CreateXmlDeclaration("1.0", "UTF-8", null);
                xmlDocument.AppendChild(xmlDeclaration);
                        XmlElement xmlElement = xmlDocument.CreateElement(RootNameALL);
                        xmlDocument.AppendChild(xmlElement);
                xmlDocument.Save(url);
            }
        }

        public XmlData GetXmlNodeContent(string nodeName)
        {
            System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
            xmlDocument.Load(url);
            XmlNodeList xmlNodeList = xmlDocument.SelectSingleNode(RootNameALL).ChildNodes;
            XmlNode xn = FindXmlNode(nodeName, xmlNodeList);

            if (xn != null)
            {
                XmlData xmlData = new XmlData();
                xmlData.NodeName = xn.Name;
                xmlData.ParentNodeName = xn.ParentNode.Name;
                if (xn is XmlElement)
                {
                    XmlElement xmlElement = (XmlElement)xn;//将子节点类型转换为XmlElement类型
                    string yn = xmlElement.GetAttribute(encodeAttr);
                    if (yn == encodeValue)
                    {
                        xmlData.BEncode = true;
                        if (eda.DecryptString(xmlElement.InnerText))
                        {
                            xmlData.NodeValue = eda.DeStr;
                        }
                    }
                    else
                    {
                        xmlData.BEncode = false;
                        xmlData.NodeValue = xmlElement.InnerText;
                    }
                }
                else
                {
                    xmlData.NodeValue = xn.InnerText;

                }
                return xmlData;
            }
            return null;
        }

        /// <summary>
        /// 新增方法
        /// </summary>
        /// <param name="rootName"></param>
        /// <param name="nodeName"></param>
        /// <returns></returns>
        public XmlData GetXmlNodeContentByParentNode(string rootName, string nodeName,bool bAllValue=false)
        {
            System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
            xmlDocument.Load(url);
            XmlNodeList xmlRootList = xmlDocument.SelectSingleNode(RootNameALL).ChildNodes;
            XmlNode root = FindXmlNode(rootName, xmlRootList);
            if (root != null)
            {
                if (bAllValue)
                {
                    XmlData xmlData = new XmlData();
                    foreach (XmlNode rootNew in xmlRootList)
                    {
                        XmlNodeList xmlNodeList = rootNew.ChildNodes;
                        foreach (XmlNode xn in xmlNodeList)
                        {
                            if (xn != null && xn.Name == nodeName)
                            {
                                xmlData.NodeName = xn.Name;
                                xmlData.ParentNodeName = xn.ParentNode.Name;
                                if (xn is XmlElement)
                                {
                                    XmlElement xmlElement = (XmlElement)xn;//将子节点类型转换为XmlElement类型
                                    string yn = xmlElement.GetAttribute(encodeAttr);
                                    if (yn == encodeValue)
                                    {
                                        xmlData.BEncode = true;
                                        if (eda.DecryptString(xmlElement.InnerText))
                                        {
                                            xmlData.NodeValue += eda.DeStr + ",";
                                        }
                                    }
                                    else
                                    {
                                        xmlData.BEncode = false;
                                        xmlData.NodeValue += xmlElement.InnerText + ",";
                                    }
                                }
                                else
                                {
                                    xmlData.NodeValue += xn.InnerText + ",";

                                }
                            }
                        }
                    }
                    if (xmlData.NodeValue != "") xmlData.NodeValue = xmlData.NodeValue.Substring(0, xmlData.NodeValue.Length - 1);
                    return xmlData;
                }
                else
                {
                    XmlNodeList xmlNodeList = root.ChildNodes;
                    XmlNode xn = FindXmlNode(nodeName, xmlNodeList);
                    if (xn != null)
                    {
                        XmlData xmlData = new XmlData();
                        xmlData.NodeName = xn.Name;
                        xmlData.ParentNodeName = xn.ParentNode.Name;
                        if (xn is XmlElement)
                        {
                            XmlElement xmlElement = (XmlElement)xn;//将子节点类型转换为XmlElement类型
                            string yn = xmlElement.GetAttribute(encodeAttr);
                            if (yn == encodeValue)
                            {
                                xmlData.BEncode = true;
                                if (eda.DecryptString(xmlElement.InnerText))
                                {
                                    xmlData.NodeValue = eda.DeStr;
                                }
                            }
                            else
                            {
                                xmlData.BEncode = false;
                                xmlData.NodeValue = xmlElement.InnerText;
                            }
                        }
                        else
                        {
                            xmlData.NodeValue = xn.InnerText;

                        }
                        return xmlData;
                    }
                }
            }
            return null;
        }

        private void ReadXml(XmlNodeList parentList,List<XmlData> xmlDataList)
        {

            if (parentList != null)
            {
                foreach (XmlNode xn in parentList)
                {
                    XmlData xmlData = new XmlData();
                    xmlData.NodeName = xn.Name;
                    xmlData.ParentNodeName = xn.ParentNode.Name;
                    if (xn is XmlElement)
                    {
                        XmlElement xmlElement = (XmlElement)xn;//将子节点类型转换为XmlElement类型
                        string yn = "";
                        xmlElement.GetAttribute(encodeAttr, yn);
                        xmlData.NodeValue = "";
                        if (xmlElement.HasAttribute(encodeAttr))
                        {
                            if (yn == encodeValue)
                            {
                                xmlData.BEncode = true;
                                if (eda.DecryptString(xn.InnerText))
                                {
                                    xmlData.NodeValue = eda.DeStr;
                                }
                            }
                            else
                            {
                                xmlData.BEncode = false;
                                xmlData.NodeValue = xn.InnerText;
                            }
                        }
                        xmlDataList.Add(xmlData);
                        if (xn.ChildNodes.Count > 0)
                        {
                            ReadXml(xn.ChildNodes, xmlDataList);
                        }
                    }

                }
            }
        }

        public List<XmlData> GetXmlNodeNames()
        {
            System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
            xmlDocument.Load(url);
            XmlNodeList xmlNodeList = xmlDocument.SelectSingleNode(RootNameALL).ChildNodes;

            List<XmlData> xmlDataList = new List<XmlData>();
            ReadXml(xmlNodeList, xmlDataList);
            return xmlDataList;
        }

        public  bool InsertXmlNodeContent(string rootName, string nodeName, string newValue, bool bEncode)
        {
                System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
                xmlDocument.Load(url);
                XmlNode root = null;
                if (rootName == RootNameALL)
                {
                    root = xmlDocument.SelectSingleNode(RootNameALL);
              
                }
                else
                {
                    root = FindXmlNode(rootName, xmlDocument.SelectSingleNode(RootNameALL).ChildNodes);
                   
                }
                if (root != null)
                {
                    XmlNodeList xmlNodeList = root.ChildNodes;
                    XmlNode xn = FindXmlNode(nodeName, xmlNodeList);
                    if (xn == null)
                    {
                        XmlElement xmlElement = xmlDocument.CreateElement(nodeName);
                        if(newValue!=""){
                        if (bEncode)
                        {
                            if (eda.EncryptString(newValue))
                            {
                                xmlElement.InnerText = eda.EnStr;
                            }
                            xmlElement.SetAttribute(encodeAttr, encodeValue);
                        }
                        else
                        {
                            xmlElement.InnerText = newValue;
                            xmlElement.SetAttribute(encodeAttr, noencodeValue);
                        }}
                        root.AppendChild(xmlElement);
                    }
                    xmlDocument.Save(url);
                    return true;
                }
            return false;
        }

        public  bool DeletXmlNodeContent(string rootName, string nodeName)
        {
            System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
            xmlDocument.Load(url);
            XmlNodeList xmlNodeList = xmlDocument.SelectSingleNode(RootNameALL).ChildNodes;
            XmlNode xn = FindXmlNode(nodeName, xmlNodeList);
            if (xn != null)
            {
                if (xn.ChildNodes.Count > 0)
                {
                    xn.RemoveAll();
                }
                xn.ParentNode.RemoveChild(xn);
                xmlDocument.Save(url);
                return true;
            }
            return false;
        }
        private XmlNode FindXmlNode(string findNodeName, System.Xml.XmlNodeList parentList)
        {

            if (parentList != null)
            {
                foreach (System.Xml.XmlNode xn in parentList)
                {
                    if (xn.Name == findNodeName)
                    {
                        return xn;
                    }
                    if (xn.ChildNodes.Count > 0)
                    {
                        XmlNode xnFind = FindXmlNode(findNodeName, xn.ChildNodes);
                        if (xnFind != null)
                        {
                            return xnFind;
                        }
                    }
                }
            }
            return null;
        }


        public bool UpdateXmlContent(string nodeName, string value, bool bEncode)
        {
            System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
            xmlDocument.Load(url);
            XmlNodeList xmlNodeList = xmlDocument.SelectSingleNode(RootNameALL).ChildNodes;
            XmlNode xn = FindXmlNode(nodeName, xmlNodeList);
            if (xn == null)
            {
                InsertXmlNodeContent(RootNameALL, nodeName, value, bEncode);
            }
            xn = FindXmlNode(nodeName, xmlNodeList);
            if (xn != null)
            {
                XmlElement xmlElement = (XmlElement)xn;
              
                if (bEncode)
                {
                    xmlElement.SetAttribute(encodeAttr, encodeValue);
                    if (eda.EncryptString(value))
                    {
                        xmlElement.InnerText = eda.EnStr;
                    }
                }
                else
                {
                    xmlElement.SetAttribute(encodeAttr, noencodeValue);
                    xmlElement.InnerText = value;
                }
                xmlDocument.Save(url);
                return true;
            }       
            return false;
        }

        /// <summary>
        /// 新增方法
        /// </summary>
        /// <param name="nodeName"></param>
        /// <param name="value"></param>
        /// <param name="bEncode"></param>
        /// <param name="rootName"></param>
        /// <returns></returns>
        public bool UpdateXmlContentByParentNode(string nodeName, string value, bool bEncode, string rootName)
        {
            System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
            xmlDocument.Load(url);
            XmlNode root = null;
            if (rootName == RootNameALL)
            {
                root = xmlDocument.SelectSingleNode(RootNameALL);
            }
            else
            {
                root = FindXmlNode(rootName, xmlDocument.SelectSingleNode(RootNameALL).ChildNodes);
            }
            if (root != null)
            {
                XmlNodeList xmlNodeList = root.ChildNodes;
                XmlNode xn = FindXmlNode(nodeName, xmlNodeList);

                if (xn != null)
                {
                    XmlElement xmlElement = (XmlElement)xn;
                    if (xmlElement.HasAttribute(encodeAttr))
                    {
                        if (bEncode)
                        {
                            xmlElement.SetAttribute(encodeAttr, encodeValue);
                        }
                        else
                        {
                            xmlElement.SetAttribute(encodeAttr, noencodeValue);
                        }
                    }
                    if (bEncode)
                    {
                        if (eda.EncryptString(value))
                        {
                            xmlElement.InnerText = eda.EnStr;
                        }
                    }
                    else
                    {
                        xmlElement.InnerText = value;
                    }
                    xmlDocument.Save(url);
                    return true;
                }
            }
            return false;
        }


        /// <summary>
        /// 新增方法获取XML文件所有内容
        /// </summary>
        /// <returns></returns>
        public string GetXMLAllContent()
        {
            System.Xml.XmlDocument xmlDocument = new System.Xml.XmlDocument();
            xmlDocument.Load(url);
            string xmlContent = xmlDocument.InnerXml;
            if (!string.IsNullOrEmpty(xmlContent))
            {
                return xmlContent;
            }
            return "";
        }
    }
}
