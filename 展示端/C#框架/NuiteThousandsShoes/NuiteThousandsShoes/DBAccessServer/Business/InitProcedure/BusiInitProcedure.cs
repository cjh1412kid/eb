using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Data;
using Pacia.Server.Common;

namespace Nuite.DBAccessServer
{
    public class BusiInitProcedure : BusiKernel
    {
        public BusiInitProcedure(ref ServiceResultData serviceResultObject)
        {
            serviceResultObject = this.ServiceResultData;
        }

        public void GetShopInfo(string userName, string pwd)
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
                    strSql = "select seq from NWBase_ShopBaseInfo";
                    DataTable dtData = this.ExecuteQuery(strSql);
                    if (ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 0;
                        this.ServiceResultData.DataTable = dtData;
                    }
                    else
                    {
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
                this.ServiceResultData.ObjectResult = this.OwnerName + "GetShopInfo" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }
        public void IsLocalShopExist(string userName, string pwd)
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
                    strSql = "select seq from NWBase_ShopBaseInfo";
                    DataTable dtData = this.ExecuteQuery(strSql);
                    if (ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 0;
                        this.ServiceResultData.DataTable = dtData;
                    }
                    else
                    {
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
                this.ServiceResultData.ObjectResult = this.OwnerName + "IsLocalShopExist" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }
        public void IsLocalBrandExist(string userName, string pwd)
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
                    strSql = "select * from NWBase_Brand";
                    DataTable dtData = this.ExecuteQuery(strSql);
                    if (ServerMethod.DataTableHasDataRow(dtData))
                    {
                        this.ServiceResultData.BusinessResult = 0;
                        this.ServiceResultData.DataTable = dtData;
                    }
                    else
                    {
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
                this.ServiceResultData.ObjectResult = this.OwnerName + "IsLocalBrandExist" + ex.Message;
                BusiKernel.WriteToLogFile(this.ServiceResultData.ObjectResult.ToString(), true);
            }
            finally
            {
                this.CloseConnection();
            }
        }


        public void SetLocalDataInfo(string userName, string pwd, List<BrandInfor> listBrand, ShopInfo shopInfo,ComplanyInfo complanInfo)// List<BrandShopRelation> listRelation)
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
                    strSql = "delete from NWBase_Brand";
                    this.StartTransaction();
                    int index = this.ExecuteUpdate(strSql);
                    strSql = "delete from NWBase_ShopBaseInfo";
                    index += this.ExecuteUpdate(strSql);
                    strSql = "delete from NWBASE_COMPANY";
                    index += this.ExecuteUpdate(strSql);
                    if (index >= 0)
                    {
                        this.CommitTransaction();
                        this.StopTransaction();
                    }
                    else
                    {
                        this.RollbackTransaction();
                        return;
                    }
                    this.StartTransaction();
                    int indexBrand = 0;
                    foreach (BrandInfor brandInfor in listBrand)
                    {
                        strSql = "insert into NWBase_Brand (SEQ,IdentifyCode,[Key],BrandName,CompanySeq) values (" + brandInfor.Seq + ",'"
                            + brandInfor.IdentifyCode + "','" + brandInfor.Key + "12','" + brandInfor.BrandName + "'," + brandInfor.CompanySeq + ")";
                        indexBrand += this.ExecuteUpdate(strSql);
                    }
                    strSql = "insert into NWBase_ShopBaseInfo (SEQ,AreabaseSeq,ShopID,ShopName,ShopAddress,ShopTypeFlag,InputDate) values ("
                        + shopInfo.Seq + "," + shopInfo.AreaBaseSeq + ",'" + shopInfo.ShopID + "','" + shopInfo.ShopName + "','" + shopInfo.ShopAddress
                        + "'," + shopInfo.ShopTypeFlag + ",Format('" + shopInfo.InputDate + "','yyyy-mm-dd hh:mm:ss'))";
                    int indexShop = this.ExecuteUpdate(strSql);

                    int indexComplany = 0;

                    strSql = "insert into NWBASE_COMPANY (SEQ,name,del,[key]) values (" + complanInfo.Seq + ",'" + complanInfo.Name + "'," + complanInfo.Del + ",'" + complanInfo.Key + "')";
                    indexComplany += this.ExecuteUpdate(strSql);

                    if (indexBrand == listBrand.Count && indexShop > 0 && indexComplany > 0)
                    {
                        this.ServiceResultData.BusinessResult = 0;//出现异常    
                        this.CommitTransaction();
                    }
                    else
                    {
                        this.RollbackTransaction();
                        return;
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
                this.ServiceResultData.ObjectResult = this.OwnerName + "SetLocalDataInfo" + ex.Message;
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
