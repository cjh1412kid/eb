/***************************************************************************************************
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

using Pacia.Server.Common;
using System.IO;


namespace NuiteThousandsShoes
{
    public class BusiKernel
    {
        protected string fErrorStr;//错误信息

        public string ErrorStr
        {
            get { return fErrorStr; }
            set { fErrorStr = value; }
        }
        protected void WriteToLog(string logString)
        {
            InitConst.WriteToLogFile(logString);
        }
        protected virtual bool CheckServiceResultData(ServiceResultData serviceRDObj)
        {
            fErrorStr = "";
            if (serviceRDObj != null)
            {
                switch (serviceRDObj.BusinessResult)
                {
                    case 0: return true;
                    case 1001: fErrorStr = "数据访问授权不能通过验证"; break;
                    case 1004: fErrorStr = "未能查找到数据"; break;
                    case 2001: fErrorStr = "数据库连接失败"; break;
                    case 2002: fErrorStr = "其它服务访问异常，异常内容：" + serviceRDObj.ObjectResult.ToString(); break;
                }
            }
            else
            {
                fErrorStr = "数据访问服务连接失败，请检查网络设置！";
            }
            return false;
        }
    }
}