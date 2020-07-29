/*******************************************************************************
* 项目名称: 海南气象服务中心数据库操作模块服务
* 模块名称: Remoting服务
* 当前版本: V 1.0
* 开始时间: 20130726
* 完成时间: 
* 开发者: cwen
* 重要描述:数据库操作模块服务
********************************************************************************
* 版本: V1.0
* 描述: cwen 20130529 创建项目
*******************************************************************************/


using System;
using System.Data;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;
using System.Diagnostics;
using System.IO.Compression;
using Pacia.Server.Common;
using System.Threading;
using System.Runtime.Serialization;
using System.Collections.Generic;

namespace Nuite.DBAccessServer
{
    public partial class DBAccessServerRemoting : MarshalByRefObject
    {
        private static string fTempFilePath = "";//操作文件目录
        private static string fLogFilePath = "";//日志文件目录

        private string GetReturnStringAndFree(ServiceResultData srd)
        {
            string str = ServerMethod.Serialize(srd);
            if (srd.DataTable != null && srd.DataTable.Rows.Count > 0)
            {
                srd.DataTable.Dispose();
                srd.DataTable = null;
                srd = null;
            }
            return str;
        }

        #region 写日志
        private void WriteToLogFile(string logString, string fileName)//写本地日志文件
        {
            StreamWriter sw = null;
            try
            {
                string nowTime = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string strFileName = fileName + DateTime.Now.ToString("yyyy-MM-dd") + ".log";

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
                sw.Close();
                sw.Dispose();
                sw = null;
            }
        }
        public void WriteLog(string str)
        {
            WriteToLogFile("outwrite;" + str, fLogFilePath);
        }
        #endregion

        #region 服务端调用初始化
        public void InitForServer(string saveFilePath,string accdbFile)
        {
            fTempFilePath = saveFilePath + @"\Temp\"; ;
            fLogFilePath = saveFilePath + @"\Log\";
            if (accdbFile == "")
            {
                BusiKernel.AccessDBFileName = saveFilePath + @"\DB\database.mdb";
            }
            else
            {
                BusiKernel.AccessDBFileName = accdbFile;
            }
            BusiKernel.StrLogFileDirPath = fLogFilePath;
            if (Directory.Exists(fTempFilePath) == false) Directory.CreateDirectory(fTempFilePath);
            if (Directory.Exists(fLogFilePath) == false) Directory.CreateDirectory(fLogFilePath);
        }

        public void DeleteTempFiles()
        {
            try
            {
                if (Directory.Exists(fTempFilePath))
                    Directory.Delete(fTempFilePath, true);
                if (Directory.Exists(fTempFilePath) == false) Directory.CreateDirectory(fTempFilePath);
            }
            catch
            {
            }
        }
        public void AddDBConn(string conStr)
        {
            BusiKernel.AddDBConn(conStr);
        }


        #endregion
       


 

    }
}
