using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO.Compression;
using Pacia.Server.Common;
using System.Threading;
using System.Runtime.Serialization;
using System.Data;

namespace Nuite.DBAccessServer
{
    public partial class DBAccessServerRemoting : MarshalByRefObject
    {

        public string QueryData(string userName, string pwd, string strSQL)
        {
            ServiceResultData serviceResultData = null;
            BusiUpdateSystem busi = new BusiUpdateSystem(ref serviceResultData);
            try
            {
                busi.QueryData(userName, pwd,strSQL);
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, false);
            }
            return GetReturnStringAndFree(serviceResultData);
        }

        public string GetUpdateSystemData(string userName, string pwd)
        {
            ServiceResultData serviceResultData = null;
            BusiUpdateSystem busi = new BusiUpdateSystem(ref serviceResultData);
            try
            {
                busi.GetUpdateSystemData(userName, pwd);
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, false);
            }
            return GetReturnStringAndFree(serviceResultData);
        }

        public string SetUpdateSystemInfo(string userName, string pwd, string companySeq, string version, string fileName)
        {
            ServiceResultData serviceResultData = null;
            BusiUpdateSystem busi = new BusiUpdateSystem(ref serviceResultData);
            try
            {
                busi.SetUpdateSystemInfo(userName, pwd, companySeq, version, fileName);
            }
            catch (Exception ex)
            {
                ServerMethod.WriteToLogFile(ex.Message, false);
            }
            return GetReturnStringAndFree(serviceResultData);
        }
    }
}
