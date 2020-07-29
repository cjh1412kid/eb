using System;
using System.Net;
using System.IO;
using System.CodeDom;
using Microsoft.CSharp;
using System.CodeDom.Compiler;
using System.Web.Services.Description;
using System.Web.Services.Protocols;

using Pacia.Server.Common;
using Nuite.DBAccessServer;
using System.Net.NetworkInformation;
using System.Text;
using System.Net.Sockets;
using System.Threading;
using MemoryShare;
namespace Nuite.ServerAccess
{
    /* 调用方式    
   *   string url = "http://www.webservicex.net/globalweather.asmx" ;    
   *   string[] args = new string[2] ;    
   *   args[0] = "Hangzhou";    
   *   args[1] = "China" ;    
   *   object result = WebServiceHelper.InvokeWebService(url ,"GetWeather" ,args) ;    
   *   Response.Write(result.ToString());    
   */
    public class WebServiceHelper
    {
        private static object obj = null;
        private static System.Reflection.MethodInfo mi = null;
        private static Type t = null;
        private static DBAccessServerRemoting objDBAccess = null;
        private static System.Reflection.MethodInfo miDBAccess = null;
        private static Type tDBAccess = null;
        public static string ErrorStr = "";
        private static bool fBConn = false;
        public static string ACCessFilePath = "";

        #region 得到店面序号
        public static string GetShopInfo()
        {
            MemoryShareMapped msp = new MemoryShareMapped();
            object objShop = msp.GetData("shopinfoseq");
            if (objShop != null)
            {
                return objShop.ToString();
            }
            else
            {
                ServiceResultData obj = InvokeWebService("", "GetShopInfo", new object[] { "57d99d89-caab-482a-a0e9-NuiteThousandsShoes", "" });
                if (obj!=null && obj.BusinessResult == 0)
                {
                    if (ServerMethod.DataTableHasDataRow(obj.DataTable))
                    {
                        objShop = obj.DataTable.Rows[0][0].ToString();
                        msp.SetData("shopinfoseq", objShop);
                        return objShop.ToString();
                    }
                }
            }

            return "";
        }
        #endregion
        #region InvokeWebService
        /// < summary>    
        /// 动态调用web服务    
        /// < /summary>    
        /// < param name="url">WSDL服务地址< /param>    
        /// < param name="methodname">方法名< /param>    
        /// < param name="args">参数< /param>    
        /// < returns>< /returns>    
        public static ServiceResultData InvokeWebService(string url, string methodname, object[] args)
        {
            ErrorStr = "";
            //return WebServiceHelper.InvokeWebService(url, null, methodname, args);
            return WebServiceHelper.InvokeDBAccessService(url, methodname, args);
        }

        /// < summary>    
        /// 动态调用web服务    
        /// < /summary>    
        /// < param name="url">WSDL服务地址< /param>    
        /// < param name="classname">类名< /param>    
        /// < param name="methodname">方法名< /param>    
        /// < param name="args">参数< /param>    
        /// < returns>< /returns>    
        public static ServiceResultData InvokeDBAccessService(string url, string methodname, object[] args)
        {
            try
            {
                if (url == "")
                {
                    //单机版代码
                    if (objDBAccess == null)
                    {
                        objDBAccess = new DBAccessServerRemoting();
                        string filedatapath = System.Windows.Forms.Application.StartupPath;
                        objDBAccess.InitForServer(filedatapath, ACCessFilePath);
                        //生成代理实例，并调用方法    
                        tDBAccess = typeof(DBAccessServerRemoting);
                    }
                }
                else
                {
                    string ipAddress = url.Substring(6, url.Length - 34);
                    if (PingIpOrDomainName(ipAddress) == false) return null;
                    if (objDBAccess == null)
                    {
                        objDBAccess = (DBAccessServerRemoting)Activator.GetObject(typeof(DBAccessServerRemoting), url);
                        //生成代理实例，并调用方法    
                        tDBAccess = typeof(DBAccessServerRemoting);
                    }
                }
                miDBAccess = tDBAccess.GetMethod(methodname);
                object obnow = miDBAccess.Invoke(objDBAccess, args);
                return ServerMethod.DeSerialize(obnow.ToString()) as ServiceResultData;
                /*    
                PropertyInfo propertyInfo = type.GetProperty(propertyname);    
                return propertyInfo.GetValue(obj, null);    
                */
            }
            catch (Exception ex)
            {
                ErrorStr = "请求数据服务失败，原因:" + ex.Message;
                ServerMethod.WriteToLogFile(ex.Message, true);
            }
            return null;
        }
        /// < summary>    
        /// 动态调用web服务    
        /// < /summary>    
        /// < param name="url">WSDL服务地址< /param>    
        /// < param name="classname">类名< /param>    
        /// < param name="methodname">方法名< /param>    
        /// < param name="args">参数< /param>    
        /// < returns>< /returns>    
        private static ServiceResultData InvokeWebService(string url, string classname, string methodname, object[] args)
        {
            //string @namespace = "EnterpriseServerBase.WebService.DynamicWebCalling";


            try
            {
                if (obj == null)
                {
                    string @namespace = "";
                    if ((classname == null) || (classname == ""))
                    {
                        classname = WebServiceHelper.GetWsClassName(url);
                    }
                    //获取WSDL    
                    WebClient wc = new WebClient();

                    Stream stream = wc.OpenRead(url + "?WSDL");
                    ServiceDescription sd = ServiceDescription.Read(stream);
                    ServiceDescriptionImporter sdi = new ServiceDescriptionImporter();
                    sdi.AddServiceDescription(sd, "", "");
                    CodeNamespace cn = new CodeNamespace(@namespace);
                    //生成客户端代理类代码    
                    CodeCompileUnit ccu = new CodeCompileUnit();
                    ccu.Namespaces.Add(cn);
                    sdi.Import(cn, ccu);
                    CSharpCodeProvider icc = new CSharpCodeProvider();
                    //设定编译参数    
                    CompilerParameters cplist = new CompilerParameters();
                    cplist.GenerateExecutable = false;
                    cplist.GenerateInMemory = true;
                    cplist.ReferencedAssemblies.Add("System.dll");
                    cplist.ReferencedAssemblies.Add("System.XML.dll");
                    cplist.ReferencedAssemblies.Add("System.Web.Services.dll");
                    cplist.ReferencedAssemblies.Add("System.Data.dll");
                    //编译代理类    
                    CompilerResults cr = icc.CompileAssemblyFromDom(cplist, ccu);
                    if (true == cr.Errors.HasErrors)
                    {
                        System.Text.StringBuilder sb = new System.Text.StringBuilder();
                        foreach (System.CodeDom.Compiler.CompilerError ce in cr.Errors)
                        {
                            sb.Append(ce.ToString());
                            sb.Append(System.Environment.NewLine);
                        }
                        throw new Exception(sb.ToString());
                    }
                    //生成代理实例，并调用方法    
                    System.Reflection.Assembly assembly = cr.CompiledAssembly;
                    t = assembly.GetType(@namespace + "." + classname, true, true);
                    obj = Activator.CreateInstance(t);
                }
                mi = t.GetMethod(methodname);
                object obnow = mi.Invoke(obj, args);
                return ServerMethod.DeSerialize(obnow.ToString()) as ServiceResultData;
                /*    
                PropertyInfo propertyInfo = type.GetProperty(propertyname);    
                return propertyInfo.GetValue(obj, null);    
                */
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, true);
            }
            return null;
        }

        private static string GetWsClassName(string wsUrl)
        {
            string[] parts = wsUrl.Split('/');
            string[] pps = parts[parts.Length - 1].Split('.');
            return pps[0];
        }
        #endregion

        #region 用于检查IP地址是否可以使用TCP/IP协议访问(使用Remoting、Socket、FTP)
        private static void ThreadTestCon(object obj)
        {
            string strIpOrDName = obj.ToString();
            try
            {
                string ip = "";
                int port = 0;
                if (strIpOrDName.IndexOf(':') < 0)
                {
                    strIpOrDName = strIpOrDName + ":9900";
                }

                string[] a = strIpOrDName.Split(':');
                ip = a[0];
                port = Convert.ToInt32(a[1]);
                Socket client = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
                client.Connect(new IPEndPoint(IPAddress.Parse(ip), port));
                fBConn = true;
            }
            catch (Exception ex)
            {
                ErrorStr = "检查网络" + strIpOrDName + "异常：" + ex.Message;
                fBConn = false;
            }
        }
        /// <summary>
        /// 用于检查IP地址是否可以使用TCP/IP协议访问(使用Remoting、Socket、FTP)
        /// </summary>
        /// <param name="strIpOrDName">ip:port(ip地址：端口号)</param>
        /// <returns></returns>
        public static bool PingIpForSocket(string strIpOrDName)
        {
            try
            {
                fBConn = false;
                if (strIpOrDName.IndexOf(':') < 0)
                {
                    fBConn = PingIpOrDomainName(strIpOrDName);
                }
                else
                {
                    fBConn = PingIpOrDomainName(strIpOrDName.Split(':')[0]);
                }
                if (fBConn) return fBConn;
                Thread curConnThread = null;
                if (curConnThread == null)
                {
                    curConnThread = new Thread(ThreadTestCon);
                    curConnThread.Start(strIpOrDName);
                }
                int curSecond = 0;
                while (curSecond <= 3 && fBConn == false)
                {
                    Thread.Sleep(1000);
                    curSecond++;
                }
                curConnThread.Abort();
                curConnThread = null;
                return fBConn;
            }
            catch (Exception ex)
            {
                ErrorStr = "检查网络" + strIpOrDName + "异常：" + ex.Message;
                return false;
            }
        }
        #endregion
        #region 用于检查IP地址或域名是否可以使用TCP/IP协议访问(使用Ping命令)
        /// <summary>
        /// 用于检查IP地址或域名是否可以使用TCP/IP协议访问(使用Ping命令),true表示Ping成功,false表示Ping失败 
        /// </summary>
        /// <param name="strIpOrDName">输入参数,表示IP地址或域名</param>
        /// <returns></returns>
        public static bool PingIpOrDomainName(string strIpOrDName)
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
    }
}
