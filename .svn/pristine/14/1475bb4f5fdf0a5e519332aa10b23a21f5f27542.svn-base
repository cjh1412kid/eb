/*******************************************************************************
* 项目名称: 甘肃综合业务平台
* 模块名称: 系统更新类
* 当前版本: V 1.0
* 开始时间: 20150731
* 完成时间: 
* 开发者: cwen
* 重要描述:更新系统的业务类
********************************************************************************
* 版本: V 1.0 
* 描述: cwen 20130731 对全部代码的规范进行统一
*******************************************************************************/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using Pacia.Server.Common;


namespace Nuite.DBAccessServer
{
    public class BusiUpdateSystem : BusiKernel
    {
        public BusiUpdateSystem(ref ServiceResultData serviceResultObject)
        {
            serviceResultObject = this.ServiceResultData;
        }
        public void QueryData(string userName, string pwd,string strSQL)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, false))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    this.ServiceResultData.ObjectResult = "查询用户访问受限，请联系管理员！";
                    return;
                }

                if (!this.CheckQuerySQL(strSQL))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    this.ServiceResultData.ObjectResult = "查询语句访问受限，请联系管理员！";
                    return;
                }

                this.connectionStringIndex = 0;//产品库
                if (this.OpenConnection())
                {
                    DataTable total = this.ExecuteQuery(strSQL);
                    if (ServerMethod.DataTableHasDataRow(total))
                    {
                        this.ServiceResultData.DataTable = total;
                        this.ServiceResultData.BusinessResult = 0;
                    }
                    else
                    {
                        this.ServiceResultData.DataTable = null;
                        this.ServiceResultData.BusinessResult = 1004;
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
                //任何问题返回0
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "QueryData" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }


        public void GetUpdateSystemData(string userName, string pwd)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    this.ServiceResultData.ObjectResult = "查询用户访问受限，请联系管理员！";
                    return;
                }
                string strSql = "";
                this.connectionStringIndex = 0;
                if (this.OpenConnection())
                {
                    strSql = "select s.seq,R.US_VERSION,R.US_FILENAME from NWBASE_COMPANY S left join  NWOTHER_UPSYSTEM R  on R.COMPANYSEQ=S.SEQ order by R.seq desc";
                   // strSql = "select s.seq,R.US_VERSION,R.US_FILENAME from NWBASE_COMPANY S left join  NWOTHER_UPSYSTEM R  on R.COMPANYSEQ=S.SEQ order by R.US_VERSION desc";
                    DataTable dtData = this.ExecuteQuery(strSql);
                    if (ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.DataTable = dtData;
                        this.ServiceResultData.BusinessResult = 0;
                    }
                    else
                    {
                        this.ServiceResultData.DataTable = null;
                        this.ServiceResultData.BusinessResult = 1004;
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
                //任何问题返回0
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetUpdateSystemData" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }

        public void SetUpdateSystemInfo(string userName, string pwd, string companySeq, string version, string fileName)
        {
            try
            {
                this.ServiceResultData.BusinessResult = -1;
                if (!this.CheckAccessUser(userName, pwd, true))
                {
                    this.ServiceResultData.BusinessResult = 1001;//不能通过验证
                    this.ServiceResultData.ObjectResult = "查询用户访问受限，请联系管理员！";
                    return;
                }
                string strSql = "";
                this.connectionStringIndex = 0;
                if (this.OpenConnection())
                {
                    strSql = "insert into NWOTHER_UPSYSTEM (US_FILENAME,US_VERSION,CompanySeq) values ('" + fileName + "','" + version + "'," + companySeq + ")";
                    this.StartTransaction();
                    int index = this.ExecuteUpdate(strSql);
                    if (index > 0)
                    {
                        this.ServiceResultData.BusinessResult = 0;
                        this.CommitTransaction();
                    }
                    else
                    {
                        this.ServiceResultData.BusinessResult = 1004;
                        this.RollbackTransaction();
                    }
                }
                else
                {
                    this.ServiceResultData.BusinessResult = 2001;//数据库连接失败
                }
                //任何问题返回0
            }
            catch (Exception ex)
            {
                this.ServiceResultData.BusinessResult = 2002;//出现异常    
                this.RollbackTransaction();
                BusiKernel.WriteToLogFile(this.ServiceResultData.BusinessResult.ToString(), false);
                this.ServiceResultData.ObjectResult = this.OwnerName + "SetUpdateSystemInfo" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.StopTransaction();
                this.CloseConnection();
            }
        }
    }
}