using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows.Forms;
using System.Data.OleDb;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Diagnostics;
using System.Drawing.Imaging;
using System.Net;
using System.Drawing;
using System.Data;
using System.Net.NetworkInformation;
using System.Threading;
using System.Net.Sockets;
using System.Xml;


namespace NuiteThousandsShoes
{
    public class InitConst
    {
        public static MemoryShare.MemoryShareMapped MemoryShareMappedForOut = new MemoryShare.MemoryShareMapped();
        public static string QSAppPath = Application.StartupPath;
        public static string DefauleUser = "57d99d89-caab-482a-a0e9-NuiteThousandsShoes";// "57d99d89-caab-482a-a0e9-ComprehensiveService";
        //public static string ServiceUrl = "tcp://127.0.0.1:9900/DBAccessServerRemoting";
        public static string ServiceUrl ="";// "tcp://127.0.0.1:9900/DBAccessServerRemoting";
        public static string ApplicationText = "";
        public static string ErrorForOut = "";
        public static string QSReMusicPath = Application.StartupPath + @"\Music\";//图片目录地址
        public static bool BFIRSTSET = true;//鞋子对应的素材只有一张时是否播放切换动画和声音，默认播放true
        public static string TESTRFID = "";//"tcp://127.0.0.1:9900/DBAccessServerRemoting";

        public static string ShopID = "";
        #region XML文件与DataTable互转

        /// <summary>
        /// 将XML转成DataTable
        /// </summary>
        /// <param name="fileName"></param>
        /// <returns></returns>
        public static DataTable GetXmlToDataTable(string fileName)
        {
            DataTable dt = new DataTable();
            StreamReader sr = null;
            StringReader stream = null;
            XmlTextReader reader = null;
            try
            {
                sr = new StreamReader(fileName);
                string xmlData = sr.ReadToEnd();
                DataSet xmlDS = new DataSet();
                stream = new StringReader(xmlData);
                reader = new XmlTextReader(stream);
                xmlDS.ReadXml(reader);
                if (xmlDS != null)
                {
                    dt = xmlDS.Tables[0];
                    if (dt != null)
                    {
                        return dt;
                    }
                }
                return null;
            }
            catch
            {
                return null;
            }
            finally
            {
                sr.Close();
                reader.Close();
            }
        }

        public static DataTable GetXmlStringToDataTable(string xmlData)
        {
            DataTable dt = new DataTable();
            StringReader stream = null;
            XmlTextReader reader = null;
            try
            {
                DataSet xmlDS = new DataSet();
                stream = new StringReader(xmlData);
                reader = new XmlTextReader(stream);
                xmlDS.ReadXml(reader);
                if (xmlDS != null)
                {
                    dt = xmlDS.Tables[0];
                    if (dt != null)
                    {
                        return dt;
                    }
                }
                return null;
            }
            catch
            {
                return null;
            }
            finally
            {
                reader.Close();
            }
        }

        /// <summary>
        /// 将DataTable转成XML
        /// </summary>
        /// <param name="dt"></param>
        /// <param name="fileName"></param>
        public static void GetDataTableToXml(DataTable dt, string fileName)
        {
            try
            {
                if (dt != null)
                {
                    dt.WriteXml(fileName, true);
                }
            }
            catch
            {

            }
        }

        #endregion

        #region 读取Excel文件到DataSet

        /// <summary>
        /// 读取Excel文件到DataSet中
        /// </summary>
        /// <param name="filePath">文件路径</param>
        /// <returns></returns>
        public static DataSet ExcelToDataSet(string filePath)
        {
            string connStr = "";
            string fileType = Path.GetExtension(filePath);
            if (string.IsNullOrEmpty(fileType)) return null;

            if (fileType == ".xls")
                connStr = "Provider=Microsoft.Jet.OLEDB.4.0;" + "Data Source=" + filePath + ";" + ";Extended Properties=\"Excel 8.0;HDR=YES;IMEX=1\"";
            else
                connStr = "Provider=Microsoft.ACE.OLEDB.12.0;" + "Data Source=" + filePath + ";" + ";Extended Properties=\"Excel 12.0;HDR=YES;IMEX=1\"";
            string sql_F = "Select * FROM [{0}]";

            OleDbConnection conn = null;
            OleDbDataAdapter da = null;
            DataTable dtSheetName = null;

            DataSet ds = new DataSet();
            try
            {
                // 初始化连接，并打开
                conn = new OleDbConnection(connStr);
                conn.Open();

                // 获取数据源的表定义元数据                        
                string SheetName = "";
                dtSheetName = conn.GetOleDbSchemaTable(OleDbSchemaGuid.Tables, new object[] { null, null, null, "TABLE" });

                // 初始化适配器
                string tableName = "";
                da = new OleDbDataAdapter();
                for (int i = 0; i < dtSheetName.Rows.Count; i++)
                {
                    SheetName = (string)dtSheetName.Rows[i]["TABLE_NAME"];

                    if (SheetName.Contains("$") && !SheetName.Replace("'", "").EndsWith("$"))
                    {
                        continue;
                    }
                    tableName = SheetName.Replace("$", "");
                    da.SelectCommand = new OleDbCommand(String.Format(sql_F, SheetName), conn);
                    DataSet dsItem = new DataSet();
                    da.Fill(dsItem, tableName);//"DEFAULTDATATAB");

                    ds.Tables.Add(dsItem.Tables[0].Copy());
                }
            }
            catch (Exception ex)
            {
            }
            finally
            {
                // 关闭连接
                if (conn.State == ConnectionState.Open)
                {
                    conn.Close();
                    da.Dispose();
                    conn.Dispose();
                }
            }
            return ds;
        }

        #endregion

        #region WebColorToRGB

        public static string GetRGBFromWebColor(string webColor)
        {
            if (webColor.IndexOf("#") < 0)
            {
                webColor = "#" + webColor;
            }
            System.Drawing.Color curC = System.Drawing.Color.Black;
            try
            {
                curC = System.Drawing.ColorTranslator.FromHtml(webColor);
            }
            catch
            {
            }
            return curC.R.ToString() + "," + curC.G.ToString() + "," + curC.B;
        }

        #endregion

        #region 正则验证

        public static bool IsMatchWith(string str, string reg)
        {
            if (str == null)
                return false;
            return new Regex(reg).IsMatch(str);
        }

        #endregion

        #region 包含中文字符串长度

        public static int GetStringLength(string str)
        {
            if (str.Length == 0) return 0;
            return System.Text.Encoding.Default.GetBytes(str).Length;
        }

        #endregion

        #region 读取配置
        public static void ReadConfig()
        {
            try
            {
                string configURL = QSAppPath + @"/Config/Config.xml";
                XmlConfig xc = new XmlConfig(configURL);

                XmlData xd = xc.GetXmlNodeContentByParentNode("ShoesSystem", "BFirstSet");
                if (xd != null)
                {
                    BFIRSTSET = xd.NodeValue=="1" ?true :false;
                }
                xd = xc.GetXmlNodeContentByParentNode("ShoesSystem", "DBFile");
                if (xd != null)
                {
                    Nuite.ServerAccess.WebServiceHelper.ACCessFilePath = QSAppPath + xd.NodeValue;
                }
                xd = xc.GetXmlNodeContentByParentNode("ShoesSystem", "TestRFID");
                if (xd != null)
                {
                    TESTRFID = xd.NodeValue;
                }
                ShopID = Nuite.ServerAccess.WebServiceHelper.GetShopInfo();
            }
            catch (Exception ex)
            {
                MessageBox.Show("读取配置出现异常：" + ex.Message, "提示");
            }

        }
        #endregion
        #region 写日志
        public static void WriteToLogFile(string logString)//写本地日志文件
        {
            StreamWriter sw = null;
            try
            {
                string nowTime = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string strFileName = QSAppPath + @"/Log/" + DateTime.Now.ToString("yyyy-MM-dd") + ".log";

                string dirString = Path.GetDirectoryName(strFileName);
                if (!Directory.Exists(dirString))
                {
                    Directory.CreateDirectory(dirString);
                }
                if (!File.Exists(strFileName))
                {
                    File.Create(strFileName).Close();
                }
                sw = File.AppendText(strFileName);
                StackTrace st = new StackTrace(true);
                sw.WriteLine(nowTime);
                sw.WriteLine(st.GetFrame(2).GetMethod().Name.ToString());
                sw.WriteLine(logString);

            }
            catch
            {

            }
            if (sw != null)
            {
                sw.Close();
                sw.Dispose();
                sw = null;
            }
        }

        public static void WriteToRFIDLogFile(string logString)//写本地日志文件
        {
            StreamWriter sw = null;
            try
            {
                string nowTime = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string strFileName = QSAppPath + @"/Log/RFIDLog" + DateTime.Now.ToString("yyyy-MM-dd") + ".log";

                string dirString = Path.GetDirectoryName(strFileName);
                if (!Directory.Exists(dirString))
                {
                    Directory.CreateDirectory(dirString);
                }
                if (!File.Exists(strFileName))
                {
                    File.Create(strFileName).Close();
                }
                sw = File.AppendText(strFileName);
                //StackTrace st = new StackTrace(true);
                //sw.WriteLine(nowTime);
                //sw.WriteLine(st.GetFrame(2).GetMethod().Name.ToString());
                sw.WriteLine(logString);

            }
            catch
            {

            }
            if (sw != null)
            {
                sw.Close();
                sw.Dispose();
                sw = null;
            }
        }
        #endregion
      
        #region 调用外部文件
        public static bool OpenFile(string pathName, string arguments)
        {
            ErrorForOut = "";
            try
            {
                System.Diagnostics.Process.Start(pathName, arguments);
                return true;
            }
            catch (Exception ex)
            {
                ErrorForOut = ex.Message;
            }
            return false;
        }
        #endregion
       
        #region 用于检查IP地址或域名是否可以使用TCP/IP协议访问(使用Ping命令)
        /// <summary>
        /// 用于检查IP地址或域名是否可以使用TCP/IP协议访问(使用Ping命令),true表示Ping成功,false表示Ping失败 
        /// </summary>
        /// <param name="strIpOrDName">输入参数,表示IP地址或域名</param>
        /// <returns></returns>
        private static bool PingIpOrDomainName(string strIpOrDName)
        {
            try
            {
                if (strIpOrDName.Length == 0 && strIpOrDName.Trim() == "") return false;
                Ping objPingSender = new Ping();
                PingOptions objPinOptions = new PingOptions();
                objPinOptions.DontFragment = true;
                string data = "";
                byte[] buffer = Encoding.UTF8.GetBytes(data);
                int intTimeout = 120;
                PingReply objPinReply = objPingSender.Send(strIpOrDName, intTimeout, buffer, objPinOptions);
                string strInfo = objPinReply.Status.ToString();
                if (strInfo == "Success")
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }
        #endregion
        public static bool SetCloseShareData(bool bClose, string name)
        {
            string str = bClose ? "1" : "0";
            return MemoryShareMappedForOut.SetData(name, str);
        }
        public static bool GetCloseShareData(string name)
        {
            object obj = MemoryShareMappedForOut.GetData(name);
            if (obj != null && obj.ToString() == "1")
            {
                return true;
            }

            return false;
        }
    }
}
