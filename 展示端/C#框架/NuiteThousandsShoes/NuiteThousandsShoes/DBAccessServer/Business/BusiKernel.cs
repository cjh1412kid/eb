/**************************************************************************************************
* 项目名称:分层系统业务基类
* 模块名称: 业务基类
* 当前版本: V 1.6
* 开始时间: 2008
* 完成时间:  
* 开发者  : .. shinemis_yzy ..
* 重要描述: 对所有业务类的基类,处理公用过程
***************************************************************************************************
* 版本: V 1.0 (.. shinemis_yzy ...)
* 描述: 建立
* 版本: V 1.2 (.. shinemis_yzy ...20110512.)
* 描述: 有可能一个项目对应了多个数据库，这些数据库也可能是不同的类型（ORACLE或者SQLSERVER）等，所以在以前
*       的基础上，增加了数据库连接对象管理列表的功能。
* 版本: V 1.4 (..ALEX...20111214.)
* 描述: 由于取SQL语句时，是根据当前类的TYPE来得到命名空间，这样的话，如果类之间存在继承，那么，this.GetType
*       会是最子类的实例，但是子类可能调用了父类（不知哪级父类）的方法，所以实际上应该取这个父类的Type。想来
*       想去，还是觉得直接将以前GetSqlString的SqlID参数，换为由子类传入MethodBase对象，根据该方法所在的
*       类来取Type，解决了此问题。 
* 版本: V 1.5 (..ALEX...20120413.)
* 描述: 因为每个项目的SQL语句可能会对基类进行调整，所以要预先加入对其进行设定
* 版本: V 1.6 (..ALEX...20120515.)
* 描述: 根据当前系统的开发类型，来确定是否需要先行加载SQL文件
***************************************************************************************************/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;

using Pacia.DB.DataAccess;//引用数据库操作类

using Pacia.Server.Common;
using System.IO;
using System.Reflection;
using System.Diagnostics;
using System.Threading;

namespace Nuite.DBAccessServer
{

    public class BusiKernel
    {
        public static string StrLogFileDirPath = "";
        public enum ErrorType
        {
            // 摘要:
            //     无错误类型。
            None = 0,
            //
            // 摘要:
            //     网络错误。
            NetError = 1,
            //
            // 摘要:
            //     数据错误。
            DataError = 2,
            //
            // 摘要:
            //     其它异常错误。
            OtherError = 3,

        }
        private string fStrUserAndPwd = "";
        private string fStrUserCustom = "";
        private string fStrPwdCustom = "";
        private static SystemMode CurrentSystemMode = SystemMode.BS;//保存系统架构,默认为BS方式
        private static List<string> DBConnList = null;//保存所有的数据库连接参数信息，为静态变量因有可能有多个数据库，默认每个业务类对应一个数据库（可能相同） 
        private static List<string> fOtherDBConnList = null;//保存所有的数据库连接参数信息，为静态变量因有可能有多个数据库，默认每个业务类对应一个数据库（可能相同） 

        protected DBMode fCurrentDBMode = DBMode.MSAccess;//保存数据库类型
        private string fConnectionString = "";//保存数据库连接字符串
        protected int connectionStringIndex = 0;//保存数据库连接字符串索引位置，针对多个数据库连接时使用
        private  DataAccessFactory fDataFactory;//会话类对象(BS每次都连接) 

        private ServiceResultData fServiceResultData;//保存业务执行结果 
        protected String OwnerName = "WebService层方法名：";
        private string SysName = "数据中心运行监控与数据库管理";
        private  bool fBCloseConn = false;
        private bool fBConnOtherDB = false;
        public static string AccessDBFileName = "";
        public ServiceResultData ServiceResultData
        {
            get { return fServiceResultData; }
            set { fServiceResultData = value; }
        }
        public int ServerTag = 1;
        #region 类的初始化部分（第一部分）


        private void Initialize()
        {
            fStrUserAndPwd = "57d99d89-caab-482a-a0e9-NuiteThousandsShoes";
            fServiceResultData = new ServiceResultData();
            fStrUserCustom = "LZ-NUITE";
            fStrPwdCustom = "25995667";
        }

        #endregion

        #region 类的初始化部分（第二部分）

        public BusiKernel()
        {
            Initialize();
        }
        ~BusiKernel()
        {
            CloseConnection();
        }

        #endregion

        #region 获得数据库连接信息参数(只在第一次程序启动时读取)

        private void GetDBParameterList()
        {
            string ipAddress = "192.168.2.246";
            if (fBConnOtherDB==false)
            {
                if (DBConnList == null)
                {
                    DBConnList = new List<string>();
                    if (fCurrentDBMode == DBMode.Oracle)
                    {
                        ipAddress = "YC";
                        DBConnList.Add("Data Source = " + ipAddress + ";User Id = nwbase;Password = 1qaz@WSX;");//基础库
                        DBConnList.Add("Data Source = " + ipAddress + ";User Id = NWProduct;Password = 2wsx#EDC;");//产品库
                        DBConnList.Add("Data Source = " + ipAddress + ";User Id = NWLog;Password = 5tgb^YHN;");//日志库
                        DBConnList.Add("Data Source = " + ipAddress + ";User Id = NWother;Password = 6yhn&UJM;");//其他库
                        DBConnList.Add("Data Source = " + ipAddress + ";User Id = NWPublish;Password = 4rfv%TGB;");//发布库
                        DBConnList.Add("Data Source = " + ipAddress + ";User Id = NWWarn;Password = 3edc$RFV;");//防灾减灾库x
                    }
                    else if (fCurrentDBMode == DBMode.SqlServer)
                    {
                        DBConnList.Add("Data Source = " + ipAddress + ";Initial Catalog = nwbase;User Id = nuitehn;Password = x963147;");//基础库
                        DBConnList.Add("Data Source = " + ipAddress + ";Initial Catalog = NWProduct;User Id = nuitehn;Password = x963147;");//产品库
                        DBConnList.Add("Data Source = " + ipAddress + ";Initial Catalog = NWLog;User Id = nuitehn;Password = x963147;");//日志库
                        DBConnList.Add("Data Source = " + ipAddress + ";Initial Catalog = NWother;User Id = nuitehn;Password = x963147;");//其他库
                        DBConnList.Add("Data Source = " + ipAddress + ";Initial Catalog = NWPublish;User Id = nuitehn;Password = x963147;");//发布库
                        DBConnList.Add("Data Source = " + ipAddress + ";Initial Catalog = NWWarn;User Id = nuitehn;Password = x963147;");//防灾减灾库
                    }
                    else if (fCurrentDBMode == DBMode.MSAccess)
                    {
                        string strconn = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + AccessDBFileName + ";";
                        DBConnList.Add(strconn);
                    }
                }
            }
            else 
            {
                if (fOtherDBConnList == null)
                {
                    fOtherDBConnList = new List<string>();
                    if (fCurrentDBMode == DBMode.Oracle)
                    {
                        ipAddress = "YC";
                        fOtherDBConnList.Add("Data Source = " + ipAddress + ";User Id = nwbase;Password = 1qaz@WSX;");//基础库
                        fOtherDBConnList.Add("Data Source = " + ipAddress + ";User Id = NWProduct;Password = 2wsx#EDC;");//产品库
                        fOtherDBConnList.Add("Data Source = " + ipAddress + ";User Id = NWLog;Password = 5tgb^YHN;");//日志库
                        fOtherDBConnList.Add("Data Source = " + ipAddress + ";User Id = NWother;Password = 6yhn&UJM;");//其他库
                        fOtherDBConnList.Add("Data Source = " + ipAddress + ";User Id = NWPublish;Password = 4rfv%TGB;");//发布库
                        fOtherDBConnList.Add("Data Source = " + ipAddress + ";User Id = NWWarn;Password = 3edc$RFV;");//防灾减灾库x
                    }
                    else if (fCurrentDBMode == DBMode.SqlServer)
                    {
                        ipAddress = "172.22.68.51";
                        fOtherDBConnList.Add("Data Source = " + ipAddress + ";Initial Catalog = NIOIPD;User Id = sa;Password = kwantler_26993800;");//大喇叭库
                    }
                    else if (fCurrentDBMode == DBMode.MSAccess)
                    {
                        string strconn = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + AccessDBFileName + ";";
                        fOtherDBConnList.Add(strconn);
                    }
                }
            }
        }
       
        public static void AddDBConn(string conStr)
        {
            if (DBConnList == null)
            {
                DBConnList = new List<string>();
            }
            DBConnList.Add(conStr);
        }
        public static void AddOtherDBConn(string conStr)
        {
            if (fOtherDBConnList == null)
            {
                fOtherDBConnList = new List<string>();
            }
            fOtherDBConnList.Add(conStr);
        }
        public static string GetDBConn(int index,bool bOtherDB)
        {
            if (bOtherDB)
            {
                if (fOtherDBConnList != null)
                {
                    return fOtherDBConnList[index];
                }
            }
            else
            {
                if (DBConnList != null)
                {
                    return DBConnList[index];
                }
            }
            return "";
        }
        #endregion

        #region 连接对象的管理控制部分


        private void SwitchDBAccessObject()
        {
            GetDBParameterList();
            if (fBConnOtherDB == false)
            {
                fConnectionString = DBConnList[connectionStringIndex];//数据库连接字符串（注意，这里不弄成大小写，是因为有些数据库的密码必须要区分大小写）
            }
            else
            {
                fConnectionString = fOtherDBConnList[connectionStringIndex];//数据库连接字符串（注意，这里不弄成大小写，是因为有些数据库的密码必须要区分大小写）
            }
            if (CurrentSystemMode == SystemMode.BS)
            {
                GetAndOPenConnection(ref fDataFactory);//BS每将都要连接
            }
            else
            {
                GetAndOPenConnection(ref fDataFactory);//每将都要连接
            }
        }
        private void GetAndOPenConnection(ref DataAccessFactory df)
        {
            df = new DataAccessFactory();
            df.ConnectionString = fConnectionString;
            int dbModeIndex = (int)(fCurrentDBMode);
            df.GetConnection((DataAccessFactory.DBMode)dbModeIndex);
            df.OpenConnection();
        }
        /// <summary>
        /// 测试连接
        /// </summary>
        /// <param name="isAutoLoadCoreSQLFile">是否在测试连接后加载核心的SQL语句文件。有些项目是独立开发，所以没有核心SQL，则不需要加载。默认要加载（TRUE）</param>
        /// <returns></returns>
        protected virtual bool OpenConnection()//关闭连接,如果是BS方式,则关闭连接,CS保持连接
        {//测试连接 
            bool bSuc = false;
            try
            {
                SwitchDBAccessObject();//就是对主要数据库的连接进行测试 

                bSuc = true;
                fBCloseConn = false;
            }
            catch
            {
                throw;
            }

            return bSuc;
        }

        #endregion

        #region 与数据访问有关的方法部分

        protected virtual void StartTransaction()//开户事务
        {
            try
            {
                Monitor.Enter(this);
            }
            catch
            {
            }
            try
            {
                fDataFactory.StartTransaction();//执行查询操作  
            }
            catch
            {
            }
        }
        protected virtual void CommitTransaction()//提交事务
        {
            try
            {
                fDataFactory.CommitTransaction();
            }
            catch
            {
            }
        }
        protected virtual void StopTransaction()//停止事务
        {
            try
            {
                fDataFactory.StopTransaction();
            }
            catch
            {
            }
            try
            {
                Monitor.Exit(this);
            }
            catch
            {
            }
        }
        protected virtual void RollbackTransaction()//回滚事务
        {
            try
            {
                fDataFactory.RollbackTransaction();
            }
            catch
            {
            }
        }
        protected virtual DataTable ExecuteQuery(string strSql)//执行查询SQL语句
        {
            DataTable dtNow = fDataFactory.ExecuteQuery(strSql);//执行查询操作              
            return dtNow;
        }
        protected virtual DataRow ExecuteQueryToFirstDataRow(string strSql)//执行查询SQL语句(返回第一行)
        {
            return ServerMethod.GetFirstDataRow(fDataFactory.ExecuteQuery(strSql));
        }
        protected virtual DataSet ExecuteQueryToDataSet(string strSql)
        {

            DataSet dsNow = fDataFactory.ExecuteQueryToDataSet(strSql);//执行查询操作   
            return dsNow;
        }
        protected virtual int ExecuteUpdate(string strSql)//执行更新和删除SQL语句
        {

            int intNow = fDataFactory.ExecuteUpdate(strSql);//执行查询操作  
            return intNow;
        }
        protected virtual int ExecuteUpdateByParameters(string strSql, params object[] sqlParams)//执行带参数数据,主要是二进制
        {
            return fDataFactory.ExecuteUpdateByParameters(strSql, sqlParams);//执行查询操作  
        }
        protected virtual void CloseConnection()//关闭连接,如果是BS方式,则关闭连接,CS保持连接
        {
            try
            {
                if (fBConnOtherDB)
                {
                    if (fDataFactory != null)
                    {
                        fDataFactory.CloseConnection();
                        fDataFactory = null;
                    }
                }
                else
                {
                    if (CurrentSystemMode == SystemMode.BS)//CS要求只用一个连接,BS则关闭
                    {
                        if (fDataFactory != null && fBCloseConn == false)
                        {
                            fDataFactory.CloseConnection();
                            fBCloseConn = true;
                            fDataFactory = null;
                        }
                    }
                }
            }
            catch
            {
            }
        }
     
        private static void CloseAllConnection(ref DataAccessFactory df)//关闭连接,如果是BS方式,则关闭连接,CS保持连接
        {
            try
            {
                if (df != null)
                {
                    df.CloseConnection();
                    df = null;
                }
            }
            catch
            {
            }
        }
        #endregion

        #region 授权认证
        protected virtual bool CheckAccessUser(string userName, string pwd, bool bDefaultSys)
        {
            if (userName == null || pwd == null) return false;
            if (bDefaultSys)
            {
                if (userName == fStrUserAndPwd && pwd == "") return true;
            }
            else
            {
                if (userName.ToUpper() == fStrUserCustom && pwd == fStrPwdCustom) return true;
            }
            return false;
        }
        #endregion

        #region sql语句检查
        protected virtual bool CheckQuerySQL(string strSQL)
        {
            if (strSQL != "")
            {
                if (strSQL.ToUpper().IndexOf("UPDATE") >= 0
                    || strSQL.ToUpper().IndexOf("DELETE") >= 0)
                {
                    return false;
                }
                return true;
            }
            return false;
        }
        #endregion

        #region 将出错日志写入数据库

        protected virtual bool WriteLogToDB(int serviceSeq, string logStr, string errorLog, string sysName, ErrorType errorType, string startTime, string endTime)
        {
            int dbconnType = this.connectionStringIndex;
            this.connectionStringIndex = 2;

            try
            {
                if (updateDataService(serviceSeq, startTime))
                {
                    if (updateDataServiceLog(serviceSeq, logStr, errorLog, sysName, errorType, startTime, endTime))
                    {
                        return true;
                    }
                }
                return false;
            }
            catch
            {

                BusiKernel.WriteToLogFile("将" + sysName + "日志写入到数据库失败！", false);
            }
            this.connectionStringIndex = dbconnType;
            return false;
        }


        public bool UpdateCollectServer(int serviceSeq)
        {
            this.connectionStringIndex = 1;
            try
            {
                string startTime = DateTime.Now.ToString("yyyy-MM-dd hh:mm:ss");
                startTime = startTime.Substring(0, startTime.Length - 8) + "00:00:00";
                string sql = "select seq,TOTALCOLLECTREQUESTCOUNT from nwproduct_totalcollect where TOTALCOLLECTDATE=to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss') and SERVICECONFIG_SEQ=" + serviceSeq.ToString();
                if (this.OpenConnection())
                {
                    int maxSeq = 0;
                    int cnt = 1;
                    DataTable dt = this.ExecuteQuery(sql);
                    if (ServerMethod.DataTableHasDataRow(dt))
                    {
                        maxSeq = int.Parse(dt.Rows[0][0].ToString());
                        cnt = int.Parse(dt.Rows[0][1].ToString()) + 1;
                        sql = "update nwproduct_totalcollect set TOTALCOLLECTREQUESTCOUNT=" + cnt.ToString() +
                                 " where SEQ=" + maxSeq.ToString();
                    }
                    else
                    {
                        sql = "select max(seq) from nwproduct_totalcollect";
                        DataTable dt1 = this.ExecuteQuery(sql);
                        if (ServerMethod.DataTableHasDataRow(dt))
                        {
                            if (dt.Rows[0][0].ToString() == "")
                                maxSeq = 1;
                            else
                                maxSeq = int.Parse(dt.Rows[0][0].ToString()) + 1;
                        }
                        sql = "insert into nwproduct_totalcollect(SEQ,SERVICECONFIG_SEQ,TOTALCOLLECTDATE,TOTALCOLLECTCOUNT,TOTALCOLLECTREQUESTCOUNT)" +
                                 " values(" + maxSeq.ToString() + "," + serviceSeq.ToString() + ",to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss'),0," + "1)";
                    }
                    this.StartTransaction();

                    this.ExecuteUpdate(sql);
                    this.CommitTransaction();
                    this.StopTransaction();
                    return true;
                }
            }
            catch (Exception ex)
            {
                this.RollbackTransaction();
                this.StopTransaction();
                BusiKernel.WriteToLogFile("UpdateCollectServer出现异常：" + ex.Message, false);
            }
            finally
            {
                this.CloseConnection();
            }
            return false;
        }
        /// <summary>
        /// 跟新数据服务日志表
        /// </summary>
        /// <returns></returns>
        private bool updateDataServiceLog(int serviceSeq, string logStr, string errorLog, string sysName, ErrorType errorType, string startTime, string endTime)
        {
            this.connectionStringIndex = 2;
            try
            {
                string sql = "select max(seq) from NWLOG_DATASERVICELOG";
                if (this.OpenConnection())
                {

                    int maxSeq = 0;
                    DataTable dt = this.ExecuteQuery(sql);
                    if (dt != null && dt.Rows.Count != 0)
                    {
                        if (dt.Rows[0][0].ToString() == "")
                            maxSeq = 1;
                        else
                            maxSeq = int.Parse(dt.Rows[0][0].ToString()) + 1;
                    }
                    int errorflag = 0;
                    switch (errorType)
                    {
                        case ErrorType.DataError:
                            errorflag = 2;
                            break;
                        case ErrorType.NetError:
                            errorflag = 1;
                            break;
                        case ErrorType.OtherError:
                            errorflag = 3;
                            break;
                        case ErrorType.None:
                            errorflag = 0;
                            break;
                        default:
                            errorflag = 0;
                            break;
                    }
                    BusiKernel.WriteToLogFile(errorflag.ToString(), false);
                    this.StartTransaction();
                    // BusiKernel.WriteToLogFile("22", false);
                    sql = "insert into nwlog_dataservicelog(SEQ,SERVICE_SEQ,STARTTIME,ENDTIME,LOGSTRING,ERRORLOG,ERRORFLAG,INTPUTTIME,REQUESTSYSNAME)" +
                              " values(" + maxSeq + "," + serviceSeq + ",to_date('" + startTime + "','yyyy-mm-dd hh24:mi:ss'),to_date('" + endTime + "','yyyy-mm-dd hh24:mi:ss')" +
                              "  ,'" + logStr + "','" + errorLog + "'," + errorflag + ",to_date('" + DateTime.Now.ToString() + "','yyyy-mm-dd hh24:mi:ss'),'" + SysName + "')";
                    BusiKernel.WriteToLogFile(sql, false);

                    BusiKernel.WriteToLogFile(sql, false);
                    this.ExecuteUpdate(sql);
                    this.CommitTransaction();
                    this.StopTransaction();
                    return true;
                }
            }
            catch
            {
                this.RollbackTransaction();
                this.StopTransaction();
                BusiKernel.WriteToLogFile("将updateDataServiceLog 日志写入到数据库失败！", false);
            }
            finally
            {
                this.CloseConnection();
            }
            return false;
        }
        /// <summary>
        /// 跟新数据服务配置表
        /// </summary>
        /// <returns></returns>
        private bool updateDataService(int serviceseq, string lastrundt)
        {
            this.connectionStringIndex = 0;
            try
            {
                string sql = "select max(allrequestcount) from nwbase_dataserviceconfig where seq = " + serviceseq;
                if (this.OpenConnection())
                {
                    int maxAllrequestcount = 0;
                    DataTable dt = this.ExecuteQuery(sql);
                    if (dt != null && dt.Rows.Count != 0)
                    {
                        if (dt.Rows[0][0].ToString() == "")
                            maxAllrequestcount = 1;
                        else
                            maxAllrequestcount = int.Parse(dt.Rows[0][0].ToString()) + 1;
                    }
                    // BusiKernel.WriteToLogFile(maxAllrequestcount.ToString(), false);
                    this.StartTransaction();
                    //  BusiKernel.WriteToLogFile("22", false);
                    sql = "update nwbase_dataserviceconfig set allrequestcount=" + maxAllrequestcount + ",lastrundt=to_date('" + lastrundt + "','yyyy/mm/dd hh24:mi:ss') where seq =" + serviceseq;
                    // BusiKernel.WriteToLogFile(sql, false);
                    this.ExecuteUpdate(sql);
                    this.CommitTransaction();
                    this.StopTransaction();
                    return true;
                }
            }
            catch
            {
                this.RollbackTransaction();
                this.StopTransaction();
                BusiKernel.WriteToLogFile("将updateDataService 日志写入到数据库失败！", false);
            }
            finally
            {
                this.CloseConnection();
            }
            return false;
        }


        #endregion

        #region 写日志
        public static void WriteToLogFile(string logString, bool bCS)//写本地日志文件
        {
            StreamWriter sw = null;
            try
            {
                string nowTime = DateTime.Now.ToString("yyyy-MM-dd");
                string strFileName = StrLogFileDirPath + nowTime + ".log";

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
                sw.WriteLine(nowTime);
                sw.WriteLine(logString);
            }
            catch
            {

            }
            finally
            {
                if (sw != null)
                {
                    sw.Close();
                    sw = null;
                }
            }
        }
        public static void WriteToLogFile(string logString, string fileName)//写本地日志文件
        {
            StreamWriter sw = null;
            try
            {
                string nowTime = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string strFileName = StrLogFileDirPath + fileName + ".log";

                string dirString = Path.GetDirectoryName(strFileName);
                if (!Directory.Exists(dirString))
                {
                    Directory.CreateDirectory(dirString);
                }
                if (!File.Exists(strFileName))
                {
                    File.Create(strFileName);
                }
                sw = File.AppendText(strFileName);
                sw.WriteLine(nowTime);
                sw.WriteLine(logString);
            }
            catch
            {

            }
            finally
            {
                if (sw != null)
                {
                    sw.Close();
                    sw = null;
                }
            }
        }
        #endregion
    }
}